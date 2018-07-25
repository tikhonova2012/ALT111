package Service;

import DAO.ComplectationDAO;
import DAO.impl.ComplectationDAOImpl;
import Entity.Complectation;
import ServiceException.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ComplectationServiceImpl  extends CommonService implements ComplectationService{

    private static volatile ComplectationService INSTANCE = null;
    private ComplectationDAO complDAO = ComplectationDAOImpl.getInstance();
    private AttrServ attrService = AttrServiceImpl.getInstance();

    public static ComplectationService getInstance() {
        ComplectationService complService = INSTANCE;
        if (complService == null) {
            synchronized (ComplectationDAOImpl.class) {
                complService = INSTANCE;
                if (complService == null) {
                    INSTANCE = complService = new ComplectationServiceImpl();
                }
            }
        }

        return complService;
    }
    @Override
    public List<Complectation> getAll(Map<String, String> attributes, List<Complectation> complectations)  {

        int constr_lenght= Integer.parseInt(attributes.get("CONSTRUCTION_LENGTH"));
        int constr_height= Integer.parseInt(attributes.get("CONSTRUCTION_HEIGHT"));
        int gl_type=0;
        if(attributes.get("GLAZING_TYPE").equals("одинарное")){
            gl_type=1;
        }
        else{
            gl_type=2;//если двойное
        }
        int glazing_thickness= Integer.parseInt(attributes.get("GLAZING_THICKNESS"));
        int blank_filling_thickness= Integer.parseInt(attributes.get("BLANK_FILLING_THICKNESS"));
        int header_quantity= Integer.parseInt(attributes.get("HEADER_QUANTITY"));
        int step_between_racks= Integer.parseInt(attributes.get("STEP_BETWEEN_RACKS"));
        int ninty_grad_angles_quantity= Integer.parseInt(attributes.get("NINTY_GRAD_ANGLES_QUANTITY"));
        int free_grad_angles_quantity= Integer.parseInt(attributes.get("FREE_GRAD_ANGLES_QUANTITY"));

        List <Complectation> list = complectations;
        for(int i=0;i<list.size();i++) {
            //расчет уплотнителя FRK57
            if (list.get(i).getArt().equals("FRK57")  && glazing_thickness == 4||
                    list.get(i).getArt().equals("FRK57")  && glazing_thickness == 5||
                    list.get(i).getArt().equals("FRK57")  && blank_filling_thickness == 5||
                    list.get(i).getArt().equals("FRK57")  && blank_filling_thickness == 4) {
                list.get(i).setValue(constr_height+constr_lenght+
                +(constr_lenght/step_between_racks-1)*constr_height+
                +header_quantity*step_between_racks);
            }
            //расчет уплотнителя FRK58
            else if (list.get(i).getArt().equals("FRK58")  && glazing_thickness == 6||
                    list.get(i).getArt().equals("FRK58")  && blank_filling_thickness == 6) {
                list.get(i).setValue(constr_height+constr_lenght+
                        +(constr_lenght/step_between_racks-1)*constr_height+
                +header_quantity*step_between_racks);
            }
            //расчет уплотнителя FRK59
            else if (list.get(i).getArt().equals("FRK59")  && glazing_thickness == 8||
                    list.get(i).getArt().equals("FRK59")  && blank_filling_thickness == 8) {
                list.get(i).setValue(constr_height + constr_lenght +
                        +(constr_lenght / step_between_racks - 1) * constr_height+
                        header_quantity*step_between_racks);
            }
                //расчет уплотнителя FRK02
            else if (list.get(i).getArt().equals("FRK02")  && glazing_thickness == 10||
                        list.get(i).getArt().equals("FRK02")  && blank_filling_thickness == 10) {
                list.get(i).setValue(constr_height + constr_lenght +
                        +(constr_lenght / step_between_racks - 1) * constr_height+
                        header_quantity*step_between_racks);
            }
            //расчет сухарного элемента для одинарного заполнения
            else if (list.get(i).getArt().equals("AYPC.111.0954") && gl_type == 1) {
                list.get(i).setValue((constr_lenght/step_between_racks- 1) * constr_height);
            }
            //расчет сухарного элемента для двойного заполнения
            else if (list.get(i).getArt().equals("AYPC.111.0955")  && gl_type == 2) {
                list.get(i).setValue((constr_lenght/step_between_racks- 1) * constr_height);
            }

            else {
                switch (list.get(i).getArt()) {
                    case ("AYPC.111.0901"):
                        list.get(i).setValue((constr_height +constr_lenght+
                                +ninty_grad_angles_quantity*constr_height+
                                +free_grad_angles_quantity*constr_height)/3*10);
                        break;
                    case ("AYPC.111.0902"):
                        list.get(i).setValue(((constr_lenght/step_between_racks- 1) * constr_height +
                                +header_quantity*step_between_racks)/3*10);
                        break;
                    case ("AYPC.111.0951M"):
                        list.get(i).setValue((((constr_lenght/step_between_racks- 1) * constr_height)*
                                +(header_quantity*step_between_racks)));
                        break;
                    case ("AYPC.111.0952M"):
                        list.get(i).setValue(((constr_lenght/step_between_racks- 1) * constr_height +
                                +header_quantity*step_between_racks)*2+4);
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
    public List<Complectation> getCompl(String color) {
        try {
            return complDAO.getByColor(color);
        } catch (SQLException e) {
            throw new ServiceException("Возникла ошибка на этапе получения комплектации в заданном цвете");
        }
    }
}

