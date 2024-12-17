package website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try { 
		PrintWriter pw=resp.getWriter();
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy", "root", "root");
		String sql="update login set name=?,password=? where email=?";
		PreparedStatement pmst=conn.prepareStatement(sql);
		pmst.setString(1, name);
		pmst.setString(2, email);
		pmst.setString(3, password);
		int i=pmst.executeUpdate();
		if (i > 0) {
			pw.println("order placed successfully");
		} else {
			pw.println("Error");
		}
		conn.close();
		pmst.close();
		} catch (Exception e) {
		e.printStackTrace();
	}
}
}
