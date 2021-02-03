package FileManager;

import SecuritySystem.AccessModifier;
import SecuritySystem.Checker;
import SecuritySystem.ModelObject;
import SecuritySystem.Subject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements IFileManager {
    private Checker checker;
    private List<FileObjectFM> allFiles;
    private List<Catalog> catalogs;
    public DefaultListModel<FileObjectFM> DLMFiles;


    public FileManager(List<ModelObject> objects, Checker checker) {
        this.checker = checker;
        DLMFiles = new DefaultListModel<>();
        catalogs = new ArrayList<>();
        allFiles = new ArrayList<>();

        for (ModelObject o : objects) {
            if (o.getClass().getName() == "FileManager.Catalog") {
                catalogs.add((Catalog) o);
            } else {
                allFiles.add((FileObjectFM) o);
            }
        }
    }

    public List<FileObjectFM> getAllFiles() {
        return allFiles;
    }

    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public void openFile(Subject s, FileObjectFM file, AccessModifier am) {
        if (checker.checkAction(s, file, am)) JOptionPane.showMessageDialog(null, "Открыт файл " + file);
    }

    public void deleteFile(Subject s, FileObjectFM file, AccessModifier am) {
        if (checker.checkAction(s, file, am)) {
            DLMFiles.removeElement(file);
            allFiles.remove(file);
            JOptionPane.showMessageDialog(null, "Файл удален " + file);
        }
    }

    public void copyFile(Subject s, FileObjectFM o, Catalog c) {
        if (checker.checkAction(s, o, AccessModifier.Read) &&
                checker.checkAction(s, c, AccessModifier.Write)) {
            FileObjectFM file = new FileObjectFM("99", c.path, o.name, c.id, o.name + "(copy)");
            DLMFiles.addElement(file);
            allFiles.add(file);
            showFilesFromCatalog(s, c);
            JOptionPane.showMessageDialog(null, "Копия создана");
        }
    }

    public void createFile(Subject s, Catalog c, FileObjectFM o) {
        if (checker.checkAction(s, c, AccessModifier.Write)) {
            DLMFiles.addElement(o);
            allFiles.add(o);
            showFilesFromCatalog(s, c);
            JOptionPane.showMessageDialog(null, "Файл создан");
        }
    }

    public void showFilesFromCatalog(Subject s, Catalog c) {
        if (checker.checkAction(s, c, AccessModifier.Read)) {
            DLMFiles.clear();

            for (FileObjectFM f : allFiles) {
                if (c.id.equalsIgnoreCase(f.catalog_id)) {
                    DLMFiles.addElement(f);
                }
            }
        }
    }
}
