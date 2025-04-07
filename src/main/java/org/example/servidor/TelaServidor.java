package org.example.servidor;

import javax.swing.*;
import java.awt.*;

public class TelaServidor extends JFrame {
    private JTextArea areaTexto;

    public TelaServidor() {
        setTitle("Servidor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        setVisible(true);
    }

    public void exibirMensagem(String mensagem) {
        SwingUtilities.invokeLater(() -> areaTexto.append(mensagem + "\n"));
    }
}
