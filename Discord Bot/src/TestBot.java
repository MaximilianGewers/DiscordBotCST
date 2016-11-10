import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;

public class TestBot
{
    public static IDiscordClient discordClient;
    public static IChannel channel;

    public static void main( String[] args ) throws Exception
    {
        if ( args.length < 1 )
        {
            System.out.print("You need to specify a token before continuing");
        }
        discordClient = getClient("XXX");

        discordClient.getDispatcher().registerListener(new EventHandler());
        discordClient.getDispatcher()
                .registerListener(new ReadyEventListener());
    }
    public static IDiscordClient getClient( String token ) throws DiscordException
    {
        return new ClientBuilder().withToken(token).login();
    }
}
