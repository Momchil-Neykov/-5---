import java.util.*;

public class ChomskyCommand extends BaseCommand {
    public ChomskyCommand() {
        super("Проверява дали дадена граматика е в нормална форма на Чомски");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "chomsky <id>");
        Grammar grammar = getGrammar(args[0]);
        boolean isChomsky = true;
        for (Production p : grammar.getProductions()) {
            String right = p.getRightSide();
            if (right.length() == 1) {
                if (!Character.isLowerCase(right.charAt(0))) {
                    isChomsky = false;
                    break;
                }
            } else if (right.length() == 2) {
                if (!Character.isUpperCase(right.charAt(0)) || !Character.isUpperCase(right.charAt(1))) {
                    isChomsky = false;
                    break;
                }
            } else {
                isChomsky = false;
                break;
            }
        }
        if (isChomsky) {
            System.out.println("Граматиката е в нормална форма на Чомски.");
        } else {
            System.out.println("Граматиката НЕ е в нормална форма на Чомски.");
        }
    }
} 