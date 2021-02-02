package FileManager;

import SecuritySystem.ModelObject;

public class Catalog extends ModelObject {
    public Catalog(int id, String path, String name) {
        super(id, path, name);
    }

    @Override
    public String toString() {
        return "FileManager.Catalog || " +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name;
    }
}
