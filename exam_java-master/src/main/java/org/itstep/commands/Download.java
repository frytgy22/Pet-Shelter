package org.itstep.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class Download implements Command {
    @Override
    public String execute(String... arg) {
        if (arg.length > 1) {
            String url = arg[0];
            File fileDirectory = new File(arg[1]);
            try {
                URLConnection con = new URL(url).openConnection();
                con.connect();
                String name;
                if (url.substring(url.length() - 5).contains(".") && !url.endsWith(".")) {
                    name = fileDirectory.getAbsolutePath() + "\\" + url.substring(url.lastIndexOf('/') + 1);
                } else {
                    if (url.contains("youtube") || url.contains("mp4")) {
                        name = fileDirectory.getAbsolutePath() + "\\" + "fileDownload.mp4";
                    } else if (url.contains("mp3")) {
                        name = fileDirectory.getAbsolutePath() + "\\" + "fileDownload.mp3";
                    } else {
                        name = fileDirectory.getAbsolutePath() + "\\" + "fileDownload.zip";
                    }
                }
                File file = new File(name);
                try (ReadableByteChannel in = Channels.newChannel(new URL(url).openStream());
                     FileChannel out = new FileOutputStream(file).getChannel()) {
                    out.transferFrom(in, 0, Long.MAX_VALUE);
                    return "File: " + name + "\nSize: " + con.getContentLength() + "\n";
                }
            } catch (IOException e) {
                return "Системе не удается найти указанный URL.";
            }
        }
        return "Ошибка ввода";
    }
}

