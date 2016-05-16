import java.util.List;
import java.util.ArrayList;
import java.util.TimeandDate;
import org.sql2o.*;

public class Cat {
  private int id;
  private String name;
  private Boolean status;
  private String location;
  private Timestamp date;
  private String description;

  public Cat() {
    this.id = id;
    this.name = name;
    this.status = status;
    this.location = location;
    this.date = date;
    this.description = description;
  }

  public getId() {
    return id;
  }

  public getName() {
    return name;
  }

  public getStatus() {
    return status;
  }

  public getLocation() {
    return location;
  }

  public getDate() {
    return date;
  }

  public getDescription() {
    return description;
  }

  public void save() {
    String sql = "INSERT INTO cats (name, status, location, date, description) VALUES (:name, :status, :location, :date, :description)";
    try (Connection con = DB.sql2o()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.getName)
        .addParameter("status", this.getStatus)
        .addParameter("location", this.getLocation)
        .addParameter("date", this.getDate)
        .addParameter("description", this.getDescription)
        .executeUpdate()
        .getKey();
    }
  }

  public static Cat find(int id) {
    String sql = "SELECT * FROM cats WHERE id=:id"
    try (Connection con = DB.sql2o()) {
      Cat cat = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cat.class);
      return Cat;
    }
  }

  public void updateName(String update) {
    String sql = "UPDATE cats SET name=:name WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .addParameter("name", update)
        .executeUpdate();
    }
  }

  public void updateStatus(Boolean status) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cats SET status=:status WHERE id=:id";
        con.createQuery(sql)
          .addParameter("id", this.getId())
          .addParameter("status", status)
          .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM cats WHERE id=:id";
      con.createQuery(deleteQuery)
        .addParameter("id", this.getId())
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM cats_users WHERE cat_id: cat_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("cat_id", this.getId())
        .executeUpdate();
    }
  }

  // Cat has many to many with users
  public void addUser(User user){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cats_users(cat_id, user_id) VALUES (:cat_id, :user_id)";
      con.createQuery(sql)
        .addParameter("cat_id", this.getId())
        .addParameter("user_id", user.getId())
        .executeUpdate();
    }
  }

  // Cat has many to many with usersv
  public List<User> getUsers() {
    String sql = "SELECT user_id FROM cats_users WHERE cat_id=:cat_id";
    try(Connection con = DB.sql2o.open()) {
      List<Integer> userIds = con.createQuery(sql)
        .addParameter("cat_id", this.getId())
        .executeAndFetch(Integer.class);

      List<User> users = new ArrayList<User>()
      for (Integer userId : userIds) {
        String sql = "SELECT * FROM users WHERE id=:id";
        User newUser = con.createQuery(sql) {
          .addParameter("id", userId)
          .executeAndFetch(User.class);
          users.add(newUser);
        }
        return users;
      }
    }
  }

  // Cat has one to many with Comments
  public List<Comments> getComments() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM comments WHERE cat_id=:cat_id";
      return con.createQuery(sql)
        .addParameter("cat_id", this.getId())
        .executeAndFetch(Comment.class);
    }
  }

}
