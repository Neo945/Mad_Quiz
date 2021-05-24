import 'dart:io';

import 'package:dart_application_1/Quiz.dart';

class JavaQuiz extends Quiz {
  JavaQuiz() {
    File('api/java.json')
        .readAsString()
        .then((value) => {parseQuestions(value)});
  }
}
