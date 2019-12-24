package org.itstep;

import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        Connection conn = jdbcConnectionPool.checkOut();
        ServletContext servletContext = getServletContext();
        try {
            conn.setAutoCommit(false);

            String idCustomer = "";
            Cookie[] cookies = req.getCookies(); //получаю id покупателя
            for (Cookie cookie : cookies) {
                if ("id".equals(cookie.getName())) {
                    idCustomer = cookie.getValue();
                }
            }

            List<Integer> ordersIdLis = Collections.synchronizedList(new ArrayList<>());
            int countParameters = 1;//name input записаны в form от 0++

            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                parameterNames.nextElement();
                ordersIdLis.add(Integer.valueOf(req.getParameter(String.valueOf(countParameters))));//считываю id всех заказанных товаров в List
                countParameters++;
            }

            Random random = new Random();//получаю random id продавца
            final String SELECT_SELLERS = "SELECT COUNT(*) FROM sellers";
            Integer countSellers = SqlQuery.getCountId(conn, SELECT_SELLERS);

            if (countSellers != null) {
                int idSeller = random.nextInt((countSellers - 1) + 1);//from 1 to all count sellers

                final String SELECT_ORDERS = "SELECT COUNT(*) FROM orders";
                Integer countOrders = SqlQuery.getCountId(conn, SELECT_ORDERS);

                if (countOrders != null) {
                    int idOrder = (countOrders == 0) ? 1 : countOrders + 1;//устанавливаю id заказа (получаю кол-во всех заказов +1)

                    final String INSERT_ORDER = "INSERT INTO orders(ordersID, sellerID, customerID) VALUES(?,?,?)";
                    final String INSERT_GOODS = "INSERT INTO ordersGoods(orderID,goodsID) VALUES(?,?)";
                    final String CHANGE_GOODS = "UPDATE goods SET counter = counter - 1 WHERE goods.goodsID = ?";

                    try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ORDER);
                         PreparedStatement preparedStatementOrders = conn.prepareStatement(INSERT_GOODS);
                         PreparedStatement preparedStatementGoods = conn.prepareStatement(CHANGE_GOODS)) {
                        preparedStatement.setInt(1, idOrder);    //запись заказа в бд
                        preparedStatement.setInt(2, idSeller);
                        preparedStatement.setInt(3, Integer.parseInt(idCustomer));
                        preparedStatement.executeUpdate();

                        for (Integer id : ordersIdLis) {     //запись товаров в заказ
                            preparedStatementOrders.setInt(1, idOrder);
                            preparedStatementOrders.setInt(2, id);
                            preparedStatementOrders.executeUpdate();

                            preparedStatementGoods.setInt(1, id);//изменяю кол-во каждого товара с заказа в бд
                            preparedStatementGoods.executeUpdate();
                        }
                        conn.commit();
                        printWriter.println("Success!");
                    }
                }
            }
        } catch (SQLException e) {
            conn.rollback();
            printWriter.println(PageError.showPage404(servletContext, "page404.html"));
            e.printStackTrace();
        }
        jdbcConnectionPool.checkIn(conn);
    }
}

