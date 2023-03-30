package com.polytechancy.BigCloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.polytechancy.BigCloud.DataBaseAccess;

@Controller
public class PagesController {
	
	@GetMapping("/")
	public String home() {
		return "Rules";
	}
	@GetMapping("/login")
	public String login() {
		return "Login";
	}
	@GetMapping("/register")
	public String register() {
		return "Register";
	}
	@PostMapping("/register")
	public String registered(ModelMap model, @RequestParam String email, @RequestParam String username,@RequestParam String password, @RequestParam String passwordconfirm) {
		if (password.equals(passwordconfirm)) {
			try {
	            DataBaseAccess db = new DataBaseAccess();
	            
	            // Exécution d'une requête SELECT
	            String sqlSelect = "SELECT mail,name FROM Users";
	            ResultSet resultSelect = db.executeQuery(sqlSelect);
	            while (resultSelect.next()) {
	                String mail = resultSelect.getString("mail");
	                String name = resultSelect.getString("name");
	               if (email.equals(mail) || username.equals(name)) {
	            	   model.put("errorMsg", "L'adresse mail ou le nom d'utilisateur existe déjà !");
	            	   return "Register";
	               }
	            }
	            String sqlInsert = "INSERT INTO Users (name, mail, password, space) VALUES ("+username+","+email+","+password+","+"1)";
	            int rowsInserted = db.executeUpdate(sqlInsert);
	            System.out.println(rowsInserted + " lignes ont été insérées.");
	            
	            db.close();
	        } catch (SQLException e) {
	            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
	        }
			model.put("AcceptMsg", "Vous êtes correctement inscrit à BigCloud ! Vous pouvez désormais vous connecter");
			return "Login";
		}
		
		model.put("errorMsg", "Les mots de passe ne sont pas identiques");
		return "Register";
	}
}
