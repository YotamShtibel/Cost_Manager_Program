import il.ac.hit.costmanager.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class CostItemDBModelTest {

    CostItem costItem1 = new CostItem(0, "chair", 56.23, Currency.EURO, new Category(0, "furniture"));
    CostItem costItem2 = new CostItem(1, "table", 96.23, Currency.ILS, new Category(1, "furniture"));
    CostItem costItem3 = new CostItem(2, "fish", 10, Currency.ILS, new Category(2, "animals"));
    CostItem costItem4 = new CostItem(3, "tomato", 0.6, Currency.GBP, new Category(3, "plants"));
    CostItemDBModel costItemDBModel;

    {
        try {
            costItemDBModel = new CostItemDBModel();
        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testDeleteTable() throws CostManagerException {
        costItemDBModel.deleteTable();
    }

    @Test
    public void addCostItem() throws CostManagerException {
        costItemDBModel = new CostItemDBModel();
        costItemDBModel.addCostItem(costItem1);
        costItemDBModel.addCostItem(costItem2);
        costItemDBModel.addCostItem(costItem3);
        costItemDBModel.addCostItem(costItem4);
    }

    @Test
    public void getCostItems() throws CostManagerException, SQLException {
        CostItem[] costItems = costItemDBModel.getCostItems();
    }

    @Test
    public void deleteCostItem() throws CostManagerException {
        costItemDBModel.deleteCostItem(costItem2);
    }

    @org.junit.Test
    public void generalTest() throws CostManagerException, SQLException {

        this.testDeleteTable();
        costItemDBModel = new CostItemDBModel();
        this.addCostItem();
        this.deleteCostItem();

        CostItem[] costItems = costItemDBModel.getCostItems();

        Assert.assertEquals(costItems[0].getId(),costItem1.getId());
        Assert.assertEquals(costItems[1].getId(),costItem3.getId());
        Assert.assertEquals(costItems[2].getId(),costItem4.getId());

    }
}