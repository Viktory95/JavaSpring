package entities;

/**
 * For file data json
 */
public class FileData {
    private String fileName;
    private String text;

    public FileData(String fileName, String text) {
        this.fileName = fileName;
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public String getText() {
        return text;
    }
}
