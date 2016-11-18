import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class EventHandler
{

    @EventSubscriber
    public void onReadyEvent( ReadyEvent event ) throws RateLimitException,
            DiscordException, MissingPermissionsException
    {
        System.out.println("The bot is now ready");
        // Runs the on start up class and the on start up method to do something
        // when the BOT is ready
        StartUp.onStartUp();
        // doSomething();
    }

    @EventSubscriber
    public void onMessageEvent( MessageReceivedEvent event )
            throws MissingPermissionsException, RateLimitException,
            DiscordException
    {
        System.out.println(event.getMessage().getAuthor().getName() + ": "
                + event.getMessage().getContent());

        if ( event.getMessage().getContent().equalsIgnoreCase("p") )
        {
            new MessageBuilder(TestBot.discordClient)
                    .withChannel("238503415326441473").withContent("Delete me")
                    .build();
            TestBot.message = event.getMessage();
            try
            {

                MessageList msgList = new MessageList(TestBot.discordClient,
                        TestBot.message.getChannel());

                System.out.println("SUCESS");
                if ( msgList.load(1) )
                {
                    System.out.println("Load success");
                }
                else
                {
                    System.out.println("Load fail");
                }
                new MessageBuilder(TestBot.discordClient)
                        .withChannel("238503415326441473")
                        .withContent(
                                "IF DELETE WORKED YOU SHOULD NOT SEE THIS -- BOO")
                        .build();
                msgList.delete(0);
                new MessageBuilder(TestBot.discordClient)
                        .withChannel("238503415326441473")
                        .withContent(
                                "I tried to delete the message -- Did it work?")
                        .build();
            }
            catch ( NullPointerException e )
            {
                new MessageBuilder(TestBot.discordClient)
                        .withChannel("238503415326441473")
                        .withContent(
                                "Could not Delete the message above -- This is the Error: "
                                        + e.getMessage())
                        .build();
            }
        }
        else if ( event.getMessage().getContent().equalsIgnoreCase("p2") )
        {
            DeleteXMessages.delete(5);
        }

    }

}
