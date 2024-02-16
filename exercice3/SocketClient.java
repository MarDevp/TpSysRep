import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        String host = "localhost"; 
        int port = 12345; 
        
        try {
            // Connexion au serveur
            Socket socket = new Socket(host, port);
            System.out.println("Connecté au serveur sur le port " + port);
            
       
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            int age = 30;
            String name = "Mar";
            
          
            output.writeInt(age);
            output.writeObject(name);
            output.flush();
            
        
            int clientId = input.readInt();
            System.out.println("Identifiant client reçu du serveur : " + clientId);
            
          
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
