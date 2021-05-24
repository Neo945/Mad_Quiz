import java.io.FileNotFoundException;
import java.util.Scanner;

class DartQuiz extends Quiz {
  DartQuiz(Scanner scanner) throws FileNotFoundException {
    super(scanner);
    this.parseQuestions("api/dart.json");

  }
}
