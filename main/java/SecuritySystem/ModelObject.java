package SecuritySystem;

import java.util.Objects;

public class ModelObject {
    public int id;
    public String path;

    public ModelObject(int id, String path) {
        this.id = id;
        this.path = path;
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
        return this.id == s.id && this.path == s.path;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }
}
