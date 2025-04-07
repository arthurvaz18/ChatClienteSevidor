package org.example.servidor;

import java.io.*;
import java.net.*;
import java.util.List;

public class AtendenteServidor implements Runnable {
    private Socket socketCliente;
    private JanelaServidor janelaServidor;
    private List<PrintWriter> listaSaidas;
    private PrintWriter saida;

    public AtendenteServidor(Socket socketCliente, JanelaServidor janelaServidor, List<PrintWriter> listaSaidas) {
        this.socketCliente = socketCliente;
        this.janelaServidor = janelaServidor;
        this.listaSaidas = listaSaidas;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        ) {
            saida = new PrintWriter(socketCliente.getOutputStream(), true);
            listaSaidas.add(saida);

            String nomeUsuario = entrada.readLine();
            AplicacaoServidor.enviarParaTodos("👤 " + nomeUsuario + " entrou no chat!");

            String mensagemRecebida;
            while ((mensagemRecebida = entrada.readLine()) != null) {
                String mensagemCompleta = nomeUsuario + ": " + mensagemRecebida;
                AplicacaoServidor.enviarParaTodos(mensagemCompleta);
                janelaServidor.adicionarMensagem(mensagemCompleta);
            }
        } catch (IOException e) {
            janelaServidor.adicionarMensagem("Erro com cliente: " + e.getMessage());
        } finally {
            if (saida != null) {
                listaSaidas.remove(saida);
            }
        }
    }
}
