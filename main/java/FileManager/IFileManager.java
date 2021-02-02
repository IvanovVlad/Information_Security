package FileManager;

import SecuritySystem.AccessModifier;
import SecuritySystem.Subject;


public interface IFileManager {
    public void openFile(Subject s, FileObjectFM file, AccessModifier am);

    void deleteFile(Subject s, FileObjectFM file, AccessModifier am);

    void copyFile(Subject s, FileObjectFM o, Catalog c);

    void createFile(Subject s, Catalog c, FileObjectFM o);
}