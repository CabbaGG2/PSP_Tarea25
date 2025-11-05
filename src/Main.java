import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final int[] PUERTOS = {21,22,80,443};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Servicio de Conexiones con Sockets");
            System.out.println("Ingresa una ip o escribe 'salir' si quieres terminar el programa");

            String dominio = sc.nextLine().trim();
            if(dominio.equalsIgnoreCase("salir")){
                System.out.println("Saliendo del programa...");
                break;
            }
            if (dominio.isEmpty()){
                System.out.println("Dirección vacia. Intenta de nuevo...");
                continue;
            }

            System.out.println("Pulsa ENTER para probar puertos famosos o escribe un puerto valido...");
            String puertoInput = sc.nextLine().trim();

            List<Integer> puertosAProbar = new ArrayList<>();

            if (puertoInput.isEmpty()) {
                for (int p : PUERTOS) {
                    puertosAProbar.add(p);
                }
            } else {
                int p = Integer.parseInt(puertoInput);
                if (p < 1 || p > 65535) {
                    System.out.println("Puerto fuera de rango (1-65535)");
                    continue;
                }
                if (!String.valueOf(p).matches("\\d+")) {
                    System.out.println("Entrada no válida para el puerto, porfavor intenta denuevo");
                    continue;
                }
                puertosAProbar.add(p);
            }

            List<Integer> puertosAbiertos = new ArrayList<>();
            List<Integer> puertosCerrados = new ArrayList<>();

            for (int puerto : puertosAProbar) {
                boolean abierto = probarPuertos(dominio, puerto);
                if(abierto) {
                    puertosAbiertos.add(puerto);
                } else {
                    puertosCerrados.add(puerto);
                }
            }

            System.out.println();
            System.out.println("----RESUMEN DE " + dominio + "----");
            if (!puertosAbiertos.isEmpty()) {
                System.out.println("Puertos abiertos: ");
                for (int i = 0; i < puertosAbiertos.size(); i++) {
                    System.out.print(puertosAbiertos.get(i));
                    if (i < puertosAbiertos.size() - 1) System.out.print(", ");
                }
                System.out.println();
            } else {
                System.out.println("Puertos abiertos: ninguno");
            }

            if (!puertosCerrados.isEmpty()) {
                System.out.println("Puertos cerrados: ");
                for (int i = 0; i < puertosCerrados.size(); i++) {
                    System.out.print(puertosCerrados.get(i));
                    if (i < puertosCerrados.size() - 1) System.out.print(", ");
                }
                System.out.println();
            } else {
                System.out.println("Puertos abiertos: ninguno");
            }
        }
        sc.close();
    }

    public static boolean probarPuertos (String dominio, int puerto){
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(dominio,puerto),2000);
            System.out.println("conectado al puerto: " + puerto);
            socket.close();
            return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}