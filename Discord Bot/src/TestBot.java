import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.Invite;
import sx.blah.discord.handle.obj.IInvite;
import sx.blah.discord.util.DiscordException;

public class TestBot
{
    public static IDiscordClient discordClient;

    public static void main( String[] args ) throws Exception
    {
        if ( args.length < 1 )
        {
            System.out.print("You need to specify a token before continuing");
        }
        discordClient = getClient("MjQ2MDM3NDkxOTQ4MTkxNzYw.CwU0Sg.zcllCVveX3P5HaHIx5cKJAK5YuQ");

        discordClient.getDispatcher().registerListener(new EventHandler());
        discordClient.getDispatcher()
                .registerListener(new ReadyEventListener());
        
        Invite inv = new Invite( discordClient, "FSVst");
        inv.accept();
        }

    public static IDiscordClient getClient( String token ) throws DiscordException
    {
        return new ClientBuilder().withToken(token).login();
    }
}
