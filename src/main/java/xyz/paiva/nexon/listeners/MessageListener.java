package xyz.paiva.nexon.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.structures.Command;
import xyz.paiva.nexon.utils.Config;

public class MessageListener extends ListenerAdapter {
	public Config config = new Config("config.yml");
	public DiscordClient client;
	
	public MessageListener(DiscordClient client) {
		this.client = client;
	}

	public void onMessageReceived(MessageReceivedEvent msg) {
		try {
			if (msg.getChannelType() == ChannelType.PRIVATE || !(msg.getMessage().getContentRaw().startsWith(this.config.getString("prefix")))) return;
			else {
				Message message = msg.getMessage();
				String content = message.getContentRaw();
				String[] args = content.split(" ");
				String[] strippedArgs = Arrays.copyOfRange(args, 1, args.length);
				String commandName = args[0].replace(this.config.getString("prefix"), "");
				if (this.checkCommand(this.client.commands, commandName)) {
					Command command = this.getCommand(this.client.commands, commandName);
					System.out.println("Command \"" + content + "\" was executed by " + message.getAuthor().getAsTag() + " on guild " + message.getGuild().getName() + ".");
					command._run(message, strippedArgs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkCommand (ArrayList<Command> commands, String name) {
		return commands
				.stream()
				.filter((o) -> o.getName().equals(name) || o.getAliases().contains(name))
				.findFirst()
				.isPresent();
	}
	
	public Command getCommand (ArrayList<Command> commands, String name) {
		return commands
				.stream()
				.filter(o -> o.getName().equals(name) || o.getAliases().contains(name))
				.findFirst()
				.get();
	}
}
