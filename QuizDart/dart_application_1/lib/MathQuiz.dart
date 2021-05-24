import 'dart:io';
import 'package:dart_application_1/Quiz.dart';

class MathQuiz extends Quiz {
  MathQuiz() {
    File('api/math.json')
        .readAsString()
        .then((value) => {parseQuestions(value)});
  }
}
