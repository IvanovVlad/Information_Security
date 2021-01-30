package SecuritySystem;

import java.util.Objects;

public class Subject {
    public String name;
    public String password;

    public Subject(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Subject)) {
            return false;
        }

        Subject s = (Subject) o;
        return this.name == s.name && this.password == s.password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
