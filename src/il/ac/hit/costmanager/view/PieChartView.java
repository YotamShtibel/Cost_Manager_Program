/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.CostManagerException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartView extends Application {

    static ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws CostManagerException {
        //Create PieChart and assign data
        PieChart pChart = new PieChart(pieData);

        Platform.setImplicitExit(false);
        //Set PieChart properties
        pChart.setTitle("My PieChart");

        Group root = new Group(pChart);
        Scene scene = new Scene(root,600,400);
        primaryStage.setTitle("Expenses");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
