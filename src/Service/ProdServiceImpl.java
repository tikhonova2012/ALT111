package Service;

import DAO.ProductDAO;
import DAO.impl.ProductDAOimpl;
import Entity.Product;
import ServiceException.ServiceException;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProdServiceImpl extends CommonService implements ProdServ {
    private static volatile ProdServ INSTANCE = null;
    private ProductDAO productDAO = ProductDAOimpl.getInstance();
    private AttrServ attrService = AttrServiceImpl.getInstance();

    public static ProdServ getInstance() {
        ProdServ prodService = INSTANCE;
        if (prodService == null) {
            synchronized (ProductDAOimpl.class) {
                prodService = INSTANCE;
                if (prodService == null) {
                    INSTANCE = prodService = new ProdServiceImpl();
                }
            }
        }

        return prodService;
    }
    @Override
    public List<Product> getAll(Map<String, String> attributes,List<Product> products)  {

        int constr_lenght= Integer.parseInt(attributes.get("CONSTRUCTION_LENGTH"));
        int constr_height= Integer.parseInt(attributes.get("CONSTRUCTION_HEIGHT"));
        int gl_type=0;
        if(attributes.get("GLAZING_TYPE").equals("одинарное")){
            gl_type=1;
        }
        else{
            gl_type=2;//если двойное
        }
        int header_quantity= Integer.parseInt(attributes.get("HEADER_QUANTITY"));
        int step_between_racks= Integer.parseInt(attributes.get("STEP_BETWEEN_RACKS"));
        int ninty_grad_angles_quantity= Integer.parseInt(attributes.get("NINTY_GRAD_ANGLES_QUANTITY"));
        int free_grad_angles_quantity= Integer.parseInt(attributes.get("FREE_GRAD_ANGLES_QUANTITY"));

        List <Product> list = products;
        for(int i=0;i<list.size();i++) {
            //расчет каркасного профиля для одинарного остекления
            if (list.get(i).getArt().equals("AYPC.111.0102")  && gl_type == 1) {
                    list.get(i).setValue(constr_height+constr_lenght);
            }
            //расчет каркарсного профиля для двойного остекления
           else if (list.get(i).getArt().equals("AYPC.111.0104") && gl_type == 2) {
                list.get(i).setValue(constr_height+constr_lenght);
            }
            //расчет стойки для одинарного остекления
            else if (list.get(i).getArt().equals("AYPC.111.0101")  && gl_type == 1) {
                list.get(i).setValue((constr_lenght/step_between_racks-1)*constr_height);
            }
            //расчет стойки для двойного остекления
            else if (list.get(i).getArt().equals("AYPC.111.0103") && gl_type == 2) {
                list.get(i).setValue((constr_lenght/step_between_racks-1)*constr_height);
            }
            //расчет ригеля
           else if (list.get(i).getArt().equals("AYPC.111.0201") && gl_type == 2 && attributes.get("HEADER_TYPE").equals("основной")) {
                list.get(i).setValue(header_quantity*step_between_racks);
            }
            else if (list.get(i).getArt().equals("AYPC.111.0201") && gl_type == 2 && attributes.get("HEADER_TYPE").equals("экономичный")) {
                list.get(i).setValue(header_quantity*step_between_racks);
            }
            //расчет экономичного ригеля
           else if (list.get(i).getArt().equals("AYPC.111.0704") && gl_type == 1) {
                list.get(i).setValue(header_quantity*step_between_racks);
            }
             //расчет поворотного на 90 профиля для одинарного остекления
           else if (list.get(i).getArt().equals("AYPC.111.0304")  && gl_type == 1) {
                list.get(i).setValue(ninty_grad_angles_quantity*constr_height);
            }
            //расчет поворотного на 90 профиля для двойного остекления
           else if (list.get(i).getArt().equals("AYPC.111.0305") && gl_type == 2) {
                list.get(i).setValue(ninty_grad_angles_quantity*constr_height);
            }

            //расчет поворотного произвольного профиля для одинарного остекления
           else if (list.get(i).getArt().equals("AYPC.111.0302")  && gl_type == 1) {
                list.get(i).setValue(free_grad_angles_quantity*constr_height);
            }
            //расчет поворотного произвольного профиля для двойного остекления
           else if (list.get(i).getArt().equals("AYPC.111.0303") && gl_type == 2) {
                list.get(i).setValue(free_grad_angles_quantity*constr_height);
            }
else {
                switch (list.get(i).getArt()) {
                    case ("AYPC.111.0301"):
                        list.get(i).setValue(free_grad_angles_quantity*constr_height);
                        break;
                    case ("AYPC.111.0501"):
                        list.get(i).setValue(constr_height +constr_lenght+
                                +ninty_grad_angles_quantity*constr_height+
                                +free_grad_angles_quantity*constr_height);
                        break;
                    case ("AYPC.111.0502"):
                        list.get(i).setValue((constr_lenght/step_between_racks- 1) * constr_height +
                                +header_quantity*step_between_racks);
                        break;
                    default:
                        list.get(i).setValue(0);
                        break;
                }
            }

        }
        return list;
    }

    @Override
    public List<Product> getProducts(String color) {
        try {
            return productDAO.getByColor(color);
        } catch (SQLException e) {
            throw new ServiceException("Возникла ошибка на этапе получения профилей в заданном цвете");
        }
    }
}
