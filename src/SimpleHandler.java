import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleHandler implements HttpHandler {
    private int count;
    @Override
    public void handle(HttpExchange exchange)throws IOException{
        count++;
        printRequest(exchange);
        var response = count + "回目のアクセスです！";
        var responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type","text/plan;charset=UTF-8");
        exchange.sendResponseHeaders(200,response.getBytes().length);
        try(var os = exchange.getResponseBody()){
            os.write(response.getBytes());
        }
    }
    private void printRequest(HttpExchange exchange){
        System.out.printf("Request Message %d ----------",count);

        var method = exchange.getRequestMethod();
        System.out.println("Method:"+method);

        var uri = exchange.getRequestURI().toString();
        System.out.println("URI:"+uri);

        System.out.println("Headers");
        var requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);
    }
}
