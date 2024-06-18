package com.example.controller;

import com.example.repository.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//http://localhost:8081/section2/remove?num=1
//                                                       | redirect
//http://localhost:8081/section2/list
@WebServlet("/bookView")
// MVC
// C :  Controller - Servlet(서블릿) -> POJO
public class BookViewController extends HttpServlet { // POJO
     @Override
       public void service(HttpServletRequest req, HttpServletResponse resp)
                                                           throws ServletException, IOException {
          // ?num=3
          int num=Integer.parseInt(req.getParameter("num")); // num="1"-->(int)
          // Model과 연동하기(DAO)
         BookDAO dao=new BookDAO();
         // 8시 7분~~

    }
}
