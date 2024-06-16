package com.example.repository;
// JDBC -> MyBatis(Java<-->SQL) ->Hibernate(ORM) + SQL(X) ->Spring Data JPA(O)
import com.example.entity.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDAO {  // JDBC -> 생산성이 떨어진다. -> MyBatis ~~~
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConnect(){
        String driverClassName="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/books";
        String username="root";
        String password="12345";
        try{
            Class.forName(driverClassName);
            conn=DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  conn;
    }
    public List<BookDTO> bookList(){
        List<BookDTO> list=new ArrayList<>();
        String SQL="select * from book order by title desc"; // Mapper XML file
        conn=getConnect(); // Connection 생성 -> 오버해드(시간이 걸린다 ,1초), 재활용(Connection POOL)
         try{
             ps=conn.prepareStatement(SQL);
             rs=ps.executeQuery();
             while (rs.next()){
                   int num=rs.getInt("num");
                   String title=rs.getString("title");
                   int price=rs.getInt("price");
                  String author=rs.getString("author");
                   int page=rs.getInt("page");
                   // 묶고(DTO)->담고(List)
                   BookDTO dto=new BookDTO(num, title, price, author, page);
                   //dto.setNum(num);
                   list.add(dto);
             }
         }catch (Exception e){
             e.printStackTrace();
         }
         return list;
    }
    public int bookRemove(int num){
        int cnt=-1; // 실패의미=>1
        conn=getConnect();
        String SQL="delete from book where num=?"; // ? 파라메터 ->  #{  } -> ?1
        try{
            ps=conn.prepareStatement(SQL);
            ps.setInt(1,  num); //  ? 설정
            cnt=ps.executeUpdate(); // 실행->성공(1), 실패(0)
        }catch(Exception e){
            e.printStackTrace();
        }
        return cnt; // 1(성공)
    }
    // 등록 기능 -> 등록기능 만들어서 오기 ? --> 9:30분~3시간 줌
    // JavaEE -> Tomcat : 8080(port)<----------Request(요청)---------------client
    public int bookRegister(BookDTO dto){
        int cnt = 0;
        conn=getConnect();
        String SQL = "INSERT INTO book (title, price, author, page) VALUES (?,?,?,?)";
        try{
            ps=conn.prepareStatement(SQL);
            ps.setString(1,  dto.getTitle()); //  ? 설정
            ps.setInt(2,  dto.getPrice()); //  ? 설정
            ps.setString(3,  dto.getAuthor()); //  ? 설정
            ps.setInt(4,  dto.getPage()); //  ? 설정
            cnt=ps.executeUpdate(); // 실행->성공(1), 실패(0)
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(ps!=null)
                    ps.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cnt; // 1(성공)
    }
   // bookView(num) 추가

}
