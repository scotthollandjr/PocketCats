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
    Comment newComment = new Comment("This cat rules!", "AwesomeUser_420");
    assertEquals(true, newComment instanceof Comment);
  }

  @Test
  public void all_EmptyAtFirst_true() {
    assertEquals(0, Comment.all().size());
  }

  @Test
  public void equals_returnTrueIdCommentsAreTheSame() {
    Comment newComment = new Comment("This cat rules!", "AwesomeUser_420");
    Comment newerComment = new Comment("This cat rules!", "AwesomeUser_420");
    assertTrue(newComment.equals(newerComment));
  }

  @Test
  public void save_savesToCommentDatabase() {
    Comment newComment = new Comment("This cat rules!", "AwesomeUser_420");
    Cat newCat = new Cat("Appa", "Siamese", "", false, 1);
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), 1);
    assertTrue(Comment.all().get(0).equals(newComment));
  }

  @Test
  public void save_saveAssignsIdToObject() {
    Comment newComment = new Comment("This cat rules!", "AwesomeUser_420");
    Cat newCat = new Cat("Appa", "Siamese", "", false, 1);
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), 1);
    assertEquals(newComment.getId(), Comment.all().get(0).getId());
  }

  @Test
  public void find_findCommentInDatabase() {
    Comment newComment = new Comment("This cat rules!", "AwesomeUser_420");
    Cat newCat = new Cat("Appa", "Siamese", "", false, 1);
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), 1);
    Comment savedComment = Comment.all().get(0);
    assertEquals(savedComment, Comment.find(newComment.getId()));
  }

  @Test
  public void delete_deletesObject() {
    Comment newComment = new Comment("This cat rules!", "AwesomeUser_420");
    Cat newCat = new Cat("Appa", "Siamese", "", false, 1);
    newCat.save();
    newComment.saveToCatAndUser(newCat.getId(), 1);
    newComment.delete();
    assertEquals(0, Comment.all().size());
  }
}
