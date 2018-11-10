package Data;

public class Words {

    private String id;
    private String word;
    private String definition;

    public Words(String id, String word, String definition) {
        this.id = id;
        this.word = word;
        this.definition = definition;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
