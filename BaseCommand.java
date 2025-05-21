public abstract class BaseCommand implements Command {
    protected final String description;

    protected BaseCommand(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    protected void validateArgs(String[] args, int minArgs, String usage) {
        if (args.length < minArgs) {
            System.out.println("Използване: " + usage);
            throw new IllegalArgumentException("Недостатъчен брой аргументи");
        }
    }

    protected Grammar getGrammar(String id) {
        Grammar grammar = Main.getGrammar(id);
        if (grammar == null) {
            throw new IllegalArgumentException("Граматика с ID " + id + " не съществува.");
        }
        return grammar;
    }
} 