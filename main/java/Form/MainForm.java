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
    private List<Subject> allSubjects;
    private static FileManager fm;

    public MainForm(List<Subject> subjects, List<ModelObject> objects, SecurityLevelGrid slg, SecurityMatrix sm) {
        allSubjects = subjects;
        checker = new Checker(slg, sm);
        fm = new FileManager(objects, checker);

        listOfFiles.setModel(fm.DLMFiles);

        refreshCombo();
        setUpLogin(subjects);
        setUpCombo();

        listOfFiles.addListSelectionListener(e -> {
            try{
            String heh = listOfFiles.getSelectedValue().toString();
            labelSelectedCopy.setText(heh);
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });

        buttonOpen.addActionListener(e -> {
            fm.openFile(Authorization.CURRENT_USER, listOfFiles.getSelectedValue(), AccessModifier.Read);
        });

        buttonDelete.addActionListener(e -> {
            fm.deleteFile(Authorization.CURRENT_USER, listOfFiles.getSelectedValue(), AccessModifier.Delete);
        });

        buttonCopy.addActionListener(e -> {
            fm.copyFile(Authorization.CURRENT_USER, listOfFiles.getSelectedValue(), (Catalog) comboBoxCopy.getSelectedItem());
        });

        buttonCreate.addActionListener(e -> {
            Catalog targetCatalog = (Catalog) comboBoxCreate.getSelectedItem();
            FileObjectFM file = new FileObjectFM(
                    textField1.getText(),
                    textField2.getText(),
                    textField4.getText(),
                    targetCatalog.id,
                    textField3.getText()
            );
            fm.createFile(Authorization.CURRENT_USER, targetCatalog, file);
        });

        setUpForm();
    }

    void setUpCombo() {
        comboBoxDirectory.setSelectedItem(null);
        comboBoxDirectory.addActionListener(e -> {
            Catalog selected = (Catalog) comboBoxDirectory.getSelectedItem();
            fm.showFilesFromCatalog(Authorization.CURRENT_USER, selected);
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
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void refreshCombo() {
        comboBoxDirectory.removeAllItems();
        comboBoxCopy.removeAllItems();
        comboBoxCreate.removeAllItems();

        for (Catalog c : fm.getCatalogs()) {
            comboBoxDirectory.addItem(c);
            comboBoxCopy.addItem(c);
            comboBoxCreate.addItem(c);
        }
    }
}
