/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.model;

/**
 * Imodel interface holds the signature for the mandatory functions
 * that is needed to be implemented in Model class
 */
public interface IModel{

    /**
     * Adding a cost item to the CostItems DataBase.
     * @param item the CostItem which will be inserted to the DataBase.
     */
    public  void addCostItem(CostItem item) throws CostManagerException;

    /**
     * Adding a Category to the Category DataBase.
     * @param category the Category which will be inserted to the DataBase.
     */
    public  void addCategory(Category category) throws CostManagerException;

    /**
     * Getting the CostItems from the DataBase.
     * @return array of CostItems provided by the DataBase;
     */
    public CostItem[] getCostItems() throws CostManagerException;

    /**
     * Getting the Categories from the DataBase.
     * @return array of Categories provided by the DataBase;
     */
    public Category[] getCategories() throws CostManagerException;

    /**
     * Deleting a cost item from the CostItems DataBase.
     * @param item the CostItem which will be deleted from the DataBase.
     */
    public void deleteCostItem(CostItem item) throws CostManagerException;

    /**
     * Deleting a Category from the Category DataBase.
     * @param category the category which will be deleted from the DataBase.
     */
    public void deleteCategory(Category category) throws CostManagerException;

}
