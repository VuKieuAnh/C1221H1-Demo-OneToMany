package controller;

import DAO.classes.ClassDAO;
import DAO.classes.IClassDAO;
import DAO.student.IStudentDAO;
import DAO.student.StudentDAO;
import model.Classes;
import model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    private IClassDAO classDAO = new ClassDAO();
    private IStudentDAO studentDAO = new StudentDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("classes", classDAO.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String idString  = request.getParameter("classes");
        int id_class = Integer.parseInt(idString);
        Classes classes = classDAO.findById(id_class);
        Student student = new Student(name, classes);
        studentDAO.save(student);
    }
}
