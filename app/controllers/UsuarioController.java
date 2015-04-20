package controllers;

import java.util.List;

import models.Usuario;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

@Transactional(value="marketplace")
public class UsuarioController extends Controller{
	
	public static Result getUsers() {
		List<Usuario> users = JPA.em().createQuery("SELECT u FROM Usuario u",Usuario.class).getResultList();
		return ok(Json.toJson(users));
	}
	
	public static Result getUser(Long id)
	{
		Usuario user = JPA.em().find(Usuario.class, id);
		return user == null ? notFound() : ok(Json.toJson(user));
	}
	
	public static Result createUser()
	{
		Usuario newUser = Json.fromJson(request().body().asJson(), Usuario.class);
		JPA.em().persist(newUser);
		return created(Json.toJson(newUser));
	}
	
	public static Result updateUser(Long id)
	{
		Usuario newUser= Json.fromJson(request().body().asJson(), Usuario.class);
		Usuario updated = JPA.em().merge( newUser);
		return ok(Json.toJson(updated));
	}
	
	public static Result deleteUser(Long id)
	{
		Usuario user=JPA.em().find(Usuario.class, id); 
		if(user==null)
		{
			return notFound();
		}
		else
		{
			JPA.em().remove(user); return noContent();
		} 
	}

}
