package Data;

import java.io.IOException;
import java.util.ArrayList;

public class Singleton {


    ArrayList<Words> wordArrayList = new ArrayList<>();
    JSONReader json = new JSONReader();


    private Words customWord;


    private boolean isCustomWord;

    private static Singleton Instance = null;

    public static Singleton getInstance() {
        if(Instance == null){
            Instance = new Singleton();
        }
        return Instance;
    }

    public Singleton() {
    }


    public void createList() throws IOException {
        wordArrayList = json.wordsFromText();
    }

    public ArrayList<Words> getWordArrayList() {
        return wordArrayList;
    }

    public Words getCustomWord() {
        return customWord;
    }

    public void setCustomWord(Words customWord) {
        this.customWord = customWord;
    }

    public boolean isCustomWord() {
        return isCustomWord;
    }

    public void setIsCustomWord(boolean customWord) {
        isCustomWord = customWord;
    }
}
