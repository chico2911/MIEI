var express = require('express');
var router = express.Router();
var axios = require('axios');

var token = 'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjExMTExMjIyMyIsImxldmVsIjoyLCJlbnRpZGFkZSI6ImVudF9BM0VTIiwiZW1haWwiOiJwcmkyMDIwQHRlc3RlLnVtaW5oby5wdCIsImlhdCI6MTYxMDk4Mzc0MCwiZXhwIjoxNjExMDEyNTQwfQ.qkiF1sFOgn1BFdWwCD8LAPBybsrYU5vE1nQDsD7fjxnGKoWp39Rzzl0E7Igj7ZxFZLeJ35OXaI7Dj6Py60KgcmXfv7TZ9KgBcPhSmYT2kWvdico0hSuXmQKjWchfGWIFmdkEI_XlM56oxI4HLfRx5kmBWnGh_MAMxrNvrKIuc-XXjzYEnO9rD5_2FzSs78t2k-PYf18uw9kcUDeUb0QcQVXEu5tbXKCarAP6dpqWzQCbR6VnKkFsLIeaO2Div-UCzxmDOB63atZDsizIAm5QWN9wwYQGae7wPDIJK8v8Jy7aHMXdBHVV8fdS4SwD1p7GataeESyinvESCe6ivyHueg'

/* GET home page. */
router.get('/', function(req, res, next) {
  axios.get('http://clav-api.di.uminho.pt/v2/classes?nivel=1&token='+token)
  .then(data=>{
    res.render('index', { title: 'Clav',lista:data.data });
   
  })
  .catch(err=>res.render('error',{error:err}))
  
});

router.get('/classe/:codigo',function(req, res){
  axios.get('http://clav-api.di.uminho.pt/v2/classes/'+req.params.codigo+'?token='+token)
  .then(data=>{
    if(data.data.nivel == 3){
      axios.get('http://clav-api.di.uminho.pt/v2/classes/'+req.params.codigo+'/procRel?token='+token)
        .then(resposta=>{ res.render('classe', { title: 'Clav',classe:data.data,nivel:data.data.nivel,relacoes:resposta.data})})
    }
    else res.render('classe', { title: 'Clav',classe:data.data,nivel:data.data.nivel});
  })
  .catch(err=>res.render('error',{error:err}))
})

module.exports = router;
