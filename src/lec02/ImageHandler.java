package lec02;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ImageHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange)throws IOException {
        var response = "<html><body>" +
                "<img src=\"https://www.chitose.ac.jp/english/common/images/logo.png\"> " +
                "</body></html>";
        var responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type","text/html;charset=UTF-8");
        exchange.sendResponseHeaders(200,response.getBytes().length);
        try(var os = exchange.getResponseBody()){
            os.write(response.getBytes());
        }
    }
}
