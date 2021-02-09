/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.viewmodel.IViewModel;

public interface IView {


    public void setViewModel(IViewModel vm);
    public void showMessage(String text);
    public void showItems(CostItem[] vec);
}
