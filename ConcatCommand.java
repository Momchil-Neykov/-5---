public class ConcatCommand extends Command {
    public ConcatCommand() {
        super("concat", "Намира конкатенацията на две граматики и създава нова граматика. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: concat <id1> <id2>");
            return;
        }
        String grammarId1 = args[0];
        String grammarId2 = args[1];
        // TODO: Implement concatenation of two grammars
        System.out.println("Concatenating grammars " + grammarId1 + " and " + grammarId2);
    }
} 