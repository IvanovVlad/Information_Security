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
    public FileObjectFM copyFile(FileObjectFM file, String newPath) {
        FileObjectFM copy = new FileObjectFM(file.id+1,newPath, file.type, file.catalog_id, file.name);
        return copy;
    }
}
