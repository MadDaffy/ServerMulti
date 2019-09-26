import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class Client  {


    public static void main(String[] args) {
        ArrayList dayni = new ArrayList();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();


        dayni.add("Ефанов Иван");
        dayni.add("Сысоев Дмитрий");
        dayni.add("Попов Дмитрий");
        jsonArray.addAll(dayni);
        jsonObject.put("dayni", jsonArray);


        try (Socket clientSocket = new Socket("127.0.0.1",6666);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
            )
        {
            out.writeObject(jsonObject);
            out.flush();


        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
