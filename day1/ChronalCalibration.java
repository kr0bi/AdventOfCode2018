package adventOfCode.day1;

import java.util.Iterator;
import java.util.Vector;

/**
 * MISSION: Risolvere il primo problema dell'advent of code 2018
 */

public class ChronalCalibration {
    /**
     * list: vettore di interi che conterra' gli input
     */
    private Vector<Integer> list;

    /**
     * ChronalCalibration: costruttore che inizializza list con file
     * @param file: vettore di interi da copiare all'interno di list
     */
    public ChronalCalibration (Vector<Integer> file){
        list = new Vector<Integer>();
        for (int i = 0; i<file.size(); i++){
            list.addElement(file.elementAt(i));
        }
    }

    /**
     * @return la frequenza risultate
     */
    public int calculateFrequency (){
        int result = 0;
        for (int i=0; i<list.size(); i++){
            result = result + list.elementAt(i);
        }
        return result;
    }

    /**
     * @return calcola la prima frequenza che si ripete
     */
    public int repeatedFrequency() {
        Vector<Integer> frequencies = new Vector<>();
        frequencies.addElement(0);

        Vector<Integer> inputs = list;

        boolean isntFound = true;

        int result = -999999999;
        int j = 0;

        while (isntFound) {
            for (int i=0; i<inputs.size(); i++){

                if (isFound(frequencies, inputs.elementAt(i) + frequencies.elementAt(j))){
                    result = inputs.elementAt(i) + frequencies.elementAt(j);
                    isntFound = false;
                    break;
                } else {
                    frequencies.addElement(inputs.elementAt(i) + frequencies.elementAt(j));
                }
                j = j + 1;
            }
        }
        return result;


    }

    /**
     * isFound: cerca se element e' presente in lista
     * @param lista elementi in cui cercare l'elemento
     * @param element elemento da cercare
     * @return true se il valore e' stato trovato, false altrimenti
     */
    private boolean isFound(Vector<Integer> lista, int element){
        boolean found = false;
        int i = 0;
        Iterator<Integer> it = lista.iterator();
        while (it.hasNext()){
            it.next();
            if (lista.elementAt(i) == element){
                found = true;
            }
            i++;
        }
        return found;
    }

}
