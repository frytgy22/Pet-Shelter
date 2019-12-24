package org.itstep;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebFilter(filterName = "FilterCharset", urlPatterns = {"/*"})
@WebServlet(
        name = "shop",
        urlPatterns = "/shop",
        initParams = {
                @WebInitParam(name = "db:url", value = "jdbc:mysql://localhost/shop?serverTimezone=Europe/Kiev&characterEncoding=utf8"),
                @WebInitParam(name = "db:user", value = "root"),
                @WebInitParam(name = "db:pass", value = "")
        }
)
public class Basket extends HttpServlet implements Filter {
    JDBCConnectionPool jdbcConnectionPool;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {  //вывод русского текста
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jdbcConnectionPool = new JDBCConnectionPool(
                "com.mysql.cj.jdbc.Driver", config.getInitParameter("db:url"),
                config.getInitParameter("db:user"), getInitParameter("db:pass"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        Connection conn = jdbcConnectionPool.checkOut();
        ServletContext servletContext = getServletContext();
        StringBuilder webXmlBuilder = new StringBuilder();
        try (InputStream stream = servletContext.getResourceAsStream("shop.html");//вывод страницы магазина
             InputStreamReader streamReader = new InputStreamReader(stream);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                webXmlBuilder.append(line);
            }
            printWriter.println(webXmlBuilder.toString());
        }


        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {             //получаю логин
            if ("login".equals(cookie.getName())) {
                String cookieValue = cookie.getValue();
                printWriter.println("<script>" +
                        "window.addEventListener('load', () => {" +
                        "document.getElementById(\"p\").appendChild(document.createTextNode(\"" + cookieValue + "\"));});" +
                        "</script>");
                break;
            }
        }

        try {
            List<Goods> goods = SqlQuery.getGoods(conn);  //получаю перечень всех товаров с бд

            for (int i = 0; i < goods.size(); i++) { //записываю в value button кол-во соответствуюшего товара (button.id == goods.id)
                printWriter.println("<script>" +
                        "window.addEventListener('load', () => {" +
                        "            let but=document.getElementById(" + (i + 1) + ");\n" +
                        "            but.setAttribute('value'," + goods.get(i).getCount() + ");" +
                        "            but.setAttribute('name',\"" + goods.get(i).getName() + "\");" +
                        "            if(but.value < 1){but.disabled = true;\n" +            //если кол-во < 1, кнопка "купить" не активна
                        "                but.style.background = \"linear-gradient(#a4a4a4, #a4a4a4 48%, #848484 52%, #3c3c3c)\";\n" +
                        "                but.style.boxShadow = \"none\";\n" +
                        "                but.style.border = \" 2px solid #848484\";\n" +
                        "                but.style.paddingLeft='3px';" +
                        "                but.replaceChild(document.createTextNode(\"ПРОДАНО\"), but.firstChild);};" +
                        "});</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            printWriter.println(PageError.showPage404(servletContext, "page404.html"));
        }
        jdbcConnectionPool.checkIn(conn);
    }
}
