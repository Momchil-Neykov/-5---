public class EmptyCommand extends Command {
    public EmptyCommand() {
        super("empty", "Проверява дали езикът на дадена контекстно-свободна граматика е празен");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: empty <id>");
            return;
        }
        String grammarId = args[0];
        // TODO: Implement emptiness check
        System.out.println("Checking if grammar " + grammarId + " is empty");
    }
} 