package entities;

/**
 * For tree data json
 */
public class TreePoint {

    private String elementName;
    private Long parentId;
    private Long elementId;
    private String name;
    private String path;
    private String type;

    public TreePoint(String elementName, Long parentId, Long elementId, String name, String path, String type) {
        this.elementName = elementName;
        this.elementId = elementId;
        this.parentId = parentId;
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElementName() {

        return elementName;
    }

    public Long getElementId() {
        return elementId;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TreePoint{" +
                "elementName='" + elementName + '\'' +
                ", parentId=" + parentId +
                ", elementId=" + elementId +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
