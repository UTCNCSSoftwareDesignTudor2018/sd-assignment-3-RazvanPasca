package project.newsagency.client;

import project.newsagency.client.controller.ClientController;
import project.newsagency.client.entities.Client;
import project.newsagency.client.view.ClientView;

public class ClientApplication {
    public static void main(String[] args) throws Exception {
        ClientController clientController = new ClientController(new ClientView(), new Client());
    }
}
