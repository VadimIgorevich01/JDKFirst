package org.example.GBrains.Models;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends DefaultWindow{

    JLabel labelIp, labelPort, labelLogin, labelPassword;
    JTextField txtFieldIp, txtFieldPort, txtFieldLogin;
    JPasswordField txtFieldPassword;
    JButton btnLogin;
    public ClientWindow() {
        super();
        setTitle("ClientWindow");

        txtFieldIp = new JTextField();
        txtFieldPort = new JTextField();
        txtFieldLogin = new JTextField();
        txtFieldPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        labelIp = new JLabel("IP");
        labelLogin = new JLabel("Login");
        labelPassword = new JLabel("Password");
        labelPort = new JLabel("Port");

        JPanel panTop = new JPanel(new GridLayout(4,2));
        panTop.add(labelIp);
        panTop.add(txtFieldIp);
        panTop.add(labelPort);
        panTop.add(txtFieldPort);
        panTop.add(labelLogin);
        panTop.add(txtFieldLogin);
        panTop.add(labelPassword);
        panTop.add(txtFieldPassword);

        add(panTop, BorderLayout.CENTER);
        add(btnLogin, BorderLayout.SOUTH);

        panTop.revalidate();
    }
}
