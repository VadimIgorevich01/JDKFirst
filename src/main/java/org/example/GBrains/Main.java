package org.example.GBrains;

import org.example.GBrains.Models.ClientWindow;
import org.example.GBrains.Models.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();

        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}