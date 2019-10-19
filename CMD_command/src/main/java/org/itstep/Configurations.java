package org.itstep;


import lombok.Data;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Data
public class Configurations {
    private Properties properties;

    private Configurations() {
        properties = new Properties();
        properties.setProperty("dir", "org.itstep.commands.Dir");
        properties.setProperty("cd", "org.itstep.commands.Cd");
        properties.setProperty("pwd", "org.itstep.commands.Pwd");
        properties.setProperty("cat", "org.itstep.commands.Cat");
        properties.setProperty("find", "org.itstep.commands.Find");
        properties.setProperty("jobs", "org.itstep.commands.Jobs");
        properties.setProperty("download", "org.itstep.commands.Download");
    }

    public static void main(String[] args) {
        Configurations configurations = new Configurations();
        try (FileOutputStream fileOutputStream = new FileOutputStream("settings.properties")) {
            configurations.getProperties().store(fileOutputStream, "configuration file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
