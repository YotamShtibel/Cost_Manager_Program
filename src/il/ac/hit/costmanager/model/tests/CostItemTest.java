import il.ac.hit.costmanager.model.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class CostItemTest {


    CostItem costItem1 = new CostItem(0, "chair", 56.23, Currency.EURO, new Category(0, "furniture"));
    CostItem costItem2 = new CostItem(1, "table", 96.23, Currency.ILS, new Category(1, "furniture"));
    CostItem costItem3 = new CostItem(2, "fish", 10, Currency.ILS, new Category(2, "animals"));
    CostItem costItem4 = new CostItem(3, "tomato", 0.6, Currency.GBP, new Category(3, "plants"));

    @Test
    public void getDescription() {
        assertEquals(costItem1.getDescription(),"chair");
        assertEquals(costItem2.getDescription(),"table");
        assertEquals(costItem4.getDescription(),"tomato" );
    }

    @Test
    public void setDescription() {
        costItem2.setDescription("Snoop Doggi Dog");
        assertEquals(costItem2.getDescription(),"Snoop Doggi Dog");
    }

    @Test
    public void setSum() {
        costItem2.setSum(555.44);
        assertTrue(costItem2.getSum() == 555.44);
    }

    @Test
    public void setCategory() {
        Category category = new Category(159,"old people");
        costItem3.setCategory(category);
        assertEquals(costItem3.getCategory(), category);

    }

    @Test
    public void setCurrency() {
        costItem4.setCurrency(Currency.USD);
        assertEquals(costItem4.getCurrency(),Currency.USD);
    }
}