public class ChomskyCommand extends Command {
    public ChomskyCommand() {
        super("chomsky", "Проверява дали дадена граматика е в нормална форма на Чомски");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: chomsky <id>");
            return;
        }
        String grammarId = args[0];
        // TODO: Implement Chomsky Normal Form check
        System.out.println("Checking if grammar " + grammarId + " is in Chomsky Normal Form");
    }
} 