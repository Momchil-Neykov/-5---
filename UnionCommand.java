public class UnionCommand extends Command {
    public UnionCommand() {
        super("union", "Намира обединението на две граматики и създава нова граматика. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: union <id1> <id2>");
            return;
        }
        String grammarId1 = args[0];
        String grammarId2 = args[1];
        // TODO: Implement union of two grammars
        System.out.println("Uniting grammars " + grammarId1 + " and " + grammarId2);
    }
} 