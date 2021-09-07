package commonUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class YamlReader {
	ConfigFileReader config = new ConfigFileReader();
	private static final Logger logger = LogManager.getLogger(YamlReader.class);

	@SuppressWarnings("unchecked")
	public Map<String, Map<String, Object>> loadYaml(String userCode, String file) {
		InputStream input;
		Map<String, Map<String, Map<String, Object>>> data = null;
		try {
			input = new FileInputStream(new File(System.getProperty("user.dir") + config.getTestDataPath() + file));
			Yaml yaml = new Yaml();
			data = (Map<String, Map<String, Map<String, Object>>>) yaml.load(input);
			logger.info("loaded " + file + " successfully!");
			input.close();
		} catch (FileNotFoundException e) {
			logger.error(file + " was not found in test data folder!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (data.get(userCode) == null) {
			logger.error(userCode + " data not found in " + file);
			return null;
		}
		return data.get(userCode);
	}
}
