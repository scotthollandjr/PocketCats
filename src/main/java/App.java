import java.util.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cats", Cat.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cats", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cats", Cat.all());
      model.put("template", "templates/cats.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cat/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Cat newCat = Cat.find(Integer.parseInt(request.params(":id")));
      model.put("cat", newCat);
      model.put("users", User.all());
      model.put("template", "templates/cat.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/catmap", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/catmap.vtl");
      List<Location> locations = Location.all();
      model.put("locations", locations);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("cats/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("catName");
      //String location = request.queryParams("catLocation");
      //String date = request.queryParams("catDate");
      String description = request.queryParams("catDescription");
      Boolean status = false;
      Cat newCat = new Cat(name, description, status);
      newCat.save();
      response.redirect("/cats");
      return null;
    });

    post("/cat/:id/comment/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int catId = Integer.parseInt(request.queryParams("idCat"));
      int userId = Integer.parseInt(request.queryParams("idUser"));
      String username = request.queryParams("username");
      String description = request.queryParams("description");
      Comment newComment = new Comment(description, username);
      newComment.saveToCatAndUser(catId, userId);
      response.redirect("/cat/" + catId);
      return null;
    });

    post("/user/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      User newUser = new User(name);
      newUser.save();
      response.redirect("/");
      return null;
    });
  }
}



// model.put("template", "templates/index.vtl");
// = new Review;
//  newReview.saveReviewToRestaurant(":id")
//  model.put(":id", request.params(:id))
