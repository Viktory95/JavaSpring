package builders;

import entities.FileData;
import entities.TreePoint;
import loaders.PropertyLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class provides functions
 * to retrieve data
 */
public final class FileBuilder {

    //because of all methods are static
    private FileBuilder() {
    }


    /**
     * Function get file name,
     * get file text and
     * return structure FileData
     *
     * @param activePath - current file is clicked by user
     */
    public static FileData getFileData(String activePath) {

        //get file path
        Path path = Paths.get(activePath);

        //get file text
        List<String> stringList = null;
        try {
            stringList = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //converting List to String
        String s = stringList.stream().map(Object::toString).collect(Collectors.joining("\n"));

        return new FileData(path.getFileName().toString(), s);
    }
}
