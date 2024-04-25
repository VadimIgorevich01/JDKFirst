package org.example.GBrains.Models.Start;

import org.example.GBrains.Models.Start.DefaultWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientLoginWindow extends DefaultWindow {

    JLabel labelIp, labelPort, labelLogin, labelPassword;
    JTextField txtFieldLogin = new JTextField();
    JLabel labelFieldInfo = new JLabel();
    JTextField txtFieldIp, txtFieldPort;
    JPasswordField txtFieldPassword;
    JButton btnLogin = new JButton("Login");
    String login = "";
    static int staticCounter = 0;
    private int localCounter = 0;

    JPanel panTop = new JPanel(new GridLayout(4,2));

    public ClientLoginWindow(ServerStartWindow server) {
        super();
        setTitle("ClientWindow");
        ++staticCounter;
        localCounter = staticCounter;

        txtFieldIp = new JTextField();
        txtFieldPort = new JTextField();
        txtFieldPassword = new JPasswordField();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerRun) {
                    runClient(server);
                } else {
                    labelFieldInfo.setText("Connection with the server is lost");
                    add(labelFieldInfo, BorderLayout.NORTH);
                    revalidate();
                    System.out.println("Connection with the server is lost");
                }
            }
        });

        labelIp = new JLabel("IP");
        labelLogin = new JLabel("Login");
        labelPassword = new JLabel("Password");
        labelPort = new JLabel("Port");


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

        revalidate();
    }

    private void runClient(ServerStartWindow server) {
        panTop.setVisible(false);
        labelFieldInfo.setText("Connected with the server");
        add(labelFieldInfo, BorderLayout.NORTH);
        if (Objects.equals(txtFieldLogin.getText(), "")) {
            login = "user" + localCounter;
        } else {
            login = txtFieldLogin.getText();
        }
        server.connectUserToServer(login);
        remove(btnLogin);
        repaint();
        revalidate();
    }
}
