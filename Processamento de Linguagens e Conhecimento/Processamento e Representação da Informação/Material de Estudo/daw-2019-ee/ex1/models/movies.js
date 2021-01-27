var mongoose = require('mongoose')

var MoviesSchema = new mongoose.Schema({
    _id: String,
    title: String,
    year: Number,
    cast:Array,
    genres:Array
})

module.exports = mongoose.model('movies', MoviesSchema)
