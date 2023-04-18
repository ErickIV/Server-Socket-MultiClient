import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;

public class Servidor extends Thread {
    private static ArrayList<Servidor> clientes = new ArrayList<>(); // lista de clientes
    private Socket con;
    private BufferedReader in;
    private PrintWriter out;

    public Servidor(Socket socket) {
        try {
            con = socket;
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            out = new PrintWriter(con.getOutputStream(), true);
            clientes.add(this); // adiciona o cliente à lista
        } catch (IOException ioe) {
            System.err.println("Problemas de IO");
        }
    }

    public void run() {
        try {
            out.println("Bem vindo!"); // Escreve ao cliente
            String mensagem;
            while ((mensagem = in.readLine()) != null) {
                System.out.println(mensagem);
                enviarParaTodos(mensagem); // envia mensagem para todos os clientes
            }
        } catch (IOException ioe) {
            System.err.println("Problemas de IO");
        } finally {
            clientes.remove(this); // remove o cliente da lista ao finalizar a conexão
        }
    }

    public static void enviarParaTodos(String mensagem) {
        for (Servidor cliente : clientes) {
            mensagem = JOptionPane.showInputDialog("Digite a Mensagem");
            cliente.out.println(mensagem); // envia mensagem para todos os clientes
        }
    }
}


