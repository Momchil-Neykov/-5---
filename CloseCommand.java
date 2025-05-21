public class CloseCommand extends BaseCommand {
    public CloseCommand() {
        super("Затваря текущата граматика (премахва я от паметта)");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "close <id>");
        String id = args[0];
        Grammar grammar = Main.getGrammar(id);
        if (grammar == null) {
            System.out.println("Граматика с ID " + id + " не съществува.");
            return;
        }
        Main.removeGrammar(id);
        System.out.println("Граматиката с ID " + id + " е затворена и премахната от паметта.");
    }
} 