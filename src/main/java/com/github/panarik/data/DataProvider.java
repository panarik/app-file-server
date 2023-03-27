package com.github.panarik.data;

import java.io.InputStreamReader;
import java.nio.file.Path;

/**
 * Provides all types of data for create server response.
 */
public interface DataProvider {

    InputStreamReader getData(Path path);

}
