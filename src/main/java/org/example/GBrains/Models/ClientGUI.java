package org.example.GBrains.Models;

import org.example.GBrains.Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ClientGUI extends DefaultGUI implements ClientView {
    Client client;

    JLabel labelIp, labelPort, labelLogin, labelPassword;
    public JLabel labelFieldInfo;

    public JLabel labelCommunicationLog;
    JTextField txtFieldLogin;
    JTextField txtFieldForSending;
    JTextField txtFieldIp, txtFieldPort;
    JPasswordField txtFieldPassword;
    JButton btnLogin;
    static int staticCounter = 0;
    private int localCounter;
    ServerView serverWindow;

    JPanel panTop;

    public ClientGUI(ServerView serverView) {
        super();

        txtFieldLogin = new JTextField();
        labelFieldInfo = new JLabel();
        labelCommunicationLog = new JLabel();
        txtFieldForSending = new JTextField();
        btnLogin = new JButton("Login");
        panTop = new JPanel(new GridLayout(4,2));

        if (serverView instanceof ServerGUI) {
            serverWindow = (ServerGUI) serverView;
        }
        client = new Client(serverWindow.getServer());

        setTitle("ClientWindow");
        ++staticCounter;
        localCounter = staticCounter;

        txtFieldIp = new JTextField();
        txtFieldPort = new JTextField();
        txtFieldPassword = new JPasswordField();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (serverWindow.isServerRun()) {
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

    public void setLabelFieldInfo(String fieldInfo) {
        labelFieldInfo.setText(fieldInfo);
    }

    public void setLabelCommunicationLog(String communicationLog) {
        labelCommunicationLog.setText(communicationLog);
    }

    @Override
    public void connectToServerWindowPart(ServerView serverWindow) {
        labelFieldInfo.setText("Connected with the server");
        add(labelFieldInfo, BorderLayout.NORTH);

        txtFieldForSending.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    sendMsgToServer();
                }
            }
        });

        if (Objects.equals(txtFieldLogin.getText(), "")) {
            client.login = "user" + localCounter;
        } else {
            client.login = txtFieldLogin.getText();
        }
        remove(panTop);
        remove(btnLogin);
        JPanel panDown = new JPanel(new GridLayout(2,1));
        JButton btnSend = new JButton("Send");

        client.getLoginForServer();
        client.getClientObjectForServer(this);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsgToServer();
            }
        });

        panDown.add(txtFieldForSending);
        panDown.add(btnSend);
        add(panDown, BorderLayout.SOUTH);
        add(labelCommunicationLog, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    private void sendMsgToServer() {
        if (serverWindow.isServerRun()) {
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
}
