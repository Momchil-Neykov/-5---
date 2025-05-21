import java.io.*;

public class SaveAsCommand extends BaseCommand {
    public SaveAsCommand() {
        super("Записва граматиката във файл с ново име");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 2, "saveas <id> <filename>");
        String id = args[0];
        String filename = args[1];
        Grammar grammar = getGrammar(id);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Production p : grammar.getProductions()) {
                writer.println(p.getLeftSide() + " -> " + p.getRightSide());
            }
            System.out.println("Граматиката е записана във файл: " + filename);
        } catch (IOException e) {
            System.out.println("Грешка при запис: " + e.getMessage());
        }
    }
} 