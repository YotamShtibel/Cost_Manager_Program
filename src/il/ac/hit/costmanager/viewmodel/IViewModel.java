/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.IModel;
import il.ac.hit.costmanager.view.IView;

/**
 * IViewModel interface holds the signature for the mandatory functions
 * that is needed to be implemented in ViewModel class
 */
public interface IViewModel {

    /**
     * setting the View.
     * @param view will be assigned to the local view parameter.
     */
    public void setView(IView view);

    /**
     * setting the Model.
     * @param model will be assigned to the local model parameter.
     */
    public void setModel(IModel model);

    /**
     * Adding a CostItem by sending it to the model.
     * the function then prints to the screen the remaining CostItems.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be added by sending it to the model.
     */
    public void addCostItem(CostItem item);

    /**
     * Deleting a CostItem by sending it to the model.
     * the function then prints to the screen the remaining CostItems.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be deleted by sending it to the model.
     */
    public void deleteCostItem(CostItem item);

    /**
     * Adding a new Category by sending it to the model.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be added by sending it to the model.
     */
    public void addCategory(Category item);

    /**
     * Deleting a Category by sending it to the model.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be deleted by sending it to the model.
     */
    public void deleteCategory(Category item);

    /**
     * Searching for a CostItem by getting the CostItems from the model.
     * @param id the ID of the requested item.
     * @return CostItem if found matching CostItem.
     * @return null if not found
     */
    public CostItem findCostItemBy(int id);

    /**
     * Searching for a CostItem by getting the CostItems from the model.
     * @param description the description of the requested item.
     * @return CostItem if found matching CostItem.
     * @return null if not found
     */
    public CostItem findCostItemBy(String description);

    /**
     * Searching for a Category by getting the Categories from the model.
     * @param categoryName the category name of the requested Category.
     * @return Category if found matching Category.
     * @return null if not found
     */
    public Category findCategory(String categoryName);

    /**
     * This function Returns the CostItems from the model.
     * @return CostItem array with all the CostItems found in the model.
     */
    public CostItem[] getCostItems();

    /**
     * This function Returns the Categories from the model.
     * @return Category array with all the Categories found in the model.
     */
    public Category[] getCategories();

}
