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
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
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
        return this.name.equals(s.name) && this.password.equals(s.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
