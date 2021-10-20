var util = require('util');

var users_happy = require('./payloads/users_happy.json');
var users_single = require('./payloads/users.json');

var oauth2_token = require('./payloads/oauth2-token.json');

var body_parser = require("body-parser");

var express = require('express');
var app = express();

app.use(body_parser.urlencoded({extended: true}));
app.use(body_parser.json());
app.use(function (req, res, next) {
  res.header("Content-Type",'application/json');
  next();
});

var users_retrieve_data = {
    'happy' : users_happy,
    'single' : users_single
};

app.post('/v2/oauth/token', (req, res) => {
    var timeout = 80;

    console.log("mock -> /v2/oauth/token -> body "+JSON.stringify(req.body));
    console.log("mock -> /v2/oauth/token -> params "+JSON.stringify(req.params));
    console.log("mock -> /v2/oauth/token -> headers "+JSON.stringify(req.headers));

    setTimeout(() => {
        res.send(oauth2_token);
    }, timeout);

});

app.get('/v1/downstream/response', (req, res) => {
	console.log("get mock -> body "+JSON.stringify(req.body));
    console.log("get mock -> params "+JSON.stringify(req.params));
    console.log("get mock -> headers "+JSON.stringify(req.headers));
    console.log("get mock -> query "+JSON.stringify(req.query));
    var data = users_retrieve_data.happy;
    res.send(data);
});

var port = process.env.PORT || 3000;
app.listen(port, () => console.log('Mock server listening on port ' + port));