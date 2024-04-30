package org.example.GBrains.Service;

import org.example.GBrains.Models.ClientWindow;

public class Client {
    Server server;
    public String login = "";

    public Client(Server server) {
        this.server = server;
    }

    public void getLoginForServer() {
        server.connectUserToServer(login);
    }


    public void getClientObjectForServer(ClientWindow clientWindow) {
        server.getClientObject(clientWindow);
    }

    public void getMsgFrClientForServer(String clientMsg) {
        server.getMsgFrClient(clientMsg, login);
    }
}
