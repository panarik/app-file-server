package com.github.panarik.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataProvider implements DataProvider {

    public final String ROOT = "www";

    /**
     * Get data from file source.
     *
     * @param path path to file.
     * @return data
     */
    public InputStreamReader getData(Path path) {
        try {
            return new InputStreamReader(new FileInputStream(path.toFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStreamReader getData(String pathToFile) {
        try {
            return new InputStreamReader(new FileInputStream(ROOT + pathToFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify current file is exist or not.
     *
     * @param path path to file.
     * @return
     */
    public boolean fileIsExist(Path path) {
        return Files.exists(path);
    }


}
