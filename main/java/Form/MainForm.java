package Form;

import Authorization.Authorization;
import SecuritySystem.ModelObject;
import SecuritySystem.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainForm {
    private JButton loginButton;
    private JPanel panelMain;
    private JPanel loginPanel;
    private JTextField textFieldName;
    private JTextField textFieldPassword;
    private JList listOfFiles;
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

    public MainForm(List<Subject> subjects, List<ModelObject> objects) { //, SecurityMatrix matrix, SecurityLevelGrid grid) {
        Authorization auth = new Authorization();

        loginButton.addActionListener(e->{
            if (auth.login(textFieldName.getText(), textFieldPassword.getText(), subjects)) {
                loginButton.setBackground(Color.GREEN);
                for (Component cp : loginPanel.getComponents() ){
                    cp.setEnabled(false);
                }
            } else {
                loginButton.setBackground(Color.RED);
            }
        });

        DefaultListModel<ModelObject> listModel = new DefaultListModel<>();
        for (ModelObject o: objects){
            listModel.addElement(o);
        }
        listOfFiles.setModel(listModel);

        JFrame frame = new JFrame("App");
        frame.setSize(1024,480);
        // frame.pack();
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
