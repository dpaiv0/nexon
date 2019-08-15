package xyz.paiva.nexon.initializers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.listeners.MessageListener;
import xyz.paiva.nexon.listeners.ReadyListener;
import xyz.paiva.nexon.structures.Initializer;

public class DiscordInitializer extends Initializer {
	public DiscordInitializer(DiscordClient discordClient) {
		super(discordClient);
		this.name = "DiscordInitializer";
	}
	
	@Override
	public void start() throws Exception {
		JDA jda = new JDABuilder(this.config.getString("discord-token"))
				.addEventListeners(new ReadyListener(), new MessageListener(this.client))
				.build();
		
		jda.awaitReady();
	}
}
