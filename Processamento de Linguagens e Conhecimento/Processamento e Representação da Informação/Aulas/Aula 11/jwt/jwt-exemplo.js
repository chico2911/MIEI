var jwt = require('jsonwebtoken');


//criação do token
try{
var token = jwt.sign({username: 'lfl',level:'admin', expiresIn: '1d'},'PRI2020');
console.log('token ' + token);
}
catch(err){console.log('erro na criação ' +err)}


//Verificação com chave publica correta
try{
    var decoded = jwt.verify(token,'PRI2020');
    console.log('Segredo correto'+JSON.stringify(decoded));
}
catch(err){
    console.log('Erro '+ err);
}


//Verificação com chave publica errada
try{
    var decoded = jwt.verify(token,'segredo errado');
    console.log(JSON.stringify(decoded));
}
catch(err){
    console.log('Erro '+ err);
}


//Verificação com chave publica correta e callback se erro na verificação
var decoded = jwt.verify(token,'PRI2020',function(e,payload){
    if(e) console.log('Erro na verificação'+e);
    else console.log('payload: '+JSON.stringify(payload));
});

//openssl genrsa -out mykey.pem 4096
//openssl rsa -in mykey.pem -pubout -out pubkey.pem 