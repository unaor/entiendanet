package controllers;

import models.AppUser;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticated;

@Authenticated(Secured.class)
public class DashBoard extends Controller {
	
	public static Result index(){
		AppUser user = (AppUser) Context.current().args.get("user");
		return ok(views.html.dashboard.index.render(user));
	}

}
