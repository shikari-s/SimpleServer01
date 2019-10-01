import com.sun.net.httpserver.HttpServer;
import lec02.ImageHandler;
import lec02.OmikujiHandler;
import lec03.GetPostHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleServer {
    public static void main(String[] args) {
        try{
            var address = new InetSocketAddress(8080);
            var httpServer = HttpServer.create(address,0);
            var simpleHandler = new SimpleHandler();
            httpServer.createContext("/hello",simpleHandler);
            var omikujiHandler = new OmikujiHandler();
            httpServer.createContext("/omikuji",omikujiHandler);
            var imageHandler = new ImageHandler();
            httpServer.createContext("/image",imageHandler);
            var getPostHandler = new GetPostHandler();
            httpServer.createContext("/GetPost",getPostHandler);
            httpServer.start();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("server start!");
    }
}
