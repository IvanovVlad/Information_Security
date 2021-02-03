package FileManager;

import SecuritySystem.ModelObject;

public class Catalog extends ModelObject {
    public Catalog(String id, String path, String name) {
        super(id, path, name);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Catalog || " +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name;
    }
}
