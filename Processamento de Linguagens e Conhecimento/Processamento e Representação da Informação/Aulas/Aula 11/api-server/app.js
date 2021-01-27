var createError = require('http-errors');
var express = require('express');
var logger = require('morgan');
var jwt = require('jsonwebtoken')

var indexRouter = require('./routes/index');

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));


app.use(function(req,res,next){
  if(req.query.token != null){
    jwt.verify(req.query.token,'PRI2020',function(e,payload){
      if(e) res.status(401).jsonp({error:'Erro na verificação do token: '+e})
      else{
        req.user = {
          level:payload.level,
          username: payload.username
        }                 
        next()
      } 
    })
  }
  else{
    if(req.url == "/registar" || req.url == "/login"){
      next()
    }
    res.status(401).jsonp("falta o token")
  }

  
})

app.use('/', indexRouter);


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
});

module.exports = app;
