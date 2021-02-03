package FileManager;

import SecuritySystem.ModelObject;

public class FileObjectFM extends ModelObject {
    public String type;
    public String catalog_id;

    public FileObjectFM(String id, String path, String type, String catalog_id, String name) {
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
