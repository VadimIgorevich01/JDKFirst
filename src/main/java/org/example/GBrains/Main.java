package org.example.GBrains;

import org.example.GBrains.GUI.Client;
import org.example.GBrains.GUI.Server;

public class Main {
    public static void main(String[] args) {
        Server serverWindow = new Server();
        Client clientWindow1 = new Client(serverWindow);
        Client clientWindow2 = new Client(serverWindow);
    }
}