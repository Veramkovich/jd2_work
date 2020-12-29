package it.academy.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecDaoImpl implements ProductSpecDao {

    private final Connection connection;

    public ProductSpecDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ProductSpec productSpec) {

    }

    @Override
    public ProductSpec read(int id) {
        return null;
    }

    @Override
    public List<ProductSpec> readAll() {
        List<ProductSpec> productSpecs = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from product_spec")) {
            while (resultSet.next()) {
                ProductSpec product = new ProductSpec();
                product.setId(resultSet.getInt("id"));
                product.setProductDate(resultSet.getDate("product_date"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductDetails(resultSet.getString("product_details"));
                productSpecs.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productSpecs;
    }

    @Override
    public void update(ProductSpec productSpec) {

    }

    @Override
    public void delete(ProductSpec productSpec) {

    }
}
