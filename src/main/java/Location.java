import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.sql2o.*;

public class Location {
  private int id;
  private String lat;
  private String lng;
  private int cat_id;

  public Location(String lat, String lng, int cat_id) {
    this.lat = lat;
    this.lng = lng;
    this.cat_id = cat_id;
  }

  public int getId() {
    return id;
  }

  public String getLat() {
    return lat;
  }

  public String getLng() {
    return lng;
  }

  public int getCatId() {
    return cat_id;
  }

  public static List<Location> all() {
    String sql = "SELECT id, lat, lng, cat_id FROM locations";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Location.class);
    }
  }

  @Override
  public boolean equals (Object otherLocation) {
    if(!(otherLocation instanceof Location)) {
      return false;
    } else {
      Location newLocation = (Location) otherLocation;
      return this.getId() == newLocation.getId() &&
             this.getLat().equals(newLocation.getLat()) &&
             this.getLng().equals(newLocation.getLng()) &&
             this.getCatId() == newLocation.getCatId();
    }
  }

  public void save() {
    String sql = "INSERT INTO locations (lat, lng, cat_id) VALUES (:lat, :lng, :cat_id)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("lat", this.getLat())
        .addParameter("lng", this.getLng())
        .addParameter("cat_id", this.getCatId())
        .executeUpdate()
        .getKey();
    }
  }

  public static Location find(int id) {
    String sql = "SELECT * FROM locations WHERE id=:id";
    try (Connection con = DB.sql2o.open()) {
      Location location = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Location.class);
      return location;
    }
  }

}

  // public void updateName(String update) {
  //   String sql = "UPDATE cats SET name=:name WHERE id=:id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //       .addParameter("name", update)
  //       .addParameter("id", this.getId())
  //       .executeUpdate();
  //   }
  // }

  // public void updateStatus(Boolean status) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE cats SET status=:status WHERE id=:id";
  //       con.createQuery(sql)
  //         .addParameter("id", this.getId())
  //         .addParameter("status", status)
  //         .executeUpdate();
  //   }
  // }

  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //   String deleteQuery = "DELETE FROM cats WHERE id=:id";
  //     con.createQuery(deleteQuery)
  //       .addParameter("id", this.getId())
  //       .executeUpdate();

  //   String joinDeleteQuery = "DELETE FROM cats_users WHERE cat_id=:cat_id";
  //     con.createQuery(joinDeleteQuery)
  //       .addParameter("cat_id", this.getId())
  //       .executeUpdate();
  //   }
  // }

//   // Cat has many to many with users
//   public void addUser(User user){
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO cats_users(cat_id, user_id) VALUES (:cat_id, :user_id)";
//       con.createQuery(sql)
//         .addParameter("cat_id", this.getId())
//         .addParameter("user_id", user.getId())
//         .executeUpdate();
//     }
//   }

//   // Cat has many to many with usersv
//   public List<User> getUsers() {
//     String sql = "SELECT user_id FROM cats_users WHERE cat_id=:cat_id";
//     try(Connection con = DB.sql2o.open()) {
//       List<Integer> userIds = con.createQuery(sql)
//         .addParameter("cat_id", this.getId())
//         .executeAndFetch(Integer.class);

//       List<User> users = new ArrayList<User>();

//       for (Integer userId : userIds) {
//         String stringQuery = "SELECT * FROM users WHERE id=:id";
//         User newUser = con.createQuery(stringQuery)
//           .addParameter("id", userId)
//           .executeAndFetchFirst(User.class);
//           users.add(newUser);
//         }
//         return users;
//       }
//     }

//   // Cat has one to many with Comments
//   public List<Comment> getComments() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM comments WHERE cat_id=:id";
//       return con.createQuery(sql)
//         .addParameter("id", this.id)
//         .executeAndFetch(Comment.class);
//     }
//   }

// }
