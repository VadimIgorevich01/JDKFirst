package org.example.GBrains.Models.Start;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerStartWindow extends DefaultWindow {
    JButton btnConnect, btnDisconnect;
    JLabel labelTopInfo = new JLabel();
    JLabel labelMainInfo = new JLabel();

    String strMainInfo = "";

    JPanel panBottom = new JPanel(new GridLayout(1,2));

//    JScrollPane scroller = new JScrollPane(labelMainInfo,
//            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    public ServerStartWindow() {
        super();
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

                revalidate();
            }
        });
        panBottom.add(btnConnect);
        panBottom.add(btnDisconnect);
        add(panBottom, BorderLayout.SOUTH);
        add(labelMainInfo, BorderLayout.CENTER);
    }

    public void connectUserToServer(String userName) {
        String newLine = userName + " connected";
        strMainInfo += "<br>" + newLine;
        String textBegining = "<html>";
        String textEnding = "</html>";
        String strMainInfoComplete = textBegining + strMainInfo + textEnding;
        labelMainInfo.setText(strMainInfoComplete);
        revalidate();
    }



}
