import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.sql.SQLException;
        import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

class ServeOne extends Thread {
    private Socket socket;
    private BufferedInputStream in;
    private ObjectOutputStream out;
    public ServeOne(Socket s) throws IOException {

        socket = s;

        in = new BufferedInputStream(socket.getInputStream());
        start();

    }
    public void run() {
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = bufferedReader.readLine();
            System.out.println(line);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(line);
            System.out.println("Принятый JSON"+json);
            JSONArray jsonArray = (JSONArray) json.get("dayni");
            List<Player> players = new ArrayList<>();

                for(int i=0; i<jsonArray.size(); i++){
                    players.add(new Player(((JSONObject) jsonArray.get(i)).get("name").toString()));
                }

            System.out.println("Распарсили JSON и получили list"+players);
            bufferedReader.close();
            in.close();
//
//            ArrayList dayni = (ArrayList) jsonObject.get("dayni");
//            DataBaseInsert.insertInto(dayni.get(0));
//            DataBaseInsert.insertInto(dayni.get(1));
//            DataBaseInsert.insertInto(dayni.get(2));
            

        } catch (IOException e ) {
            System.err.println("IO Exception");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
class Player{
    Player(String name){
        this.name = name;
    }
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
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
