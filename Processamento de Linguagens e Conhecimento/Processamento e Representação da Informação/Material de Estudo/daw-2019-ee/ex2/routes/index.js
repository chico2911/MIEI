var express = require('express');
var router = express.Router();
var axios = require('axios');
var token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZWJjZTA2ZGUzODcyMzBmYmY0ZjAyYyIsImlhdCI6MTYwOTI4OTIyMiwiZXhwIjoxNjExODgxMjIyfQ.By8mFyHeIQSshDo1iN6HaDewA0sGf29PfnJP0GujprPTvNY_a9N7G0jF5c364S66PBEnV6FB6qfiFlfgMpLBw6vgZJlsY9XfQ_ZU3Lj93NNX67qO68BfxdR5EJFFq75b38ZPYwOkCnOTv2feZYpEH_PZd4ccdH1DspGkCzuBy_1hJov3Go0ct40juKt9d_LqR2ohsgD0pV4C77L8MWs87mV-Ark5lXGsyQv_t8JS2DuKgS1LTUy78JjOGL4QSQrG0ss3r-hvWXpt-MM6rJMqVemN8v7KeA9V7km8y5wLsZ5GW8JStXkVtGwtZfZimgc5Uj-CGQwuQuRf7gz7I3cZrg"


/* GET home page. */
router.get('/', function(req, res, next) {
  axios.get('http://clav-api.di.uminho.pt/v2/tipologias?apikey='+token)
    .then(dados => {
      res.render('index', {title: 'CLAV',tipologias:dados.data})
    })
    .catch(err => res.render('error',{error:err}))
});

router.get('/tipologia/:id',function(req, res){
  var tipologiaRequest = axios.get('http://clav-api.di.uminho.pt/v2/tipologias/'+req.params.id+'?apikey='+token)
  var elementosRequest = axios.get('http://clav-api.di.uminho.pt/v2/tipologias/'+req.params.id+'/elementos?apikey='+token)
  var donaRequest = axios.get('http://clav-api.di.uminho.pt/v2/tipologias/'+req.params.id+'/intervencao/dono?apikey='+token)
  var participaRequest = axios.get('http://clav-api.di.uminho.pt/v2/tipologias/'+req.params.id+'/intervencao/participante?apikey='+token)
  
  axios.all([tipologiaRequest,elementosRequest,donaRequest,participaRequest])
    .then(axios.spread((...responses) => {
        const tipologia = responses[0].data
        const elementos = responses[1].data
        const dona = responses[2].data
        const participante = responses[3].data
        res.render('tipologia', {title: 'CLAV - '+tipologia.designacao,tip:tipologia,elementos:elementos,dona:dona,participa:participante})
    }))
    .catch(err => res.render('error',{error:err}))
})

router.get('/entidade/:id', function(req, res, next) {
  axios.get('http://clav-api.di.uminho.pt/v2/entidades/'+req.params.id+'?apikey='+token)
    .then(dados => {
      res.render('entidades', {title: 'CLAV - ' +dados.data.designacao,enti:dados.data})
    })
    .catch(err => res.render('error',{error:err}))
});

module.exports = router;
