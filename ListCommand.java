import java.util.Collection;

public class ListCommand extends BaseCommand {
    public ListCommand() {
        super("Списък с идентификаторите на всички прочетени граматики");
    }

    @Override
    public void execute(String[] args) {
        Collection<Grammar> grammars = Main.getAllGrammars();
        if (grammars.isEmpty()) {
            System.out.println("Няма заредени граматики.");
            return;
        }
        System.out.println("Заредени граматики:");
        for (Grammar grammar : grammars) {
            System.out.println(grammar.getId());
        }
    }
} 