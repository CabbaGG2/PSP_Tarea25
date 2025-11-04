import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Servicio de Conexiones con Sockets");
            System.out.println("Ingresa una ip o escribe 'salir' si quieres terminar el programa");
            String dominio = sc.nextLine();
            if(dominio.equalsIgnoreCase("salir")){
                System.out.println("Saliendo del programa...");
                sc.close();
                break;
            }
            System.out.println("Marca 'A' si quieres conectarte por protocolo FTP: ");
            System.out.println("Marca 'B' si quieres conectarte por protocolo SSH: ");
            System.out.println("Marca 'C' si quieres conectarte por protocolo HTTP: ");
            System.out.println("Marca 'D' si quieres conectarte por protocolo HTTPS: ");
            System.out.println("Marca 'E' si quires conectarte por un puerto distinto: ");
            String decision = sc.nextLine();
            if(decision.equals("A")) {
                try {
                    Socket ftp = new Socket(dominio, 21);
                    System.out.println("Conectado a: " + ftp.getInetAddress());
                    ftp.close();
                } catch (IOException e) {
                    System.out.println("No se pudo conectar: " + e.getMessage());
                }

            } else if(decision.equals("B")) {
                try {
                    Socket ssh = new Socket(dominio, 22);
                    System.out.println("Conectado a: " + ssh.getInetAddress());
                    ssh.close();
                } catch (UnknownHostException e) {
                    System.out.println("Error con el host: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("No se pudo conectar: " + e.getMessage());
                }

            } else if(decision.equals("C")) {
                try {
                    Socket http = new Socket(dominio, 80);
                    System.out.println("Conectado a: " + http.getInetAddress());
                    http.close();
                } catch (UnknownHostException e) {
                    System.out.println("Error con el host: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("No se pudo conectar: " + e.getMessage());
                }

            } else if(decision.equals("D")) {
                try {
                    Socket https = new Socket(dominio, 443);
                    System.out.println("Conectado a: " + https.getInetAddress());
                    https.close();
                } catch (UnknownHostException e) {
                    System.out.println("Error con el host: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("No se pudo conectar: " + e.getMessage());
                }

            } else if(decision.equals("E")) {
                System.out.println("Escribe el puerto al que quieres conectarte: ");
                int puerto = Integer.parseInt(sc.nextLine());
                try {
                    Socket socket = new Socket(dominio, puerto);
                    System.out.println("Conectado a: " + socket.getInetAddress());
                    socket.close();
                } catch (UnknownHostException e) {
                    System.out.println("Error con el host: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("No se pudo conectar: " + e.getMessage());
                }

            }
        }
    }
}