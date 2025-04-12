public class CykCommand extends Command {
    public CykCommand() {
        super("cyk", "Проверява дали дадена дума е в езика на дадена граматика (CYK алгоритъм)");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: cyk <id> <word>");
            return;
        }
        String grammarId = args[0];
        String word = args[1];
        // TODO: Implement CYK algorithm
        System.out.println("Checking if word '" + word + "' belongs to grammar " + grammarId);
    }
} 