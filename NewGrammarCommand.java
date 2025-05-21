import java.util.Scanner;

public class NewGrammarCommand extends BaseCommand {
    public NewGrammarCommand() {
        super("Създава нова граматика чрез въвеждане от конзолата");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "newgrammar <id>");
        String id = args[0];
        if (Main.getGrammar(id) != null) {
            System.out.println("Граматика с ID " + id + " вече съществува!");
            return;
        }
        Grammar grammar = new Grammar(id);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Въведете правилата на граматиката (един по един). За край напишете 'end':");
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("end")) break;
            if (line.isEmpty() || line.startsWith("#")) continue;
            String[] parts = line.split("->");
            if (parts.length != 2) {
                System.out.println("Невалиден формат! Използвайте: X -> нещо");
                continue;
            }
            char left = parts[0].trim().charAt(0);
            String right = parts[1].trim();
            try {
                grammar.addProduction(new Production(left, right));
            } catch (Exception e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        }
        Main.addGrammar(grammar);
        System.out.println("Граматиката с ID " + id + " е създадена!");
    }
} 