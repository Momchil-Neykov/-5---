// Абстрактен базов клас за всички команди
public interface Command {
    void execute(String[] args);
    String getDescription();
}
