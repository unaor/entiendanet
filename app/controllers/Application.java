package controllers;


import play.*;
import play.data.Form;
import play.mvc.*;

import util.pdf.PDF;
import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render());
    }
    
    public static Result document(){
    	return PDF.ok(invoice.render());
    }
    
    
  
}
