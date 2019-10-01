package lec03;

public class Response {
    private int statusCode;
    private String body;

    private Response(int statusCode,String body){
        this.statusCode=statusCode;
        this.body=body;
    }
    public int getStatusCode(){
        return this.statusCode;
    }
    public byte[] getBodyBytes(){
        return body.getBytes();
    }
    public long getBodyLength(){
        return getBodyBytes().length;
    }
    public static Response of200(String body){
        return new Response(200,body);
    }
    public static Response of405(){
        return new Response(405,"Method Not Allowed");
    }
    public static Response of500(){
        return new Response(500,"Internal Server Error");
    }
}
