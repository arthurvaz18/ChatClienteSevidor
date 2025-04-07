package org.example.servidor;

import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.*;

public class AplicacaoServidor {
    private static final int PORTA = 12345;
    private static List<PrintWriter> saidasParaClientes = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        JanelaServidor janelaServidor = new JanelaServidor();

        janelaServidor.adicionarAcaoBotaoEnviar(e -> {
            String texto = janelaServidor.getMensagem();
            if (!texto.isEmpty()) {
                String mensagem = "Servidor: " + texto;
                enviarParaTodos(mensagem);
                janelaServidor.adicionarMensagem(mensagem);
                janelaServidor.limparCampoMensagem();
            }
        });

        try (ServerSocket servidorSocket = new ServerSocket(PORTA)) {
            janelaServidor.adicionarMensagem("🟢 Servidor iniciado na porta " + PORTA);

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                janelaServidor.adicionarMensagem("Cliente conectado: " + clienteSocket.getInetAddress());

                AtendenteServidor atendente = new AtendenteServidor(clienteSocket, janelaServidor, saidasParaClientes);
                new Thread(atendente).start();
            }
        } catch (Exception e) {
            janelaServidor.adicionarMensagem("Erro no servidor: " + e.getMessage());
        }
    }

    public static void enviarParaTodos(String mensagem) {
        for (PrintWriter escritor : saidasParaClientes) {
            escritor.println(mensagem);
        }
    }
}
