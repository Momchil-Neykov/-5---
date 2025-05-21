import java.util.*;

public class IterCommand extends BaseCommand {
    public IterCommand() {
        super("Намира резултат от изпълнението на операцията 'итерация' (звезда на Клини) над граматика и създава нова граматика. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "iter <id>");
        Grammar g = getGrammar(args[0]);
        String newId = args[0] + "_iter";
        Grammar iterGrammar = new Grammar(newId);

        // Нов начален символ
        char newStart = 'S';
        while (g.getNonTerminals().contains(newStart) || iterGrammar.getNonTerminals().contains(newStart)) {
             newStart++;
             if (newStart > 'Z') { // Ограничение за буквите
                 System.out.println("Не може да се намери нов уникален начален символ.");
                 return;
             }
        }
        iterGrammar.setStartSymbol(newStart);

        // S' -> S S' | ε (тук ε ще е специален символ, напр. 'e')
        iterGrammar.addProduction(new Production(newStart, String.valueOf(g.getStartSymbol()) + newStart));
        iterGrammar.addProduction(new Production(newStart, "e")); // 'e' ще означава празна дума

        // Копираме всички продукции
        for (Production p : g.getProductions()) {
            iterGrammar.addProduction(new Production(p.getLeftSide(), p.getRightSide()));
        }

        Main.addGrammar(iterGrammar);
        System.out.println("Итерационната граматика е създадена с ID: " + newId);
    }
} 