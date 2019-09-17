import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleServer {
    public static void main(String[] args) {
        try{
            var address = new InetSocketAddress(8080);
            var httpServer = HttpServer.create(address,0);
            var simpleHandler = new SimpleHandler();
            httpServer.createContext("/hello",simpleHandler);
            httpServer.start();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("server start!");
    }
}
