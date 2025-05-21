import java.util.*;

public class Main {
    private static Map<String, Grammar> grammars = new HashMap<>();
    private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);
    private static int nextGrammarId = 1;

    private static void initializeCommands() {
        commands.put(CommandType.LIST, new ListCommand());
        commands.put(CommandType.PRINT, new PrintCommand());
        commands.put(CommandType.SAVE, new SaveCommand());
        commands.put(CommandType.ADD_RULE, new AddRuleCommand());
        commands.put(CommandType.REMOVE_RULE, new RemoveRuleCommand());
        commands.put(CommandType.UNION, new UnionCommand());
        commands.put(CommandType.CONCAT, new ConcatCommand());
        commands.put(CommandType.CHOMSKY, new ChomskyCommand());
        commands.put(CommandType.CYK, new CykCommand());
        commands.put(CommandType.ITER, new IterCommand());
        commands.put(CommandType.EMPTY, new EmptyCommand());
        commands.put(CommandType.CHOMSKIFY, new ChomsifyCommand());
        commands.put(CommandType.OPEN, new OpenCommand());
        commands.put(CommandType.CLOSE, new CloseCommand());
        commands.put(CommandType.SAVEAS, new SaveAsCommand());
        commands.put(CommandType.NEWGRAMMAR, new NewGrammarCommand());
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

    public static void removeGrammar(String id) {
        grammars.remove(id);
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

            CommandType commandType = CommandType.fromName(commandName);
            if (commandType == null) {
                System.out.println("Непозната команда: " + commandName);
                continue;
            }

            try {
                Command command = commands.get(commandType);
                command.execute(commandArgs);
            } catch (Exception e) {
                System.out.println("Грешка при изпълнение на командата: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("Налични команди:");
        for (CommandType type : CommandType.values()) {
            Command command = commands.get(type);
            System.out.printf("%-15s - %s%n", type.getName(), command.getDescription());
        }
    }
} 