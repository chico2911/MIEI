var express = require('express');
var axios = require('axios');
var router = express.Router();

/* GET users listing. */
router.get(/\/(?:[0-9]{1,3}\.){3}[0-9]{1,3}\/[0-9a-zA-Z]*/,(req,res)=>{
    
  var split = req.url.split('/')[2]
  var url = req.url.split('/')[1]
  axios.get("https://json-server-runtime.azurewebsites.net/"+ url+"?id="+split)
  .then(response=>{
    var processo = response.data[0];
    var dataCPU = getData(processo.CPU,processo.CPU.length-1)
    var dataRam = getData(processo.RAM,processo.RAM.length-1)
    var cpu = processo.CPU.reverse().slice(-7)
    var ram = processo.RAM.reverse().slice(-7)
    console.log(url)
    res.render('processo', {a:processo,RAM:ram,CPU:cpu,cpuData: dataCPU,ramData:dataRam,host:url})
  })
}) 


router.get(/\/(?:[0-9]{1,3}\.){3}[0-9]{1,3}/, function(req, res, next) {
    var url = req.url.split('/')[1]
    axios.get("https://json-server-runtime.azurewebsites.net/"+url +"?_sort=id")
      .then(response=>{
        var processos = response.data;
        res.render('processos', {host:url,lista:processos})
      })
      .catch(error =>{res.send('erro')})
  });
  


 

function getData(cpu,N){
    if(N>7){
        i=7*30
    }
    else{i=N*30}
    cpu.reverse()
    var data = []
    for(j=0;j<=7 && cpu[j];j++){
        data[j]=[i,cpu[j]]
        i-=30
    }
    data.reverse()
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
    return rw;

}




module.exports = router;
