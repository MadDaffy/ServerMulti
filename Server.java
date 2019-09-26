import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.sql.SQLException;
        import java.util.ArrayList;

class ServeOne extends Thread {
    private Socket socket;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    public ServeOne(Socket s) throws IOException {

        socket = s;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        start();


    }
    public void run() {
        try {


//            Нужно пройтись по всем ключам и сделать для них массивы всех значий
            JSONObject jsonObject =(JSONObject) in.readObject();
            System.out.println(jsonObject.toString());
//            ArrayList dayni = (ArrayList) jsonObject.get("dayni");
//            DataBaseInsert.insertInto(dayni.get(0));
//            DataBaseInsert.insertInto(dayni.get(1));
//            DataBaseInsert.insertInto(dayni.get(2));


        } catch (IOException e ) {
            System.err.println("IO Exception");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Сервер запущен");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("сокет создался");
                try {
                    new ServeOne(socket);
                }
                catch (IOException e) {
                    socket.close();
                    System.out.println("все пошло по пи3де");
                }
            }
        }
        finally {
            serverSocket.close();
        }
    }
}
