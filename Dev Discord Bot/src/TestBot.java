import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;

public class TestBot {
	public static IDiscordClient discordClient;
	public static IMessage message; // Gets the message from the event object
									// NOTE: This is not the content of the
									// message, but the object itself
	public static IChannel channel; // Gets the channel in which this message
									// was sent.

	public static final String VERSION = "0.0.1";
	

	public static void main(String[] args) throws Exception {
		// if ( args.length < 1 )
		// {
		// System.out.print("You need to specify a token before continuing");
		// }
		discordClient = getClient("NOPE.AVI");

		discordClient.getDispatcher().registerListener(new EventHandler());
		discordClient.getDispatcher().registerListener(new ReadyEventListener());
		discordClient.getDispatcher().registerListener(new CommandListener(discordClient));
	}

	public static IDiscordClient getClient(String token) throws DiscordException {
		return new ClientBuilder().withToken(token).login();
	}
}
