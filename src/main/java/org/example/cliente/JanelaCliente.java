package org.example.cliente;

import javax.swing.*;
import java.awt.*;

public class JanelaCliente extends JFrame {
    private JTextArea areaTexto;
    private JTextField campoMensagem;
    private JTextField campoNome;
    private JButton botaoConectar;
    private JButton botaoEnviar;

    public JanelaCliente() {
        super("Cliente de Chat");

        campoNome = new JTextField(15);
        botaoConectar = new JButton("Conectar");
        areaTexto = new JTextArea(15, 50);
        areaTexto.setEditable(false);
        campoMensagem = new JTextField(40);
        botaoEnviar = new JButton("Enviar");

        botaoEnviar.setEnabled(false);
        campoMensagem.setEnabled(false);

        JPanel painelSuperior = new JPanel();
        painelSuperior.add(new JLabel("Seu nome:"));
        painelSuperior.add(campoNome);
        painelSuperior.add(botaoConectar);

        JPanel painelInferior = new JPanel();
        painelInferior.add(campoMensagem);
        painelInferior.add(botaoEnviar);

        setLayout(new BorderLayout());
        add(painelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void ativarChat() {
        botaoConectar.setEnabled(false);
        campoNome.setEnabled(false);
        campoMensagem.setEnabled(true);
        botaoEnviar.setEnabled(true);
    }

    public void adicionarMensagem(String mensagem) {
        SwingUtilities.invokeLater(() -> areaTexto.append(mensagem + "\n"));
    }

    public JTextArea getAreaTexto() { return areaTexto; }
    public JTextField getCampoMensagem() { return campoMensagem; }
    public JTextField getCampoNome() { return campoNome; }
    public JButton getBotaoConectar() { return botaoConectar; }
    public JButton getBotaoEnviar() { return botaoEnviar; }
}