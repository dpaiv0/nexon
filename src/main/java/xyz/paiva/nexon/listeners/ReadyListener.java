package xyz.paiva.nexon.listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
	public void onReady(ReadyEvent event) {
		System.out.println("Connected successfully!");
		System.out.println(String.format("User: %s", event.getJDA().getSelfUser().getAsTag()));
		System.out.println(String.format("Guilds: %d", event.getGuildTotalCount()));
	}
}
