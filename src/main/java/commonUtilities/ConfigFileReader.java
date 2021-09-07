package commonUtilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src/test/resources/configuration.properties";


    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public int getMinWait() {
        String minWait = properties.getProperty("min-wait");
        if (minWait != null) return Integer.parseInt(minWait);
        else throw new RuntimeException("Minimum wait is not specified in the Configuration.properties file.");
    }

    public int getMaxWait() {
        String maxWait = properties.getProperty("max-wait");
        if (maxWait != null) return Integer.parseInt(maxWait);
        else throw new RuntimeException("Maximum wait is not specified in the Configuration.properties file.");
    }

    public String getApiBaseUrl() {
        String url = properties.getProperty("api-base-url");
        if (url != null) return url;
        else throw new RuntimeException("API URL not specified in the Configuration.properties file.");
    }

    public String getWebBaseUrl() {
        String url = properties.getProperty("web-base-url");
        if (url != null) return url;
        else throw new RuntimeException("API URL not specified in the Configuration.properties file.");
    }

    public String getTestDataPath() {
        String testDataPath = properties.getProperty("test-data-path");
        if (testDataPath != null) return testDataPath;
        else throw new RuntimeException("test data path not specified in the Configuration.properties file.");
    }

    public String getAPITestDataFile() {
        String testDataFile = properties.getProperty("api-test-data-file");
        if (testDataFile != null) return testDataFile;
        else throw new RuntimeException("API test data file is not specified in the Configuration.properties file.");
    }

    public String getReportsPath() {
        String reportsPath = properties.getProperty("reports-path");
        if (reportsPath != null) return reportsPath;
        else throw new RuntimeException("Reports path not specified in the Configuration.properties file.");
    }

    public String getReportsFile() {
        String reportsFile = properties.getProperty("reports-file");
        if (reportsFile != null) return reportsFile;
        else throw new RuntimeException("Reports file name not specified in the Configuration.properties file.");
    }

    public String getAuthUsername() {
        String username = properties.getProperty("auth-username");
        if (username != null) return username;
        else
            throw new RuntimeException("Username not found in the Configuration.properties file.");
    }

    public String getAuthPassword() {
        String password = properties.getProperty("auth-password");
        if (password != null) return password;
        else
            throw new RuntimeException("Password not found in the Configuration.properties file.");
    }

    public String getLoginUsername() {
        String username = properties.getProperty("login-username");
        if (username != null) return username;
        else
            throw new RuntimeException("Username not found in the Configuration.properties file.");
    }

    public String getLoginPassword() {
        String password = properties.getProperty("login-password");
        if (password != null) return password;
        else
            throw new RuntimeException("Password not found in the Configuration.properties file.");
    }

    
    public String getProjectName() {
        String projectName = properties.getProperty("project-name");
        if (projectName != null) return projectName;
        else
            throw new RuntimeException("Project Name not found in the Configuration.properties file.");
    }

    public String getReleaseName() {
        String releaseName = properties.getProperty("release-name");
        if (releaseName != null) return releaseName;
        else
            throw new RuntimeException("Release not found in the Configuration.properties file.");
    }

    public String getEnvironment() {
        String environment = properties.getProperty("environment");
        if (environment != null) return environment;
        else
            throw new RuntimeException("Environment not found in the Configuration.properties file.");
    }
}