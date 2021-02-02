package Form;

import Authorization.Authorization;
import FileManager.FileManager;
import FileManager.Catalog;
import FileManager.FileObjectFM;
import SecuritySystem.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainForm {
    private JButton loginButton;
    private JPanel panelMain;
    private JPanel loginPanel;
    private JTextField textFieldName;
    private JTextField textFieldPassword;
    private JList<FileObjectFM> listOfFiles;
    private JComboBox comboBoxDirectory;
    private JButton buttonOpen;
    private JButton buttonCopy;
    private JButton buttonCreate;
    private JButton buttonDelete;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel labelSelectedCopy;
    private JComboBox comboBoxCopy;
    private JComboBox comboBoxCreate;

    private Checker checker;
    private DefaultListModel<FileObjectFM> DLMFiles;
    private List<Catalog> catalogs;

    private List<Subject> allSubjects;
    private List<FileObjectFM> allFiles;

    public MainForm(List<Subject> subjects, List<ModelObject> objects, SecurityLevelGrid slg, SecurityMatrix sm) {
        allSubjects = subjects;

        checker = new Checker(slg, sm);

        DLMFiles = new DefaultListModel<>();
        listOfFiles.setModel(DLMFiles);

        catalogs = new ArrayList<>();
        allFiles = new ArrayList<>();

        for (ModelObject o : objects) {
            if (o.getClass().getName() == "FileManager.Catalog") {
                catalogs.add((Catalog) o);
            } else {
                allFiles.add((FileObjectFM) o);
            }
        }

        refreshCombo();
        setUpLogin(subjects);
        setUpCombo();

        listOfFiles.addListSelectionListener(e -> {
            // labelSelectedCopy.setText(listOfFiles.getSelectedValue().toString());
        });

        buttonOpen.addActionListener(e -> {
            openFile(Authorization.CURRENT_USER, listOfFiles.getSelectedValue(), AccessModifier.Read);
        });

        buttonDelete.addActionListener(e -> {
            deleteFile(Authorization.CURRENT_USER, listOfFiles.getSelectedValue(), AccessModifier.Delete);
        });

        buttonCopy.addActionListener(e -> {
            copyFile(Authorization.CURRENT_USER, listOfFiles.getSelectedValue(), (Catalog) comboBoxCopy.getSelectedItem());
        });

        buttonCreate.addActionListener(e -> {
            Catalog targetCatalog = (Catalog) comboBoxCreate.getSelectedItem();
            FileObjectFM file = new FileObjectFM(
                    Integer.parseInt(textField1.getText()),
                    textField2.getText(),
                    textField4.getText(),
                    targetCatalog.id,
                    textField3.getText()
            );
            createFile(Authorization.CURRENT_USER, targetCatalog, file);
        });

        setUpForm();
    }

    void setUpCombo() {
        comboBoxDirectory.setSelectedItem(null);
        comboBoxDirectory.addActionListener(e -> {
            Catalog selected = (Catalog) comboBoxDirectory.getSelectedItem();
            showFilesFromCatalog(Authorization.CURRENT_USER, selected);
        });
    }

    void setUpLogin(List<Subject> subjects) {
        Authorization auth = new Authorization();

        loginButton.addActionListener(e -> {
            if (auth.login(textFieldName.getText(), textFieldPassword.getText(), subjects)) {
                loginButton.setBackground(Color.GREEN);
                for (Component cp : loginPanel.getComponents()) {
                    // сделать логин неактивным
                    // cp.setEnabled(false);
                }
            } else {
                loginButton.setBackground(Color.RED);
            }
        });
    }

    void setUpForm() {
        JFrame frame = new JFrame("App");
        frame.setSize(1024, 480);
        // frame.pack();
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void refreshCombo() {
        comboBoxDirectory.removeAllItems();
        comboBoxCopy.removeAllItems();
        comboBoxCreate.removeAllItems();

        for (Catalog c : catalogs) {
            comboBoxDirectory.addItem(c);
            comboBoxCopy.addItem(c);
            comboBoxCreate.addItem(c);
        }
    }

    void showFilesFromCatalog(Subject s, Catalog c) {
        if (checker.checkAction(s, c, AccessModifier.Read)) {
            DLMFiles.clear();

            for (FileObjectFM f : allFiles) {
                if (c.id == f.catalog_id) {
                    DLMFiles.addElement(f);
                }
            }
        }
    }

    void openFile(Subject s, FileObjectFM file, AccessModifier am) {
        if (checker.checkAction(s, file, am)) JOptionPane.showMessageDialog(null, "Открыт файл " + file);
    }

    void deleteFile(Subject s, FileObjectFM file, AccessModifier am) {
        if (checker.checkAction(s, file, am)) {
            DLMFiles.removeElement(file);
            allFiles.remove(file);
            JOptionPane.showMessageDialog(null, "Файл удален " + file);
        }
    }

    void copyFile(Subject s, FileObjectFM o, Catalog c) {
        if (checker.checkAction(s, o, AccessModifier.Read) &&
                checker.checkAction(s, c, AccessModifier.Write)) {
            FileObjectFM file = new FileObjectFM(99, c.path, o.name, c.id, o.name + "(copy)");
            DLMFiles.addElement(file);
            allFiles.add(file);
            showFilesFromCatalog(s, c);
            JOptionPane.showMessageDialog(null, "Копия создана");
        }
    }

    void createFile(Subject s, Catalog c, FileObjectFM o) {
        if (checker.checkAction(s, c, AccessModifier.Write)) {
            DLMFiles.addElement(o);
            allFiles.add(o);
            showFilesFromCatalog(s, c);
            JOptionPane.showMessageDialog(null, "Файл создан");
        }
    }
}
