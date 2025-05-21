public class PrintCommand extends BaseCommand {
    public PrintCommand() {
        super("Извежда граматиката в подходящ формат. За всяко правило се отпечатва поредния номер");
    }

    @Override
    public void execute(String[] args) {
        validateArgs(args, 1, "print <id>");
        Grammar grammar = getGrammar(args[0]);
        System.out.println(grammar);
    }
} 