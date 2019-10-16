package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PrimitiveIterator;

public class ProductDB {
    public static ObservableList<Product> getProductsBySupplierId(int supplierId) throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        Connection conn = ConnectionDB.getConnection();
        String sql = "select p.ProductId, p.ProdName " +
                "from Products p inner join Products_Suppliers ps " +
                "on p.ProductId = ps.ProductId " +
                "inner join Suppliers s " +
                "on s.SupplierId = ps.SupplierId " +
                "where ps.SupplierId = " + supplierId;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            Product product = new Product(
                    rs.getInt(1),
                    rs.getString(2)
            );
            products.add(product);
        }
        conn.close();
        return products;
    }
}
