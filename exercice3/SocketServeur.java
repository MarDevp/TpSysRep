import java.io.*;
import java.net.*;

public class SocketServeur {
    public static void main(String[] args) {
        int port = 12345; // Port sur lequel le serveur écoute
        
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente sur le port " + port);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion établie avec " + clientSocket.getInetAddress());
                
               
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                
               
                int age = input.readInt();
                String name = (String) input.readObject();
                System.out.println("Données reçues du client : Age = " + age + ", Nom = " + name);
                
                // Génération de l'identificateur client
                int clientId = clientSocket.hashCode();
                
                // Envoi de l'identificateur client au client
                output.writeInt(clientId);
                output.flush();
                
                
                input.close();
                output.close();
                clientSocket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
