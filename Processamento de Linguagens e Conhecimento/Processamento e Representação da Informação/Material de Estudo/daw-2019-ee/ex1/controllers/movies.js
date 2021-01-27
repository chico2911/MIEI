var Movies = require("../models/movies");

/* Return list Movies */
module.exports.listMovies = () => {
  return Movies.find(
    {},
    {
      _id: 0,
      title: 1,
      year: 1,
    }
  ).exec();
};

module.exports.lookUp = (id) => {
  return Movies.findOne({ _id: id }).exec();
};

module.exports.getAtores = () =>{
    return Movies.distinct("cast").sort().exec()
}


module.exports.listByAtor = () => {
  return Movies.aggregate([{ $unwind: "$cast" },
  {$group: {_id:"$cast",lista:{$push:"$title"}}},
  {$project:{_id:1,lista:1}}]).exec();
};

module.exports.listByGenre = () => {
    return Movies.aggregate([{ $unwind: "$genres"},
    {$group: {_id:"$genres",lista:{$push:"$title"}}},
    {$project:{_id:1,lista:1}}]).exec();
  };
  

  module.exports.listarQuantAtor = () => {
    return Movies.aggregate([
    {
        $project: {
            "_id": 1,
            "title": 1,
            "year": 1,
            NumberOfAtors:{
                $size: "$cast",
            },
        },
    }
    ]).exec();
};