import sx.blah.discord.handle.impl.events.ReadyEvent;

public class ReadyEventListener
{
    public void handle( ReadyEvent event )
    {
        System.out.println("The bot is now ready");
//        doSomething();
    }
}
