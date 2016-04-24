package ua.stqa.pft.mantis.appmanager;


import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper { // отключаем проверку капчи на сайте для нашего теста

  private final ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient(); // инициализация
  }


  //загружаем новый файл, старый переименовываем
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(backup); // удаляем предыдущую резервную копию
    ftp.rename(target, backup); // переименовываем удаленный файл
    ftp.enterLocalPassiveMode(); // пассивный режим передачи данных
    ftp.storeFile(target, new FileInputStream(file)); // передаем локальный файл и передаются на удаленную машину в target
    ftp.disconnect();
  }

  // восстанавливаем исходную конфиругаю тестируемой системы
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}