/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/



package il.ac.hit.costmanager.model;


import java.util.Date;

public class CostItem {


    private String description;
    private double sum;
    private Currency currency;
    private Category category;
    public static int numberOfItems = 1;
    private int id;
    private Date costItemDate = new Date() ;

    public CostItem() {}

    /**
     * Constructs a new CostItem without ID number which will be given automatically.
     * the date field of the Category will be the current date.
     * @param description the description of the CostItem constructed.
     * @param sum the cost of the Category constructed.
     * @param currency holds the Currency type
     * @param category the Category of the constructed CostItem
     */
    public CostItem(String description, double sum, Currency currency, Category category) {
        setDescription(description);
        setCurrency(currency);
        setSum(sum);
        setCategory(category);
        costItemDate = new java.sql.Date(costItemDate.getTime());
        numberOfItems++;
        this.id  = numberOfItems;
    }

    /**
     * Constructs a new CostItem without
     * the date field of the Category. the date will be the current date.
     * @param id the ID which will be assigned to the new CostItem.
     * @param description the description of the CostItem constructed.
     * @param sum the cost of the Category constructed.
     * @param currency holds the Currency type
     * @param category the Category of the constructed CostItem
     */
    public CostItem(int id, String description, double sum, Currency currency, Category category) {
        setDescription(description);
        setCurrency(currency);
        setSum(sum);
        setCategory(category);
        this.id  = id;
    }

    /**
     * Constructs a new CostItem.
     * @param id the ID which will be assigned to the new CostItem.
     * @param description the description of the CostItem constructed.
     * @param sum the cost of the Category constructed.
     * @param currency holds the Currency type .
     * @param category the Category of the constructed CostItem.
     * @param costItemDate the date which will be assigned to the new CostItem.
     */
    public CostItem(int id, String description, double sum, Currency currency, Category category, Date costItemDate) {
        this(id,description,sum,currency,category);
        this.costItemDate = new java.sql.Date(costItemDate.getTime());
    }

    //////////////getters
    public String getDescription() {
        return description;
    }
    public Date getDate() { return costItemDate; }
    public double getSum() {
        return sum;
    }
    public int getId() { return id; }
    public Category getCategory() { return category; }
    public Currency getCurrency() {
        return currency;
    }
    /////////////

    //////////setters
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }
    public void setCategory(Category category) { this.category = category; }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    ////////////

    /**
     * @Override toString() function to make a costume made toString function
     * @return CostItem ready to be printed
     */
    @Override
    public String toString() {
        if(this != null) {
            return "CostItem{" +
                    "id=" + id +
                    " ,description='" + description + '\'' +
                    ", sum=" + sum +
                    ", currency=" + currency +
                    ", category=" + category.getCategoryName() +
                    ", Date=" + getDate().toString() +
                    '}';
        }else return "CostItem is null";
    }
}
