package Form;

import Authorization.Authorization;
import FileManager.FileManager;
import FileManager.Catalog;
import SecuritySystem.ModelObject;
import SecuritySystem.Subject;

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
    private JList<ModelObject> listOfFiles;
    private JButton buttonOpen;
    private JButton buttonCopy;
    private JButton buttonCreate;
    private JButton buttonDelete;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField7;
    private JLabel labelSelected;
    private JComboBox comboBoxDirectory;

    public MainForm(List<Subject> subjects, List<ModelObject> objects) {
        FileManager fm = new FileManager();
        
        Authorization auth = new Authorization();

        loginButton.addActionListener(e->{
            if (auth.login(textFieldName.getText(), textFieldPassword.getText(), subjects)) {
                loginButton.setBackground(Color.GREEN);
                for (Component cp : loginPanel.getComponents() ){
                    // сделать логин неактивным
                    // cp.setEnabled(false);
                }
            } else {
                loginButton.setBackground(Color.RED);
            }
        });

        DefaultListModel<ModelObject> DLMFiles = new DefaultListModel<>();
        List<ModelObject> DLMCatalogs = new ArrayList<>();

        for (ModelObject o: objects){
            if (o.getClass().getName() == "FileManager.Catalog") {
                DLMCatalogs.add(o);
            } else {
                DLMFiles.addElement(o);
            }
        }

        listOfFiles.setModel(DLMFiles);

        for (ModelObject c : DLMCatalogs) {
            comboBoxDirectory.addItem(c);
        }


        JFrame frame = new JFrame("App");
        frame.setSize(1024,480);
        // frame.pack();
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
