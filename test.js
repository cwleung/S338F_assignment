console.log('testing');
//ex2
var variable = {
   var1: "rest",
   var2: "ur",
   var3: "ant"
}

console.log(variable);
//ex3
function addnumber(a,b){
   return a+b;
}
console.log(addnumber(10,20));
//ex4
function functionless(){}

console.log(functionless());
//ex5
var functionVar = function(){
   console.log('Variable called');
   console.log('Variable called');
   console.log('Variable called');
}

console.log(functionVar);
//TIMEOUT
setTimeout(functionVar,1000);

var http = require("http");
var v = 10;
function onRequest(request, response) {
   console.log("Request received.");
   response.writeHead(200, {"Content-Type": "text/plain"});
   response.write("Hello World");
   response.end();
}

http.createServer(onRequest).listen(8888);

console.log("Server has started.");
