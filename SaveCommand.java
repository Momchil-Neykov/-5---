public class SaveCommand extends Command {
    public SaveCommand() {
        super("save", "Записва граматиката във файл");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: save <id> <filename>");
            return;
        }
        String grammarId = args[0];
        String filename = args[1];
        // TODO: Implement saving grammar to file
        System.out.println("Saving grammar " + grammarId + " to file: " + filename);
    }
} 