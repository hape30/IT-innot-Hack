package hakaton.webcommit.webCommit.Models;

public class Branch {
    private String name;

    public Branch() {}

    public Branch(String name) {
        this.name = name;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

