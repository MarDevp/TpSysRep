import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        // .............................................................
        System.out.print("Nom du serveur : ");
        host = keyb.next(); // L'utilisateur est invité à entrer le nom du serveur auquel se connecter.
        System.out.print("Port du serveur : ");

        try {
            port = keyb.nextInt(); // L'utilisateur est invité à entrer le numéro de port sur lequel le serveur écoute.
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        // .............................................................
        try {
            // .............................................................
            InetAddress adr = InetAddress.getByName(host); // Résolution du nom de l'hôte en une adresse IP.
            // .............................................................
            Socket socket = new Socket(adr, port); // Création d'un objet Socket pour la communication avec le serveur spécifié.
            // .............................................................
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); // Création d'un flux de sortie d'objets pour envoyer des données au serveur.
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream()); // Création d'un flux d'entrée d'objets pour recevoir des données du serveur.
            // .............................................................
            output.writeObject(new String("ma première socket")); // Envoi d'une chaîne de caractères au serveur.
            // .............................................................
            String chaine = (String) input.readObject(); // Lecture d'une chaîne de caractères envoyée par le serveur.
            System.out.println(" recu du serveur : " + chaine); // Affichage de la chaîne reçue du serveur.
        } catch (Exception e) {
            System.err.println("Erreur : " + e); 
        }
    }
}
