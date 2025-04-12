public class ChomsifyCommand extends Command {
    public ChomsifyCommand() {
        super("chomsify", "Преобразува граматика в нормална форма на Чомски. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: chomsify <id>");
            return;
        }
        String grammarId = args[0];
        // TODO: Implement conversion to Chomsky Normal Form
        System.out.println("Converting grammar " + grammarId + " to Chomsky Normal Form");
    }
} 