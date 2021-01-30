package Authorization;

import SecuritySystem.Subject;

import java.util.List;

public class Authorization {
    public static Subject CURRENT_USER;

    public boolean login(Subject s, List<Subject> users) {
        boolean b = users.get(0).equals(s);
        if (users.contains(s)) {

            CURRENT_USER = s;
            return true;
        }
        return false;
    }

    public boolean login(String login, String password, List<Subject> users) {
        return login(new Subject(login, password), users);
    }
}
