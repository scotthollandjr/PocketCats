import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.sql2o.*;

public class Comment {
  private int id;
  private String description;
  private LocalDateTime date;
  private int user_id;
  private int cat_id;

  public Comment(String description) {
    this.description = description;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public static List<Comment> all() {
    String sql = "SELECT id, user_id, description, date FROM comments";
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


  public void saveToCat(int inputId) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO comments (user_id, description, date, cat_id) VALUES (:user_id, :description, :date, :cat_id)";
      this.cat_id = inputId;
      this.id = (int) con.createQuery(sql, true)
        .addParameter("user_id", this.getId()) //what do i put here!
        .addParameter("description", this.getDescription())
        .addParameter("date", this.getDate())
        .addParameter("cat_id", this.cat_id)
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
