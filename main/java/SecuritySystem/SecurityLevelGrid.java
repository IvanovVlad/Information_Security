package SecuritySystem;

// сурс для формул: https://learn.urfu.ru/resource/index/data/resource_id/40980/revision_id/0 -- Bell la Padulla Model
// now read up   -- не может читать файлы с уровнем секретности выше
// no write down -- может записывать в файлы и папки выше уровнем

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
}

