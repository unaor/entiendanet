package controllers;


import models.FormData;
import models.AppUser;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Registration extends Controller {
	
	public static Result register(){
		return ok(views.html.registration.registerV3.render(
				Form.form(AppUser.class)));
	}
	
	public static Result saveUser(){
		Form<AppUser> registrationForm = Form.form(AppUser.class).bindFromRequest();

		if(!AppUser.isEmailFree(registrationForm.field("email").value().trim())){
			registrationForm.reject("email", "The email "+registrationForm.field("email").value() +" is already taken");
		}
		if(!registrationForm.field("hashedPassword").value().equals(registrationForm.field("repeatPassword").value())){
			registrationForm.reject("repeatPassword" ,"Password not match");
		}
		if(registrationForm.hasErrors()){
				return badRequest(views.html.registration.registerV3.render(registrationForm));
			}
		
			else{
				AppUser user  = new AppUser();
				user.email = registrationForm.bindFromRequest().field("email").value();
				user.userFirstName=registrationForm.bindFromRequest().field("userFirstName").value();
				user.userSurname =registrationForm.bindFromRequest().field("userSurName").value();
				user.hashedPassword=registrationForm.bindFromRequest().field("hashedPassword").value();
				AppUser.createAppUser(user);
				session("email", user.email);
				session("userId" ,user.userId.toString());
				return redirect(routes.DashBoard.index());
			}
	}
		
}
