package Service;

import Entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public interface AttrServ {
    HashMap<String,String> getAll();
    Map<String, String> collectMap(HttpServletRequest req, HttpServletResponse resp);



//    String collectColor(Map<String,Integer> map);
}

