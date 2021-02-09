/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.model.IModel;
import il.ac.hit.costmanager.view.IView;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ViewModel class handles all the operations transferred from the View to the Model.
 * @implemets IViewModel
 */
public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    /**
     * Initiate EDT thread
     */
    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    /**
     * setting the View.
     * @param view will be assigned to the local view parameter.
     * @override setView from IViewModel Interface.
     */
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * setting the Model.
     * @param model will be assigned to the local model parameter.
     * @override setModel from IViewModel Interface.
     */
    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    /**
     * Adding a CostItem by sending it to the model.
     * the function then prints to the screen the remaining CostItems.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be added by sending it to the model.
     * @override addCostItem from IViewModel Interface.
     */
    @Override
    public void addCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCostItem(item);
                    view.showMessage("cost item was added successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch(CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * Deleting a CostItem by sending it to the model.
     * the function then prints to the screen the remaining CostItems.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be deleted by sending it to the model.
     * @override deleteCostItem from IViewModel Interface.
     */
    @Override
    public void deleteCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCostItem(item);
                    view.showMessage("cost item was deleted successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch(CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * Adding a new Category by sending it to the model.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be added by sending it to the model.
     * @override addCategory from IViewModel Interface.
     */
    @Override
    public void addCategory(Category item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCategory(item);
                    view.showMessage("Category was added successfully");
                } catch(CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * Deleting a Category by sending it to the model.
     * this function making sure the program is still running in the EDT thread.
     * @param item will be deleted by sending it to the model.
     * @override deleteCategory from IViewModel Interface.
     */
    @Override
    public void deleteCategory(Category item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCategory(item);
                    view.showMessage("Category was deleted successfully");
                } catch(CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * Searching for a CostItem by getting the CostItems from the model.
     * @param id the ID of the requested item.
     * @return CostItem if found matching CostItem.
     * @return null if not found
     * @override findCostItemBy from IViewModel Interface.
     */
    @Override
    public CostItem findCostItemBy(int id) {
        try {
            CostItem[] items = model.getCostItems();
            for(int i = 0 ; i < items.length ; i++ ) {
                if (items[i].getId() == id)
                    return items[i];
            }
        } catch(CostManagerException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    /**
     * Searching for a CostItem by getting the CostItems from the model.
     * @param description the description of the requested item.
     * @return CostItem if found matching CostItem.
     * @return null if not found
     * @override findCostItemBy from IViewModel Interface.
     */
    @Override
    public CostItem findCostItemBy(String description) {
        try {
            CostItem[] items = model.getCostItems();
            for(int i = 0 ; i < items.length ; i++ ) {
                if (items[i].getDescription().compareToIgnoreCase(description) == 0)
                    return items[i];
            }
        } catch(CostManagerException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    /**
     * This function Returns the CostItems from the model.
     * @return CostItem array with all the CostItems found in the model.
     * @override getCostItems from IViewModel Interface.
     */
    @Override
    public CostItem[] getCostItems() {
        CostItem[] costItems = null;
                try {
                     costItems = model.getCostItems();
                } catch(CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
                return costItems;
            }

    /**
     * This function Returns the Categories from the model.
     * @return Category array with all the Categories found in the model.
     * @override getCategories from IViewModel Interface.
     */
    @Override
    public Category[] getCategories() {
        Category[] categories = null;
        try {
            categories = model.getCategories();
        } catch(CostManagerException e) {
            view.showMessage(e.getMessage());
        }
        return categories;
    }

    /**
     * Searching for a Category by getting the Categories from the model.
     * @param categoryName the category name of the requested Category.
     * @return Category if found matching Category.
     * @return null if not found
     * @override findCategory from IViewModel Interface.
     */
    @Override
    public Category findCategory(String categoryName) {
        try {
            Category[] items = model.getCategories();
            for(int i = 0 ; i < items.length ; i++ ) {
                if (items[i].getCategoryName().compareToIgnoreCase(categoryName) == 0)
                    return items[i];
            }
        } catch(CostManagerException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }



}
