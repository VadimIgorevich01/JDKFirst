package org.example.GBrains.Service;

import org.example.GBrains.Models.ClientGUI;
import org.example.GBrains.Models.ServerGUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public Server(ServerGUI serverWindow) {
        this.serverWindow = serverWindow;
        clientGUIList = new ArrayList<>();
    }

    List<ClientGUI> clientGUIList;
    ServerGUI serverWindow;
    String strEntireLog = "";
    String strCommunicationLog = "";
    String strCommunicationLogReady;

    public void setFieldInfoForAllClients(String connectionStatusMsg) {
        for (int i = 0; i < clientGUIList.size(); i++) {
            clientGUIList.get(i).labelFieldInfo.setText(connectionStatusMsg);
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
        serverWindow.labelEntireLogServerWindow.setText(strEntireLogComplete);
        serverWindow.revalidate();
    }

    public void getClientObject(ClientGUI client) {
        clientGUIList.add(client);
        for (int i = 0; i < clientGUIList.size(); i++) {
            clientGUIList.get(i).labelCommunicationLog.setText(strCommunicationLogReady);
        }
    }

    public void getMsgFrClient(String msgForSending, String login) {
        String newLine = login + ": " + "\"" + msgForSending + "\"";
        String textBegining = "<html>";
        String textEnding = "</html>";

        strCommunicationLog += "<br>" + newLine;
        strCommunicationLogReady = textBegining + strCommunicationLog + textEnding;
        for (int i = 0; i < clientGUIList.size(); i++) {
            clientGUIList.get(i).labelCommunicationLog.setText(strCommunicationLogReady);
        }

        strEntireLog += "<br>" + newLine;
        String strEntireLogReady = textBegining + strEntireLog + textEnding;
        serverWindow.labelEntireLogServerWindow.setText(strEntireLogReady);
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
