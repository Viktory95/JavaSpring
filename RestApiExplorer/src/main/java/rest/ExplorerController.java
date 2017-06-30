package rest;

import builders.FileBuilder;
import builders.TreeBuilder;
import entities.FileData;
import entities.TreePoint;
import loaders.PropertyLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Class provides views
 */
@Controller
public class ExplorerController {

    /**
     * Main view with fileTree and textBox
     */
    @RequestMapping("/")
    public String greeting() {
        return "filetree";
    }

    /**
     * Response with
     *
     * @param path - server root directory
     *             or directory is clicked by user
     * @return <List<TreePoint>> - json structure
     * with files and directories information
     */
    @RequestMapping(value = "/treedata", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TreePoint>> getTree(@RequestParam(value = "path", required = false, defaultValue = "") String path) {

        TreeBuilder treeBuilder = new TreeBuilder();
        List<TreePoint> treePoints = treeBuilder.build(path);

        return new ResponseEntity<List<TreePoint>>(treePoints, HttpStatus.OK);
    }

    /**
     * Response with
     * @param path - empty path
     *             or file is clicked by user
     * @return FileData - json structure
     * with information about file*/
    @RequestMapping(value="/file", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FileData> getFile (@RequestParam(value = "path", required = false, defaultValue = "") String path) {

        FileData fileData = FileBuilder.getFileData(path);

        return new ResponseEntity<FileData>(fileData, HttpStatus.OK);
    }

    @RequestMapping(value = "/path", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET)
    @ResponseBody
    public String setPath(@RequestParam(value = "path", required = false, defaultValue = "") String path) {
        return PropertyLoader.getInstance().setProperty("path", path);
    }

}