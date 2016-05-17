import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CatTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Cat_instantiatesCorrectly_true() {
    Cat newCat = new Cat("Appa", "Siamese");
    assertEquals(true, newCat instanceof Cat);
  }

  @Test
  public void all_EmptyAtFirst_true() {
    assertEquals(0, Cat.all().size());
  }

  @Test
  public void equals_returnTrueIdCatsAreTheSame() {
    Cat newCat = new Cat("Appa", "Siamese");
    Cat newerCat = new Cat("Appa", "Siamese");
    assertTrue(newCat.equals(newerCat));
  }

  @Test
  public void save_savesToCatDatabase() {
    Cat newCat = new Cat("Appa", "Siamese");
    newCat.save();
    assertTrue(Cat.all().get(0).equals(newCat));
  }

  @Test
  public void save_saveAssignsIdToObject() {
    Cat newCat = new Cat("Appa", "Siamese");
    newCat.save();
    assertEquals(newCat.getId(), Cat.all().get(0).getId());
  }

  @Test
  public void find_findCatInDatabase() {
    Cat newCat = new Cat("Appa", "Siamese");
    newCat.save();
    Cat savedCat = Cat.all().get(0);
    assertEquals(savedCat, Cat.find(newCat.getId()));
  }

  @Test
  public void updateName_updateCatNameInDatabase() {
    Cat newCat = new Cat("Appa", "Siamese");
    newCat.save();
    newCat.updateName("Stevens");
    assertEquals("Stevens", Cat.all().get(0).getName());
  }

  @Test
  public void delete_deletesObject() {
    Cat newCat = new Cat("Appa", "Siamese");
    newCat.save();
    newCat.delete();
    assertEquals(0, Cat.all().size());
  }

  @Test
  public void getComments_getsComments() {
    Cat newCat = new Cat("Appa", "Siamese");
    newCat.save();
    Comment newComment = new Comment("This cat is great");
    newComment.save();
    newCat.add
  }

  // @Test
  // public void addUser_addsAUserToThisCat() {
  //   Cat newCat = new Cat("Appa", "Siamese");
  //   newCat.save();
  //   User firstUser = new User ("Pepe");
  //   firstUser.save();
  //   newCat.addUser(firstUser);
  //   User secondUser = new User ("Pepe");
  //   secondUser.save();
  //   newCat.addUser(secondUser);
  //   assertTrue(newCat.getUsers().contains(Array.asList()));
  // }
  //
  // @Test
  // public void getUsers_getUsersLinkedToThisCat() {
  //   Cat newCat = new Cat("Appa", "Siamese");
  //   newCat.save();
  //   User newUser = new User ("Pepe");
  //   newUser.save();
  //   newCat.addUser(newUser);
  //   assertTrue(newCat.getUsers().getName == newUser.getName());
  // }
  //
  // @Test
  // public void getComments_getsCommentsLinkedToThisCat() {
  //
  // }

}
