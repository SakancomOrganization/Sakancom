package io;

import java.io.File;
import java.io.IOException;

public class FileSystemMaker {

    public boolean mkDir(File file) {
        return file.mkdir();
    }

    public boolean mkFile(File file) throws IOException {
        return file.createNewFile();
    }
}
