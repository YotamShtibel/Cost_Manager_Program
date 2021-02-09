/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.model.Currency;
import il.ac.hit.costmanager.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddScreen {

    private JTextField sumBox;
    private JButton btEXit;
    private JButton btConfirm;
    private JComboBox categoryComboBox;
    private JComboBox currencyComboBox;
    private JPanel addCostPanel;
    private JTextField descriptionBox;
    private JLabel messageLabel;
    private JFrame frame;

    /**
     * Constructs a new AddScreen which shows the adding options for the user.
     * @param mainscreen the main screen who connects with the AddScreen.
     */
    public AddScreen(MainScreen mainscreen) {

        //setting currency combo box
        String currencies[] = {"ILS","EURO","USD","GBP"};
        currencyComboBox.addItem("ILS");
        currencyComboBox.addItem("EURO");
        currencyComboBox.addItem("USD");
        currencyComboBox.addItem("GBP");

        //setting currency combo box
        Category[] categories = mainscreen.getVm().getCategories();
        for (int i = 0 ; i < categories.length ; i++){
            categoryComboBox.addItem(categories[i].getCategoryName());
        }

        //setting window frame
        frame = new JFrame();
        frame.add(addCostPanel);
        frame.setTitle("Add Cost");
        frame.setSize(500,300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        /////////////////

        //exit button listener
        btEXit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {frame.dispose(); }});

        //confirm button listener
        btConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String description = descriptionBox.getText();
                    if(description==null || description.length()==0) { // if the description is empty
                        throw new CostManagerException("description cannot be empty");
                    }
                    double sum = Double.parseDouble(sumBox.getText());
                    String currencyStr = currencyComboBox.getSelectedItem().toString();
                    Currency currency = null;
                    switch (currencyStr) {
                        case "EURO":
                            currency = Currency.EURO;
                            break;
                        case "USD":
                            currency = Currency.USD;
                            break;
                        case "GBP":
                            currency = Currency.GBP;
                        default:
                            currency = Currency.ILS;

                    }
                    Category category = mainscreen.getVm().findCategory(categoryComboBox.getSelectedItem().toString());

                    CostItem item = new CostItem(description, sum, currency,category);
                    mainscreen.getVm().addCostItem(item);
                    frame.dispose();


                } catch (NumberFormatException ex) {
                    mainscreen.showMessage("problem with entered sum... "+ex.getMessage());
                    messageLabel.setText("problem with entered sum");
                    messageLabel.setVisible(true);
                } catch(CostManagerException ex){
                    mainscreen.showMessage("problem with entered data... problem with description... "+ex.getMessage());
                    messageLabel.setText("problem with entered description");
                    messageLabel.setVisible(true);
                }
            }
        });
    }
}
