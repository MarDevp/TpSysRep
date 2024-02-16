import java.io.*;
import java.net.*;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 10000; // Port d'écoute fixé à 10000
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente sur le port " + port);
            Socket socket = serverSocket.accept();
            System.out.println("Connexion acceptée depuis " + socket.getInetAddress());

            // Création des flux d'entrée et de sortie
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lecture de l'objet Voiture envoyé par le client
            Voiture voiture = (Voiture) input.readObject();
            System.out.println("Voiture reçue : " + voiture.getType() + " " + voiture.getModel());

            // Modification du carburant de la voiture
            voiture.setCarburant(50); // Par exemple, fixé à 50 litres

            // Envoi de la voiture modifiée en réponse
            output.writeObject(voiture);

            // Fermeture des flux et de la connexion
            output.close();
            input.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
