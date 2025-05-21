import java.util.*;

public class EmptyCommand extends BaseCommand {
    public EmptyCommand() {
        super("Проверява дали езикът на дадена контекстно-свободна граматика е празен");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "empty <id>");
        Grammar grammar = getGrammar(args[0]);
        Set<Character> generating = new HashSet<>();

        // Добавяме терминалите към генериращите символи
        generating.addAll(grammar.getTerminals());

        boolean changed;
        do {
            changed = false;
            for (Production p : grammar.getProductions()) {
                // Проверяваме дали дясната страна генерира терминален низ
                boolean rightSideGenerates = true;
                for (char c : p.getRightSide().toCharArray()) {
                    if (Character.isUpperCase(c) && !generating.contains(c)) {
                        rightSideGenerates = false;
                        break;
                    }
                }

                // Ако лявата страна не е вече в генериращите символи и дясната страна генерира
                if (rightSideGenerates && !generating.contains(p.getLeftSide())) {
                    generating.add(p.getLeftSide());
                    changed = true;
                }
            }
        } while (changed);

        if (generating.contains(grammar.getStartSymbol())) {
            System.out.println("Езикът на граматиката НЕ е празен.");
        } else {
            System.out.println("Езикът на граматиката е празен.");
        }
    }
} 