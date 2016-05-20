import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.*;

public class Comment {
  private int id;
  private String username;
  private String description;
  private Date date;
  private int user_id;
  private int cat_id;

  public Comment(String description, String username) {
    this.username = username;
    this.description = description;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getUsername() {
    return username;
  }

  public Date getDate() {
    java.util.Date date = new java.util.Date();
    return date;
  }

  public int getUser_id() {
    return user_id;
  }

  public static List<Comment> all() {
    String sql = "SELECT id, description, user_id, cat_id, date FROM comments";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Comment.class);
    }
  }

  @Override
  public boolean equals (Object otherComment) {
    if(!(otherComment instanceof Comment)) {
      return false;
    } else {
      Comment newComment = (Comment) otherComment;
      return this.getDescription().equals(newComment.getDescription()) &&
             this.getId() == newComment.getId();
    }
  }


  public void saveToCatAndUser(int catInput, int userInput) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO comments (username, description, date, cat_id, user_id) VALUES (:username, :description, :date, :cat_id, :user_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("username", this.getUsername())
        .addParameter("description", this.getDescription())
        .addParameter("date", this.getDate())
        .addParameter("cat_id", catInput)
        .addParameter("user_id", userInput)
        .executeUpdate()
        .getKey();
    }
  }



  public static Comment find(int id) {
    String sql = "SELECT * FROM comments WHERE id=:id";
    try (Connection con = DB.sql2o.open()) {
      Comment comment = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Comment.class);
      return comment;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM comments WHERE id=:id";
      con.createQuery(deleteQuery)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

}
