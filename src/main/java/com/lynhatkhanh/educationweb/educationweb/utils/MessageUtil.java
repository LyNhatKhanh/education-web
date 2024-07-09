package com.lynhatkhanh.educationweb.educationweb.utils;


import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class MessageUtil {

    public static void showMessage(String message, Model model) {
        if (message != null) {
            String messageResponse = "";
            String alert = "";

            if (message.equals("insert_success")) {
                messageResponse = "Insert success";
                alert = "alert-success";
            } else if (message.equals("update_success")) {
                messageResponse = "Update success";
                alert = "alert-success";
            } else if (message.equals("error_system")) {
                messageResponse = "Error System";
                alert = "alert-danger";
            } else if (message.equals("delete_success")) {
                messageResponse = "Delete success";
                alert = "alert-success";
            }
            model.addAttribute("messageResponse", messageResponse);
            model.addAttribute("alert", alert);
        }
    }


}
