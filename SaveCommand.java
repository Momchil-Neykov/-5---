import java.io.*;

public class SaveCommand extends BaseCommand {
    public SaveCommand() {
        super("Записва граматиката във файл");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "save <id>");
        String id = args[0];
        Grammar grammar = getGrammar(id);
        String filename = id + ".txt";
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