package com.github.panarik.data;

import java.io.InputStreamReader;
import java.nio.file.Path;

public interface DataProvider {

    InputStreamReader getData(Path path);

}
