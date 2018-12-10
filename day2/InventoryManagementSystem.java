package adventOfCode.day2;

import adventOfCode.ReaderList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Mission: risolvere il problema del giorno due
 */

public class InventoryManagementSystem {

    private ReaderList rl;

    public InventoryManagementSystem(String nameFile, String day){
        try{
            this.rl = new ReaderList(nameFile, day);
        } catch (IOException ioe){
            System.err.println(ioe.toString());
        }
    }

    public int calculateChecksum(){
        int first = this.howManyWordsContainsManyEqualLetters(2);
        int second = this.howManyWordsContainsManyEqualLetters(3);
        int result = first * second;
        return result;
    }

    /**
     * REQUIRE: word to be sorted
     * @param word
     * @param howManyEqualLetters
     * @return
     */
    private boolean containsManyEqualLetters (String word, int howManyEqualLetters){
        boolean result = false;
        if (word.length()>howManyEqualLetters){
            if (word.charAt(0) == word.charAt(howManyEqualLetters-1) && word.charAt(0) != word.charAt(howManyEqualLetters)){
                result = true;
            } else {
                return containsManyEqualLetters(word.substring(howManyTimesThereIsALetter(word.charAt(0), word)), howManyEqualLetters);
            }
        }
        return result;
    }

    private int howManyTimesThereIsALetter (char letter, String word){
        int result = 0;
        for (int i=0; i<word.length(); i++){
            if (letter == word.charAt(i)){
                result = result + 1;
            }
        }
        return result;
    }

    private int howManyWordsContainsManyEqualLetters(int howManyEqualLetters){
        int result=0;
        List<String> theList = this.rl.getFile();
        for (int i=0; i<theList.size(); i++){
            String word = sort(theList.get(i)) + "0";
            if (this.containsManyEqualLetters(word, howManyEqualLetters)){
                result = result + 1;
            }
        }
        return result;
    }

    private String sort(String word){
        char[] tempArray = word.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public String commonLetters (){
        Vector<String> list = findSemiEqualStrings();
        String first = list.get(0);
        String second = list.get(1);
        String res = "";
        for (int i=0; i<first.length(); i++){
            if (first.charAt(i) == second.charAt(i)){
                res = res + first.charAt(i);
            }
        }
        return res;
    }

    private Vector<String> findSemiEqualStrings(){
        List<String> inputs = this.rl.getFile();
        Vector<String> risultato = new Vector<>();
        boolean trovato = false;

        for (int i=0; i<inputs.size(); i++){
            int errore=0;
            String current = inputs.get(i);
            for (int j = i+1; j<inputs.size(); j++){
                if (isFounded(trovato)) break;
                String daConfrontare = inputs.get(j);
                errore = 0;

                for (int k = 0; k < current.length(); k++) {
                    if (!(current.charAt(k) == daConfrontare.charAt(k))) {
                        errore = errore + 1;
                        if (errore == 2) {
                            break;
                        }
                    }
                    if (k==current.length()-1){
                        risultato.addElement(current);
                        risultato.addElement(daConfrontare);
                        trovato = true;
                        break;
                    }
                }
            }
            if (isFounded(trovato)) break;
        }
        return risultato;

    }

    private boolean isFounded(boolean trovato) {
        if (trovato) {
            return true;
        }
        return false;
    }
}
