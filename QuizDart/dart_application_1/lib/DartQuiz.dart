import 'dart:io';
import 'package:dart_application_1/Quiz.dart';

class DartQuiz extends Quiz {
  DartQuiz() {
    File('api/dart.json')
        .readAsString()
        .then((value) => {parseQuestions(value)});
  }
}
