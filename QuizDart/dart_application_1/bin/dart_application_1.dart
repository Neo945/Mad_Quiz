import 'dart:io';

import 'package:dart_application_1/DartQuiz.dart';
import 'package:dart_application_1/JavaQuiz.dart';
import 'package:dart_application_1/MathQuiz.dart';

void main(List<String> arguments) {
  print("------------Quiz------------");
  print("1. Maths");
  print("2. Java");
  print("3. Dart");
  stdout.write('Enter your Test Code: ');
  var whichTest = int.parse(stdin.readLineSync());
  switch (whichTest) {
    case 1:
      MathQuiz();
      break;
    case 2:
      JavaQuiz();
      break;
    case 3:
      DartQuiz();
      break;
    default:
      print('No test found!!');
  }
}
