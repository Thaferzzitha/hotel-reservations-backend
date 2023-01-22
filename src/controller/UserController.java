/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicaciones.web.hotel.reservations.controller;

import com.aplicaciones.web.hotel.reservations.controller.auth.PasswordHasher;
import com.aplicaciones.web.hotel.reservations.controller.handler.ErrorHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.aplicaciones.web.hotel.reservations.model.User;
import com.aplicaciones.web.hotel.reservations.model.dao.UserDAO;
import com.aplicaciones.web.hotel.reservations.model.factory.ConnectionManager;
import jakarta.servlet.annotation.WebServlet;

/**
 *
 * @author nesch
 */

@WebServlet("/user/servlet")
public class UserController extends HttpServlet{
    
    RequestDispatcher dispatcher = null;
    
    private UserDAO userDAO;

    public UserController() throws SQLException {
        ConnectionManager conn = new ConnectionManager();
        this.userDAO = new UserDAO(conn.getConnection());
    }
    
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) throws ServletException, IOException {
		
            String action = request.getParameter("action");

            if(action == null) {
                    action = "LIST";
            }

            switch(action) {

                    case "LIST":
                    {
                        try {
                            listUser(request, response);
                        } catch (SQLException ex) {
                            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                            break;


                    /*case "EDIT":
                            getSingleUser(request, response);
                            break;

                    case "DELETE":
                            deleteUser(request, response);
                            break;*/

                    default:
                    {
                        try {
                            listUser(request, response);
                        } catch (SQLException ex) {
                            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                            break;


            }

    }
    
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
		
            String userId = request.getParameter("user_id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int role = Integer.parseInt(request.getParameter("role"));

            if(userId.isEmpty() || userId == null) {
                String message = "";
                try {
                    message = registerUser(
                            username,
                            password,
                            email,
                            role
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    message = "ERROR";
                }
                request.setAttribute("message", message);
            }else {
                /*user.setId(Integer.parseInt(userId));
                try {
                    userDAO.update(user);
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("NOTIFICATION", "Usuario actualizado éxitosamente!");*/
            }

        try {
            listUser(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listUser(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException, SQLException {
		
        List<User> theList = userDAO.list();

        request.setAttribute("list", theList);

        dispatcher = request.getRequestDispatcher("/user/list.jsp");

        dispatcher.forward(request, response);
    }
    
    private String registerUser(String username,String password,String email,int role) throws SQLException {
        ErrorHandler error = new ErrorHandler();
        
        String[] fields = {username, email, password};
        if (error.isNotEmptyField(fields) == false) {
            return "Los campos con * son obligatorios";
        }
        
        if (error.isValidPassword(password) == false) {
            return "Contraseña debe tener al menos 8 caracteres, una mayuscula, un número y un caracter especial";
        }
        
        if (error.isValidEmail(email) == false) {
            return "Correo Electrónico no es válido";
        }
        
        if (userDAO.checkUniqueEmail(email) == false) {
            return "Correo Electrónico ya registrado, inicie sesión";
        }    
        
        if (userDAO.checkUniqueUsername(username) == false) {
            return "Nombre de Usuario ya registrado, inicie sesión";
        }
        
        PasswordHasher hasher = new PasswordHasher();
        password = hasher.hashPassword(password);
        User user = new User(
            username,
            password,
            email,
            role
        );
        userDAO.create(user);
        
        return "Usuario registrado éxitosamente";
    }
}
