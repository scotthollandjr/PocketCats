import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CommentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Comment_instantiatesCorrectly_true() {
    Comment newComment = new Comment("This cat rules!", "SCOTTYBOY");
    assertEquals(true, newComment instanceof Comment);
  }

  @Test
  public void all_EmptyAtFirst_true() {
    assertEquals(0, Comment.all().size());
  }

  @Test
  public void equals_returnTrueIdCommentsAreTheSame() {
    Comment newComment = new Comment("This cat rules!", "SCOTTYBOY");
    Comment newerComment = new Comment("This cat rules!", "SCOTTYBOY");
    assertTrue(newComment.equals(newerComment));
  }

  @Test
  public void save_savesToCommentDatabase() {
    User newUser = new User("SCOTTYBOY");
    newUser.save();
    Comment newComment = new Comment("This cat rules!", "SCOTTYBOY");
    Cat newCat = new Cat("Appa", "Siamese", "Under the bridge downtown", false, 1, "AHUIOFHOIUHGOUEDHGH.jpg");
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), newUser.getId());
    assertTrue(Comment.all().get(0).equals(newComment));
  }

  @Test
  public void save_saveAssignsIdToObject() {
    User newUser = new User("SCOTTYBOY");
    newUser.save();
    Comment newComment = new Comment("This cat rules!", "SCOTTYBOY");
    Cat newCat = new Cat("Appa", "Siamese", "Under the bridge downtown", false, 1, "AHUIOFHOIUHGOUEDHGH.jpg");
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), newUser.getId());
    assertEquals(newComment.getId(), Comment.all().get(0).getId());
  }

  @Test
  public void find_findCommentInDatabase() {
    User newUser = new User("SCOTTYBOY");
    newUser.save();
    Comment newComment = new Comment("This cat rules!", "SCOTTYBOY");
    Cat newCat = new Cat("Appa", "Siamese", "Under the bridge downtown", false, 1, "AHUIOFHOIUHGOUEDHGH.jpg");
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), newUser.getId());
    Comment savedComment = Comment.all().get(0);
    assertEquals(savedComment, Comment.find(newComment.getId()));
  }

  @Test
  public void delete_deletesObject() {
    User newUser = new User("SCOTTYBOY");
    newUser.save();
    Comment newComment = new Comment("This cat rules!", "SCOTTYBOY");
    Cat newCat = new Cat("Appa", "Siamese", "Under the bridge downtown", false, 1, "AHUIOFHOIUHGOUEDHGH.jpg");
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), newUser.getId());
    newComment.delete();
    assertEquals(0, Comment.all().size());
  }
}
