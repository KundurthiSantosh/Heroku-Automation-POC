package webUtilities;

import commonUtilities.ConfigFileReader;
import commonUtilities.Reporter;

public class BasePage {
	public ConfigFileReader configFileReader = new ConfigFileReader();
	public Reporter reporter = new Reporter();
	public int minWait = configFileReader.getMinWait();
	public String authUserName = configFileReader.getAuthUsername();
	public String authPassword = configFileReader.getAuthUsername();
	public String loginUserName = configFileReader.getLoginUsername();
	public String loginPassword = configFileReader.getLoginPassword();
}
