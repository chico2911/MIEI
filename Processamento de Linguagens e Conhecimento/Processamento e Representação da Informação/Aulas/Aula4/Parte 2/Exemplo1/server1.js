var http = require('http')

http.createServer(function (req,res){
    res.writeHead(200,{'Conten-Type': 'text/plain'});
    res.end('Olá turma de 2020');
}).listen(7777);
console.log('Servidor à escuta na porta 7777...')