/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.viewmodel.IViewModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreen implements IView {

    private IViewModel vm;
    private ApplicationUI ui;

    public IViewModel getVm() {
        return vm;
    }

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showItems(CostItem[] vec) {
        ui.showItems(vec);
    }


    private JButton btPieChart;
    private JButton btReport;
    private JButton btAddCost;
    private JButton btDeleteItem;
    private JTextField tfMessage;
    private JTextPane textArea;
    private JButton btExit;
    private JPanel mainPanel;
    private JLabel dateLabel;
    private JButton btCategories;
    private JFrame frame;
    private static volatile boolean javaFxLaunched = false;


    /**
     * Constructs a new MainScreen which shows main options for the user.
     */
    public MainScreen() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainScreen.this.ui = new ApplicationUI();
                MainScreen.this.ui.start();
            }
        });
    }


    /**
     * this class is in charge of the set up of the main screen.
     */
    public class ApplicationUI //implements IView
    {


        public ApplicationUI() {

            //sets corner date
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateLabel.setText(" Date: " + format.format(date) + "   ");

            showItems(vm.getCostItems());

            //init main screen
            frame = new JFrame();
            frame.add(mainPanel);
            frame.setTitle("Cost Manager Pro");
            frame.setSize(1200,600);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            //add default categories
            if(vm.findCategory("Weapons") == null){
                Category weapons = new Category("Weapons");
                vm.addCategory(weapons);
            }if(vm.findCategory("Drugs") == null){
                Category Drugs = new Category("Drugs");
                vm.addCategory(Drugs);
            }if(vm.findCategory("Cars") == null){
                Category Cars = new Category("Cars");
                vm.addCategory(Cars);
            }



        }

        public void start() {


            //add cost button listener
            btAddCost.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { new AddScreen(MainScreen.this); }});

            //delete cost button listener
            btDeleteItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { new deleteScreen(MainScreen.this); }});

            //make report button listener
            btReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {new ReportScreen(MainScreen.this); }});

            //pie chart button listener
            btPieChart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setPieChart(vm.getCostItems());
                    if (!javaFxLaunched) { // First time
                        Platform.setImplicitExit(false);
                        new Thread(()->Application.launch(PieChartView.class)).start();
                        javaFxLaunched = true;
                    } else { // Next times
                        Platform.runLater(()->{
                            try {
                                Application application = PieChartView.class.newInstance();
                                Stage primaryStage = new Stage();
                                application.start(primaryStage);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        });
                    }
                }});

            //categories button listener
            btCategories.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {new CategoriesScreen(MainScreen.this); }});

            //exit button listener
            btExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { System.exit(0); }});

        }

        /**
         * Shows the given CostItems array to the user with the testArea pane.
         * @param  items array of CostItem which will be shown in the main screen
         */
        public void showItems(CostItem[] items) {
            StringBuilder sb = new StringBuilder();
            for (CostItem item : items) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {
                textArea.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.setText(text);
                    }
                });

            }
        }

        /**
         * Shows the given text to the user with the Text field.
         * @param  text the text that will be shown.
         */
        public void showMessage (String text){
            if (SwingUtilities.isEventDispatchThread()) {
                tfMessage.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        tfMessage.setText(text);
                    }
                });

            }
        }


        public void setPieChart(CostItem[] costItems) {
            int categoryNum[] = new int[costItems.length];
            PieChartView.pieData.clear();


            // the function search for categories with same id/description and combine them
            for (int i = 0, k = 1; i < costItems.length; i++) {
                if (categoryNum[i] == 0) {
                    categoryNum[i] = k;
                    k++;
                    for (int j = i + 1; j < costItems.length; j++) {
                        if (costItems[i].getCategory().getCategoryName().equalsIgnoreCase( costItems[j].getCategory().getCategoryName())) {
                            categoryNum[j] = categoryNum[i];
                        }
                    }
                }
            }


            for (int i=0; i<categoryNum.length ; i++){
                if(categoryNum[i] != 0) {
                    double sum = costItems[i].getSum();
                    for (int k = i+1; k < categoryNum.length; k++) {
                        if (categoryNum[i] == categoryNum[k]) {
                            sum += costItems[k].getSum();
                            categoryNum[k] = 0;
                        }
                    }
                    PieChartView.
                    pieData.add(new javafx.scene.chart.PieChart.Data(costItems[i].getCategory().getCategoryName(), sum));
                    sum = 0;
                }


            }
        }



    }
}

