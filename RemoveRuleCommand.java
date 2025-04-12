public class RemoveRuleCommand extends Command {
    public RemoveRuleCommand() {
        super("removeRule", "Премахване на правило по пореден номер");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: removeRule <id> <ruleNumber>");
            return;
        }
        String grammarId = args[0];
        int ruleNumber = Integer.parseInt(args[1]);
        // TODO: Implement removing rule from grammar
        System.out.println("Removing rule #" + ruleNumber + " from grammar " + grammarId);
    }
} 