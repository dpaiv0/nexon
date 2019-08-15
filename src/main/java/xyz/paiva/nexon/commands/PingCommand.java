package xyz.paiva.nexon.commands;

import java.awt.Color;
import java.util.Arrays;

import net.dv8tion.jda.api.EmbedBuilder;
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
	
	public void run(MessageChannel channel, User author, Message message, String[] args, EmbedBuilder embed) {
		try {
			message.getJDA().getRestPing()
			.queue((time) -> {
				embed
					.setColor(Color.green)
					.setTitle("🏓 Pong!")
					.setDescription(String.format("REST Ping: **%1$dms**\nWebSocket Ping: **%2$dms**", time, message.getJDA().getGatewayPing()));

				channel.sendMessage(embed.build()).queue();
			});
		} catch (Exception e) {
			e.printStackTrace();
			
			embed
				.setColor(Color.red)
				.setTitle("An error has occurred")
				.setDescription("```" + e.toString() + "```");
			
			channel.sendMessage(embed.build()).queue();
		}
	}
}
