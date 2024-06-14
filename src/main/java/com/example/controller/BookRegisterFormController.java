package com.example.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerForm") // Servlet Mapping -> new BookRegisterFormController() -> service()
public class BookRegisterFormController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // 책 등록 화면으로 이동~~~(jsp)
             RequestDispatcher rd =req.getRequestDispatcher("/WEB-INF/views/register.jsp"); // -> ?
             rd.forward(req, resp);
    }
}
