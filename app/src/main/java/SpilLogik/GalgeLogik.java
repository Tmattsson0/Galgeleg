package SpilLogik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import Data.Player;
import Data.TextReader;

public class GalgeLogik {
    /**
     * AHT afprøvning er muligeOrd synlig på pakkeniveau
     */

    TextReader txtrdr = new TextReader();
    public ArrayList<String> muligeOrd;
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;

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

        muligeOrd = txtrdr.wordsFromText();

        nulstil();
    }

    public void nulstil() {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
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


//    public static String hentUrl(String url) throws IOException {
//        System.out.println("Henter data fra " + url);
//        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
//        StringBuilder sb = new StringBuilder();
//        String linje = br.readLine();
//        while (linje != null) {
//            sb.append(linje + "\n");
//            linje = br.readLine();
//        }
//        return sb.toString();
//    }


//    public void hentOrdFraDr() throws Exception {
//        String data = hentUrl("https://dr.dk");
//        //System.out.println("data = " + data);
//
//        data = data.substring(data.indexOf("<body")). // fjern headere
//                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
//                replaceAll("&#198;", "æ"). // erstat HTML-tegn
//                replaceAll("&#230;", "æ"). // erstat HTML-tegn
//                replaceAll("&#216;", "ø"). // erstat HTML-tegn
//                replaceAll("&#248;", "ø"). // erstat HTML-tegn
//                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
//                replaceAll("&#229;", "å"). // erstat HTML-tegn
//                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
//                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
//                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord
//
//        System.out.println("data = " + data);
//        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
//        muligeOrd.clear();
//        muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));
//
//        System.out.println("muligeOrd = " + muligeOrd);
//        nulstil();
//    }

//    public String wordToStarConverter(String word) {
//        String starstr = "";
//
//        for (int i = 0; i < word.length(); i++){
//            starstr += "*";
//        }
//        return starstr;
//    }

    //Kode jeg har fundet på nettet og rettet i
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
}

