import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String argv[]) {
        String host = "localhost"; 
        int port = 10000; 

        try {
          
            Socket socket = new Socket(host, port);

      
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

        
            Voiture voiture = new Voiture("SUV", "Toyota");
            output.writeObject(voiture);

            Voiture voitureModifiee = (Voiture) input.readObject();
            System.out.println("Carburant de la voiture : " + voitureModifiee.getCarburant() + " litres");

            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
