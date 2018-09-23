package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Config INSTANCE;
    private static final String PROPS = "./config/resumes.properties";
    private final Properties props;
    private String storageDir;

    public static Config getInstance() {
        if (INSTANCE == null) INSTANCE = new Config();
        return INSTANCE;
    }

    private Config() {
        try (InputStream in = new FileInputStream(PROPS)) {
            props = new Properties();
            props.load(in);
            storageDir = props.getProperty("storage.dir");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

    public String getStorageDir() {
        return storageDir;
    }
}
