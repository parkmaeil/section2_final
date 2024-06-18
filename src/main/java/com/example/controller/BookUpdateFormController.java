package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAO;

import javax.servlet.RequestDispatcher;
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
         BookDTO dto=dao.bookView(num);
         if(dto!=null){
             //  상세보기  View(JSP)
             // 객체바인딩
             req.setAttribute("dto", dto); // "dto"(문자열)---->BookDTO(번지)
             // RequestDispatcher(요청을 의뢰해주는 객체=비서)
             RequestDispatcher rd =req.getRequestDispatcher("/WEB-INF/views/view.jsp");
             rd.forward(req, resp); //----------------froward, dispatcher--------| ${dto}
         }else{
             System.out.println("데이터가 없습니다.");
         }
    }
}
