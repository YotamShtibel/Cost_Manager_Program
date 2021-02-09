import il.ac.hit.costmanager.model.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category1 = new Category(0,"food");
    Category category2 = new Category(1,"animals");
    Category category3 = new Category(2,"snacks");
    Category category4 = new Category(3,"students");

    @Test
    public void getId() {
        assertEquals(category1.getId(),0);
        assertEquals(category2.getId(),1);
        assertEquals(category3.getId(),2);
        assertEquals(category4.getId(),3);
    }

    @Test
    public void getCategoryName() {
        assertEquals(category1.getCategoryName(),"food");
        assertEquals(category2.getCategoryName(),"animals");
        assertEquals(category3.getCategoryName(),"snacks");
        assertEquals(category4.getCategoryName(),"students");
    }

    @Test
    public void setId() {

        category2.setId(77);
        assertEquals(category2.getId(),77);
    }

    @Test
    public void setCategoryName() {
        category3.setCategoryName("WackaFlacka");
        assertEquals(category3.getCategoryName(),"WackaFlacka");
    }
}