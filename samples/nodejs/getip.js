var http = require("http");

http.createServer(function (req, resp) {
    console.log(req.headers);
    resp.writeHead(200, {"Content-Type": "text/plain"});
    resp.write("var ip='" + getIp(req) + "';");
    resp.end();
}).listen(8888);

function getIp(req) {
    var ip = req.headers["x-forward-for"];
    return ip == undefined ? req.connection.remoteAddress : ip;
}