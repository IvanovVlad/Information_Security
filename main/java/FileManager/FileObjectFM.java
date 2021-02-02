package FileManager;

import SecuritySystem.ModelObject;

public class FileObjectFM extends ModelObject {
    public String type;
    public int catalog_id;

    public FileObjectFM(int id, String path, String type, int catalog_id, String name) {
        super(id, path, name);
        this.type = type;
        this.catalog_id = catalog_id;
    }

    @Override
    public String toString() {
        return "File || " +
                "type='" + type + '\'' +
                ", catalog_id=" + catalog_id +
                ", id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name;
    }
}
