package Service;

import Entity.Complectation;
import Entity.Product;

import java.util.List;
import java.util.Map;

public interface ComplectationService {
    List<Complectation> getAll(Map<String, String> map, List<Complectation> products);
    List<Complectation> getCompl(String color);
}
