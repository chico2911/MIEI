var express = require('express');
var router = express.Router();

function verificaNivel(a,b){
  return a==b;
}


/* GET home page. */
router.get('/', function(req, res) {
    res.status(200).jsonp({dados:'no estoril brotha'})
});


/* GET Login*/
router.get('/login', function(req, res) {
  res.render('login-form');
});



router.get('/secreta', function(req,res){
  if(verificaNivel(req.user.level)) res.status(200).jsonp({dados:'no estoril brotha'})
  else res.status(401).jsonp({error:'Poe te a andar migo'})
});
module.exports = router;
