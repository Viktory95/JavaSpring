package builders;

import entities.TreePoint;
import loaders.PropertyLoader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Виктория on 27.06.2017.
 */
public class FileAssert {

    private static Long id = new Long(0);
    static List<TreePoint> list;

    public FileAssert(){
        list = new ArrayList<>();
    }

    public List<TreePoint> build(String path) {
        String thisDir = path;

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

        walkin(new File(thisDir)); //Replace this with a suitable directory

        //list.stream().filter(treePoint -> treePoint.getElementId() == 4831).forEach(System.err::println);

        return list;
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
     * Recursive function to descend into the directory tree and find all the files
     *
     * @param dir A file object defining the top directory
     **/
    private void walkin(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            Long parentId = new Long(id);
            Long ulId = new Long(id+1);
            list.add(new TreePoint("ul", parentId, ulId, "", "", ""));
            id++;
            parentId = new Long(id);
            for (int i = 0; i < listFile.length; i++) {
                id++;
                if (listFile[i].isDirectory()) {
                    list.add(new TreePoint("li", parentId, id, listFile[i].getName(), listFile[i].getAbsolutePath(), "dir"));
                    walkin(listFile[i]);
                } else {
                    list.add(new TreePoint("li", parentId, id, listFile[i].getName(), listFile[i].getAbsolutePath(), "file"));
                    //System.out.println("+---" + listFile[i].getName().toString());

                }
            }
        }
    }
}
