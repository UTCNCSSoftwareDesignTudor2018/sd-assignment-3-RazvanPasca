/*
 * Created by JFormDesigner on Sat May 05 19:11:28 EEST 2018
 */

package project.newsagency.client.view;

import project.newsagency.server.persistence.entities.Article;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Razvan Pasca
 */
public class ArticleView extends JFrame {

    private boolean editable;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Razvan Pasca
    private JLabel label1;
    private JLabel label2;
    private JTextField titleTextField;
    private JTextField authorsTextField;
    private JLabel label3;
    private JTextField abstractTextField;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea bodyTextArea;
    private JButton addArticleButton;
    private JButton removeArticleButton;

    public ArticleView(boolean editable) {
        this.editable = editable;
        initComponents();
        setComponentsEditable();
    }

    private void setComponentsEditable() {
        titleTextField.setEditable(editable);
        authorsTextField.setEditable(editable);
        abstractTextField.setEditable(editable);
        bodyTextArea.setEditable(editable);
        addArticleButton.setEnabled(editable);
        removeArticleButton.setEnabled(editable);
        if (titleTextField.getText() == null)
            removeArticleButton.setEnabled(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Razvan Pasca
        label1 = new JLabel();
        label2 = new JLabel();
        titleTextField = new JTextField();
        authorsTextField = new JTextField();
        label3 = new JLabel();
        abstractTextField = new JTextField();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        bodyTextArea = new JTextArea();
        addArticleButton = new JButton();
        removeArticleButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Title");

        //---- label2 ----
        label2.setText("Authors");

        //---- label3 ----
        label3.setText("Abstract");

        //---- label4 ----
        label4.setText("Body");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(bodyTextArea);
        }

        //---- addArticleButton ----
        addArticleButton.setText("Add article");

        //---- removeArticleButton ----
        removeArticleButton.setText("Remove Article");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 607, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                                                                .addGap(27, 27, 27)
                                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
                                                .addGap(18, 18, 18)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(addArticleButton)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(removeArticleButton))
                                                        .addComponent(authorsTextField)
                                                        .addComponent(abstractTextField))))
                                .addContainerGap(52, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label1)
                                                        .addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addArticleButton)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(removeArticleButton)))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(authorsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(abstractTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(8, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public String getTitleTextField() {
        return titleTextField.getText();
    }

    private void setTitleTextField(String titleTextField) {
        this.titleTextField.setText(titleTextField);
    }

    public String getAuthorsTextField() {
        return authorsTextField.getText();
    }

    private void setAuthorsTextField(String authorsTextField) {
        this.authorsTextField.setText(authorsTextField);
    }

    public String getAbstractTextField() {
        return abstractTextField.getText();
    }

    private void setAbstractTextField(String abstractTextField) {
        this.abstractTextField.setText(abstractTextField);
    }

    public String getBodyTextArea() {
        return bodyTextArea.getText();
    }

    private void setBodyTextArea(String bodyTextArea) {
        this.bodyTextArea.setText(bodyTextArea);
    }

    public void setArticleContent(Article article) {
        setAbstractTextField(article.getAbs());
        setAuthorsTextField(article.getAuthors().toString());
        setBodyTextArea(article.getBody());
        setTitleTextField(article.getTitle());
    }

    public Article getArticleFromView() {
        Article article = new Article();
        article.setAbs(this.getAbstractTextField());
        article.setTitle(this.getTitleTextField());
        article.setBody(this.getBodyTextArea());
        return article;
    }

    public void addRemoveArticleButtonListener(ActionListener e) {
        this.removeArticleButton.addActionListener(e);
    }

    public void addCreateArticleButtonListener(ActionListener e) {
        this.addArticleButton.addActionListener(e);
    }
}
