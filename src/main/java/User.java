import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.sql2o.*;

public class User {
  private int id;
  private String name;

  public User(String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void save() {
    String sql = "INSERT INTO users (name) VALUES (:name)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.getName())
        .executeUpdate()
        .getKey();
    }
  }

  public static User find(int id) {
    String sql = "SELECT * FROM users WHERE id=:id";
    try (Connection con = DB.sql2o.open()) {
      User user = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(User.class);
      return user;
    }
  }

  public void updateName(String update) {
    String sql = "UPDATE users SET name=:name WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .addParameter("name", update)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM users WHERE id=:id";
      con.createQuery(deleteQuery)
        .addParameter("id", this.getId())
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM cats_users WHERE user_id: user_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("user_id", this.getId())
        .executeUpdate();
    }
  }

  // User has many to many with users
  public void addCat(Cat cat){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cats_users(cat_id, user_id) VALUES (:cat_id, :user_id)";
      con.createQuery(sql)
        .addParameter("user_id", this.getId())
        .addParameter("cat_id", cat.getId())
        .executeUpdate();
    }
  }

  // User has many to many with usersv
  public List<Cat> getCats() {
    String sql = "SELECT cat_id FROM cats_users WHERE user_id=:user_id";
    try(Connection con = DB.sql2o.open()) {
      List<Integer> catIds = con.createQuery(sql)
        .addParameter("user_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Cat> cats = new ArrayList<Cat>();

      for (Integer catId : catIds) {
        String stringQuery = "SELECT * FROM cats WHERE id=:id";
        Cat newCat = con.createQuery(stringQuery)
          .addParameter("id", catId)
          .executeAndFetchFirst(Cat.class);
          cats.add(newCat);
        }
        return cats;
      }
    }

}
