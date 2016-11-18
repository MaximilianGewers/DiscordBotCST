import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandExecutionEvent
{
    private final IMessage message;
    private final String command;
    private final IUser by;
    private final String[] args;

    public CommandExecutionEvent(IMessage message, String command, IUser by,
            String[] args)
    {
        this.message = message;
        this.command = command;
        this.by = by;
        this.args = args;
    }

    public String[] getArgs()
    {
        return args;
    }

    public IMessage getMessage()
    {
        return message;
    }

    public boolean isCommand( String command )
    {
        return command.equalsIgnoreCase(command);
    }

    public IUser getBy()
    {
        return by;
    }

}
