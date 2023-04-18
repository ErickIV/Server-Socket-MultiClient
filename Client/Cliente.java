import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Cliente extends Thread {
    private Socket con;
    private BufferedReader in;

    public Cliente(Socket socket) {
        try {
            con = socket;
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException ioe) {
            System.err.println("Problemas de IO");
        }
    }

    public void run() {
        try {
            String mensagem = "";
            while ((mensagem = in.readLine()) != null) {
                System.out.println(mensagem); // Escreve mensagens do servidor
            }
        } catch (IOException ioe) {
            System.err.println("Problemas de IO");
        }
    }
}