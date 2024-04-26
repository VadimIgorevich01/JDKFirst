package org.example.GBrains.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Server extends DefaultWindow {
    JButton btnConnect, btnDisconnect;
    JLabel labelTopInfo = new JLabel();
    JLabel labelEntireLog = new JLabel();

    String strEntireLog = "";
    String strCommunicationLog = "";
    String strCommunicationLogReady;

    JPanel panBottom = new JPanel(new GridLayout(1,2));


    public Server() {
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
        add(labelEntireLog, BorderLayout.CENTER);
    }

    public void connectUserToServer(String userName) {
        String newLine = userName + " connected";
        strEntireLog += "<br>" + newLine;
        String textBegining = "<html>";
        String textEnding = "</html>";
        String strMainInfoComplete = textBegining + strEntireLog + textEnding;
        labelEntireLog.setText(strMainInfoComplete);
        revalidate();
    }


    public void receiveMsgFrClient(String msgForSending, String userNameWhoSent) {
        String newLine = userNameWhoSent + ": " + "\"" + msgForSending + "\"";
        String textBegining = "<html>";
        String textEnding = "</html>";

        strCommunicationLog += "<br>" + newLine;
        strCommunicationLogReady = textBegining + strCommunicationLog + textEnding;

        strEntireLog += "<br>" + newLine;
        String strEntireLogReady = textBegining + strEntireLog + textEnding;
        labelEntireLog.setText(strEntireLogReady);

        writeEntireLogToFile(strEntireLogReady);

        revalidate();
    }

    private void writeEntireLogToFile(String strEntireLogReady) {

        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/java/org/example/GBrains/LogFile.html");
            writer.write(strEntireLogReady);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
            e.printStackTrace();
        }
    }

    public String sendCommunicationLogToClient() {
        return strCommunicationLogReady;
    }
}
