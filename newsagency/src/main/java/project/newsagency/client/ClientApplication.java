package project.newsagency.client;

import project.newsagency.client.controller.SimpleClientController;
import project.newsagency.client.entities.SimpleClient;
import project.newsagency.client.view.SimpleClientView;

public class ClientApplication {
    public static void main(String[] args) throws Exception {
        SimpleClientController simpleClientController = new SimpleClientController(new SimpleClientView(), new SimpleClient());
    }
}
