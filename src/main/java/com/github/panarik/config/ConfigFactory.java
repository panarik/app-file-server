package com.github.panarik.config;

public final class ConfigFactory {

    public static Config getConfig() {
        if (TerminalConfig.getInstance().getPath() != null) {
            return TerminalConfig.getInstance();
        } else return new PropertyConfig();
    }

}
