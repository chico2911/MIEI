var mongoose = require('mongoose')

var pubsSchema = new mongoose.Schema({
    title:String,
    booktitle:String,
    year:String,
    month:String,
    doi:String,
    id:String,
    type:String,
    authors:Array
})

module.exports = mongoose.model('pubs', pubsSchema)