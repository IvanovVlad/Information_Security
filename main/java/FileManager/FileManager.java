package FileManager;

public class FileManager implements IFileManager {
    public boolean openCatalog () {
        return false;
    }

    @Override
    public boolean createFile() {
        return false;
    }

    @Override
    public boolean deleteFile() {
        return false;
    }

    @Override
    public FileObject copyFile(FileObject file, String newPath) {
        FileObject copy = new FileObject(file.id+1,newPath, file.type, file.catalog_id, file.name);
        return copy;
    }
}
