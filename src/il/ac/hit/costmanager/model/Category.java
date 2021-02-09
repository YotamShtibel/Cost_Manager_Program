/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/


package il.ac.hit.costmanager.model;

public class Category {

    private String categoryName;
    public static int count = 1;
    private int id;

    /**
     * Constructs a new Category with the Category name.
     *
     * @param categoryName the name of the Category constructed.
     */
    public Category(String categoryName) {
        setCategoryName(categoryName);
        setId(count);
        count++;
    }

    /**
     * Constructs a new Category with the Category name and ID.
     * this constructor avoid generating new ID for an existed Category
     * @param categoryName the name of the Category constructed.
     * @param id ID number of the Category constructed.
     */
    public Category(int id, String categoryName) {
        this(categoryName);
        setId(id);
    }

    ////////////getters
    public int getId() { return id; }
    public String getCategoryName() { return categoryName; }
    ////////////

    ////////////setters
    public void setId(int id) { this.id = id; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    //////////////


    /**
     * @Override toString() function to make a costume made toString function
     * @return Category ID and name by String
     */
    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", categoryName=" + categoryName +
                '}';
    }
}
