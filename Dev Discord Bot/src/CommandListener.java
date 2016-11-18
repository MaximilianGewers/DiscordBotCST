import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class CommandListener
{

    final static String COMMAND_PREFIX = "!";

    public CommandListener(IDiscordClient client)
    {
        client.getDispatcher().registerListener(this);
    }

    @EventSubscriber
    public void watchForCommands( MessageReceivedEvent event )
    {
        try
        {
            IMessage message = event.getMessage();
            String content = message.getContent();

            if ( content.startsWith(COMMAND_PREFIX) )
            {
                String command = content.toLowerCase();
                String[] args = null;

                if ( content.contains(" ") )
                {
                    command = command.split(" ")[0];
                    args = command.substring(content.indexOf(' ') + 1)
                            .split(" ");
                }

                CommandExecutionEvent theEvent = new CommandExecutionEvent(
                        message, command, message.getAuthor(), args);
                commands(theEvent);
//                System.out.println("by : " +theEvent.getBy());
//                System.out.println("Message is: " +theEvent.getMessage());
                

            }
            else
            {
                return;
            }
        }
        catch ( Exception e )
        {
            // TODO: Have to write the catch
        }

    }

    public void commands( CommandExecutionEvent event )
            throws MissingPermissionsException, RateLimitException,
            DiscordException
    {
        if ( event.isCommand("ping") )
        {
            event.getMessage().reply("pong -- The command was ");
        } else if ( event.isCommand("prune") )
        {
            PruneMessages.prune(event);
        }
    }

}
