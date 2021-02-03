package SecuritySystem;

import java.util.ArrayList;
import java.util.List;

public class SecurityMatrix {
    List<MatrixUnit> securityMatrix = new ArrayList<MatrixUnit>();

    public boolean addRecord(ModelObject o, Subject s, List<AccessModifier> accessModifiers) {
        return  securityMatrix.add(new MatrixUnit(o, s, accessModifiers));
    }

    public List<AccessModifier> getAccessModifiers(Subject s, ModelObject o) {
        for (MatrixUnit mu: securityMatrix) {
            if (mu.subject.equals(s) && mu.modelObject.equals(o)) {
                return mu.accessModifiers;
            }
        }

        return null;
    }
}

class MatrixUnit {
    public ModelObject modelObject;
    public Subject subject;
    public List<AccessModifier> accessModifiers;

    public MatrixUnit(ModelObject modelObject, Subject subject, List<AccessModifier> accessModifiers) {
        this.modelObject = modelObject;
        this.subject = subject;
        this.accessModifiers = accessModifiers;
    }
}
