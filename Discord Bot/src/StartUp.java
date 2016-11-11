import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class StartUp
{
    private static boolean send = true;

    public static void onStartUp() throws RateLimitException, DiscordException,
            MissingPermissionsException
    {
        System.out.println("Running onStartUp -- Start");

        if ( send )
        {
            new MessageBuilder(TestBot.discordClient)
                    .withChannel("238503415326441473")
                    .withContent("```Bot is up and running " + "\nVersion: "
                            + TestBot.version + "```")
                    .build();
        }

        System.out.println("Running onStartUp -- Done");
    }
}
