package org.example.cliente;

import javax.swing.*;
import java.awt.*;

public class TelaCliente extends JFrame {
    private JTextArea areaTexto;
    private JTextField campoMensagem;
    private JTextField campoNome;
    private JButton botaoConectar;
    private JButton botaoEnviar;

    public TelaCliente() {
        super("Cliente");

        campoNome = new JTextField(15);
        botaoConectar = new JButton("Conectar");
        areaTexto = new JTextArea(15, 50);
        areaTexto.setEditable(false);
        campoMensagem = new JTextField(40);
        botaoEnviar = new JButton("Enviar");
        botaoEnviar.setEnabled(false);
        campoMensagem.setEnabled(false);

        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Digite seu nome:"));
        painelTopo.add(campoNome);
        painelTopo.add(botaoConectar);

        JPanel painelInferior = new JPanel();
        painelInferior.add(campoMensagem);
        painelInferior.add(botaoEnviar);

        setLayout(new BorderLayout());
        add(painelTopo, BorderLayout.NORTH);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JTextArea getAreaTexto() { return areaTexto; }
    public JTextField getCampoMensagem() { return campoMensagem; }
    public JTextField getCampoNome() { return campoNome; }
    public JButton getBotaoConectar() { return botaoConectar; }
    public JButton getBotaoEnviar() { return botaoEnviar; }

    public void adicionarMensagem(String msg) {
        SwingUtilities.invokeLater(() -> areaTexto.append(msg + "\n"));
    }
}