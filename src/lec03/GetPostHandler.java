package lec03;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GetPostHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException{
        var httpMethod = exchange.getRequestMethod();
        var response=Response.of405();
        switch (httpMethod){
            case "GET":
                response = doGet();
                break;
            case "POST":
                response = doPost(exchange);
                break;
        }
        response(exchange,response);
    }
    private void response(HttpExchange exchange,Response response)throws IOException{
        var headers = exchange.getResponseHeaders();
        headers.add("Content-Type","text/html; charset=UTF-8");
        exchange.sendResponseHeaders(response.getStatusCode(),response.getBodyLength());
        try(var os = exchange.getResponseBody()){
            os.write(response.getBodyBytes());
        }
    }
    private Response doGet(){
        //language=HTML
        var html="<html><body>" +
                "<form action='GetPost' method='post'>" +
                "<textarea name='text' cols='40' rows='5'></textarea>" +
                "<button type = 'submit'>送信する</button>" +
                "</body></html>";
        return Response.of200(html);
    }
    private Response doPost(HttpExchange exchange){
        try(var scanner = new Scanner(exchange.getRequestBody())){
            var text = URLDecoder.decode(scanner.nextLine(), StandardCharsets.UTF_8);
            //language=HTML
            var html = "<html><body>" +
                    "<p>送信された文字列:</p>" +
                    "<pre>" +
                    text +
                    "</pre>" +
                    "</body></html>";
            return Response.of200(html);
        }catch(RuntimeException e){
            e.printStackTrace();
            return Response.of500();
        }
    }
}
