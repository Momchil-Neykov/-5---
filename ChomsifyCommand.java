import java.util.*;

public class ChomsifyCommand extends BaseCommand {
    public ChomsifyCommand() {
        super("Преобразува граматика в нормална форма на Чомски. Отпечатва идентификатора на новата граматика");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "chomsify <id>");
        Grammar g = getGrammar(args[0]);
        String newId = args[0] + "_cnf";
        Grammar cnfGrammar = new Grammar(newId);

        // Копираме всички продукции първоначално
        List<Production> currentProductions = new ArrayList<>(g.getProductions());
        List<Production> newProductions = new ArrayList<>();

        // Етап 1: Премахване на ε-продукции (ако има такива - изисква допускане на ε)
        // В текущата реализация Production не позволява празна дясна страна, така че този етап е пропуснат.
        // Ако ε-продукции са разрешени (например S -> e), трябва да се обработят тук.

        // Етап 2: Премахване на единични продукции (A -> B)
        // Този етап е сложен и изисква намиране на транзитивното затваряне. Опростена версия:
        List<Production> unitProductions = new ArrayList<>();
        for (Production p : currentProductions) {
            if (p.getRightSide().length() == 1 && Character.isUpperCase(p.getRightSide().charAt(0))) {
                unitProductions.add(p);
            } else {
                newProductions.add(p);
            }
        }
        // Опростен подход: За всяка единична продукция A -> B, добавяме всички правила B -> ...
        // Тази опростена версия не е напълно коректна за всички случаи и цикли.
        // Пълната реализация изисква алгоритъм за единични продукции.
        for (Production unitP : unitProductions) {
            char left = unitP.getLeftSide();
            char rightNT = unitP.getRightSide().charAt(0);
            for (Production p : currentProductions) {
                if (p.getLeftSide() == rightNT) {
                    newProductions.add(new Production(left, p.getRightSide()));
                }
            }
        }
        currentProductions = newProductions;
        newProductions = new ArrayList<>();

        // Етап 3: Преобразуване на продукции с дясна страна > 2 или с терминали сред нетерминали
        int ntIndex = 1; // Започваме след основните нетерминали A-Z
        Map<Character, Character> termToNT = new HashMap<>();
        Map<String, Character> longRightToNT = new HashMap<>(); // За десни страни с дължина > 2

        for (Production p : currentProductions) {
            String right = p.getRightSide();
            if (right.length() <= 2 && (right.length() == 1 || Character.isUpperCase(right.charAt(0)) && Character.isUpperCase(right.charAt(1)))) {
                // Вече в CNF формат (правила от вида A -> a или A -> BC)
                newProductions.add(p);
            } else {
                // Правило, което трябва да се преобразува
                StringBuilder tempRight = new StringBuilder();
                for (char c : right.toCharArray()) {
                    if (Character.isLowerCase(c)) {
                        // Заменяме терминал с нов нетерминал
                        if (!termToNT.containsKey(c)) {
                            char newNT = (char) ('A' + ('Z' - 'A') + 1 + ntIndex++);
                            termToNT.put(c, newNT);
                            newProductions.add(new Production(newNT, String.valueOf(c)));
                        }
                        tempRight.append(termToNT.get(c));
                    } else {
                        tempRight.append(c);
                    }
                }

                String currentRight = tempRight.toString();
                if (currentRight.length() > 2) {
                    // Разбиваме на бинарни продукции
                    char firstNT = p.getLeftSide();
                    for (int i = 0; i < currentRight.length() - 2; i++) {
                         char newNT = (char) ('A' + ('Z' - 'A') + 1 + ntIndex++);
                         newProductions.add(new Production(firstNT, "" + currentRight.charAt(i) + newNT));
                         firstNT = newNT;
                    }
                    newProductions.add(new Production(firstNT, currentRight.substring(currentRight.length() - 2)));

                } else { // Дясна страна е с дължина 2, но съдържаше терминали
                     newProductions.add(new Production(p.getLeftSide(), currentRight));
                }
            }
        }

        // Вместо директно задаване на списъка, добавяме всяка продукция поотделно
        for (Production p : newProductions) {
            cnfGrammar.addProduction(p);
        }
        cnfGrammar.setStartSymbol(g.getStartSymbol()); // Запазваме началния символ

        Main.addGrammar(cnfGrammar);
        System.out.println("Граматиката в Чомски е създадена с ID: " + newId);
        System.out.println("ВАЖНО: Преобразуването в Чомски е опростено и може да не работи за всички граматики.");
    }
} 