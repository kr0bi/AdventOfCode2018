package adventOfCode.day3;

import adventOfCode.ReaderList;

import java.io.IOException;
import java.util.List;

public class NoMatterHowYouSliceIt {
    /**
     * Immutable object
     */
    private int[][] fabric;
    private int altezza;
    private int lato;

    /**
     * INNER CLASS: Parser per trovare i valori all'interno del claim
     */
    private class ParserClaim{
        List<String> inputs;
        ParserClaim (){
            ReaderList rl = null;
            try {
                rl = new ReaderList("input", "3");
            } catch (IOException ioe){
                System.err.println(ioe.toString());
            }

            inputs = rl.getFile();
        }
        int maxLato (){
            int result=0;
            result = findSx(inputs.get(0)) + findColonna(inputs.get(0));
            for (int i=1; i<inputs.size(); i++){
                int somma = findSx(inputs.get(i)) + findColonna(inputs.get(i));
                if (somma > result){
                    result = somma;
                }
            }
            return result;
        }
        int maxAltezza(){
            int result=0;
            result = findTop(inputs.get(0)) + findRiga(inputs.get(0));
            for (int i=1; i<inputs.size(); i++){
                int somma = findTop(inputs.get(i)) + findRiga(inputs.get(i));
                if (somma > result){
                    result = somma;
                }
            }
            return result;

        }
        private int findChar(String claim, char c) {
            int atPuntatore = 0;
            for (int i = 0; i < claim.length(); i++) {
                if (claim.charAt(i) == c) {
                    atPuntatore = i;
                }
            }
            return atPuntatore;
        }
        private int findNumberClaim(String claim){
            int res=0;
            int atPuntatore = findChar(claim, '@');
            res = Integer.parseInt(claim.substring(1, atPuntatore-1));
            return res;
        }
        private int findSx (String claim){
            int res=0;
            int atPuntatore = findChar(claim, '@');
            int virgolaPuntatore = findChar(claim, ',');
            res = Integer.parseInt(claim.substring(atPuntatore+2, virgolaPuntatore));
            return res;
        }
        private int findColonna(String claim){
            int res=0;
            int xPuntatore = findChar(claim, 'x');
            res = Integer.parseInt(claim.substring(xPuntatore+1));
            return res;
        }
        private int findTop(String claim){
            int res=0;
            int virgolaPuntatore = findChar(claim, ',');
            int doppiPuntiPuntatore = findChar(claim, ':');
            res = Integer.parseInt(claim.substring(virgolaPuntatore+1, doppiPuntiPuntatore));
            return res;
        }
        private int findRiga(String claim){
            int res=0;
            int dopppiPuntiPuntatore = findChar(claim, ':');
            int xPuntatore = findChar(claim, 'x');
            res = Integer.parseInt(claim.substring(dopppiPuntiPuntatore+2, xPuntatore));
            return res;
        }

    }

    public NoMatterHowYouSliceIt(){
        ParserClaim  pc = new ParserClaim();
        altezza = pc.maxAltezza();
        lato = pc.maxLato();
        inizializzaFabric();
        insertValue();
    }

    private void inizializzaFabric() {
        fabric = new int[altezza+1][lato+1];
        for (int i=0; i<fabric.length; i++){
            for (int j=0; j<fabric[0].length; j++){
                fabric[i][j] = 0;
            }
        }
    }

    private void insertValue(){
        ParserClaim pc = new ParserClaim();
        //for every claim insert value
        //so get the first claim
        String claim;
        for (int i=0; i<pc.inputs.size(); i++){

            //find the coordinates
            claim = pc.inputs.get(i);
            int sx = pc.findSx(claim);
            int top = pc.findTop(claim);
            int colonne = pc.findColonna(claim);
            int righe = pc.findRiga(claim);
            for (int x = top; x<colonne+top; x++){
                for (int y = sx; y<righe+sx; y++){
                    if (fabric[x][y] == 0){
                        fabric[x][y] = pc.findNumberClaim(claim);
                    } else {
                        fabric[x][y] = -1;
                    }
                }
            }
        }
    }

    public int findNonSovrapposto() {
        ParserClaim pc = new ParserClaim();
        int result = -1;
        //for every claim insert value
        //so get the first claim
        String claim;
        boolean[] claimsCheck = new boolean[pc.inputs.size()];

        for (int i = 0; i < pc.inputs.size(); i++) {

            boolean check = true;
            //find the coordinates
            claim = pc.inputs.get(i);
            int sx = pc.findSx(claim);
            int top = pc.findTop(claim);
            int colonne = pc.findColonna(claim);
            int righe = pc.findRiga(claim);
            for (int x = top; x<colonne+top; x++){
                if (!check){
                    break;
                } else {
                    for (int y = sx; y<righe+sx; y++){
                        if (!check){
                            break;
                        } else {
                            if (fabric[x][y] != pc.findNumberClaim(claim)) {
                                check = false;
                            }
                        }
                    }
                }
            }
            if (!check){
                claimsCheck[i] = false;
            } else {
                claimsCheck[i] = true;
            }
        }
        for (int i=0; i<claimsCheck.length; i++){
            if (claimsCheck[i]==true){
                result = i+1;
            }
        }
        return result;
    }

    public void viewMatrix() {
        for (int i = 0; i < this.fabric.length; i++) {
            for (int j = 0; j < this.fabric[0].length; j++) {
                System.out.format("%3d ", fabric[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int howManySquareInchesOfFabricAreWithingTwoOrMoreClaims(){
        int res=0;
        for (int i = 0; i < fabric.length; i++) {
            for (int j = 0; j < fabric[0].length; j++) {
                if (fabric[i][j] == -1){
                    res = res + 1;
                }
            }
        }
        return res;
    }
}
