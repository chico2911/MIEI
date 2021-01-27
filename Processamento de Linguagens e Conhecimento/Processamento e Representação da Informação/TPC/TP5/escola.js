var http = require('http')
const axios = require('axios');
const { match } = require('assert');

var servidor = http.createServer(function (req, res) {
    console.log(req.method + ' ' + req.url)
   
    if (req.method == 'GET') {
        if(req.url.match(/\/alunos\/[A]([E][\-])?[0-9]{2,5}/)){
            var split = req.url.split("/")[2]
                    axios.get('http://localhost:3000/alunos/'+ split)
                        .then(function (resp){
                            aluno = resp.data
                            res.writeHead(200,{'Content-Type': 'text/html; charset=utf-8'})
                            res.write(`<h3>Ficha Aluno: ${aluno.nome} </h3>`)
                            res.write('<ul>')
                            res.write(`<li> ID: ${aluno.id} </li>`)
                            res.write(`<li> Nome: ${aluno.nome} </li>`)
                            res.write(`<li> Data de Nascimento: ${aluno.dataNasc} </li>`)
                            res.write(`<li> Curso: ${aluno.curso} </li>`)
                            res.write(`<li> Instrumento: ${aluno.instrumento} </li>`)
                            res.write(`<li> Ano do Curso: ${aluno.anoCurso} </li>`)
                            res.write('</ul>')
                            res.write('<address>[<a href="/alunos">Voltar</a>]</address>')
                            res.end()
                        })
                        .catch(function(error){
                            console.log("Erro na obtenção do aluno " + error) })
        }
        else{
        switch (req.url) {
            default:
                res.writeHead(200, {
                    'Content-Type': 'text/html; charset=utf-8'
                })
                res.write('<style> h1{text-align:center;font-size:50px;} h2{text-align:center} </style>')
                res.write('<head> <title>DataSet - Escola de Música</title> </head>')
                res.write('<body>')
                res.write('<h1> Escola de Música </h1>')
                res.write('<ul>')
                res.write('<h2> <a href=\"http://localhost:3001/alunos\"> Lista de Alunos </a> </h2>')
                res.write('<h2> <a href=\"http://localhost:3001/cursos\"> Lista de Cursos </a> </h2>')
                res.write('<h2> <a href=\"http://localhost:3001/instrumentos\"> Lista de Instrumentos </a> </h2>')
                res.write('</ul>')
                res.write('</body>')
                res.end()
                break;
        
            case '/alunos':
                axios.get('http://localhost:3000/alunos')
                .then(resp => {
                    res.writeHead(200, {
                        'Content-Type': 'text/html; charset=utf-8'
                    })
                    alunos = resp.data;
                    res.write('<style> h1{text-align:center;font-size:50px;} h2{text-align:center} </style>')
                    res.write('<head> <title>DataSet - Escola de Música</title> </head>')
                    res.write('<body>')
                    res.write('<h1> Lista de Alunos </h1>')
                    res.write('<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">')
                    res.write('<table class="w3-table w3-striped w3-border">')
                    res.write('<tr> <th>ID</th>  <th>Nome</th></tr>')
                    alunos.forEach(a => {
                        res.write(`<tr> <th> <a href=\"http://localhost:3001/alunos/${a.id}\"> ${a.id} </th> <th>${a.nome}</th> </tr>`)
                    });
                    res.write('</table>')
                    res.write('</body>')
                    res.end();
                })
                .catch(error => {
                    console.log('ERRO: ' + error);
                    res.writeHead(200, {
                        'Content-Type': 'text/html'
                    })
                    res.write('<p>Não consegui obter a lista de alunos...</p>')
                    res.end()
                });
                break;

            case '/instrumentos':
                axios.get('http://localhost:3000/instrumentos')
                .then(resp => {
                    res.writeHead(200, {
                        'Content-Type': 'text/html; charset=utf-8'
                    })
                    instrumentos = resp.data;
                    res.write('<style> h1{text-align:center;font-size:50px;} h2{text-align:center} </style>')
                    res.write('<head> <title>DataSet - Escola de Música</title> </head>')
                    res.write('<body>')
                    res.write('<h1> Lista de Instrumentos </h1>')
                    res.write('<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">')
                    res.write('<table class="w3-table w3-striped w3-border">')
                    res.write('<tr> <th>ID</th>  <th>Designação</th></tr>')
                    instrumentos.forEach(i => {
                        res.write(`<tr> <th> ${i.id} </th> <th>${i.text} </th> </tr>`);
                    });
                    res.write('</table>')
                    res.write('</body>')
                    res.end();
                })
                .catch(error => {
                    console.log('ERRO: ' + error);
                    res.writeHead(200, {
                        'Content-Type': 'text/html'
                    })
                    res.write('<p>Não consegui obter a lista de alunos...</p>')
                    res.end()
                });
                break;

            case '/cursos':
                axios.get('http://localhost:3000/cursos')
                .then(resp => {
                    res.writeHead(200, {
                        'Content-Type': 'text/html; charset=utf-8'
                    })
                    cursos = resp.data;

                    res.write('<style> h1{text-align:center;font-size:50px;} h2{text-align:center} </style>')
                    res.write('<head> <title>DataSet - Escola de Música</title> </head>')
                    res.write('<body>')
                    res.write('<h1> Lista de Cursos </h1>')
                    res.write('<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">')
                    res.write('<table class="w3-table w3-striped w3-border">')
                    res.write('<tr> <th>ID</th>  <th>Designação</th></tr>')
                    cursos.forEach(c => {
                        res.write(`<tr> <th> ${c.id} </th> <th>${c.designacao}</th> </tr>`);
                    });
                    res.write('</table>')
                    res.write('</body>')


                    res.end();
                })
                .catch(error => {
                    console.log('ERRO: ' + error);
                    res.writeHead(200, {
                        'Content-Type': 'text/html'
                    })
                    res.write('<p>Não consegui obter a lista de alunos...</p>')
                    res.end()
                });
                break;
        }
    }
}
    else {
        res.writeHead(200, {
            'Content-Type': 'text/html'
        })
        res.write('<p>Pedido não suportado: ' + req.method + '</p>')
        res.end()
    }
})




servidor.listen(3001)
console.log('Servidor à escuta na porta 3001...')