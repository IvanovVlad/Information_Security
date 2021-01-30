package SecuritySystem;

public class SecurityLevelGrid {
    public GridUnit topSecret = new GridUnit(AccessLevel.TopSecret);
    public GridUnit secret = new GridUnit(AccessLevel.Secret);
    public GridUnit confidential = new GridUnit(AccessLevel.Confidential);
    public GridUnit unclassified = new GridUnit(AccessLevel.Unclassified);

    public AccessLevel getSecurityLevel(ModelObject o) {
        if (topSecret.findObject(o)) return AccessLevel.TopSecret;
        if (secret.findObject(o)) return AccessLevel.Secret;
        if (confidential.findObject(o)) return AccessLevel.Confidential;
        if (unclassified.findObject(o)) return AccessLevel.Unclassified;
        return null;
    }
    public AccessLevel getSecurityLevel(Subject s) {
        if (topSecret.findSubject(s)) return AccessLevel.TopSecret;
        if (secret.findSubject(s)) return AccessLevel.Secret;
        if (confidential.findSubject(s)) return AccessLevel.Confidential;
        if (unclassified.findSubject(s)) return AccessLevel.Unclassified;
        return null;
    }

    public boolean checkPrivilege(Subject s, ModelObject o) {
        int cmp = getSecurityLevel(s).compareTo(getSecurityLevel(o));
        return cmp <= 0;
    }
}

