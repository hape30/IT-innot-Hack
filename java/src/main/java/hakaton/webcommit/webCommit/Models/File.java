package hakaton.webcommit.webCommit.Models;

public class File {
    private String path;

    public File() {}

    public File(String path) {
        this.path = path;
    }

    // Геттеры и сеттеры
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

