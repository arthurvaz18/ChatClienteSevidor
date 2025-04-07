package org.example.servidor;


import java.net.*;
import java.util.*;
import java.io.*;

public class Servidor {
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        TelaServidor gui = new TelaServidor();
        List<PrintWriter> clientes = Collections.synchronizedList(new ArrayList<>());

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            gui.exibirMensagem("Servidor iniciado na porta " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new AtendimentoCliente(socket, clientes, gui)).start();
            }
        } catch (IOException e) {
            gui.exibirMensagem("Erro no servidor: " + e.getMessage());
        }
    }
}
