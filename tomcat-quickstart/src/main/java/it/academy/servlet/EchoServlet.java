package it.academy.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "echoServlet", urlPatterns = "/echo")
public class EchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            if (session.getAttribute("timestamp") == null) {
                session.setAttribute("timestamp", System.currentTimeMillis());
            }

            final String id = session.getId();

            final PrintWriter writer = resp.getWriter();
            writer.println("Hello from Echo servlet! " + new Date());
            writer.println("My session ID=" + id + " "
                    + session.getAttribute("timestamp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
