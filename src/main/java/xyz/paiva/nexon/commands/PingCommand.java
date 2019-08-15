package xyz.paiva.nexon.commands;

import java.util.Arrays;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.structures.Command;

public class PingCommand extends Command {
	public PingCommand(DiscordClient client) {
		super(client);
		this.name = "ping";
		this.aliases = Arrays.asList("pang", "peng", "pong", "pung");
	}
	
	public void run(MessageChannel channel, User author, Message message, String[] args) {
		message.getJDA().getRestPing()
			.queue((time) -> {
				channel
				    .sendMessageFormat(":ping_pong: Pong!\nREST Ping: %1$dms\nWebSocket Ping: %2$dms", time, message.getJDA().getGatewayPing())
					.queue();
			});
	}
}
