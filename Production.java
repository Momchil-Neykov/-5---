// Клас за представяне на правило в граматиката
public class Production {
    private char leftSide;      // Нетерминал (главна латинска буква)
    private String rightSide;   // Низ от терминали и нетерминали

    public Production(char leftSide, String rightSide) {
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
        Production that = (Production) obj;
        return leftSide == that.leftSide && rightSide.equals(that.rightSide);
    }

    @Override
    public int hashCode() {
        return 31 * leftSide + rightSide.hashCode();
    }
}
