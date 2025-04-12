public class AddRuleCommand extends Command {
    public AddRuleCommand() {
        super("addRule", "Добавя правила");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Използване: addRule <id> <leftSide> <rightSide>");
            System.out.println("Пример: addRule g1 S aB");
            return;
        }

        String grammarId = args[0];
        char leftSide = args[1].charAt(0);
        String rightSide = args[2];

        Grammar grammar = Main.getGrammar(grammarId);
        if (grammar == null) {
            System.out.println("Граматика с ID " + grammarId + " не съществува.");
            return;
        }

        try {
            Production production = new Production(leftSide, rightSide);
            grammar.addProduction(production);
            System.out.println("Правилото " + production + " беше добавено към граматика " + grammarId);
        } catch (IllegalArgumentException e) {
            System.out.println("Грешка: " + e.getMessage());
            System.out.println("Лявата страна трябва да бъде главна латинска буква (нетерминал).");
        }
    }
} 