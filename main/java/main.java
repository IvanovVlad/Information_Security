import FileManager.Catalog;
import FileManager.FileObjectFM;
import Form.MainForm;
import SecuritySystem.*;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Subject s1 = new Subject("Subject 1", "pass");
        Subject s2 = new Subject("Subject 2", "pass");
        Subject s3 = new Subject("Subject 3", "pass");
        Subject s4 = new Subject("Subject 4", "pass");
        Subject s5 = new Subject("Subject 5", "pass");
        Subject s6 = new Subject("Subject 6", "pass");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(s1);
        subjects.add(s2);
        subjects.add(s3);
        subjects.add(s4);
        subjects.add(s5);
        subjects.add(s6);

        ModelObject o1 = new Catalog(0, "o1", "1");
        ModelObject o2 = new Catalog(1, "o2", "2");
        ModelObject o3 = new FileObjectFM(2, "o3", "txt",0, "document");
        ModelObject o4 = new FileObjectFM(3, "o4", "png",0, "picture1");
        ModelObject o5 = new FileObjectFM(4, "o5", "jpg",0, "picture2");
        ModelObject o6 = new FileObjectFM(5, "o6", "exe",1, "executable");

        List<ModelObject> objects = new ArrayList<>();
        objects.add(o1);
        objects.add(o2);
        objects.add(o3);
        objects.add(o4);
        objects.add(o5);
        objects.add(o6);

        SecurityLevelGrid grid = new SecurityLevelGrid();
        grid.topSecret.addSubject(s1);
        grid.topSecret.addSubject(s2);
        grid.topSecret.addObject(o5);
        grid.secret.addSubject(s3);
        grid.secret.addObject(o1);
        grid.secret.addObject(o2);
        grid.confidential.addSubject(s4);
        grid.confidential.addObject(o4);
        grid.unclassified.addSubject(s5);
        grid.unclassified.addSubject(s6);
        grid.unclassified.addObject(o3);
        grid.unclassified.addObject(o6);

        SecurityMatrix matrix = new SecurityMatrix();
        matrix.addRecord(o1, s1, AccessModifier.Read);
        matrix.addRecord(o2, s1, AccessModifier.Read);
        matrix.addRecord(o3, s1, AccessModifier.Read);
        matrix.addRecord(o4, s1, AccessModifier.Read, AccessModifier.Delete);
        matrix.addRecord(o5, s1, AccessModifier.Read, AccessModifier.Write, AccessModifier.Delete);
        matrix.addRecord(o6, s1, AccessModifier.Read, AccessModifier.Delete);

        matrix.addRecord(o1, s2, AccessModifier.Read);
        matrix.addRecord(o2, s2, AccessModifier.Read);
        matrix.addRecord(o3, s2, AccessModifier.Read);
        matrix.addRecord(o4, s2, AccessModifier.Read);
        matrix.addRecord(o5, s2, AccessModifier.Read, AccessModifier.Write);
        matrix.addRecord(o6, s2, AccessModifier.Read);

        matrix.addRecord(o1, s3, AccessModifier.Read, AccessModifier.Write);
        matrix.addRecord(o2, s3, AccessModifier.Read, AccessModifier.Write);
        matrix.addRecord(o3, s3, AccessModifier.Read);
        matrix.addRecord(o4, s3, AccessModifier.Read);
        matrix.addRecord(o5, s3, AccessModifier.Write);
        matrix.addRecord(o6, s3, AccessModifier.Read);

        matrix.addRecord(o1, s4, AccessModifier.Write);
        matrix.addRecord(o2, s4, AccessModifier.Write);
        matrix.addRecord(o3, s4, AccessModifier.Read);
        matrix.addRecord(o4, s4, AccessModifier.Read);
        matrix.addRecord(o5, s4, AccessModifier.Write);
        matrix.addRecord(o6, s4, AccessModifier.Read);

        matrix.addRecord(o1, s5, AccessModifier.Write);
        matrix.addRecord(o2, s5, AccessModifier.Write);
        matrix.addRecord(o3, s5, AccessModifier.Read, AccessModifier.Write);
        matrix.addRecord(o4, s5, AccessModifier.Write);
        matrix.addRecord(o5, s5, AccessModifier.Write);
        matrix.addRecord(o6, s5, AccessModifier.Read, AccessModifier.Write);

        matrix.addRecord(o1, s6, AccessModifier.Write);
        matrix.addRecord(o2, s6, AccessModifier.Write);
        matrix.addRecord(o3, s6, AccessModifier.Read, AccessModifier.Write);
        matrix.addRecord(o4, s6, AccessModifier.Read);
        matrix.addRecord(o5, s6, AccessModifier.Write);
        matrix.addRecord(o6, s6, AccessModifier.Read, AccessModifier.Write);

//        System.out.println(grid.checkPrivilege(s4, o5)); // higher
//        System.out.println(grid.checkPrivilege(s4, o4)); // same
//        System.out.println(grid.checkPrivilege(s4, o3)); // lower privilege


//        if (grid.checkPrivilege(s4, o3)) {
//            System.out.println(matrix.getAccessModifiers(s4, o3));
//        }

//        Checker checker = new Checker(grid, matrix);

//        System.out.println(checker.checkAction(s4, o5, AccessModifier.Read));
//        System.out.println(checker.checkAction(s4, o5, AccessModifier.Write));
//        System.out.println();
//        System.out.println(checker.checkAction(s4, o4, AccessModifier.Read));
//        System.out.println(checker.checkAction(s4, o4, AccessModifier.Write));
//        System.out.println();
//        System.out.println(checker.checkAction(s4, o6, AccessModifier.Read));
//        System.out.println(checker.checkAction(s4, o6, AccessModifier.Write));

        // формочка
        MainForm form = new MainForm(subjects, objects, grid, matrix);

    }
}
