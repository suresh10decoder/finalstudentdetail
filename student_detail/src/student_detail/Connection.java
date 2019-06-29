package student_detail;

import java.sql.*;

public class Connection {
	public java.sql.Connection con;
	Student student = new Student();

	public Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false", "root",
					"10decoders");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void add(Student student) {
		try {
			String query = " insert into list (ID, Name, Cgpa)" + " values (?, ?, ? )";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setInt(1, student.getId());
			pStmt.setString(2, student.getName());
			pStmt.setFloat(3, student.getCgpa());
			pStmt.execute();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

	public void show(Student student) {
		try {

			String query = "select * from list";
			PreparedStatement pStmt = con.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				rs.getInt(student.getId());
				rs.getString(student.getName());
				rs.getFloat((int) student.getCgpa());
				System.out.format("%s, %s, %s, \n", student.getId(), student.getName(), student.getCgpa());
			}
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void modify(Student stu) {
		try {
			PreparedStatement ps = con.prepareStatement("update list set name=? , cgpa=? where Id=?");
			ps.setInt(1, stu.getId());
			ps.setString(2, stu.getName());
			ps.setFloat(3, stu.getCgpa());
			ps.executeUpdate();
			System.out.println("records updated");
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void del(int i) {
		try {
			PreparedStatement ps = con.prepareStatement("delete from list" + " where id=?");
			ps.setInt(1, i);
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println("Got an Exception ");
			System.err.println(e.getMessage());
		}
	}
}
