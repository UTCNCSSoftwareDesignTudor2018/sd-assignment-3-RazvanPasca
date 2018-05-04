/*
 * Created by JFormDesigner on Fri May 04 20:32:10 EEST 2018
 */

package project.newsagency.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Razvan Pasca
 */
public class SimpleClientView extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Razvan Pasca
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1;
    private JTextField userNameTextField;
    private JLabel label2;
    private JPasswordField passwordField;
    private JButton loginButton;

    public SimpleClientView() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Razvan Pasca
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        userNameTextField = new JTextField();
        label2 = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }

        //---- label1 ----
        label1.setText("Username");

        //---- label2 ----
        label2.setText("Password");

        //---- loginButton ----
        loginButton.setText("Login as Writer");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(userNameTextField, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                        .addComponent(passwordField)
                                        .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(label1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(label2)
                                                .addGap(18, 18, 18)
                                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(loginButton)))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void addLoginListener(ActionListener e) {
        this.loginButton.addActionListener(e);
    }

    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public void setUserNameTextField(String userNameTextField) {
        this.userNameTextField.setText(userNameTextField);
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void setPasswordField(String passwordField) {
        this.passwordField.setToolTipText(passwordField);
    }
}
