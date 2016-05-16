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
       
       model.put("template", "templates/index.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());
  }
}



// model.put("template", "templates/index.vtl");
// = new Review;
//  newReview.saveReviewToRestaurant(":id")
//  model.put(":id", request.params(:id))
