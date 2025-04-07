package org.example.cliente;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        TelaCliente gui = new TelaCliente();

        gui.getBotaoConectar().addActionListener(e -> {
            String nome = gui.getCampoNome().getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(gui, "Digite seu nome!");
                return;
            }

            gui.getBotaoConectar().setEnabled(false);
            gui.getCampoNome().setEnabled(false);
            gui.getCampoMensagem().setEnabled(true);
            gui.getBotaoEnviar().setEnabled(true);
            gui.adicionarMensagem("Aguardando conexão...");

            try {
                Socket socket = new Socket(HOST, PORTA);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println(nome);

                // Recebendo mensagens
                new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = in.readLine()) != null) {
                            gui.adicionarMensagem(msg);
                        }
                    } catch (IOException ex) {
                        gui.adicionarMensagem("Desconectado do servidor.");
                    }
                }).start();

                // Enviando mensagens
                gui.getBotaoEnviar().addActionListener(ev -> {
                    String texto = gui.getCampoMensagem().getText().trim();
                    if (!texto.isEmpty()) {
                        out.println(texto);
                        gui.getCampoMensagem().setText("");
                    }
                });

            } catch (IOException ex) {
                gui.adicionarMensagem("Erro: " + ex.getMessage());
            }
        });
    }
}