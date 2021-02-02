package FileManager;

import SecuritySystem.AccessModifier;
import SecuritySystem.Subject;

public interface IFileManager {
    // public boolean openCatalog(Subject s, Catalog c, AccessModifier am);
    public boolean createFile();
    public boolean deleteFile();
    public FileObjectFM copyFile(FileObjectFM file, String newPath);
}
