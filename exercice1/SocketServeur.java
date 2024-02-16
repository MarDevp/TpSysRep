import java.io.*;
import java.net.*;
import java.util.Scanner;
public class SocketServeur {
 public static void main(String argv[]) {
 int port = 0;
 Scanner keyb = new Scanner(System.in);
 // .............................................................
 System.out.print("Port d'écoute : ");
 try {
 port = keyb.nextInt(); // L'utilisateur est invité à entrer le numéro de port sur lequel le serveur écoutera.
 } catch (NumberFormatException e) {
 System.err.println("Le paramètre n'est pas un entier.");
 System.err.println("Usage : java ServeurUDP port-serveur");
 System.exit(-1);
 }
 try {
 // .............................................................
 ServerSocket serverSocket = new ServerSocket(port); // Création d'un objet de type ServerSocket pour écouter les connexions entrantes sur le port spécifié.
 // ..........................................................

Socket socket = serverSocket.accept();  // Le serveur attend qu'un client se connecte et accepte la connexion lorsqu'elle arrive, créant un objet Socket pour la communication avec le client.
 // ..........................................................
 ObjectOutputStream output =
 new ObjectOutputStream(socket.getOutputStream());  // Création d'un flux de sortie d'objets pour envoyer des données au client.
 ObjectInputStream input =
 new ObjectInputStream(socket.getInputStream());    // Création d'un flux d'entrée d'objets pour recevoir des données du client.
 // ..........................................................
 String chaine = (String) input.readObject();  // Lecture d'une chaîne de caractères envoyée par le client.
 System.out.println(" recu : " + chaine);    // Affichage de la chaîne reçue par le client.
 // ..........................................................
 System.out.println(" ca vient de : " + socket.getInetAddress() +
":" + socket.getPort());    // Affichage de l'adresse IP et du port du client connecté.
 // ..........................................................
 output.writeObject(new String("bien recu"));    // Envoi d'une réponse au client pour confirmer que le message a été bien reçu.
 } catch (Exception e) {
 System.err.println("Erreur : " + e);
 }
 
