package lec02;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Random;

public class OmikujiHandler implements HttpHandler {
    private int count;
    @Override
    public void handle(HttpExchange exchange)throws IOException {
        int n=new Random().nextInt(5);
        var unsei = "";
        switch (n){
            case 0:
                unsei="凶";
                break;
            case 1:
                unsei ="吉";
                break;
            case 2:
                unsei ="小吉";
                break;
            case 3:
                unsei ="中吉";
                break;
            case 4:
                unsei ="大吉";
                break;
                default:
                    break;
        }
        var response = "本日の運勢は"+ unsei +"です。";
        var responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type","text/plan;charset=UTF-8");
        exchange.sendResponseHeaders(200,response.getBytes().length);
        try(var os = exchange.getResponseBody()){
            os.write(response.getBytes());
        }
    }
}
