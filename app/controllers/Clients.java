package controllers;

import models.Client;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;



public class Clients extends Controller {
	
	public static Result register(){
		return ok(views.html.registration.registerV2.render(
				Form.form(Client.class)));
	}
	
	public static Result saveClient(){
		Form<Client> registrationForm = Form.form(Client.class).bindFromRequest();
			if(registrationForm.hasErrors()){
				return badRequest(views.html.registration.registerV2.render(registrationForm));
			}
			else{
				Client client = registrationForm.bindFromRequest().get();
				Long id = Client.saveClient(client);
				return ok("hola " +id);
			}
	}

}
