var http = require('http')
var axios = require('axios')
var fs = require('fs')

var static = require('./static.js')
//ir buscar apenas a função parse e var parse -> func parse
var {parse} = require('querystring')


// Funções auxilidares

// Ir ao body do Post buscar a info
function recuperaInfo(request, callback){
    if(request.headers['content-type'] == 'application/x-www-form-urlencoded'){
        let body = ''
        request.on('data', bloco => {
            body += bloco.toString()
        })
        request.on('end', ()=>{
            console.log(body)
            //chamo a função parse da querystring 
            callback(parse(body))
        })
    }
}


function geraFormTarefa(responsaveis){
    let pagHTML = `<html>
    <head>
        <title>ToDo</title>
        <meta charset="utf-8"/>
        <link rel="stylesheet" href="../w3.css"/>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
        <script type="text/javascript" src="funcoes.js" ></script>
    </head>
    <body>
        <div class="w3-container w3-teal">
            <h2>Registo de Tarefa</h2>
        </div>

        <form class="w3-container" action="/tarefas" method="POST">
            <label class="w3-text-teal"><b>Data Limite</b></label>
            <input class="w3-input w3-border w3-light-grey" type="text" name="data_limite">
            <input type="hidden" name="estado" value="toDo"/>
            <label class="w3-text-teal"><b>Responsável</b></label>
            <input class="w3-input w3-border w3-light-grey" type="text" list="responsaveis" name="responsavel">

            <datalist id="responsaveis">`

        var option = 
        responsaveis.forEach(r=>{
            pagHTML += `
            <option>${r.responsavel}</option>
            `
        })

    pagHTML += `</datalist>

    <label class="w3-text-teal"><b>Descrição</b></label>
    <input class="w3-input w3-border w3-light-grey" type="text" name="descricao">
    
    <input class="w3-btn w3-blue-grey" type="submit" value="Registar"/>
    <input class="w3-btn w3-blue-grey" type="reset" value="Limpar valores"/> 
    </form>`
    
    
    
    return pagHTML
}



function geraPagTarefas(lista, d){
    let pagHTML = `
              <div class="w3-container w3-teal">
                  <h2>Tarefas</h2>
              </div>
              <table id="toDo" class="w3-table w3-bordered">
                  <tr>
                      <th>Data Limite</th>
                      <th>Responsável</th>
                      <th>Descrição</th>
                      <th></th>
                      <th></th>
                  </tr>
    `
    lista.forEach(t => {
      pagHTML += 
       ` <tr id=${t.id}>
            <td>${t.data_limite}</td>
            <td>${t.responsavel}</td>
            <td>${t.descricao}</td>
            <td><button class="w3-button w3-teal w3-circle" onclick="d(${t.id})" type="submit" value="Feito">&#10004</button></td>
            <td><button class="w3-button w3-red w3-circle" onclick="c(${t.id})" type="submit" value="Cancelado">&#10006</button></td>
        </tr>`
      
  });
  
    pagHTML += `
          </table>
    `
    return pagHTML
  }


function geraFeitas_Canceladas(doneCancelled,d){
    let pagHTML = `
              <div class="w3-container w3-teal">
                  <h2>Tarefas Passadas</h2>
              </div>
              <table id="doneCancelled" class="w3-table w3-bordered">
                  <tr>
                      <th>Data Limite</th>
                      <th>Responsável</th>
                      <th>Descrição</th>
                      <th>Estado</th>
                  </tr>
    `
        doneCancelled.forEach(t => {
            pagHTML += 
             ` <tr>
                  <td>${t.data_limite}</td>
                  <td>${t.responsavel}</td>
                  <td>${t.descricao}</td>
                  <td>${t.estado}</td>
              </tr>`
            
        });
    
    
      pagHTML += `
            </table>
            <div class="w3-container w3-teal">
                <address>Gerado por ToDoList::PRI2020 em ${d} --------------</address>
            </div>
        </body>
        </html>
      `


    return pagHTML
}


function getMainPage(res,d){
    var requestResponsaveis = axios.get("http://localhost:3000/tarefas")
    var requestTarefasToDo = axios.get("http://localhost:3000/tarefas?estado=toDo&_sort=data_limite&_order=asc")
    var requestDoneCancelled = axios.get("http://localhost:3000/tarefas?estado_ne=toDo&_sort=data_limite&_order=asc")

    axios.all([requestResponsaveis,requestTarefasToDo,requestDoneCancelled])
        .then(axios.spread((...responses)=>{
            var responsaveis = responses[0].data
            var toDo = responses[1].data
            var doneCancelled = responses[2].data
            res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
            res.write(geraFormTarefa(responsaveis))
            res.write(geraPagTarefas(toDo,d))
            res.write(geraFeitas_Canceladas(doneCancelled,d))
            res.end()
        }))
        .catch(function(erro){
            res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
            res.write("<p>Não foi possível obter a lista de tarefas...")
            console.log('erro:' + erro)
            res.end()
        })
}


var toDoServer = http.createServer(function (req, res) {
    // Logger: que pedido chegou e quando
    var d = new Date().toISOString().substr(0, 16)
    console.log(req.method + " " + req.url + " " + d)
    // Tratamento do pedido
    switch(req.method){
        case "GET": 
            // GET /Lista --------------------------------------------------------------------
            if((req.url == "/") || (req.url == "/tarefas")){
                getMainPage(res,d)
            }
            else if(/\/tarefas\/*/.test(req.url)){
                var idt = req.url.split("/")[2]
                axios.get("http://localhost:3000/tarefas/" + idt).
                    then(response=>{
                        res.writeHead(200, { 'Content-Type': 'application/json' }); 
                        res.end(JSON.stringify(response.data));
                    })
                    .catch(function(erro){
                        console.log(erro)
                    })
            }
            else if (/\/atualizartarefa/.test(req.url)){
                var idt = req.url.split("=")[1]
                axios.get("http://localhost:3000/tarefas/" + idt)
                .then(response => {
                    requestget = response.data

                    axios.put("http://localhost:3000/tarefas/" + idt, {
                        descricao: requestget.descricao,
                        responsavel: requestget.responsavel,
                        data_limite: requestget.data_limite,
                        estado: 'feita'
                    })
                    .then(response => {
                        res.end();
                    })
                    .catch(err => {
                        console.log(err);
                    });
                })
                .catch(err => {
                    console.log(err);
                });
            }
            else if (/\/cancelartarefa/.test(req.url)){
                var idt = req.url.split("=")[1]
                axios.get("http://localhost:3000/tarefas/" + idt)
                .then(response => {
                    requestget = response.data

                    axios.put("http://localhost:3000/tarefas/" + idt, {
                        descricao: requestget.descricao,
                        responsavel: requestget.responsavel,
                        data_limite: requestget.data_limite,
                        estado: 'cancelada'
                    })
                    .then(response => {
                        res.end()
                    })
                    .catch(err => {
                        console.log(err);
                    });
                })
                .catch(err => {
                    console.log(err);
                });
            }
            else if(req.url == "/funcoes.js"){
                fs.readFile("funcoes.js", function(erro, dados){
                    if(!erro){
                        res.writeHead(200, {'Content-Type': 'text/css;charset=utf-8'})
                        res.write(dados)
                        res.end()
                    }
                })
            }
            // GET /w3.css ------------------------------------------------------------------------
            else if(req.url == "/w3.css"){
                fs.readFile("w3.css", function(erro, dados){
                    if(!erro){
                        res.writeHead(200, {'Content-Type': 'text/css;charset=utf-8'})
                        res.write(dados)
                        res.end()
                    }
                })
            }
            else{
                res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
                res.write("<p>" + req.method + " " + req.url + " não suportado neste serviço.</p>")
                res.end()
            }
            break
        case "POST":
            if (req.url == '/tarefas') {
                recuperaInfo(req, function (info) { // info =>
                    console.log('POST de tarefa:' + JSON.stringify(info))
                    axios.post('http://localhost:3000/tarefas', info)
                    getMainPage(res,d)
                })
            }
            else {
                res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
                res.write("<p> POST" + req.url + " não suportado neste serviço.</p>")
                res.end()
            }
            break
        default: 
            res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'})
            res.write("<p>" + req.method + " não suportado neste serviço.</p>")
            res.end()
    }
})

toDoServer.listen(7777)
console.log('Servidor à escuta na porta 7777...')