package org.example.GBrains;

import org.example.GBrains.Models.ClientWindow;
import org.example.GBrains.Models.DefaultWindow;
import org.example.GBrains.Models.ServerWindow;

public class Main {
    public static void main(String[] args) {
        DefaultWindow serverWindow = new ServerWindow();
        DefaultWindow clientWindow1 = new ClientWindow();
        DefaultWindow clientWindow2 = new ClientWindow();
    }
}