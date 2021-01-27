var Aluno = require('../models/aluno')


//Devolve a lista de alunos
module.exports.listar = () => {
    return Aluno
        .find()
        .exec()
}

module.exports.consultar = id => {
    return Aluno
        .findOne({_id: id})
        .exec()
}

module.exports.inserir = a => {
        console.log('Post de: ' + JSON.stringify(a.body))
        var novo=new Aluno(a.body)
         return novo.save()
    
}