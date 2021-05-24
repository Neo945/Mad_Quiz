class Question {
  String question;
  List options;
  int _correct;
  int _marks;
  int seletedOptions;

  Question(String question, List options, int correct, int marks) {
    this.question = question;
    this.options = options;
    _marks = marks;
    _correct = correct;
    seletedOptions = null;
  }

  String getQuestion() {
    return question;
  }

  int isCorrect() {
    return _correct == seletedOptions ? _marks : 0;
  }

  List getOptions() {
    return options;
  }

  void getCorrectOption() {
    for (var i = 0; i < options.length; i++) {
      if (i == _correct && seletedOptions == _correct) {
        print('\u001B[32m${i + 1}.${options[i]} \u2705\n');
      } else if (i == seletedOptions) {
        print('\u001B[31m${i + 1}.${options[i]} \u274C\n');
      } else if (i == _correct) {
        print('\u001B[0m${i + 1}.${options[i]} \u2705\n');
      } else {
        print('\u001B[0m${i + 1}.${options[i]} \u274C\n');
      }
    }
  }

  @override
  String toString() {
    return '$question ($_marks Marks)';
  }

  int getMarks() {
    return _marks;
  }
}
