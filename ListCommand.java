public class ListCommand extends Command {
    public ListCommand() {
        super("list", "Списък с идентификаторите на всички прочетени граматики");
    }

    @Override
    public void execute(String[] args) {
        // TODO: Implement listing of all loaded grammars
        System.out.println("Executing list command");
    }
} 