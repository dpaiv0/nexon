package xyz.paiva.nexon.initializers;

import xyz.paiva.nexon.DiscordClient;
import xyz.paiva.nexon.commands.*;
import xyz.paiva.nexon.structures.Command;
import xyz.paiva.nexon.structures.Initializer;

public class CommandInitializer extends Initializer {
	/**
	 * @param discordClient
	 */
	public CommandInitializer(DiscordClient discordClient) {
		super(discordClient);
		this.name = "CommandInitializer";
	}
	
	@Override
	public void start() throws Exception {
		try {
			this.addCommand(new PingCommand(this.client));
			this.addCommand(new UserInfoCommand(this.client));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param command
	 * @return Boolean
	 */
	public boolean addCommand(Command command) {
		System.out.println("Adding command " + command.getName());
		System.out.println("Command can run? " + command.canRun());
		if (!(command.canRun())) return false;
		return this.client.commands.add(command);
	}
}
