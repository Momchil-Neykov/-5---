import java.util.*;

public class CykCommand extends BaseCommand {
    public CykCommand() {
        super("Проверява дали дадена дума е в езика на дадена граматика (CYK алгоритъм)");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 2, "cyk <id> <word>");
        Grammar grammar = getGrammar(args[0]);
        String word = args[1];
        List<Production> productions = grammar.getProductions();
        char start = grammar.getStartSymbol();

        // Създаваме map: нетерминал -> списък с десни страни
        Map<Character, List<String>> rules = new HashMap<>();
        for (Production p : productions) {
            rules.computeIfAbsent(p.getLeftSide(), k -> new ArrayList<>()).add(p.getRightSide());
        }

        int n = word.length();
        Set<Character>[][] table = new HashSet[n][n];
        for (int i = 0; i < n; i++) {
            table[i][0] = new HashSet<>();
            for (Map.Entry<Character, List<String>> entry : rules.entrySet()) {
                for (String right : entry.getValue()) {
                    if (right.length() == 1 && right.charAt(0) == word.charAt(i)) {
                        table[i][0].add(entry.getKey());
                    }
                }
            }
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                table[i][l - 1] = new HashSet<>();
                for (int k = 1; k < l; k++) {
                    Set<Character> leftSet = table[i][k - 1];
                    Set<Character> rightSet = table[i + k][l - k - 1];
                    for (Character B : leftSet) {
                        for (Character C : rightSet) {
                            for (Map.Entry<Character, List<String>> entry : rules.entrySet()) {
                                for (String right : entry.getValue()) {
                                    if (right.length() == 2 && right.charAt(0) == B && right.charAt(1) == C) {
                                        table[i][l - 1].add(entry.getKey());
                                    } else if (right.equals("e") && word.isEmpty()) { // Проверка за празна дума ε
                                         table[i][l - 1].add(entry.getKey());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

         // Добавяне на проверка за празна дума за началния символ
        if (n == 0) {
             if (rules.containsKey(start) && rules.get(start).contains("e")) { // Предполагаме 'e' за празна дума
                 System.out.println("Думата '' (празна дума) принадлежи на езика на граматиката.");
             } else {
                 System.out.println("Думата '' (празна дума) НЕ принадлежи на езика на граматиката.");
             }
             return;
        }

        if (table[0][n - 1].contains(start)) {
            System.out.println("Думата '" + word + "' принадлежи на езика на граматиката.");
        } else {
            System.out.println("Думата '" + word + "' НЕ принадлежи на езика на граматиката.");
        }
    }
} 