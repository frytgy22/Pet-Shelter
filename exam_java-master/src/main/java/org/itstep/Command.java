package org.itstep;

import java.io.File;
import java.io.IOException;

public abstract class Command {
    public void dir(File file) {
    }

    public File cd(String wayDirectory, File existFile) {
        return null;
    }

    public void pwd(File file) {
    }

    public void cat(String fileName) throws IOException {
    }

    public void download(String url, File fileDirectory) {
    }

    public void find(String filenameForSearch) {
    }
}
