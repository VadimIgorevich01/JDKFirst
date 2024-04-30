package org.example.GBrains.Service;

import org.example.GBrains.Models.ClientGUI;

public class Client {
    Server server;
    public String login = "";

    public Client(Server server) {
        this.server = server;
    }

    public void getLoginForServer() {
        server.connectUserToServer(login);
    }


    public void getClientObjectForServer(ClientGUI clientGUI) {
        server.getClientObject(clientGUI);
    }

    public void getMsgFrClientForServer(String clientMsg) {
        server.getMsgFrClient(clientMsg, login);
    }
}
