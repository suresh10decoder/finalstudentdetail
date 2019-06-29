package student_detail;

import java.util.ArrayList;
import java.util.Scanner;

public class Test extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Student> someStudent = new ArrayList<Student>();
	Scanner scan = new Scanner(System.in);
	Student student = new Student();
	Connection connection = new Connection();

	public static void main(String[] args) {
		Test test = new Test();

		Scanner sc = new Scanner(System.in);
		int op;
		do {
			System.out.println("Enter your choice");
			System.out.println("1.Add stuent detail");
			System.out.println("2.remove student detail ");
			System.out.println("3.update student detail");
			System.out.println("4.display");
			System.out.println("5.exit");
			op = sc.nextInt();
			switch (op) {
			case 1:
				test.add();
				break;
			case 2:
				test.remove();
				break;
			case 3:
				test.update();
				break;
			case 4:
				test.display();
				break;
			case 5:
				System.exit(0);
				break;
			}
		} while (op > 0 && op <= 4);
		sc.close();
	}

	public void add() throws Invalidcgpaexception {

		System.out.print(" ID: ");
		int id = scan.nextInt();
		System.out.print(" Name: ");
		String name = scan.next();
		float cgpa;
		try {
			System.out.print(" CGPA: ");
			cgpa = scan.nextFloat();

			if (cgpa >= 5)

				System.out.println("valid");
			else
				throw new Exception();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new Invalidcgpaexception("Invalid Cgpa entered");
		}
		someStudent.add(new Student(id, name, cgpa));
		connection.add(new Student(id, name, cgpa));
	}

	public void remove() {

		System.out.print(" Enter student id to delete");
		int delete = scan.nextInt();
		for (Student student : someStudent) {
			if (student.getId() == delete) {
				System.out.println(student.getId());
				System.out.println(delete);
				someStudent.remove(delete);
				connection.del(delete);
			} else {
				System.out.println("Student with ID " + delete + " was not found");
			}
		}
	}

	public void update() {

		System.out.print("Select student by ID: ");
		int pickID = scan.nextInt();
		for (Student students : someStudent) {
			if (students.getId() == pickID) {
				System.out.println(students.getId());
				System.out.print("enter student id: ");
				int newID = scan.nextInt();
				students.setId(newID);
				System.out.print("enter student name: ");
				String newName = scan.next();
				students.setName(newName);
				System.out.print("enter student cgpa: ");
				float newCGPA = scan.nextFloat();
				students.setCgpa(newCGPA);
				connection.modify(students);
			}
		}

	}

	public void display() {
		for (int i = 0; i < someStudent.size(); i++) {
			System.out.println(someStudent.size());
			System.out.println(someStudent.get(i));
			connection.show(someStudent.get(i));
		}

	}

}