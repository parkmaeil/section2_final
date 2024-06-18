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

@WebServlet("/registerSave") // Servlet Mapping -> new BookRegisterSaveController() -> service()
public class BookRegisterSaveController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // 요청파라메터를 수집(DTO)
        req.setCharacterEncoding("utf-8"); // 유니코드(ㅈ ㅏ ㅂ ㅏ) -->자바(EUC-KR)

        String title=req.getParameter("title"); // 자바 -> ㅏㅈ  ㅂ ㅏ(61200->16진수 00011101010101)
        int price=Integer.parseInt(req.getParameter("price")); // "10000"->10000
        String author=req.getParameter("author");
        int page=Integer.parseInt(req.getParameter("page")); // "900"->900
        BookDTO dto=new BookDTO(); // 수집
        dto.setTitle(title);
        dto.setPrice(price);
        dto.setAuthor(author);
        dto.setPage(page);
        BookDAO dao=new BookDAO(); // -> bookRegister(dto)
        int cnt=dao.bookRegister(dto);
        // 등록이 성공 -> /list
        if(cnt>0) {
            resp.sendRedirect("/s2_f/list");
        }else{
            throw new ServletException("error");
        }
    }
}
