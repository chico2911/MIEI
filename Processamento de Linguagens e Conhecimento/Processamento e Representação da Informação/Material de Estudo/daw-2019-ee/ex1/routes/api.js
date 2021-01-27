var express = require('express');
var router = express.Router();
var FControl = require("../controllers/movies");

// GET /api/filmes - Devolve a lista de filmes apenas com os campos "title", e "year";
//GET /api/filmes?by=ator - Devolve a lista de obras agrupadas por ator, ou seja, devolve uma lista de atores em que a cada ator está associada uma lista de filmes (coloque apenas o id e o título do filme);
//GET /api/filmes?by=genero - Devolve a lista de filmes agrupadas por género, ou seja, devolve uma lista de géneros em que a cada género está associada uma lista de filmes (coloque apenas o id e o título do filme);
router.get('/filmes', function(req, res, next) {
  switch(req.query.by){
    case 'ator':
      FControl.listByAtor()
      .then(data =>{res.status(200).jsonp(data)})
      .catch(err => res.status(500).jsonp({error:err}))
    break;
    case 'genero':
      FControl.listByGenre()
      .then(data => res.status(200).jsonp({data}))
      .catch(err => res.status(500).jsonp({error:err}))
    break;
    default:
      FControl.listMovies()
      .then(data => res.status(200).jsonp({movies:data}))
      .catch(err => res.status(500).jsonp({error:err}))
    break;
  }
  
});

//GET /api/filmes/:id - Devolve a informação completa de um filme (considere para id o id que geraste "f1", "f2", ... "fn";
router.get('/filmes/:id', function(req, res, next) {
  FControl.lookUp(req.params.id)
    .then(data => res.status(200).jsonp({movies:data}))
    .catch(err => res.status(500).jsonp({error:err}))
});

//GET /api/atores - Devolve apenas uma lista com os nomes dos atores, sem repetições e ordenada alfabeticamente;
router.get('/atores', function(req, res, next) {
  FControl.getAtores()
    .then(data => res.status(200).jsonp({atores:data}))
    .catch(err => res.status(500).jsonp({error:err}))
});


//GET /api/filmesQuantAtor - Devolve uma lista de filmes com os seguintes campos: id, titulo, número de atores participantes.
router.get('/filmesQuantAtor', function(req, res, next) {
  FControl.listarQuantAtor()
  .then(data => res.status(200).jsonp({atores:data}))
  .catch(err => res.status(500).jsonp({error:err}))
});

module.exports = router;
