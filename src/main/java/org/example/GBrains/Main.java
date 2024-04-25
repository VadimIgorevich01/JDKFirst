package org.example.GBrains;

import org.example.GBrains.Models.Start.ClientLoginWindow;
import org.example.GBrains.Models.Start.DefaultWindow;
import org.example.GBrains.Models.Start.ServerStartWindow;

public class Main {
    //добавить в окно клиента строчку ввода и кнопку отправить, втстроить логику отправки сообщений в общий текстовый файл вывода, доработать и выводить общий текстовый файл вывода в клиентских окошках.
    // Добавить последствие, если сервер неожидано выключился для клиентов, добавить апдейт клиентских окон, если сервер включили
    public static void main(String[] args) {
        ServerStartWindow serverWindow = new ServerStartWindow();
        ClientLoginWindow clientWindow1 = new ClientLoginWindow(serverWindow);
        ClientLoginWindow clientWindow2 = new ClientLoginWindow(serverWindow);
    }
}