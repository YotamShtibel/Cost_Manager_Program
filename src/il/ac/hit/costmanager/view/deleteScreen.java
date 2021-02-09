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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteScreen{
    private JPanel deleteScreenPanel;
    private JButton btExit;
    private JButton btConfirm;
    private JTextField idField;
    private JTextField descriptionField;
    private JLabel messageLabel;
    private JFrame frame;


    /**
     * Constructs a new deleteScreen which shows the deleting options for the user.
     * @param mainscreen the main screen who connects with the deleteScreen.
     */
    public deleteScreen(MainScreen mainscreen) {

        //setting window frame
        frame = new JFrame();
        frame.add(deleteScreenPanel);
        frame.setTitle("Delete Cost Item");
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        /////////////

        //Exit button listener
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {frame.dispose(); }});

        //confirm button listener
        btConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String description = descriptionField.getText();
                    int id = -1;
                    if(!idField.getText().isEmpty()){ // if ID is entered

                        //try to convert into Integer
                        try{
                            id = Integer.parseInt(idField.getText());
                        }catch (NumberFormatException e1){
                            messageLabel.setText("Must enter a number!");
                            messageLabel.setVisible(true);
                            throw new CostManagerException("Wrong input");
                        }
                    }

                    // else try to delete by description
                    if((description==null || description.length()==0) && id<0) {
                            messageLabel.setText("Must enter something...");
                            messageLabel.setVisible(true);
                            throw new CostManagerException("there is no valid input");
                        }

                    CostItem costItem;
                    if(id >= 0){
                        costItem = mainscreen.getVm().findCostItemBy(id);
                    }else {
                        costItem = mainscreen.getVm().findCostItemBy(description);
                    }
                    if (costItem == null){
                        messageLabel.setText("Can't find Cost Item");
                        messageLabel.setVisible(true);
                        throw new CostManagerException("Can't find Cost Item by");
                    }

                    mainscreen.getVm().deleteCostItem(costItem);

                        frame.dispose();
                    } catch(CostManagerException ex){
                    mainscreen.showMessage("problem with entered data... problem with description... "+ex.getMessage());
                }

            }
        });
    }
}
