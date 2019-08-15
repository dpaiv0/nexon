package xyz.paiva.nexon;

import xyz.paiva.nexon.utils.Config;

public class Launcher {
	public Config config;
	
	public static void main(String[] args) {
		Config config = new Config("config.yml");
		try {
			if (config.exists()) {
				// When there's a config.yml
				System.out.println("config.yml file found");
				DiscordClient client = new DiscordClient();
				System.out.println("Starting DiscordClient...");
				client.start();
			} else {
				// When there's not a config.yml
				config.writeDefaultConfig();
				System.out.println("It was created a config.yml file for you to fill in with information about the bot.");
				System.out.println("Fill in with the information needed, then execute \"java -jar Nexon.jar\" again.");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
