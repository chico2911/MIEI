var fs = require('fs');
var jwt = require('jsonwebtoken');
var myToken = "" + myToken



var privateKey = fs.readFileSync('mykey.pem');
jwt.sign({username:'jcr'},privateKey,{algorithm:'RS256'},function(err,token){
    if(err) console.log('erro ' + err)
    else{
        console.log('Token ' + token + '\n\n')
        myToken = token;
    }
});

fs.readFile('pubkey.pem',function(e,publicKey){
    jwt.verify(myToken,publicKey,{algorithms:['RS256']},function(e,decoded){
        if(e) console.log('erro ' + e)
        else{
            console.log('data ' + JSON.stringify(decoded))
        }
    })
})