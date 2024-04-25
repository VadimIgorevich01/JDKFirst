package org.example.GBrains.Models.Start;


import javax.swing.*;

public abstract class DefaultWindow extends JFrame {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 507;

    boolean isServerRun = false;



    public DefaultWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
