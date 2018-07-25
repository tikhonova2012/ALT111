package web;

import Entity.Complectation;
import Entity.Construction;
import Entity.Product;
import Service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/attr")
public class CountingServlet extends HttpServlet {

    private AttrServ attrService = AttrServiceImpl.getInstance();
    private ProdServ prodService = ProdServiceImpl.getInstance();
    private ComplectationService complectationService = ComplectationServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String prColor = req.getParameter("P_COLOR");
        String complColor = req.getParameter("C_COLOR");
        req.getSession().setAttribute("attrs", attrService.collectMap(req, resp));

        Map<String, String> attributes = attrService.collectMap(req, resp);
        List<String >warn=Validator.validate(attributes);

        if (Validator.isValidationPassed()) {
            List<Product> productsInUserColor = prodService.getProducts(prColor);
            List<Complectation> complInUserColor = complectationService.getCompl(complColor);
            List<Product> prod = prodService.getAll(attributes, productsInUserColor);
            List<Complectation> compl = complectationService.getAll(attributes, complInUserColor);
            List<Construction> construction = new ArrayList<>();
            construction.addAll(prod);
            construction.addAll(compl);
            Iterator<Construction> itr = construction.iterator();
            while (itr.hasNext()) {
                if (itr.next().getValue() == 0) {
                    itr.remove();
                }
            }
            req.getSession().setAttribute("props", construction);
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else {
            req.getSession().setAttribute("valid", warn);
            req.getSession().setAttribute("warn", "Неверно введены данные");
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        }
    }

}
