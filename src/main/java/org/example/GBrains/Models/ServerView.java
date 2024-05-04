package org.example.GBrains.Models;

import org.example.GBrains.Service.Server;

public interface ServerView {
    void addBtn();
    void setLabelEntireLogServerWindow(String strEntireLogComplete);

    void revalidate();

    Server getServer();

    public boolean isServerRun();
}
