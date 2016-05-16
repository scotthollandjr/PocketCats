import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void User_instantiatesCorrectly_true() {
    User newUser = new User("Pepe");
    assertEquals(true, newUser instanceof User);
  }

  @Test
  public void all_EmptyAtFirst_true() {
    assertEquals(0, User.all().size());
  }

  @Test
  public void equals_returnTrueIdUsersAreTheSame() {
    User newUser = new User("Pepe");
    User newerUser = new User("Pepe");
    assertTrue(newUser.equals(newerUser));
  }

  @Test
  public void save_savesToUserDatabase() {
    User newUser = new User("Pepe");
    newUser.save();
    assertTrue(User.all().get(0).equals(newUser));
  }

  @Test
  public void save_saveAssignsIdToObject() {
    User newUser = new User("Pepe");
    newUser.save();
    assertEquals(newUser.getId(), User.all().get(0).getId());
  }

  @Test
  public void find_findUserInDatabase() {
    User newUser = new User("Pepe");
    newUser.save();
    User savedUser = User.all().get(0);
    assertEquals(savedUser, User.find(newUser.getId()));
  }

  @Test
  public void updateName_updateUserNameInDatabase() {
    User newUser = new User("Pepe");
    newUser.save();
    newUser.updateName("Jorge");
    assertEquals("Jorge", User.all().get(0).getName());
  }

  @Test
  public void delete_deletesObject() {
    User newUser = new User("Pepe");
    newUser.save();
    newUser.delete();
    assertEquals(0, User.all().size());
  }

}
