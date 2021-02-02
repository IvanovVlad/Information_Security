package SecuritySystem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Checker {
    private SecurityLevelGrid securityLevelGrid;
    private SecurityMatrix securityMatrix;

    public Checker(SecurityLevelGrid securityLevelGrid, SecurityMatrix securityMatrix) {
        this.securityLevelGrid = securityLevelGrid;
        this.securityMatrix = securityMatrix;
    }

    // проверки nru / nwd
    List<AccessModifier> checkPrivilege(Subject s, ModelObject o) {
        List<AccessModifier> safeActions = new ArrayList<>();
        int cmp = securityLevelGrid.getSecurityLevel(s).compareTo(securityLevelGrid.getSecurityLevel(o));

        if (cmp == 0) {
            safeActions.add(AccessModifier.Read);
            safeActions.add(AccessModifier.Write);
            safeActions.add(AccessModifier.Delete);
            return safeActions;
        }

        if (cmp <= 0) {
            safeActions.add(AccessModifier.Read);
            safeActions.add(AccessModifier.Delete);

        } else {
            safeActions.add(AccessModifier.Write);
        }

        return safeActions;
    }

    public boolean checkAction(Subject s, ModelObject o, AccessModifier requiredAction) {
        List<AccessModifier> safeActions = checkPrivilege(s, o);
        String message = "Нет права доступа на уровне решетки";

        for (AccessModifier am : safeActions) {
            if (am == requiredAction) {
                List<AccessModifier> availableActions = securityMatrix.getAccessModifiers(s, o);

                if (availableActions.size() == 0){
                    message = "Нет права доступа на уровне матрицы";
                    JOptionPane.showMessageDialog(null, message);
                    return false;
                }
                for (AccessModifier ao : availableActions) {
                    if (ao == requiredAction) {
                        return true;
                    } else {
                        message = "Нет права доступа на уровне матрицы";
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, message);
        return false;
    }
}
