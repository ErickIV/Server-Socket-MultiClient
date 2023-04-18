import java.net.*;
import java.io.*;

public class Principal {
    public static void main(String[] args) {
        int numClientes = 0; // contador de clientes
        int numThreads = 0; // contador de threads
        try {
            ServerSocket servidor = new ServerSocket(6969);
            System.out.println("Aguardando cliente!");
            while (true) {
                Socket socket = servidor.accept(); // espera
                Servidor cliente = new Servidor(socket);
                System.out.println("Chegou um cliente!");
                numClientes++; // incrementa contador de clientes
                numThreads++; // incrementa contador de threads
                System.out.println("Clientes conectados: " + numClientes);
                System.out.println("Threads em execução: " + numThreads);
                cliente.start();
            }
        } catch (IOException e) {
            System.err.println("Problemas de IO");
        } finally {
            numThreads--; // decrementa contador de threads
            System.out.println("Threads em execução: " + numThreads);
        }
    }
}
