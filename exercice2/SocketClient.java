import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String argv[]) {
        String host = "localhost"; // Adresse du serveur local
        int port = 10000; // Port d'écoute du serveur

        try {
            // Connexion au serveur
            Socket socket = new Socket(host, port);

            // Création des flux d'entrée et de sortie
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Création d'une voiture et envoi au serveur
            Voiture voiture = new Voiture("SUV", "Toyota");
            output.writeObject(voiture);

            // Réception de la voiture modifiée depuis le serveur
            Voiture voitureModifiee = (Voiture) input.readObject();
            System.out.println("Carburant de la voiture : " + voitureModifiee.getCarburant() + " litres");

            // Fermeture des flux et de la connexion
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
