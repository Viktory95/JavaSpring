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
public final class DataBuilder {

    //because of all methods are static
    private DataBuilder() {
    }

    /**
     * Function get directory tree
     * and add its in a List
     *
     * @param dir - root server directory or
     *            directory is clicked by user
     */
    public static List<TreePoint> getFileTreeList(String dir) {

        String thisDir = dir;

        //if dir is empty
        if ("".equals(thisDir) || thisDir == null) {
            if ("".equals(PropertyLoader.getInstance().getProperty("path"))) {
                Path currentRelativePath = Paths.get("");
                thisDir = currentRelativePath.toAbsolutePath().toString();
            } else {
                thisDir = PropertyLoader.getInstance().getProperty("path");
            }
        }

        List<TreePoint> treePoints = new ArrayList<>();

        thisDir = intoPath(thisDir);

        //if directory exists then build tree
        if (new File(thisDir).exists()) {
            String[] files = new File(thisDir).list(new FilenameFilter() {
                public boolean accept(File thisDir, String name) {
                    return name.charAt(0) != '.';
                }
            });
            Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
            // All directories
            for (String file : files) {
                if (new File(thisDir, file).isDirectory()) {
                    treePoints.add(new TreePoint(file, thisDir + file, "dir"));
                }
            }
            // All files
            for (String file : files) {
                if (!new File(thisDir, file).isDirectory()) {
                    int dotIndex = file.lastIndexOf('.');
                    String ext = dotIndex > 0 ? file.substring(dotIndex + 1) : "";
                    treePoints.add(new TreePoint(file, thisDir + file, "file"));
                }
            }
        }

        return treePoints;
    }

    public static String intoPath(String thisDirPrev) {
        String thisDir = thisDirPrev;
        //in case if path contains '\\' instead '/'
        for (int i = 0; i < thisDir.length() - 1; i++) {
            if ("\\".equals(thisDir.substring(i, i + 1))) {
                thisDir = thisDir.substring(0, i) + "/" + thisDir.substring(i + 1, thisDir.length());
            }
        }
        if (thisDir.charAt(thisDir.length() - 1) == '\\') {
            thisDir = thisDir.substring(0, thisDir.length() - 1) + "/";
        } else if (thisDir.charAt(thisDir.length() - 1) != '/') {
            thisDir += "/";
        }
        return thisDir;
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
        String s = stringList.stream().map(Object::toString).collect(Collectors.joining(","));

        return new FileData(path.getFileName().toString(), s);
    }
}
