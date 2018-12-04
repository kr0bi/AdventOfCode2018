package adventOfCode.dayOne;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ChronalCalibrationTest {

    public static void main(String[] args) {

        ChronalCalibration theChronalCalibration = getInputs();

        int firstResult, secondResult;
        firstResult = theChronalCalibration.calculateFrequency();
        secondResult = theChronalCalibration.repeatedFrequency();
        System.out.format("Frequenza totale: %d \nFrequenza doppia: %d", firstResult, secondResult);
    }

    @NotNull
    private static ChronalCalibration getInputs() {
        ReaderList theReaderList = null;
        theReaderList = letturaInput(theReaderList);
        return new ChronalCalibration(theReaderList.convertList());
    }

    private static ReaderList letturaInput(ReaderList theReaderList) {
        try {
            theReaderList = new ReaderList("input");
        } catch (IOException ioe){
            System.err.println(ioe.toString() + " file non trovato!");
        }
        return theReaderList;
    }
}
