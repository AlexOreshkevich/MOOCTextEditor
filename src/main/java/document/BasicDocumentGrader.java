package document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BasicDocumentGrader {

  public static void main(String[] args) {
    try {
      System.out.println("Sentences, words, and syllables:");
      File graderDir = new File(System.getProperty("user.dir") + "/grader_output");
      graderDir.mkdir();

      BufferedReader br = new BufferedReader(new FileReader(getTestResource("mod1TestCases.txt")));
      String line;
      PrintWriter out = new PrintWriter(getSafeOutputFilePath("grader_output/module1.part1.out"), "utf-8");
      while ((line = br.readLine()) != null) {
        BasicDocument doc = new BasicDocument(line);
        String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
        System.out.print(result);
        out.print(result);
      }
      out.print("\n");
      out.close();
      System.out.println("\nFlesch scores:");
      br.close();

      br = new BufferedReader(new FileReader(getTestResource("mod1TestCases.txt")));
      out = new PrintWriter(getSafeOutputFilePath("grader_output/module1.part2.out"), "utf-8");
      while ((line = br.readLine()) != null) {
        BasicDocument doc = new BasicDocument(line);
        String result = doc.getFleschScore() + " ";
        System.out.print(result);
        out.print(result);
      }
      out.print("\n");
      out.close();
      System.out.print('\n');
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String getTestResource(String fileName) {
    return System.getProperty("user.dir") + "/src/test/resources/" + fileName;
  }

  private static String getSafeOutputFilePath(String desiredFilePath) throws IOException {
    File file = new File(System.getProperty("user.dir") + "/" + desiredFilePath);
    if (!file.exists()) {
      file.createNewFile();
    }
    return file.getAbsolutePath();
  }
}
