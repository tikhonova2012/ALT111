package Service;

import Entity.Product;

import java.util.List;
import java.util.Map;

public interface ProdServ {
    List<Product> getAll(Map<String, String> map,List<Product> products);
    List<Product> getProducts(String color);
}
