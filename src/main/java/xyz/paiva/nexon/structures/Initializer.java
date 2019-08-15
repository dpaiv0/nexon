package xyz.paiva.nexon.structures;

import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.utils.Config;

public class Initializer {
	public String name;
	public Config config = new Config("config.yml");
	public DiscordClient client;

	public Initializer (DiscordClient discordClient) {
		this.client = discordClient;
		this.name = "";
	}
	
	public void start() throws Exception {}
}
