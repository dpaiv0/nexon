package xyz.paiva.nexon.commands;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.structures.Command;

public class UserInfoCommand extends Command {
	public UserInfoCommand(DiscordClient client) {
		super(client);
		this.name = "userinfo";
		this.aliases = Arrays.asList("ui");
	}
	
	public void run(MessageChannel channel, User author, Message message, String[] args, EmbedBuilder embed) {	
		try {
			Member member;

			if (args.length > 0) {
	            member = message.getGuild().getMember(message.getMentionedUsers().get(0));
	        } else {
	            member = message.getMember();
	        }
			
			String roles = "";
			
			for ( Role r : member.getRoles() ) {
	            roles += r.getName() + ", ";
	        }

	        if (roles.length() > 0)
	            roles = roles.substring(0, roles.length() - 2);
	        else
	            roles = "No roles on this server.";
	        
	        String boosted = "";
			
			if (member.getTimeBoosted() != null)
				boosted = member.getTimeBoosted().format(DateTimeFormatter.RFC_1123_DATE_TIME);
			else
				boosted = "Not boosted this guild";

			embed
				.setColor(Color.green)
				.setAuthor(member.getUser().getAsTag(), null, member.getUser().getEffectiveAvatarUrl())
				.setThumbnail(member.getUser().getEffectiveAvatarUrl())
				.addField("ID", member.getId(), true)
				.addField("Status", member.getOnlineStatus().getKey(), true)
				.addField("Roles", roles, true)
				.addField("Joined at", member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), true)
				.addField("Account created at", member.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), true)
				.addField("Boosted at", boosted, true);
			
			
			channel.sendMessage(embed.build()).queue();
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
