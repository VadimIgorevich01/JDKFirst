package org.example.GBrains.Models;


import org.example.GBrains.Service.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ServerWindow extends DefaultWindow {

    JButton btnConnect, btnDisconnect;
    JLabel labelTopInfo = new JLabel();
    public JLabel labelEntireLogServerWindow = new JLabel();
    JPanel panBottom = new JPanel(new GridLayout(1,2));
    Server server;


    public ServerWindow() {
        super();
        server = new Server(this);
        setTitle("Launch Server");
        btnConnect = new JButton("Activate");
        btnDisconnect = new JButton("Disable");
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Connected");
                isServerRun = true;
                labelTopInfo.setText("Server is online");
                add(labelTopInfo, BorderLayout.NORTH);
                panBottom.remove(btnConnect);
                panBottom.add(btnDisconnect);

                server.setFieldInfoForAllClients("Connected with the server");

                revalidate();
            }
        });
        btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Disconnected");
                isServerRun = false;
                labelTopInfo.setText("Server is offline");
                add(labelTopInfo, BorderLayout.NORTH);
                panBottom.remove(btnDisconnect);
                panBottom.add(btnConnect);

                server.setFieldInfoForAllClients("Connection with the server is lost");

                revalidate();
            }
        });
        panBottom.add(btnConnect);
        panBottom.add(btnDisconnect);
        add(panBottom, BorderLayout.SOUTH);
        add(labelEntireLogServerWindow, BorderLayout.CENTER);
    }
}
