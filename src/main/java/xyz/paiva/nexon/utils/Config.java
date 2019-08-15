package xyz.paiva.nexon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public final class Config {
	private String fileName;
	
	public Config(String fileName) {
		this.fileName = fileName;
	}

	public Boolean exists() {
		File file = new File(String.format("./%s", this.fileName));
		if (file.isFile()) return true;
		else return false;
	}
	
	public void writeDefaultConfig() throws Exception {
		List<String> defaultConfig = Arrays.asList("discord-token:", "prefix:");
		Files.write(Paths.get(this.fileName), 
				defaultConfig,
				StandardCharsets.UTF_8,
				StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}
	
	public String getString(String entity) throws Exception {
		Yaml yaml = new Yaml();
		Map<String, String> map = yaml.load(new FileInputStream(new File(String.format("./%s", this.fileName))));
		return map.get(entity) != null ? map.get(entity) : "";
	}
	
	public Boolean entityExists(String entity) throws Exception {
		if (this.getString(entity).isEmpty()) return false;
		else return true;
	}
}
