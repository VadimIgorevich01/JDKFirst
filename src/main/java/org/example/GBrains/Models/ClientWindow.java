package org.example.GBrains.Models;

import org.example.GBrains.Service.Client;
import org.example.GBrains.Service.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientWindow extends DefaultWindow {
    Client client;

    JLabel labelIp, labelPort, labelLogin, labelPassword;
    public JLabel labelFieldInfo = new JLabel();
    public JLabel labelCommunicationLog = new JLabel();
    JTextField txtFieldLogin = new JTextField();
    JTextField txtFieldForSending = new JTextField();
    JTextField txtFieldIp, txtFieldPort;
    JPasswordField txtFieldPassword;
    JButton btnLogin = new JButton("Login");
    static int staticCounter = 0;
    private int localCounter = 0;

    JPanel panTop = new JPanel(new GridLayout(4,2));

    public ClientWindow(ServerWindow serverWindow) {
        super();
        client = new Client(serverWindow.server);

        setTitle("ClientWindow");
        ++staticCounter;
        localCounter = staticCounter;

        txtFieldIp = new JTextField();
        txtFieldPort = new JTextField();
        txtFieldPassword = new JPasswordField();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isServerRun) {
                    connectToServerWindowPart(serverWindow);
                } else {
                    labelFieldInfo.setText("Check connection with the server");
                    add(labelFieldInfo, BorderLayout.NORTH);
                    revalidate();
                    System.out.println("Check connection with the server");
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

    private void connectToServerWindowPart(ServerWindow serverWindow) {
        labelFieldInfo.setText("Connected with the server");
        add(labelFieldInfo, BorderLayout.NORTH);
        if (Objects.equals(txtFieldLogin.getText(), "")) {
            client.login = "user" + localCounter;
        } else {
            client.login = txtFieldLogin.getText();
        }
        remove(panTop);

        client.getLoginForServer();
        client.getClientObjectForServer(this);

        remove(btnLogin);

        JPanel panDown = new JPanel(new GridLayout(2,1));
        JButton btnSend = new JButton("Send");


        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isServerRun) {
                    labelFieldInfo.setText("Connected with the server");

                    if (Objects.equals(txtFieldForSending.getText(), "")) {
                        System.out.println("Nothing to send");
                    } else {
                        client.getMsgFrClientForServer(txtFieldForSending.getText());
                    }
                } else {
                    labelFieldInfo.setText("Error, sending failed. Connection with the server is lost");
                    revalidate();
                    System.out.println("Connection with the server is lost");
                }
            }
        });

        panDown.add(txtFieldForSending);
        panDown.add(btnSend);
        add(panDown, BorderLayout.SOUTH);
        add(labelCommunicationLog, BorderLayout.CENTER);
        repaint();
        revalidate();
    }
}
