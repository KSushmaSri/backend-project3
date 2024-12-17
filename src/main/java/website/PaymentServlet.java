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

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amount = req.getParameter("amount");
		try {
			PrintWriter pw = resp.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggy", "root", "root");
			String sql = "insert into payments(amount) values(?)";
			PreparedStatement pmst = conn.prepareStatement(sql);
			pmst.setString(1, amount);
			int i = pmst.executeUpdate();
			if (i > 0) {
				pw.println("order placed successfully");
			} else {
				pw.println("Error");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
