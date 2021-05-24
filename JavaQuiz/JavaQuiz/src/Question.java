import java.util.ArrayList;

class Question {
  String question;
  ArrayList<String> options;
  int correct;
  int marks;
  int seletedOptions;

  Question(String question, ArrayList<String> options, int correct, int marks) {
    this.question = question;
    this.options = new ArrayList<>(options);
    this.marks = marks;
    this.correct = correct;
    seletedOptions = 0;
  }

  String getQuestion() {
    return question;
  }

  int isCorrect() {
    return correct == seletedOptions ? marks : 0;
  }

  ArrayList<String> getOptions() {
    return options;
  }

  void getCorrectOption() {
    for (var i = 0; i < options.size(); i++) {
      if (i == correct && seletedOptions == correct) {
        System.out.println("\u001B[32m" + (i + 1) + "." + options.get(i) + " \u2705\n");
      } else if (i == seletedOptions) {
        System.out.println("\u001B[31m" + (i + 1) + "." + options.get(i) + " \u274C\n");
      } else if (i == correct) {
        System.out.println("\u001B[0m" + (i + 1) + "." + options.get(i) + " \u2705\n");
      } else {
        System.out.println("\u001B[0m" + (i + 1) + "." + options.get(i) + " \u274C\n");
      }
    }
  }

  @Override
  public String toString() {
    return question + " " + options.toString() + " " + marks + " " + " " + correct;
  }

  int getMarks() {
    return marks;
  }
}
