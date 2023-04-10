package com.github.panarik.data;

import com.github.panarik.config.ConfigHolder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDataProvider implements DataProvider {

    public final String ROOT;

    public FileDataProvider() {
        this.ROOT = ConfigHolder.instance().getConfig().getPath();
    }

    /**
     * Get data from file source.
     *
     * @param path path to file.
     * @return data
     */
    public InputStreamReader getData(String path) {
        try {
            return new InputStreamReader(new FileInputStream(ROOT + path));
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
        Path filePath = Paths.get(ROOT, path.toString());
        return Files.exists(filePath);
    }


}
