package Service;

import DAO.AttributeDAO;
import DAO.impl.AttributeDAOimpl;
import Entity.Product;
import ServiceException.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AttrServiceImpl extends CommonService implements AttrServ{
    private static volatile AttrServ INSTANCE = null;
    AttributeDAO attrDao = AttributeDAOimpl.getInstance();


    public static AttrServ getInstance() {
        AttrServ attrService = INSTANCE;
        if (attrService == null) {
            synchronized (AttrServiceImpl.class) {
                attrService = INSTANCE;
                if (attrService == null) {
                    INSTANCE = attrService = new AttrServiceImpl();
                }
            }
        }

        return attrService;
    }
    @Override
    public HashMap<String,String> getAll()  {
        try {
            return attrDao.getAll();
        } catch (SQLException e) {
            throw new ServiceException("Возникла ошибка на этапе получения характеристик конструкции");
        }
    }

    @Override
    public Map<String, String> collectMap(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> attr = null;
        try {
            attr = attrDao.getAll();
            for (Map.Entry<String, String> entry : attr.entrySet()) {
                attr.put(entry.getKey(), req.getParameter(entry.getKey()));
            }

        } catch (SQLException e) {
            throw new ServiceException("Возникла ошибка на этапе получения характеристик конструкции");
        }
        return attr;
    }




}

