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
        frame.setSize(640,480);
        // frame.pack();
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
