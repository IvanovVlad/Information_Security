package FileManager;

public interface IFileManager {
    public boolean openCatalog();
    public boolean createFile();
    public boolean deleteFile();
    public FileObject copyFile(FileObject file, String newPath);
}
