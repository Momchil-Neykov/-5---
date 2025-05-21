public enum CommandType {
    OPEN("open", "Отваря файл с граматика"),
    CLOSE("close", "Затваря текущата граматика (премахва я от паметта)"),
    SAVEAS("saveas", "Записва граматиката във файл с ново име"),
    NEWGRAMMAR("newgrammar", "Създава нова граматика чрез въвеждане от конзолата"),
    LIST("list", "Списък с идентификаторите на всички прочетени граматики"),
    PRINT("print", "Извежда граматиката в подходящ формат. За всяко правило се отпечатва поредния номер"),
    SAVE("save", "Записва граматиката във файл"),
    ADD_RULE("addRule", "Добавя правила"),
    REMOVE_RULE("removeRule", "Премахване на правило по пореден номер"),
    UNION("union", "Намира обединението на две граматики и създава нова граматика. Отпечатва идентификатора на новата граматика"),
    CONCAT("concat", "Намира конкатенацията на две граматики и създава нова граматика. Отпечатва идентификатора на новата граматика"),
    CHOMSKY("chomsky", "Проверява дали дадена граматика е в нормална форма на Чомски"),
    CYK("cyk", "Проверява дали дадена дума е в езика на дадена граматика (CYK алгоритъм)"),
    ITER("iter", "Намира резултат от изпълнението на операцията 'итерация' (звезда на Клини) над граматика и създава нова граматика. Отпечатва идентификатора на новата граматика"),
    EMPTY("empty", "Проверява дали езикът на дадена контекстно-свободна граматика е празен"),
    CHOMSKIFY("chomsify", "Преобразува граматика в нормална форма на Чомски. Отпечатва идентификатора на новата граматика");

    private final String name;
    private final String description;

    CommandType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static CommandType fromName(String name) {
        for (CommandType type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
} 