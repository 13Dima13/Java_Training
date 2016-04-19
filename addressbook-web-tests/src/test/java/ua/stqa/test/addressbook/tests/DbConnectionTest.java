package ua.stqa.test.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.test.addressbook.model.GroupData;
import ua.stqa.test.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {
  @Test
  public void testDbConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password="); //соединяемся с БД
      Statement st = conn.createStatement(); //достаем информация из БД

      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list"); // какие поля извлекаем из БД

      Groups groups = new Groups();  //добавляем сюда полученную информацию из цикла

      // извлечь данные можно через цикл
      while (rs.next()) {  // rs.next курсор который двигаем по таблице на еденицу
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }

      // что бы не было потери ресурсов закрываем все
      rs.close(); // закрываем rs, значит что больше ничего читать не будем и можно особождать память
      st.close(); // закрываем запросы
      conn.close(); // закрываем соединение

      System.out.println(groups); // выводим на консоль инфу которую получили из БД

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}