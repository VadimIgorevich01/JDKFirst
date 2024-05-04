package org.example.GBrains.Models;

import javax.swing.*;

public interface ClientView {
    void connectToServerWindowPart (ServerView serverWindow);
    void setLabelFieldInfo(String labelFieldInfo);
    void setLabelCommunicationLog(String labelFieldInfo);
}
