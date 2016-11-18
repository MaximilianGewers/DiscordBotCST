
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;

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

        TestBot.message = event.getMessage();

        String command = "";
        String subCommand = "";

        if ( event.getMessage().getContent().charAt(0) == '!' )
        {
            String input = event.getMessage().getContent();
            int space = input.indexOf(' ');
            command = input.substring(0, space).trim();
            subCommand = input.substring(space).trim();
        }

        switch ( command )
        {
            case "!prune":
                System.out.println("made it to case");
                MessageList msgList = new MessageList(TestBot.discordClient,
                        TestBot.message.getChannel());

                try
                {
                    System.out
                            .println("String is " + subCommand.length() + " long");
                    System.out.println("The subCommand is :" + subCommand + ":");
                    int deleteIndex = Integer.parseInt(subCommand);

                    msgList.load(deleteIndex);
                    msgList.bulkDelete(msgList);
                }
                catch ( NullPointerException e )
                {
                    System.out
                            .println("There was a problem\n" + e.getMessage());
                }
                break;

            case "!check":
                try
                {
                    String url = "https://steamdb.info/app/";
                    try
                    {
                        System.out.println(subCommand);
                        new MessageBuilder(TestBot.discordClient)
                                .withChannel("238503415326441473")
                                .withContent(
                                        GrabInfo.GetList(url + subCommand, subCommand))
                                .build();
                    }
                    catch ( Exception e )
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                catch ( NullPointerException e )
                {
                    System.out
                            .println("There was a problem\n" + e.getMessage());
                }
                break;
        }

        // else if(event.getMessage().getContent().contains("!check")){
        // try{
        // TestBot.message = event.getMessage();
        // MessageList msgList = new MessageList(TestBot.discordClient,
        // TestBot.message.getChannel());
        //
        // String command = event.getMessage().getContent();
        // command = command.trim();
        // System.out.println("String is " +command.length() +" long");
        // command = command.substring(7, command.length() );
        // String url = "https://steamdb.info/app/";
        // try
        // {
        // System.out.println(command);
        // new MessageBuilder(TestBot.discordClient)
        // .withChannel("238503415326441473")
        // .withContent(GrabInfo.GetList(url+command, command))
        // .build();
        // }
        // catch ( Exception e )
        // {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }catch (NullPointerException e){
        // System.out.println("There was a problem\n" + e.getMessage());
        // }
        // }

        // start of Example
        /*
         * OnMessageEvent listens for a message and will than execute this code
         * The if statements will filter message content until it finds a true;
         */

        // if ( event.getMessage().getContent().equalsIgnoreCase("who do
        // you love the most. Me, Kale or Conner?") )
        // {
        // TestBot.channel =
        // TestBot.discordClient.getChannelByID("238503415326441473");
        // TestBot.channel.sendMessage("@CommanderDerpy#7862 of course. He is
        // senpai. <3");
        // }

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
