package xyz.paiva.nexon;

import java.util.ArrayList;

import xyz.paiva.nexon.initializers.CommandInitializer;
import xyz.paiva.nexon.initializers.DiscordInitializer;
import xyz.paiva.nexon.structures.Command;

public class DiscordClient {
	public ArrayList<Command> commands; 

	/**
	 * @throws Exception
	 */
	public void start() throws Exception {
		this.commands = new ArrayList<Command>();
		DiscordInitializer discordInitializer = new DiscordInitializer(this);
		CommandInitializer commandInitializer = new CommandInitializer(this);
		System.out.println(String.format("Starting %s...", discordInitializer.name));
		discordInitializer.start();
		
		System.out.println(String.format("Starting %s...", commandInitializer.name));
		commandInitializer.start();
	}

}
