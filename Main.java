import java.util.*;

public class Main {
    private static Map<String, Grammar> grammars = new HashMap<>();
    private static Map<String, Command> commands = new HashMap<>();
    private static int nextGrammarId = 1;

    private static void initializeCommands() {
        Command[] commandList = {
            new ListCommand(),
            new PrintCommand(),
            new SaveCommand(),
            new AddRuleCommand(),
            new RemoveRuleCommand(),
            new UnionCommand(),
            new ConcatCommand(),
            new ChomskyCommand(),
            new CykCommand(),
            new IterCommand(),
            new EmptyCommand(),
            new ChomsifyCommand()
        };

        for (Command cmd : commandList) {
            commands.put(cmd.getName(), cmd);
        }
    }

    private static String generateNextGrammarId() {
        return "g" + nextGrammarId++;
    }

    public static Grammar getGrammar(String id) {
        return grammars.get(id);
    }

    public static void addGrammar(Grammar grammar) {
        grammars.put(grammar.getId(), grammar);
    }

    public static Collection<Grammar> getAllGrammars() {
        return grammars.values();
    }

    public static void main(String[] args) {
        initializeCommands();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Контекстно-свободни граматики - Команден интерпретатор");
        System.out.println("Въведете 'help' за списък с команди или 'exit' за изход");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) continue;
            if (line.equalsIgnoreCase("exit")) break;

            if (line.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }

            String[] parts = line.split("\\s+");
            String commandName = parts[0].toLowerCase();
            String[] commandArgs = Arrays.copyOfRange(parts, 1, parts.length);

            Command command = commands.get(commandName);
            if (command == null) {
                System.out.println("Непозната команда: " + commandName);
                continue;
            }

            try {
                command.execute(commandArgs);
            } catch (Exception e) {
                System.out.println("Грешка при изпълнение на командата: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("Налични команди:");
        List<Command> sortedCommands = new ArrayList<>(commands.values());
        sortedCommands.sort(Comparator.comparing(Command::getName));
        
        for (Command cmd : sortedCommands) {
            System.out.printf("%-15s - %s%n", cmd.getName(), cmd.getDescription());
        }
    }
} 