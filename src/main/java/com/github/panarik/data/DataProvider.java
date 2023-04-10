package com.github.panarik.data;

import java.io.InputStreamReader;

/**
 * Provides all types of data for create server response.
 */
public interface DataProvider {

    InputStreamReader getData(String path);

}
