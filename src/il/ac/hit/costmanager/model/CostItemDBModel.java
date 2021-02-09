/*
Code by:
        Shirly Bittan . id 204786107
        &
        Yotam Shtibel . id: 205662398
*/

package il.ac.hit.costmanager.model;
import java.sql.*;

public class CostItemDBModel  implements IModel  {

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String connectionString = "jdbc:derby:MyDB;create=true";



    /**
     * this constructor generates the first connection to the DB DataBase.
     * open/create the CostItems DataBase named INVENTORY.
     * * open/create the Category DataBase named Categories.
     */
    public CostItemDBModel() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            //loading the driver class will indirectly register
            //this driver as an available driver for DriverManager
            Class.forName(driver);
            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            // check if "inventory" table is there
            // true - do nothing , false - create new table
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "INVENTORY", null);
            if (!tables.next()) {
                statement.execute("create table inventory(id int, fee double, description VARCHAR(40)," +
                        " currency VARCHAR(5), category VARCHAR(40), date DATE)");
            }

            // check if "categories" table is there
            // true - do nothing , false - create new table
            dbm = connection.getMetaData();
            tables = dbm.getTables(null, null, "CATEGORIES", null);
            if (!tables.next()) {
                statement.execute("create table categories(id int, category VARCHAR(40))");
            }


            statement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new CostManagerException("Cant open/create table" , e);
        }
    }

    /**
     * Delete both CostItems and Categories DataBases
     */
    public void deleteTable() throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            //delete DB
            connection = DriverManager.getConnection(connectionString);
            statement = connection.createStatement();
            statement.execute("DROP TABLE inventory");
            statement.execute("DROP TABLE CATEGORIES");

            statement.close();
            connection.close();

        }catch (SQLException e){
            throw new CostManagerException("Problem with deleting table", e);
        }
    }

    /**
     * Adding a cost item to the CostItems DataBase.
     * @param item the CostItem which will be inserted to the DataBase.
     * @Override addCostItem function from IModel Interface.
     */
    @Override
    public  void addCostItem(CostItem item) throws CostManagerException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        String adding = "insert into inventory values (" + item.getId() + "," + item.getSum() +
                ",'"+ item.getDescription()+"' ," + " '"+item.getCurrency().toString()+"', " +
                " '" + item.getCategory().getCategoryName()+"' ,'" + item.getDate() + "' )";


        try {

            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            //adding the new CostITem

            int check = statement.executeUpdate(adding);
            if( check <= 0)
                throw new CostManagerException("Problem with executeUpdate in addCostItem class");


    // in finale
            statement.close();
            connection.close();

        } catch (SQLException e ) {
            throw new CostManagerException("Problem with adding new item to DB",e);
        }

    }

    /**
     * Adding a Category to the Category DataBase.
     * @param category the Category which will be inserted to the DataBase.
     * @Override addCategory function from IModel Interface.
     */
    @Override
    public void addCategory(Category category) throws CostManagerException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        String adding = "insert into categories values (" + category.getId() + ",'" + category.getCategoryName() + "')";

        try {

            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            //adding the new CostITem
            int check = statement.executeUpdate(adding);
            if( check <= 0)
                throw new CostManagerException("Problem with executeUpdate in addCategory class");


            statement.close();
            connection.close();

        } catch (SQLException  e) {
            throw new CostManagerException("Problem with adding new item to DB",e);
        }
    }

    /**
     * Getting the CostItems from the DataBase.
     * @return array of CostItems provided by the DataBase;
     * @Override getCostItems function from IModel Interface.
     */
    @Override
    public CostItem[] getCostItems() throws CostManagerException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        int size = 0;
        CostItem[] tempList = null;

        try {

            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            //selecting columns and order by id
            rs = statement.executeQuery("SELECT * FROM inventory ORDER BY id");

            //counting num of rows
            while (rs.next()) {
                size ++;
            }
            CostItem.numberOfItems = size;
            tempList = new CostItem[size];
            rs= statement.executeQuery("SELECT * FROM inventory ORDER BY id");// reestablishment

            int i = 0;

            //creating new array of CostItems
            while (rs.next()) {
                Currency currency = Currency.ILS; // by default
                switch (rs.getString("currency")) { // creating Enum from DB
                    case "EURO":
                        currency = Currency.EURO;
                        break;
                    case "USD":
                        currency = Currency.USD;
                        break;
                    case "ILS":
                        currency = Currency.ILS;
                        break;
                    case "GBP":
                        currency = Currency.GBP;
                        break;
                }

                Category category = new Category(rs.getString("category")); // searching for the Category given by the DB

                int id = rs.getInt("id");
                CostItem tempCostItem = new CostItem(id, rs.getString("description")
                        , rs.getDouble("fee"), currency, category , rs.getDate("date")); // creating Cost item

                if(CostItem.numberOfItems < id)
                    CostItem.numberOfItems = id + 1;

                tempList[i] =tempCostItem;
                i++;
            }

            rs.close();
            statement.close();
            connection.close();

        } catch ( SQLException e ) {
            throw new CostManagerException("Problem with adding new item to DB",e);
        }


        return tempList;
    }

    /**
     * Getting the Categories from the DataBase.
     * @return array of Categories provided by the DataBase;
     * @Override getCategory function from IModel Interface.
     */
    @Override
    public Category[] getCategories() throws CostManagerException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        int size = 0;
        Category[] tempList = null;

        try {

            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            //selecting columns and order by id
            rs = statement.executeQuery("SELECT * FROM categories ORDER BY id");
            while (rs.next()) {//counting num of rows
                size++;
            }
            Category.count = size;
            tempList = new Category[size];
            rs = statement.executeQuery("SELECT * FROM categories ORDER BY id");// reestablishment
            int i = 0;

            //creating new array of Categories
            while (rs.next()) {

                int id = rs.getInt("id");
                Category tempCategory = new Category(id, rs.getString("category")); // creating Category item

                if(id > Category.count)
                    Category.count = id + 1;

                tempList[i] = tempCategory;
                i++;
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new CostManagerException("Problem with adding new item to DB",e);
        }


        return tempList;
    }

    /**
     * Deleting a cost item from the CostItems DataBase.
     * @param item the CostItem which will be deleted from the DataBase.
     * @Override deleteCostItem function from IModel Interface.
     */
    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();


            int check = statement.executeUpdate("DELETE FROM inventory WHERE (id =" + item.getId() + ")");

            if( check <= 0)
                throw new CostManagerException("Problem with executeUpdate in deleteCostItem class");


            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new CostManagerException("Problem deleting item from DB",e);
        }
    }

    /**
     * Deleting a Category from the Category DataBase.
     * @param category the category which will be deleted from the DataBase.
     * @Override deleteCategory function from IModel Interface.
     */
    @Override
    public void deleteCategory(Category category) throws CostManagerException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {


            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(connectionString);

            statement = connection.createStatement();

            try {
                statement.executeUpdate("DELETE FROM categories WHERE (id =" + category.getId() + ")");
            }catch (SQLException e){
                throw new CostManagerException("Problem with executeUpdate in deleteCategory class");
            }



            statement.close();
            connection.close();


        } catch (SQLException  e) {
            throw new CostManagerException("Problem deleting item from DB",e);
        }


    }

}

