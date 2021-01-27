var casamentos = require("../models/casamentos");

/* Retorna Lista de Filmes apenas com o _id,title e year, para retornar o objeto todo, tirar os {} */
module.exports.listarCasamentos = () => {
  return casamentos.find(
    {},
    {
      _id: 1,
      title: 1,
      date: 1,
    }
  ).exec();
};

// Encontra objeto pelo ID
module.exports.lookUp = (id) => {
  return casamentos.findOne({ _id: id }).exec();
};

//Lista de atores e respetivos titulos
module.exports.listarByNome = (nome) => {
  return casamentos.find({title :{$regex:nome}});
};

module.exports.listarAno = (ano) => {
    return casamentos.find({date:ano}).exec();
};

module.exports.listarByAno = () => {
    return casamentos.aggregate([{
        $group: {
            _id: "$date",
            date:{$first:"$date"},
            casamentos: {
                $push: {
                    id: "$_id",
                    title: "$title"
                }
            }
        }
    },{$project:{_id:0,date:1,casamentos:1}}]).exec();
}

//Lista distinta de todos os atores de um dataset ordenado
module.exports.getNoivos = () =>{
    return casamentos.aggregate([
        { $addFields: {
            "lista": { $regexFind: { input: "$title", regex: "(?<=: ).*?(?= c)" } }
         }
        },
        {$sort: {lista: 1}},
        {$group:
            {_id:null,
            noivos:{$push:{id:"$_id",noivo:"$lista.match"}}
            }
        },
        {
        $project: {
            _id:0,
          noivos:1
        }}
      
    ]).exec();
}
