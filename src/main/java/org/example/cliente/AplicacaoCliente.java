package org.example.cliente;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class AplicacaoCliente {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        JanelaCliente janela = new JanelaCliente();

        janela.getBotaoConectar().addActionListener(e -> {
            String nome = janela.getCampoNome().getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Informe um nome antes de conectar.");
                return;
            }

            try {
                Socket socket = new Socket(HOST, PORTA);
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                saida.println(nome);
                janela.ativarChat();
                janela.adicionarMensagem("Conectado como " + nome);

                new Thread(() -> {
                    try {
                        String msg;
                        while ((msg = entrada.readLine()) != null) {
                            janela.adicionarMensagem(msg);
                        }
                    } catch (IOException ex) {
                        janela.adicionarMensagem("Desconectado do servidor.");
                    }
                }).start();

                janela.getBotaoEnviar().addActionListener(ev -> {
                    String mensagem = janela.getCampoMensagem().getText().trim();
                    if (!mensagem.isEmpty()) {
                        saida.println(mensagem);
                        janela.getCampoMensagem().setText("");
                    }
                });

            } catch (IOException ex) {
                janela.adicionarMensagem("Erro ao conectar: " + ex.getMessage());
            }
        });
    }
}