package SecuritySystem;

import java.util.ArrayList;
import java.util.List;

public class GridUnit {
    private AccessLevel accessLevel;
    private List<ModelObject> modelObjects;
    private List<Subject> subjects;

    public GridUnit(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        this.modelObjects = new ArrayList<ModelObject>();
        this.subjects = new ArrayList<Subject>();
    }

    public GridUnit(AccessLevel accessLevel, List<ModelObject> modelObjects, List<Subject> subjects) {
        this.accessLevel = accessLevel;
        this.modelObjects = modelObjects;
        this.subjects = subjects;
    }

    public boolean addSubject(Subject s) {
        return subjects.add(s);
    }

    public boolean addObject(ModelObject o) {
        return modelObjects.add(o);
    }

    public boolean findSubject(Subject s) {
        return subjects.contains(s);
    }

    public boolean findObject(ModelObject o) {
        return modelObjects.contains(o);
    }
}
