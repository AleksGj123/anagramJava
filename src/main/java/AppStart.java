
import filereader.DefaultFileReader;
import app.Application;
import service.ParallelAnagramService;
import service.SimpleAnagramService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppStart {
    public static void main(String[] args) {
        // load configuration
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = AppStart.class.getResourceAsStream("config.properties");
            properties.load(resourceAsStream);
            System.out.println("Loaded config");
        } catch (IOException ioe) {
            System.out.println("Error found: "+ ioe.getMessage());
        }

        // check the mode of the program
        String serviceMode = properties.getProperty("service-mode");

        System.out.println("service-mode is: " + serviceMode);

        DefaultFileReader defaultFileReader = new DefaultFileReader();

        if(serviceMode!= null && serviceMode.equals(Application.ModeParallel)){
            ParallelAnagramService parallelAnagramService = new ParallelAnagramService();
            new  Application(parallelAnagramService, defaultFileReader).runAnagram();
        }
        else if(serviceMode!= null && serviceMode.equals(Application.ModeSimple)) {
            SimpleAnagramService simpleAnagramService = new SimpleAnagramService();
            new Application(simpleAnagramService, defaultFileReader).runAnagram();
        }
        else {
            System.out.println("Mode ["+serviceMode+"] not found");
        }
    }
}
