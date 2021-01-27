var express = require('express');
var router = express.Router();
var jwt = require('jsonwebtoken');

var user2 = {username: 'lfl', password: 'dorgas',level:'admin'}
var user = {username: 'jcr', password: 'dorgas',level:'consumer'}


/* 
   O utilizador autentica/se com username e password.
   Se as credenciais estiverem corretas um token é gerado e enviado como resposta.
 */
router.post('/', function(req, res) {
    jwt.sign({_id: 'admin',level:'admin', expiresIn: '3m'},'PRI2020',
      function(err,token){
        if(err){
          res.status(500).jsonp({error:"Something went wrong"})
        } 
        else{
          res.status(201).jsonp({token:token})
        }

    });
  });

module.exports = router;
