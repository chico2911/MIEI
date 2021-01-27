var express = require("express");
var router = express.Router();
var pubsControll = require("../controllers/pubs");

/* GET /API/PUBS */
router.get("/pubs", function (req, res, next) {
  if (req.query.type != null && req.query.year != null) {
    pubsControll
      .listPubsByTypeAndYear(req.query.type, req.query.year)
      .then((dados) => res.status(200).jsonp({ pubs: dados }))
      .catch((error) => res.status(500).jsonp({ error: error }));
  } else {
    if (req.query.type != null) {
      pubsControll
        .listPubsByType(req.query.type)
        .then((dados) => res.status(200).jsonp({ pubs: dados }))
        .catch((error) => res.status(500).jsonp({ error: error }));
    } else {
      if(req.query.autor!=null){
        pubsControll
        .listPubsByAuthor(req.query.autor)
        .then((dados) => res.status(200).jsonp({ pubs: dados }))
        .catch((error) => res.status(500).jsonp({ error: error }));
      }
      else{
      pubsControll
        .listPubs()
        .then((dados) => res.status(200).jsonp({ pubs: dados }))
        .catch((error) => res.status(500).jsonp({ error: error }));}
    }
  }
});

/* GET /API/PUBS/:ID */
router.get("/pubs/:id", function (req, res, next) {
  pubsControll
    .lookUp(req.params.id)
    .then((dados) => res.status(200).jsonp({ pub: dados }))
    .catch((error) => res.status(500).jsonp({ error: error }));
});

/*GET /api/types*/
router.get("/types", function (req, res, next) {
  pubsControll
    .listPubs()
    .then((dados) =>{
      types = []
      for(i=0;i<dados.length;i++){
        if(!types.includes(dados[i].type)) types.push(dados[i].type)
      }
    res.status(200).jsonp({ types: types })
    })
    .catch((error) => res.status(500).jsonp({ error: error }));
});

//GET /api/autores
router.get("/autores", function (req, res, next) {
  pubsControll
    .listPubs()
    .then((dados) =>{
      authors = []
      for(i=0;i<dados.length;i++){
        var autores = dados[i].authors;
        for(j=0;j<autores.length;j++){
           if(!authors.includes(autores[j])) authors.push(autores[j])
        }
      }
      res.status(200).jsonp({ authors: authors.sort() })
    })
    .catch((error) => res.status(500).jsonp({ error: error }));
});

module.exports = router;
