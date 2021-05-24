import java.io.FileNotFoundException;
import java.util.Scanner;

class MathQuiz extends Quiz {
  MathQuiz(Scanner scanner) throws FileNotFoundException {
    super(scanner);
    this.parseQuestions("api/math.json");
  }
}
