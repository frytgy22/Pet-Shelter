package org.itstep;

import lombok.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Data
public class Configurations {
    private Properties properties;

    public Configurations() {
        properties = new Properties();
        properties.setProperty("dir", "org.itstep.CommandDir");
        properties.setProperty("cd", "org.itstep.CommandCd");
        properties.setProperty("pwd", "org.itstep.CommandPwd");
        properties.setProperty("cat", "org.itstep.CommandCat");
        properties.setProperty("find", "org.itstep.CommandFind");
        properties.setProperty("download", "org.itstep.CommandDownload");
        properties.setProperty("jobs", "org.itstep.CommandJobs");
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
