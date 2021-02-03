import FileManager.Catalog;
import FileManager.FileObjectFM;
import SecuritySystem.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CsvParser implements ICsvParser {
    List<Subject> subjects;
    List<ModelObject> objects;
    SecurityLevelGrid grid;
    SecurityMatrix matrix;

    @Override
    public CsvParser parse(String path) throws IOException {
        subjects = new ArrayList<>();
        objects = new ArrayList<>();
        grid = new SecurityLevelGrid();
        matrix = new SecurityMatrix();

        List<String> fileLines = Files.readAllLines(Paths.get(path));
        for (String fl : fileLines) {
            String[] params = fl.split(",");

            switch (params[0]) {
                case "subject":
                    makeSubject(params);
                    break;
                case "catalog":
                    makeObjectCatalog(params);
                    break;
                case "file":
                    makeObjectFile(params);
                    break;
                case "grid":
                    makeGridUnit(params[2], params[1]);
                    break;
                case "matrix":
                    makeMatrixUnit(params[1], params[2], params[3]);
                    break;
                default:
                    break;
            }
        }

        return this;
    }

    private void makeMatrixUnit(String idObj, String idSubj, String access) {
        ModelObject object;
        Subject subject;

        List<AccessModifier> accessModifiers = new ArrayList<>();

        if (access.contains("r")) accessModifiers.add(AccessModifier.Read);
        if (access.contains("w")) accessModifiers.add(AccessModifier.Write);
        if (access.contains("d")) accessModifiers.add(AccessModifier.Delete);

        for (Subject s : subjects) {
            if (s.id.equalsIgnoreCase(idSubj)) {
                for (ModelObject o : objects) {
                    if (o.id.equalsIgnoreCase(idObj)) {
                        matrix.addRecord(o, s, accessModifiers);
                        return;
                    }
                }
            }
        }
    }

    private void makeGridUnit(String id, String access) {
        for (Subject s : subjects) {
            if (s.id.equalsIgnoreCase(id)) {
                addToGrid(access, s);
                return;
            }
        }

        for (ModelObject o : objects) {
            if (o.id.equalsIgnoreCase(id)) {
                addToGrid(access, o);
                return;
            }
        }
    }

    private void addToGrid(String access, ModelObject o) {
        switch (access.toLowerCase(Locale.ROOT)) {
            case "topsecret":
                grid.topSecret.addObject(o);
                break;
            case "secret":
                grid.secret.addObject(o);
                break;
            case "confidential":
                grid.confidential.addObject(o);
                break;
            case "unclassified":
                grid.unclassified.addObject(o);
                break;
        }
    }

    private void addToGrid(String access, Subject s) {
        switch (access.toLowerCase(Locale.ROOT)) {
            case "topsecret":
                grid.topSecret.addSubject(s);
                break;
            case "secret":
                grid.secret.addSubject(s);
                break;
            case "confidential":
                grid.confidential.addSubject(s);
                break;
            case "unclassified":
                grid.unclassified.addSubject(s);
                break;
        }
    }

    private void makeObjectCatalog(String[] params) {
        Catalog c = new Catalog(params[1], params[2], params[3]);
        objects.add(c);
    }

    private void makeObjectFile(String[] params) {
        FileObjectFM file = new FileObjectFM(
                params[1],
                params[2],
                params[3],
                params[4],
                params[5]);
        objects.add(file);
    }

    private void makeSubject(String[] params) {
        Subject s = new Subject(params[1], params[2], params[3]);
        subjects.add(s);
    }
}
