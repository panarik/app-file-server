package com.github.panarik.config;

public class ConfigFactory {

    public Config getConfig() {
        if (TerminalConfig.getInstance().getPath() != null) {
            return TerminalConfig.getInstance();
        } else return new PropertyConfig();
    }

}
