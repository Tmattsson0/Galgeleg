package SpilLogik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Data.Singleton;
import Data.Words;

public class GalgeLogik {
    /**
     * AHT afprøvning er muligeOrd synlig på pakkeniveau
     */

//    JSONReader txtrdr = new JSONReader();

    Singleton p = Singleton.getInstance();

    public ArrayList<Words> muligeOrd;
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private String customWord;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private boolean isCustomWord;

    public String getCustomWord() {
        return customWord;
    }

    public void setCustomWord(String customWord) {
        this.customWord = customWord;
    }

    public boolean isCustomWord() {
        return isCustomWord;
    }

    public void setIsCustomWord(boolean customWord) {
        this.isCustomWord = customWord;
    }

    public boolean isSpilletErVundet() {
        return spilletErVundet;
    }

    public void setSpilletErVundet(boolean spilletErVundet) {
        this.spilletErVundet = spilletErVundet;
    }

    public boolean isSpilletErTabt() {
        return spilletErTabt;
    }

    public void setSpilletErTabt(boolean spilletErTabt) {
        this.spilletErTabt = spilletErTabt;
    }

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }


    public GalgeLogik() throws IOException {
        muligeOrd = p.getWordArrayList();
        customWord = p.getCustomWord().getWord();
        nulstil();
    }

    public void nulstil() {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        if(!p.isCustomWord()){
            ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size())).getWord().toLowerCase();
        } else {
            ordet = customWord.toLowerCase();
        }
        p.setIsCustomWord(false);
        opdaterSynligtOrd();
    }


    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                spilletErVundet = false;
            }
        }
    }

    public void gætBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 6) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
    }

    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }

    //Returns a string with only the wrong letters.
    //TODO Make it ad ", " back in after removing right letters
    public String wrongLetters(String str1, String str2) {
        String one = str1;
        String two = str2;
        StringBuilder s = new StringBuilder();
        int a = 0;

        Set<Character> set = new HashSet<>();
        for (char c : two.toCharArray())
            set.add(c); //add all second string character to set
        for (char c : one.toCharArray()) {
            if (!set.contains(c)) //check if the character is not one of the character of second string
                s.append(c); //append the current character to the pool
        }

        System.out.println("LOL" + s);
        return s.toString().replace(", ", "");
    }

//    public void customWordStart(String customWord) {
//        setIsCustomWord(true);
//    }
}

