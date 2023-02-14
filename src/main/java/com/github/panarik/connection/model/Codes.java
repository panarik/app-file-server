package com.github.panarik.connection.model;

import java.util.HashMap;
import java.util.Map;

public class Codes {

    private final Map<Integer, String> codes = new HashMap<>();

    public Codes() {
        codes.put(200, "OK");
        codes.put(404, "NOT_FOUND");
    }

    public String get(int key) {
        return codes.get(key);
    }

}
