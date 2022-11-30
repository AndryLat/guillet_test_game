package dev.andrylat.game.javarush_game;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExceptionServlet", value = "/error")
public class ExceptionServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ExceptionServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("request for throwing exception");
        logger.info("Params: " + request.getAttributeNames());

        String exceptionName;
        if ((exceptionName = request.getParameter("exception")) != null) {
            logger.info("Throw exception: " + exceptionName);

            Class<?> exceptionClass = Class.forName(exceptionName);
            throw (Throwable) exceptionClass.getConstructor().newInstance();
        } else {
            logger.info("Throw default exception ");
            throw new RuntimeException("Default exception");
        }

    }
}