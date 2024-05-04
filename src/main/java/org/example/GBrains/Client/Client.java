package org.example.GBrains.Client;

import org.example.GBrains.Models.ClientGUI;
import org.example.GBrains.Models.ClientView;
import org.example.GBrains.Models.ServerView;
import org.example.GBrains.Service.Server;

public class Client {
    Server server;
    public String login;

    public Client(Server server) {
        this.server = server;
        login = "";
    }

    public void getLoginForServer() {
        server.connectUserToServer(login);
    }


    public void getClientObjectForServer(ClientView clientGUI) {
        server.getClientObject(clientGUI);
    }

    public void getMsgFrClientForServer(String clientMsg) {
        server.getMsgFrClient(clientMsg, login);
    }
}
