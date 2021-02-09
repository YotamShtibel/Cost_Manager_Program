/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.view;

import datechooser.beans.DateChooserCombo;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportScreen{

    private JButton btExit;
    private JButton btShow;
    private JPanel reportPanel;
    private DateChooserCombo dateChooserFrom;
    private DateChooserCombo dateChooserTo;
    private JLabel messageLabel;
    private JTextArea textArea;
    private JFrame frame;


    /**
     * Constructs a new ReportScreen which shows the report options for the user.
     * @param mainscreen the main screen who connects with the ReportScreen.
     */
    public ReportScreen(MainScreen mainscreen) {

        //sets date model
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //setting window frame
        frame = new JFrame();
        frame.add(reportPanel);
        frame.setTitle("Report Panel");
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ////////////////


        //Exit button listener
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {frame.dispose(); }});

        //Confirm button listener
        btShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Date currentDate = new Date();
                    Date dateFrom = dateChooserFrom.getSelectedDate().getTime();
                    Date dateTo = dateChooserTo.getSelectedDate().getTime();
                    if (dateFrom.compareTo(currentDate) > 0) {// dateFrom is after currentDate
                        messageLabel.setText("Cant choose that date");
                        messageLabel.setVisible(true);
                        throw new CostManagerException("Cant choose that date");
                    }
                    CostItem[] costItems = mainscreen.getVm().getCostItems();
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < costItems.length ; i++) {
                        if (costItems[i].getDate().compareTo(dateFrom) >= 0 && costItems[i].getDate().compareTo(dateTo) <= 0) {
                            sb.append(costItems[i].toString());
                            sb.append("\n");
                        }
                    }
                    String text = sb.toString();
                    textArea.setText(text);

                }catch (CostManagerException e1){

                }
            }
        });
    }
}
