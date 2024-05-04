package org.example.GBrains.Service;

import org.example.GBrains.Models.ClientGUI;
import org.example.GBrains.Models.ClientView;
import org.example.GBrains.Models.ServerGUI;
import org.example.GBrains.Models.ServerView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    List<ClientView> clientGUIList;

    ServerView serverWindow;
    String strEntireLog;
    String strCommunicationLog;
    String strCommunicationLogReady;

    public Server(ServerGUI serverWindow) {
        this.serverWindow = serverWindow;
        clientGUIList = new ArrayList<>();
        strEntireLog = "";
        strCommunicationLog = "";
    }

    public void setFieldInfoForAllClients(String connectionStatusMsg) {
        for (int i = 0; i < clientGUIList.size(); i++) {
            clientGUIList.get(i).setLabelFieldInfo(connectionStatusMsg);
        }
    }
    public void connectUserToServer(String userName) {
        String newLine = userName + " connected";
        System.out.println(newLine);
        System.out.println(userName);
        strEntireLog += "<br>" + newLine;
        String textBegining = "<html>";
        String textEnding = "</html>";
        String strEntireLogComplete = textBegining + strEntireLog + textEnding;
        serverWindow.setLabelEntireLogServerWindow(strEntireLogComplete);
        serverWindow.revalidate();
    }

    public void getClientObject(ClientView client) {
        clientGUIList.add(client);
        for (int i = 0; i < clientGUIList.size(); i++) {
            clientGUIList.get(i).setLabelCommunicationLog(strCommunicationLogReady);
        }
    }

    public void getMsgFrClient(String msgForSending, String login) {
        String newLine = login + ": " + "\"" + msgForSending + "\"";
        String textBegining = "<html>";
        String textEnding = "</html>";

        strCommunicationLog += "<br>" + newLine;
        strCommunicationLogReady = textBegining + strCommunicationLog + textEnding;
        for (int i = 0; i < clientGUIList.size(); i++) {
            clientGUIList.get(i).setLabelCommunicationLog(strCommunicationLogReady);
        }

        strEntireLog += "<br>" + newLine;
        String strEntireLogReady = textBegining + strEntireLog + textEnding;
        serverWindow.setLabelEntireLogServerWindow(strEntireLogReady);
        serverWindow.revalidate();

        writeEntireLogToFile(strEntireLogReady);
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
}
