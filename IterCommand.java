public class IterCommand extends Command {
    public IterCommand() {
        super("iter", "Намира резултат от изпълнението на операцията 'итерация' (звезда на Клини) над граматика и създава нова граматика. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: iter <id>");
            return;
        }
        String grammarId = args[0];
        // TODO: Implement Kleene star operation
        System.out.println("Performing iteration on grammar " + grammarId);
    }
} 