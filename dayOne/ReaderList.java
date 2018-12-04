package adventOfCode.dayOne;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;

/**
 * Dato un file trasformarlo in una lista di input
 */

public class ReaderList {
    private List<String> file;

    public ReaderList(String nameFile) throws IOException {
        Path filePath = buildPath(nameFile);
        file = Files.readAllLines(filePath);
    }

    private Path buildPath(String nameFile) {
        assert (nameFile != null) : "nameFile can't be null";
        assert (nameFile.length() > 0) : "nameFile.length MUST be > 0";
        nameFile = "/src/main/java/adventOfCode/dayOne/" + nameFile;
        return FileSystems.getDefault().getPath(System.getProperty("user.dir") + nameFile);
    }

    public Vector<Integer> convertList (){
        Vector<Integer> fileInteger = new Vector<>(file.size());
        for (int i=0; i<file.size(); i++){
            fileInteger.addElement(Integer.parseInt(file.get(i)));
        }
        return fileInteger;
    }
}
