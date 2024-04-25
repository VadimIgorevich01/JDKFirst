package org.example.GBrains.Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends DefaultWindow{
    JButton btnConnect, btnDisconnect;

    public ServerWindow() {
        super();
        setTitle("ServerWindow");
        btnConnect = new JButton("Connect");
        btnDisconnect = new JButton("Disconnect");
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Connected");
            }
        });
        btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Disconnected");
            }
        });
        JPanel panBottom = new JPanel(new GridLayout(1,2));
        panBottom.add(btnConnect);
        panBottom.add(btnDisconnect);
        add(panBottom, BorderLayout.SOUTH);
    }
}
