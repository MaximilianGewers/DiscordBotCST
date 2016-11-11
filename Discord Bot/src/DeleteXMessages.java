import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class DeleteXMessages
{
    public static void delete() throws RateLimitException, DiscordException, MissingPermissionsException
    {
        TestBot.channel = TestBot.discordClient.getChannelByID("238503415326441473");
        for ( int i = 0; i < 6; i++ )
        {
            TestBot.channel.getMessages().delete(0);
        }
    }

}
