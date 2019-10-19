package org.itstep.commands;

import lombok.Data;
import org.itstep.CommandsExecute;
import org.itstep.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

@Data
public class Download implements CommandsExecute {
    Context context;

    public Download(Context context) {
        this.context = context;
    }

    /**
     * Метод загружает файл
     */

    @Override
    public String execute(String... args) {
        if (args.length > 0) {
            try {
                File file = context.getFile();
                String fileName = args[0];
                URLConnection con = new URL(fileName).openConnection();
                con.connect();
                String name;
                if (fileName.substring(fileName.length() - 5).contains(".") && !fileName.endsWith(".")) {
                    name = file.getAbsolutePath() + "\\" + fileName.substring(fileName.lastIndexOf('/') + 1);
                } else {
                    if (fileName.contains("youtube") || fileName.contains("mp4")) {
                        name = file.getAbsolutePath() + "\\" + "fileDownload.mp4";
                    } else if (fileName.contains("mp3")) {
                        name = file.getAbsolutePath() + "\\" + "fileDownload.mp3";
                    } else {
                        name = file.getAbsolutePath() + "\\" + "fileDownload.zip";
                    }
                }
                File fileDownload = new File(name);
                try (ReadableByteChannel in = Channels.newChannel(new URL(fileName).openStream());
                     FileChannel out = new FileOutputStream(fileDownload).getChannel()) {
                    out.transferFrom(in, 0, Long.MAX_VALUE);
                    return "File: " + name + "\nSize: " + con.getContentLength() + "\n";
                }
            } catch (IOException e) {
                System.err.println("Системе не удается найти указанный URL.");
            }
        }
        return "";
    }
}
