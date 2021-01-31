package SecuritySystem;

import java.util.Objects;

public class ModelObject {
    public int id;
    public String path;
    public String name;

    public ModelObject(int id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ModelObject)) {
            return false;
        }

        ModelObject s = (ModelObject) o;
        return (this.id == s.id) && this.path.equals(s.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }

    @Override
    public String toString() {
        return "ModelObject{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
