package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class UserWeb extends Controller{
	
	public static Result renderUserList(){
		return ok(index.render("Usuarios"));
	}
	
	public static Result renderPurchaseList(){
		return ok(index.render("Compras"));
	}

}
