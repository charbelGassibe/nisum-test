package cl.charbel.nisumtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;

public class TestingUtils {

  public static String getFileContent(String path) throws IOException {
    ClassPathResource resource = new ClassPathResource(path);
    InputStream inputStream = resource.getInputStream();

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder content = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      content.append(line).append("\n");
    }

    return content.toString();
  }
}
