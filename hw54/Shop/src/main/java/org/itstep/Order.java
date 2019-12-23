package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet(
        name = "order",
        urlPatterns = "/order",
        initParams = {
                @WebInitParam(name = "db:url", value = "jdbc:mysql://localhost/shop?serverTimezone=Europe/Kiev&characterEncoding=utf8"),
                @WebInitParam(name = "db:user", value = "root"),
                @WebInitParam(name = "db:pass", value = "")
        }
)
public class Order extends HttpServlet {
    JDBCConnectionPool jdbcConnectionPool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jdbcConnectionPool = new JDBCConnectionPool(
                "com.mysql.cj.jdbc.Driver", config.getInitParameter("db:url"),
                config.getInitParameter("db:user"), getInitParameter("db:pass"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        Connection conn = jdbcConnectionPool.checkOut();

        String id = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("id".equals(cookie.getName())) {
                id = cookie.getValue();
            }
        }

        List<String> ordersId = Collections.synchronizedList(new ArrayList<>());
        int countParameters = 1;//name input записаны в form от 0++

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            parameterNames.nextElement();
            ordersId.add(req.getParameter(Integer.toString(countParameters)));//считываю id всех заказанных товаров в List
            countParameters++;
        }
        int idSeller;
        try {
            Random random = new Random();//получаю id продавца
            Integer countSellers = sqlQuery.getCountSellers(conn,"sellers");
            if (countSellers != null) {
                idSeller = random.nextInt((countSellers - 1) + 1);//from 1 to all count sellers
            }

            String INSERT_ORDER = "INSERT INTO orders(sellerID,customerID) VALUES(?,?)";
            String INSERT_GOODS = "INSERT INTO ordersGoods(orderID,goodsID) VALUES(2,2)";


        } catch (SQLException e) {
            e.printStackTrace();
            printWriter.println("<img src=\"../images/4.jpg\"/>");
        }

        printWriter.println(ordersId.toString());
        printWriter.println(id);


        jdbcConnectionPool.checkIn(conn);
    }
}

