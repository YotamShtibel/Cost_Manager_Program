/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.CostItemDBModel;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.model.IModel;
import il.ac.hit.costmanager.view.*;


public class Application {
    public static void main(String args[]) throws CostManagerException {


        //creating the application components
        IModel model = new CostItemDBModel();
        IView view = new MainScreen();
        IViewModel vm = new ViewModel();

        //connecting the components with each other
        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);


    }
}
