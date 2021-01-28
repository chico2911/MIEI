var express = require('express');
var router = express.Router();
var axios = require('axios')

/* GET home page. */
router.get('/', function(req, res, next) {
  axios.get('https://json-server-runtime.azurewebsites.net/resources')
    .then(resposta=>{
      var resources = resposta.data
      console.log(resources)
      res.render('index',{recursos :resources})
    })
});

router.get(/gerais\/(?:[0-9]{1,3}\.){3}[0-9]{1,3}/, function(req, res, next) {
  var host = req.url.split('/')[2]
  axios.get("https://json-server-runtime.azurewebsites.net/"+host)
    .then(resposta=>{
      var dados = resposta.data;
      var mediaC = getMediaCPUProcessos(dados)
      var mediaR = getMediaRAMProcessos(dados)
      var transformC = transforma(mediaC)
      var transformR = transforma(mediaR)
      var maiorCPU = getMaior(mediaC,host)
      var maiorRAM = getMaior(mediaR,host)
      res.render('geral',{cpuData:transformC,ramData:transformR,maiorR:maiorRAM,maiorC:maiorCPU,host:host})
    })
});


function transforma(data){
  var rw = "[";
  for(i=0;i<data.length;i++){
    if (data[i] != data[data.length - 1]){
        rw+= '[' + data[i][0] + ","+ data[i][1] + "],";
    }  
    else {
        rw+= "[" + data[i][0] + ","+ data[i][1] + "]"
      }
      
  }
  rw+="]"
  return rw
}


function getMediaCPUProcessos(dados){
  var data = []
  for(d=0;d<dados.length-1;d++){
      var media = 0;
      var soma = 0;
      for(i=0;i<dados[d].CPU.length-1;i++){
          soma+=dados[d].CPU[i];
      }
      media = soma/dados[d].CPU.length;
      data[d]=[dados[d].id,media] ;
  }
 
  return data;
}

function getMediaRAMProcessos(dados){
  var data = []
  for(d=0;d<dados.length-1;d++){
      var media = 0;
      var soma = 0;
      for(i=0;i<dados[d].RAM.length-1;i++){
          soma+=dados[d].RAM[i];
      }
      media = soma/dados[d].RAM.length;
      data[d]=[dados[d].id,media] ;
  }
  return data;
}

function getMaior(dados,host){
  var maior = 0
  var id
  for(i=0;i<dados.length-1;i++){
    if(dados[i][1]>maior){
      maior = dados[i][1]
      id = dados[i][0]
    }
  }
  return id
}  



module.exports = router;
