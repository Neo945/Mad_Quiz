import java.io.FileNotFoundException;
import java.util.Scanner;

class JavaQuiz extends Quiz {
  JavaQuiz(Scanner scanner) throws FileNotFoundException {
    super(scanner);
    this.parseQuestions("api/java.json");

  }
}
