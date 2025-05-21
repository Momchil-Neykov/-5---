import java.io.*;

public class OpenCommand extends BaseCommand {
    public OpenCommand() {
        super("Отваря файл с граматика");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "open <filename>");
        String filename = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String id = filename;
            Grammar grammar = new Grammar(id);
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("->");
                if (parts.length != 2) continue;
                char left = parts[0].trim().charAt(0);
                String right = parts[1].trim();
                grammar.addProduction(new Production(left, right));
            }
            Main.addGrammar(grammar);
            System.out.println("Граматиката е заредена с ID: " + id);
        } catch (IOException e) {
            System.out.println("Грешка при четене на файл: " + e.getMessage());
        }
    }
} 