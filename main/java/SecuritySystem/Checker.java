package SecuritySystem;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private SecurityLevelGrid securityLevelGrid;
    private SecurityMatrix securityMatrix;

    public Checker(SecurityLevelGrid securityLevelGrid, SecurityMatrix securityMatrix) {
        this.securityLevelGrid = securityLevelGrid;
        this.securityMatrix = securityMatrix;
    }

    // проверки nwu / nrd
    List<AccessModifier> checkPrivilege(Subject s, ModelObject o) {
        List<AccessModifier> safeActions = new ArrayList<>();
        int cmp = securityLevelGrid.getSecurityLevel(s).compareTo(securityLevelGrid.getSecurityLevel(o));

        if (cmp == 0) {
            safeActions.add(AccessModifier.Read);
            safeActions.add(AccessModifier.Write);
            return safeActions;
        }

        if (cmp <= 0) {
            safeActions.add(AccessModifier.Read);
        } else {
            safeActions.add(AccessModifier.Write);
        }

        return safeActions;
    }

    public boolean checkAction(Subject s, ModelObject o, AccessModifier requiredAction) {
        List<AccessModifier> safeActions = checkPrivilege(s, o);

        for (AccessModifier am : safeActions) {
            if (am == requiredAction) {
                List<AccessModifier> availableActions = securityMatrix.getAccessModifiers(s, o);
                for (AccessModifier ao : availableActions) {
                    if (ao == requiredAction) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
