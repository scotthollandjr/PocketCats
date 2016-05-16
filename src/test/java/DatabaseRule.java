import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/pocket_cats_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCatsQuery = "DELETE FROM cats *;";
      String deleteCommentsQuery = "DELETE FROM comments *;";
      String deleteUsersQuery = "DELETE FROM users *;";
      String deleteJoin1Query = "DELETE FROM cats_users *;";
      con.createQuery(deleteCatsQuery).executeUpdate();
      con.createQuery(deleteCommentsQuery).executeUpdate();
      con.createQuery(deleteUsersQuery).executeUpdate();
      con.createQuery(deleteJoin1Query).executeUpdate();
    }
  }
}
