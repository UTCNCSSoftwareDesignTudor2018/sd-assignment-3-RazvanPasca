package project.newsagency.client.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import project.newsagency.client.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static project.newsagency.config.ConfigStarter.readConfig;
import static project.newsagency.config.Constants.FILE_LOCATION;

public class SimpleClient {
    private BufferedReader in;
    private PrintWriter clientToServerOut;


    public SimpleClient() throws IOException {

        int port = readConfig(FILE_LOCATION);
        connectToServer(port);
//        dataField.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                clientToServerOut.println(dataField.getText());
//                String response;
//                try {
//                    response = in.readLine();
//                    if (response == null || response.equals("")) {
//                        System.exit(0);
//                    }
//                } catch (IOException ex) {
//                    response = "Error: " + ex;
//                }
//                messageArea.append(response + "\n");
//                dataField.selectAll();
//            }
//        });
    }


    public void connectToServer(int port) throws IOException {


        // Make connection and initialize streams
        Socket socket = new Socket("127.0.0.1", port);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        clientToServerOut = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server
        for (int i = 0; i < 3; i++) {
            System.out.println(in.readLine() + "\n");
        }
    }

    public void sendCommand(Command command) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonString = objectMapper.writeValueAsString(command);
        clientToServerOut.println(jsonString);
    }

}
