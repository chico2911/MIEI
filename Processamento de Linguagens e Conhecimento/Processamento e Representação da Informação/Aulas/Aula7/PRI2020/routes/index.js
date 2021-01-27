var express = require('express');
var router = express.Router();
const Aluno = require('../controllers/aluno');
const aluno = require('../models/aluno');

/* GET home page. */
/*router.get('/', function(req, res, next) {
  // render invoca o pug
  res.render('index', { title: 'Turma PRI de 2020' });
});
*/

// Render vai à view buscar o pug
router.get(['/alunos','/'], (req, res) => {
  //res.render('alunos', {alunos: registos /* vem da base de dados */})
  Aluno.listar()
    .then(dados => res.render('alunos', {lista: dados}))
    .catch(e => res.render('error', {error: e}))
})


router.get('/registar', (req, res) => {
  //res.render('alunos', {alunos: registos /* vem da base de dados */})
  res.render('form',{title: 'Registo de Aluno'})
})


router.get(/\/alunos\/[0-9a-zA-Z]*/,(req,res)=>{
  var split = req.url.split("/")[2]
  console.log(req.params)
  console.log(split)
  Aluno.consultar(split)
    .then(aln => res.render('aluno',{aluno: aln}))
    .catch(e => res.render('error', {error: e}))

})

router.post('/alunos', (req,res)=>{
   Aluno.inserir(req)
   res.render('index', { title: 'Turma PRI de 2020' });
})



module.exports = router;
