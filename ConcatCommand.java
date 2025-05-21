public class ConcatCommand extends BaseCommand {
    public ConcatCommand() {
        super("Намира конкатенацията на две граматики и създава нова граматика. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 2, "concat <id1> <id2>");
        String id1 = args[0];
        String id2 = args[1];
        Grammar g1 = getGrammar(id1);
        Grammar g2 = getGrammar(id2);

        // Генерираме ново ID за конкатенираната граматика
        String newId = id1 + "_concat_" + id2;
        Grammar concatGrammar = new Grammar(newId);

        // Нов начален символ
        char newStart = 'S';
        while (g1.getNonTerminals().contains(newStart) || g2.getNonTerminals().contains(newStart)) {
            newStart++;
        }
        concatGrammar.setStartSymbol(newStart);
        // Добавяме ново правило: S -> S1S2
        concatGrammar.addProduction(new Production(newStart, String.valueOf(g1.getStartSymbol()) + g2.getStartSymbol()));

        // Копираме всички продукции от двете граматики
        for (Production p : g1.getProductions()) {
            concatGrammar.addProduction(new Production(p.getLeftSide(), p.getRightSide()));
        }
        for (Production p : g2.getProductions()) {
            concatGrammar.addProduction(new Production(p.getLeftSide(), p.getRightSide()));
        }

        Main.addGrammar(concatGrammar);
        System.out.println("Конкатенираната граматика е създадена с ID: " + newId);
    }
} 