package FileManager;

public interface IFileManager {
    public boolean openCatalog();
    public boolean createFile();
    public boolean deleteFile();
    public FileObjectFM copyFile(FileObjectFM file, String newPath);
}
