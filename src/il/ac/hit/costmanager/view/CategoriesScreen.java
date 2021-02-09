/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostManagerException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesScreen {
    private JTextField categoryNameTextField;
    private JButton btConfirm;
    private JComboBox categoriesComboBox;
    private JButton btRemove;
    private JButton btCancel;
    private JPanel categoriesPanel;
    private JLabel messageLabel;
    private JFrame frame;

    /**
     * Constructs a new CategoriesScreen which shows the categories options for the user.
     * @param mainscreen the main screen who connects with the CategoriesScreen.
     */
    public CategoriesScreen(MainScreen mainscreen) {

        //setting currency combo box
        Category[] categories = mainscreen.getVm().getCategories();
        for (int i = 0; i < categories.length; i++) {
            categoriesComboBox.addItem(categories[i].getCategoryName());
        }

        //setting window frame
        frame = new JFrame();
        frame.add(categoriesPanel);
        frame.setTitle("Categories");
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //////////////////


        //confirm button listener
        btConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String str = categoryNameTextField.getText();
                    if (str.isEmpty()) { //category name is empty
                        messageLabel.setText("Category name can't be blanked");
                        messageLabel.setVisible(true);
                        throw new CostManagerException("Category name is empty");
                    }
                    if (mainscreen.getVm().findCategory(str) != null){ //if user is trying to create existing category
                        messageLabel.setText("Category already exists");
                        messageLabel.setVisible(true);
                        throw new CostManagerException("Category already exists");
                    }
                    Category category = new Category(str);
                    mainscreen.getVm().addCategory(category);
                    frame.dispose();

                }catch (CostManagerException e1){
                    mainscreen.showMessage("unknown error");
                }
            }
        });

        //remove button listener
        btRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String str = categoriesComboBox.getSelectedItem().toString();
                Category category = mainscreen.getVm().findCategory(str);
                mainscreen.getVm().deleteCategory(category);
                frame.dispose();

            }
        });

        //cancel button listener
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {frame.dispose(); }});
    }
}