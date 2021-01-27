const pubs = require('../models/pubs')
var Pubs = require('../models/pubs')


/* Return list Users */
module.exports.listPubs = () => {
    return Pubs.find().exec()
}

module.exports.listPubsByType = type => {
    return Pubs.find({type:type}).exec()
}

module.exports.listPubsByTypeAndYear = (type,year) => {
    return Pubs.find({type:type,year:year}).exec()
}


module.exports.listPubsByAuthor = Author => {
    return Pubs.find({ authors : { $all : [Author] }}).exec()
}

module.exports.lookUp = id => {
    return Pubs.findOne({id: id}).exec()
}


module.exports.inserePubs = p => {
    var newPubs = new Pubs(p)
    return newPubs.save()
}


