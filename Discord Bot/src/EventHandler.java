import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class EventHandler
{
    @EventSubscriber
    public void onReadyEvent( ReadyEvent event )
    {
        System.out.println("The bot is now ready");
//        doSomething();
    }

    @EventSubscriber
    public void onMessageEvent( MessageReceivedEvent event )
    {
        System.out.println(event.getMessage().getAuthor().getName() + ": "
                + event.getMessage().getContent());
    }
}
