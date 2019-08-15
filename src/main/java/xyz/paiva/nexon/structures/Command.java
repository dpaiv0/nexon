package xyz.paiva.nexon.structures;

import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.utils.Config;

public class Command {
	public String name;
	public List<String> aliases;
	public Config config = new Config("config.yml");
	public DiscordClient client;

	public Command(DiscordClient client) {
		this.client = client;
		this.name = "";
		this.aliases = Arrays.asList("");
	}
	
	public void _run(Message message, String[] strippedArgs) throws Exception {
		MessageChannel channel = message.getChannel();
		User author = message.getAuthor();
		this.run(channel, author, message, strippedArgs);
	}
	
	public void run(MessageChannel channel, User author, Message message, String[] args) throws Exception {}
	
	public Boolean canRun() {
		return true;
	}
	
	public String getName () {
		return this.name;
	}
	
	public List<String> getAliases () {
		return this.aliases;
	}
}
