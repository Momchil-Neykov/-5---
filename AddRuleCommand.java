public class AddRuleCommand extends BaseCommand {
    public AddRuleCommand() {
        super("Добавя правила");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 3, "addRule <id> <leftSide> <rightSide>");
        String id = args[0];
        char left = args[1].charAt(0);
        String right = args[2];
        Grammar grammar = getGrammar(id);
        try {
            grammar.addProduction(new Production(left, right));
            System.out.println("Правилото " + left + " -> " + right + " беше добавено към граматика " + id);
        } catch (Exception e) {
            System.out.println("Грешка: " + e.getMessage());
        }
    }
} 