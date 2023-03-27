package com.github.panarik.config;

public final class ConfigHolder {

    private static ConfigHolder configHolder;
    private Config serverConfig;

    private ConfigHolder() {
    }

    public static ConfigHolder instance() {
        if (configHolder == null) {
            configHolder = new ConfigHolder();
        }
        return configHolder;
    }

    public void setServerConfig(Config serverConfig) {
        this.serverConfig = serverConfig;
    }

    public Config getConfig() {
        return serverConfig;
    }
}
