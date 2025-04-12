public class PrintCommand extends Command {
    public PrintCommand() {
        super("print", "Извежда граматиката в подходящ формат. За всяко правило се отпечатва поредния номер");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: print <id>");
            return;
        }
        String grammarId = args[0];
        // TODO: Implement printing of grammar with given ID
        System.out.println("Printing grammar with ID: " + grammarId);
    }
} 