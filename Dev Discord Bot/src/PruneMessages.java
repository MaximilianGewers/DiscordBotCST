import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class PruneMessages
{
    public static void prune(CommandExecutionEvent event) throws RateLimitException,
            DiscordException, MissingPermissionsException
    {

        try
        {
            TestBot.message = event.getMessage();
            MessageList msgList = new MessageList(TestBot.discordClient,
                    TestBot.message.getChannel());
            String command = event.getMessage().getContent();

            System.out.println("String is " + command.length() + " long");
            System.out.println("The number is :" + command + ":");

            msgList.load(1);
            String[] test = event.getArgs();
            int deleteIndex = Integer.parseInt(test[0]);
            System.out.println("Number of messages to delete is " +deleteIndex);
            msgList.load(deleteIndex);
            msgList.bulkDelete(msgList);
        }
        catch ( NullPointerException e )
        {
            System.out.println("There was a problem with the prune command\n"
                    + e.getMessage());
        }
    }
}
