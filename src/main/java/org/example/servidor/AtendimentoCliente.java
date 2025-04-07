package org.example.servidor;

import java.io.*;
import java.net.*;
import java.util.List;

public class AtendimentoCliente implements Runnable {
    private Socket socket;
    private List<PrintWriter> clientes;
    private TelaServidor gui;
    private PrintWriter out;

    public AtendimentoCliente(Socket socket, List<PrintWriter> clientes, TelaServidor gui) {
        this.socket = socket;
        this.clientes = clientes;
        this.gui = gui;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out = new PrintWriter(socket.getOutputStream(), true);
            clientes.add(out);

            String nome = in.readLine();
            enviarParaTodos("Cliente " + socket.getInetAddress() + " entrou!");
            String linha;

            while ((linha = in.readLine()) != null) {
                String msgFormatada = "(" + nome + "): " + linha;
                enviarParaTodos(msgFormatada);
            }
        } catch (IOException e) {
            gui.exibirMensagem("Erro com cliente: " + e.getMessage());
        } finally {
            clientes.remove(out);
        }
    }

    private void enviarParaTodos(String msg) {
        gui.exibirMensagem(msg);
        for (PrintWriter writer : clientes) {
            writer.println(msg);
        }
    }
}
