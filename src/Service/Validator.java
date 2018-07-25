package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static final String CONSTRUCTION_LENGTH = "CONSTRUCTION_LENGTH";
    public static final String CONSTRUCTION_HEIGHT = "CONSTRUCTION_HEIGHT";
    public static final String HEADER_QUANTITY = "HEADER_QUANTITY";
    public static final String CEILING_FITTING_POINT_QUANTITY = "CEILING_FITTING_POINT_QUANTITY";
    public static final String WALL_FITTING_POINT_QUANTITY = "WALL_FITTING_POINT_QUANTITY";
    public static final String STEP_BETWEEN_RACKS = "STEP_BETWEEN_RACKS";
    public static final String NINTY_GRAD_ANGLES_QUANTITY = "NINTY_GRAD_ANGLES_QUANTITY";
    public static final String FREE_GRAD_ANGLES_QUANTITY = "FREE_GRAD_ANGLES_QUANTITY";
    public static final String FREE_DOORS_QUANTITY = "FREE_DOORS_QUANTITY";
    private static boolean validationPassed=true;
    private static List<String> validationList;

    public static List<String> getValidationList() {
        return validationList;
    }


    public static boolean isValidationPassed() {
        return validationPassed;
    }



    public static List<String> validate(Map<String, String> map) {
        List<String> validationList = new ArrayList<>();
        Pattern p = Pattern.compile("[0-9]+");
        Matcher constr_lenght = p.matcher(map.get(CONSTRUCTION_LENGTH));
        Matcher constr_height = p.matcher(map.get(CONSTRUCTION_HEIGHT));
        Matcher header_quontity = p.matcher(map.get(HEADER_QUANTITY));
        Matcher ceiling_fitting_point_q = p.matcher(map.get(CEILING_FITTING_POINT_QUANTITY));
        Matcher wall_fitting_point_quantity = p.matcher(map.get(WALL_FITTING_POINT_QUANTITY));
        Matcher step_between_racks = p.matcher(map.get(STEP_BETWEEN_RACKS));
        Matcher ninty_grad_angles_quantity = p.matcher(map.get(NINTY_GRAD_ANGLES_QUANTITY));
        Matcher free_grad_angles_quantity = p.matcher(map.get(FREE_GRAD_ANGLES_QUANTITY));
        Matcher free_doors_quantity = p.matcher(map.get(FREE_DOORS_QUANTITY));

        if (constr_lenght.find()) {
            validationList.add("Длина конструкции задана верно");

        } else {

            validationList.add("Длина конструкции задана НЕ верно");
            validationPassed=false;
        }

        if (constr_height.find()) {
            validationList.add("Высота конструкции задана верно");

        } else {

            validationList.add("Высота конструкции задана НЕ верно");
            validationPassed=false;
        }

        if (header_quontity.find()) {
            validationList.add("Количество ригелей задано верно");

        } else {

            validationList.add("Количество ригелей задано НЕ верно");
            validationPassed=false;
        }

        if (ceiling_fitting_point_q.find()) {
            validationList.add("Количество точек крепления к потолку задано верно");

        } else {

            validationList.add("Количество точек крепления к потолку задано НЕ верно");
            validationPassed=false;
        }

        if (wall_fitting_point_quantity.find()) {
            validationList.add("Количество точек крепления к стене задано верно");

        } else {

            validationList.add("Количество точек крепления к стене задано НЕ верно");
            validationPassed=false;
        }

        if (step_between_racks.find()) {
            validationList.add("Шаг между стойками задан верно");

        } else {
            validationList.add("Шаг между стойками задан НЕ верно");
            validationPassed=false;
        }

        if (ninty_grad_angles_quantity.find()) {
            validationList.add("Количество углов поворота на 90 градусов задано верно");

        } else {

            validationList.add("Количество углов поворота на 90 градусов задано НЕ верно");
            validationPassed=false;
        }

        if (free_grad_angles_quantity.find()) {
            validationList.add("Количество произвольных углов поворота задано верно");

        } else {

            validationList.add("Количество произвольных углов поворота задано НЕ верно");
            validationPassed=false;
        }

        if (free_doors_quantity.find()) {
            validationList.add("Количество пустых дверных проемов задано верно");

        } else {

            validationList.add("Количество пустых дверных проемов задано НЕ верно");
            validationPassed=false;
        }

        return validationList;
    }
}
