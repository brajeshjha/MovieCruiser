var express = require("express"); 
var app = express(); 
app.use(express.static(__dirname + '/dist/movie-cruiser-frontend/')); 
app.get('/', function (req, res) {  res.status(200).send('ok') }); 
app.listen(4200, function(request, response){ });
