package controllers;



import java.util.HashMap;
import java.util.Map;

import models.AppUser;
import authtoken.validator.AuthenticityToken;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.*;
import play.mvc.Http.Context;
import play.*;

import util.AppConstants;
import views.html.*;

public class Login extends Controller {
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	public static Result login(){
		
		

		return ok(login.render(Form.form(LoginForm.class))
				);
			
	}
	
	public static Result authenticate(){
		Form<LoginForm> loginform = Form.form(LoginForm.class).bindFromRequest();
    	if(loginform.hasErrors()){
    		return badRequest(login.render(loginform));
    	}
    	else{
    		session().clear();
    		AppConstants constants = new AppConstants();
    		AppUser user = AppUser.getUserByEmail(loginform.get().email);
    		session("email",loginform.get().email);
    		map.put(loginform.get().email, user);
    		constants.setItemMap(map);
    		Context.current().args.put("user", user);
    		return redirect(routes.DashBoard.index());
    	}
	}
	
	public static Result logout(){
		session().clear();
    	flash("success", "You've been logged out");
    	return redirect(routes.Application.index());
	}
	
	public static class LoginForm{
		
		@AuthenticityToken
		public String authtoken;
		@Required
		public String email;
		@Required
		public String password;
		
		public String validate(){
			if(AppUser.authenticate(email, password)==null){
				return "The details entered were incorrect";
			}
			else{
				return null;
			}
		}
	}

}
