package controllers;

import java.util.List;

import models.Compra;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@Transactional(value="marketplace")
public class CompraController extends Controller{
	
	public static Result getPurchases() {
		List<Compra> purchases = JPA.em().createQuery("SELECT u FROM Compra u",Compra.class).getResultList();
		return ok(Json.toJson(purchases));
	}
	
	public static Result getPurchase(Long id)
	{
		Compra purchase = JPA.em().find(Compra.class, id);
		return purchase == null ? notFound() : ok(Json.toJson(purchase));
	}
	
	public static Result createPurchase()
	{
		Compra newPurchase = Json.fromJson(request().body().asJson(), Compra.class);
		JPA.em().persist(newPurchase);
		return created(Json.toJson(newPurchase));
	}
	
	public static Result updatePurchase(Long id)
	{
		Compra newPurchase= Json.fromJson(request().body().asJson(), Compra.class);
		Compra updated = JPA.em().merge( newPurchase);
		return ok(Json.toJson(updated));
	}
	
	public static Result deletePurchase(Long id)
	{
		Compra purchase=JPA.em().find(Compra.class, id); 
		if(purchase==null)
		{
			return notFound();
		}
		else
		{
			JPA.em().remove(purchase); return noContent();
		} 
	}

}
