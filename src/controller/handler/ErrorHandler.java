/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nesch
 */
public class ErrorHandler {
    
    public boolean isValidEmail(String email) {
        
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        
        Matcher matcher = PATTERN.matcher(email);
        
        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
        Pattern PATTERN = Pattern.compile(PASSWORD_REGEX);
        
        Matcher matcher = PATTERN.matcher(password);
        
        return matcher.matches();
    }
    
    public boolean isNotEmptyField(String[] fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
