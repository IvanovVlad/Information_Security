import SecuritySystem.ModelObject;

public class File extends ModelObject {
    public String type;
    public String catalog_id;

    public File(int id, String path, String type, String catalog_id) {
        super(id, path);
        this.type = type;
        this.catalog_id = catalog_id;
    }
}
