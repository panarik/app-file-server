package com.github.panarik.config;

import java.util.Arrays;
import java.util.List;

public class TerminalConfig implements Config {

    private List<String> args;
    private static TerminalConfig terminalConfig;

    private TerminalConfig() {
    }

    public static TerminalConfig getInstance() {
        if (terminalConfig == null) {
            terminalConfig = new TerminalConfig();
        }
        return terminalConfig;
    }

    public void setArgs(String[] args) {
        this.args = Arrays.asList(args);
    }

    public String getPath() {
        if (args.size() > 0) return args.get(0);
        else return null;
    }

    public int getPort() {
        return Integer.parseInt(args.get(1));
    }
}
