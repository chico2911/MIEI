var express = require('express');
var router = express.Router();
var casamentos = require('../controllers/casamentos')

/* GET home page. */
router.get('/', function(req, res, next) {
});

//GET /api/casamentos - Devolve a lista dos casamentos, com os campos: date, title e ref;
//GET /api/casamentos?nome=X - Devolve apenas uma lista com os casamentos onde o nome X aparece incluído no título;
//GET /api/casamentos?ano=YYYY - Devolve a lista de casamentos realizados no ano YYYY;
//GET /api/casamentos?byAno=true - Devolve a lista de casamentos agrupadas por ano, ou seja, devolve uma lista de anos em que a cada ano está associada uma lista de casamentos (coloque apenas a ref e o title do casamento);
router.get('/casamentos', function(req, res){
  if(req.query.byAno !=null ){
    if(req.query.byAno!=true){
      casamentos.listarByAno()
        .then(data=>res.status(200).jsonp(data))
        .catch(err=>res.status(500).jsonp(err))
    }
  }
  else{
    if(req.query.nome!=null){
      casamentos.listarByNome(req.query.nome)
        .then(data=>res.status(200).jsonp(data))
        .catch(err=>res.status(500).jsonp(err))
    }
    else{
      if(req.query.ano!=null){
        casamentos.listarAno(req.query.ano+'/'+req.query.ano)
        .then(data=>res.status(200).jsonp(data))
        .catch(err=>res.status(500).jsonp(err))
      }
      else{
        casamentos.listarCasamentos()
        .then(data=>res.status(200).jsonp(data))
        .catch(err=>res.status(500).jsonp(err))
      }
    }

  }
});

//GET /api/casamentos/noivos - Devolve uma lista de nomes dos noivos, ordenada alfabeticamente, e o id do respetivo registo..
router.get('/casamentos/noivos', function(req, res, next) {
  casamentos.getNoivos()
    .then(data=>res.status(200).jsonp(data))
    .catch(err=>res.status(500).jsonp(err))
});



//GET /api/casamentos/:id - Devolve a informação completa de um casamento (nesta rota, considere para id o campo ref);
router.get('/casamentos/:id', function(req, res) {
  casamentos.lookUp(req.params.id)
    .then(data=>res.status(200).jsonp(data))
    .catch(err=>res.status(500).jsonp(err))
});






module.exports = router;






/*router.get('/tipologia/:id',function(req, res){
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

*/