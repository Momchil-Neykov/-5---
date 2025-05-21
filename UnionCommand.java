public class UnionCommand extends BaseCommand {
    public UnionCommand() {
        super("Намира обединението на две граматики и създава нова граматика. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 2, "union <id1> <id2>");
        String id1 = args[0];
        String id2 = args[1];
        Grammar g1 = getGrammar(id1);
        Grammar g2 = getGrammar(id2);

        // Генерираме ново ID за обединената граматика
        String newId = id1 + "_union_" + id2;
        Grammar unionGrammar = new Grammar(newId);

        // Нов начален символ
        char newStart = 'S';
        while (g1.getNonTerminals().contains(newStart) || g2.getNonTerminals().contains(newStart)) {
            newStart++;
        }
        unionGrammar.setStartSymbol(newStart);
        // Добавяме ново правило: S -> S1 | S2
        unionGrammar.addProduction(new Production(newStart, String.valueOf(g1.getStartSymbol())));
        unionGrammar.addProduction(new Production(newStart, String.valueOf(g2.getStartSymbol())));

        // Копираме всички продукции от двете граматики
        for (Production p : g1.getProductions()) {
            unionGrammar.addProduction(new Production(p.getLeftSide(), p.getRightSide()));
        }
        for (Production p : g2.getProductions()) {
            unionGrammar.addProduction(new Production(p.getLeftSide(), p.getRightSide()));
        }

        Main.addGrammar(unionGrammar);
        System.out.println("Обединената граматика е създадена с ID: " + newId);
    }
} 