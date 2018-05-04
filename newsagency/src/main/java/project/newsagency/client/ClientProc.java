package project.newsagency.client;

import project.newsagency.client.entities.SimpleClient;

import static project.newsagency.config.ConfigStarter.readConfig;
import static project.newsagency.config.Constants.FILE_LOCATION;

public class ClientProc {
    public static void main(String[] args) throws Exception {
        SimpleClient client = new SimpleClient();
        int port = readConfig(FILE_LOCATION);
        client.connectToServer(port);
    }

}
