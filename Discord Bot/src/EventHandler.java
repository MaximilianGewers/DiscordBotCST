
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
        // when the bot is ready
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

        // start of Example
        /*
         * OnMessageEvent listens for a message and will than execute this code
         * The if statements will filter message content until it finds a true;
         */
        // if ( event.getMessage().getContent().equalsIgnoreCase("ping") )
        // {
        // // This sets the channel object to a static channel. The id here
        // // below
        // TestBot.channel =
        // TestBot.discordClient.getChannelByID("238503415326441473");
        // // This is the message the bot will send in that channel
        // TestBot.channel.sendMessage("Pong");
        // // End of Example
        // }
        // else if ( event.getMessage().getContent().equalsIgnoreCase("!help") )
        // {
        // TestBot.channel =
        // TestBot.discordClient.getChannelByID("238503415326441473");
        // TestBot.channel.sendMessage("Sorry I can't do that yet");
        // }
        // else if ( event.getMessage().getContent().equalsIgnoreCase("who do
        // you love the most. Me, Kale or Conner?") )
        // {
        // TestBot.channel =
        // TestBot.discordClient.getChannelByID("238503415326441473");
        // TestBot.channel.sendMessage("@CommanderDerpy#7862 of course. He is
        // senpai. <3");
        // }
        // else if ( event.getMessage().getContent().equalsIgnoreCase("!LC") )
        // {
        // TestBot.channel =
        // TestBot.discordClient.getChannelByID("238503415326441473");
        // TestBot.channel.sendMessage("Testing channel list: " +
        // TestBot.discordClient.getChannels());
        // TestBot.channel.sendMessage("Testing channel list: " +
        // TestBot.channel.getID());
        // }
        // else
        if ( event.getMessage().getContent().contains("!prune") )
        {
            try
            {
                TestBot.message = event.getMessage();
                MessageList msgList = new MessageList(TestBot.discordClient,
                        TestBot.message.getChannel());
                
                 String command = event.getMessage().getContent();
                 command = command.trim();
                 System.out.println("String is " +command.length() +" long");
                 command = command.substring(7, command.length() );
                 System.out.println("The number is :" +command+ ":");
                 int deleteIndex = Integer.parseInt(command);
                
                msgList.load(deleteIndex);
                msgList.bulkDelete(msgList);
            }
            catch ( NullPointerException e )
            {
                System.out.println("There was a problem\n" +e.getMessage());
            }
        }
        else if(event.getMessage().getContent().contains("!check")){
            try{
                TestBot.message = event.getMessage();
                MessageList msgList = new MessageList(TestBot.discordClient,
                        TestBot.message.getChannel());
                
                 String command = event.getMessage().getContent();
                 command = command.trim();
                 System.out.println("String is " +command.length() +" long");
                 command = command.substring(7, command.length() );
                 String url = "https://steamdb.info/app/";
                 try
                {
                     System.out.println(command);
                    new MessageBuilder(TestBot.discordClient)
                    .withChannel("238503415326441473")
                    .withContent(GrabInfo.GetList(url+command, command))
                    .build();
                }
                catch ( Exception e )
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }catch (NullPointerException e){
                System.out.println("There was a problem\n" + e.getMessage());
            }
        }

        // else if (event.getMessage().getContent().equalsIgnoreCase("give
        // current channel id")) {
        // String channelID = TestBot.channel.getID();
        //// work in progress TestBot.channel =
        // TestBot.discordClient.getChannelByID("238503415326441473");
        // TestBot.channel. = channelID;
        // TestBot.channel.sendMessage("This channel called : `"
        // +TestBot.channel.getName() +"` has the ID of : `"
        // +TestBot.channel.getID()+"`");
        // }
    }
}
