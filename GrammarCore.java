import java.util.*;

// Абстрактен базов клас за всички команди
public abstract class Command {
    protected String name;
    protected String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void execute(String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

// Клас за представяне на правило в граматиката
class Rule {
    private char leftSide;      // Нетерминал (главна латинска буква)
    private String rightSide;   // Низ от терминали и нетерминали

    public Rule(char leftSide, String rightSide) {
        if (!Character.isUpperCase(leftSide)) {
            throw new IllegalArgumentException("Left side must be an uppercase letter (non-terminal)");
        }
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public char getLeftSide() {
        return leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    @Override
    public String toString() {
        return leftSide + " -> " + rightSide;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rule that = (Rule) obj;
        return leftSide == that.leftSide && rightSide.equals(that.rightSide);
    }

    @Override
    public int hashCode() {
        return 31 * leftSide + rightSide.hashCode();
    }
}

// Клас за представяне на контекстно-свободна граматика
class Grammar {
    private String id;
    private Set<Character> nonTerminals;  // Главни латински букви
    private Set<Character> terminals;     // Малки латински букви и цифри
    private List<Rule> productions;
    private char startSymbol;

    public Grammar(String id) {
        this.id = id;
        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();
        this.productions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addRule(Rule rule) {
        productions.add(rule);
        nonTerminals.add(rule.getLeftSide());
        for (char c : rule.getRightSide().toCharArray()) {
            if (Character.isUpperCase(c)) {
                nonTerminals.add(c);
            } else {
                terminals.add(c);
            }
        }
    }

    public void setStartSymbol(char startSymbol) {
        this.startSymbol = startSymbol;
    }

    public List<Rule> getRules() {
        return new ArrayList<>(productions);
    }

    public Set<Character> getNonTerminals() {
        return new HashSet<>(nonTerminals);
    }

    public Set<Character> getTerminals() {
        return new HashSet<>(terminals);
    }

    public char getStartSymbol() {
        return startSymbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grammar ").append(id).append(":\n");
        for (int i = 0; i < productions.size(); i++) {
            sb.append(i + 1).append(". ").append(productions.get(i)).append("\n");
        }
        return sb.toString();
    }
} 