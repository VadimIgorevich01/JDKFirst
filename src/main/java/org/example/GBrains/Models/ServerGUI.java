package org.example.GBrains.Models;


import org.example.GBrains.Service.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends DefaultGUI implements ServerView {

    JButton btnConnect, btnDisconnect;
    JLabel labelTopInfo;
    public JLabel labelEntireLogServerWindow;
    JPanel panBottom;
    Server server;


    public ServerGUI() {
        super();

        labelTopInfo = new JLabel();
        labelEntireLogServerWindow = new JLabel();
        panBottom = new JPanel(new GridLayout(1,2));


        server = new Server(this);
        setTitle("Launch Server");
        addBtn();
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setFieldInfoForAllClients("Connected with the server");

                System.out.println("Connected");
                isServerRun = true;
                labelTopInfo.setText("Server is online");
                add(labelTopInfo, BorderLayout.NORTH);
                panBottom.remove(btnConnect);
                panBottom.add(btnDisconnect);
                revalidate();
            }
        });
        btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setFieldInfoForAllClients("Connection with the server is lost");

                System.out.println("Disconnected");
                isServerRun = false;
                labelTopInfo.setText("Server is offline");
                add(labelTopInfo, BorderLayout.NORTH);
                panBottom.remove(btnDisconnect);
                panBottom.add(btnConnect);
                revalidate();
            }
        });
        panBottom.add(btnConnect);
        panBottom.add(btnDisconnect);
        add(panBottom, BorderLayout.SOUTH);
        add(labelEntireLogServerWindow, BorderLayout.CENTER);
    }

    @Override
    public void addBtn() {
        btnConnect = new JButton("Activate");
        btnDisconnect = new JButton("Disable");
    }

    @Override
    public void setLabelEntireLogServerWindow(String strEntireLogComplete) {
        labelEntireLogServerWindow.setText(strEntireLogComplete);
    }

    @Override
    public Server getServer() {
        return server;
    }
}
