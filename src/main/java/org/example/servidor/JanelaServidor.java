package org.example.servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JanelaServidor extends JFrame {
    private JTextArea areaMensagens;
    private JTextField campoMensagem;
    private JButton botaoEnviar;

    public JanelaServidor() {
        super("Servidor (Administrador)");

        areaMensagens = new JTextArea(20, 50);
        areaMensagens.setEditable(false);
        campoMensagem = new JTextField(40);
        botaoEnviar = new JButton("Enviar");

        JPanel painelInferior = new JPanel();
        painelInferior.add(campoMensagem);
        painelInferior.add(botaoEnviar);

        add(new JScrollPane(areaMensagens), BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void adicionarMensagem(String mensagem) {
        SwingUtilities.invokeLater(() -> areaMensagens.append(mensagem + "\n"));
    }

    public String getMensagem() {
        return campoMensagem.getText().trim();
    }

    public void limparCampoMensagem() {
        campoMensagem.setText("");
    }

    public void adicionarAcaoBotaoEnviar(ActionListener acao) {
        botaoEnviar.addActionListener(acao);
    }
}
