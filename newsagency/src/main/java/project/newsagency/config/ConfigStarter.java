package project.newsagency.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigStarter {

    private ConfigStarter() {
    }

    public static int readConfig(String file) throws FileNotFoundException {
        int port = -1;
        File file1 = new File(file);
        Scanner sc = new Scanner(file1);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("port")) {
                String[] result = line.split("\\s");
                port = Integer.parseInt(result[1]);
            }
        }
        return port;
    }
}
