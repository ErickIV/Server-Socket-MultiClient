import java.net.*;
import java.io.*;
import javax.swing.*;

public class Principal {
    public static void main(String args[]) {
        try {
            Socket servidor = new Socket("10.150.94.195", 6969);// endereco e porta
            PrintWriter out = new PrintWriter(servidor.getOutputStream(), true);
            // Executa uma Thread para receber as mensagens de retorno
            Cliente in = new Cliente(servidor);
            in.start();
            String mensagem = "";
            while (!mensagem.equals("fim")) {
                mensagem = JOptionPane.showInputDialog("Digite a Mensagem ou fim para sair");
                out.println(mensagem); // Escreve a mensagem
            }
        } catch (IOException io) {
            System.err.println("Problemas de IO");
        }
    }
}
