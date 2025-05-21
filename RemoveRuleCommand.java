public class RemoveRuleCommand extends BaseCommand {
    public RemoveRuleCommand() {
        super("Премахване на правило по пореден номер");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 2, "removeRule <id> <ruleNumber>");
        String id = args[0];
        int ruleNumber;
        try {
            ruleNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Грешен номер на правило!");
            return;
        }
        Grammar grammar = getGrammar(id);
        if (ruleNumber < 1 || ruleNumber > grammar.getProductions().size()) {
            System.out.println("Невалиден номер на правило!");
            return;
        }
        grammar.getProductions().remove(ruleNumber - 1);
        System.out.println("Правилото с номер " + ruleNumber + " беше премахнато от граматика " + id);
    }
} 