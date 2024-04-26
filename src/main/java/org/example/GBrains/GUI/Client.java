package org.example.GBrains.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Client extends DefaultWindow {

    JLabel labelIp, labelPort, labelLogin, labelPassword;
    JLabel labelFieldInfo = new JLabel();
    JLabel labelCommunicationLog = new JLabel();
    JTextField txtFieldLogin = new JTextField();
    JTextField txtFieldForSending = new JTextField();
    JTextField txtFieldIp, txtFieldPort;
    JPasswordField txtFieldPassword;
    JButton btnLogin = new JButton("Login");
    String login = "";
    String strCommunicationLog;
    static int staticCounter = 0;
    private int localCounter = 0;

    JPanel panTop = new JPanel(new GridLayout(4,2));

    public Client(Server server) {
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
                    connectToServer(server);

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

    private void connectToServer(Server server) {
        labelFieldInfo.setText("Connected with the server");
        add(labelFieldInfo, BorderLayout.NORTH);
        if (Objects.equals(txtFieldLogin.getText(), "")) {
            login = "user" + localCounter;
        } else {
            login = txtFieldLogin.getText();
        }
        //panTop.setVisible(false);
        remove(panTop);
        server.connectUserToServer(login);
        remove(btnLogin);

        JPanel panDown = new JPanel(new GridLayout(2,1));
        JButton btnSend = new JButton("Send");


        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerRun) {
                    labelFieldInfo.setText("Connected with the server");
                    if (Objects.equals(txtFieldForSending.getText(), "")) {
                        System.out.println("Nothing to send");
                    } else {
                        sendMsgToServer(server);
                        strCommunicationLog = server.sendCommunicationLogToClient();
                        labelCommunicationLog.setText(strCommunicationLog);

                    }
                } else {
                    labelFieldInfo.setText("Error, sending failed. Connection with the server is lost");
                    //add(labelFieldInfo, BorderLayout.NORTH);
                    revalidate();
                    System.out.println("Connection with the server is lost");
                }
            }
        });
        strCommunicationLog = server.sendCommunicationLogToClient();
        labelCommunicationLog.setText(strCommunicationLog);
        JButton btnUpdate = new JButton("UpdateWindow");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerRun) {
                    labelFieldInfo.setText("Connected with the server");
                } else {
                    labelFieldInfo.setText("Error, sending failed. Connection with the server is lost");
                }
                strCommunicationLog = server.sendCommunicationLogToClient();
                labelCommunicationLog.setText(strCommunicationLog);
            }
        });
        add(btnUpdate, BorderLayout.EAST);
        panDown.add(txtFieldForSending);
        panDown.add(btnSend);
        add(panDown, BorderLayout.SOUTH);
        add(labelCommunicationLog, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    private void sendMsgToServer(Server server) {
        String msgForSending = txtFieldForSending.getText();
        server.receiveMsgFrClient (msgForSending, login);
    }

}
