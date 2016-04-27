package ua.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ua.stqa.pft.mantis.model.MailMessage;
import ua.stqa.pft.mantis.model.UserData;

import java.util.List;

public class ChangePasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() throws Exception {
    app.mail().start();
  }
  @Test
  public void testChangePassword() throws Exception {
    String username = "user";
    String changePassword = "test";
    app.changePassword().login("Administrator","root");
    app.goTo().Users();
    UserData selectUser = app.db().users().stream().filter((u) -> username.equals(u.getUsername())).iterator().next();
    app.goTo().clickUser(selectUser.getId());
    app.goTo().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, selectUser.getEmail());
    app.goTo().cheangePassword(confirmationLink, changePassword);
    app.newSession().login(username, changePassword);
    app.newSession().isLogginAs(username);

  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() throws Exception {
    app.mail().stop();
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}
