import java.util.*;

public class Grammar {
    private String id;
    private Set<Character> nonTerminals;  // Главни латински букви
    private Set<Character> terminals;     // Малки латински букви и цифри
    private List<Production> productions = new ArrayList<>();
    private char startSymbol;

    public Grammar(String id) {
        this.id = id;
        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void addProduction(Production production) {
        productions.add(production);
        nonTerminals.add(production.getLeftSide());
        for (char c : production.getRightSide().toCharArray()) {
            if (Character.isUpperCase(c)) {
                nonTerminals.add(c);
            } else {
                terminals.add(c);
            }
        }
        if (startSymbol == '\0') {
            startSymbol = production.getLeftSide();
        }
    }

    public void setStartSymbol(char startSymbol) {
        this.startSymbol = startSymbol;
    }

    public List<Production> getProductions() {
        return productions;
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