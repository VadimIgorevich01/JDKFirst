package org.example.GBrains;

import org.example.GBrains.Models.ClientGUI;
import org.example.GBrains.Models.ClientView;
import org.example.GBrains.Models.ServerGUI;
import org.example.GBrains.Models.ServerView;

public class Main {
    public static void main(String[] args) {

        ServerView serverWindow = new ServerGUI();
        ClientView clientWindow1 = new ClientGUI(serverWindow);
        ClientView clientWindow2 = new ClientGUI(serverWindow);
    }
}