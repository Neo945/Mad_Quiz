import 'dart:io';
import 'dart:math';

import 'package:dart_application_1/Test.dart';
import 'package:gson/gson.dart';

import 'Question.dart';

class Quiz extends Test {
  int result;
  List<Question> _questions;
  List order;

  Quiz() {
    result = 0;
    order = [];
  }

  int _totalMarks() {
    var total = 0;
    _questions.forEach((element) {
      total += element.getMarks();
    });
    return total;
  }

  void genrateRandomOrder() {
    while (order.length < _questions.length) {
      var r = Random().nextInt(_questions.length);
      if (!order.contains(r)) {
        order.add(r);
      }
    }
    // print(order);
  }

  List genrateRandomOptions(var n) {
    var k = [];
    while (k.length < n) {
      var r = Random().nextInt(n);
      if (!k.contains(r)) {
        k.add(r);
      }
    }
    return k;
  }

  void printNumberOfOptions() {
    stdout.write('Questions: ');
    for (var i = 0; i < order.length; i++) {
      if (_questions[order[i]].seletedOptions != null) {
        stdout.write('\u001B[0m${i + 1}  ');
      } else {
        stdout.write('\u001B[33m${i + 1}  ');
      }
    }
    print('');
  }

  void test() {
    print('qwer');
    genrateRandomOrder();
    print('qwer');
    while (true) {
      printNumberOfOptions();
      // print('\u001B[0m');
      stdout.write('\u001B[33mEnter the choice: ');
      var qs = int.parse(stdin.readLineSync()) - 1;
      if (!order.contains(qs)) {
        print('Invalid Input');
        continue;
      }
      printQuestion(order[qs]);
      if (!checkIfComplete()) {
        printRemaining();
        submit();
        return;
      }
    }
  }

  void submit() {
    for (var i = 0; i < order.length; i++) {
      result += _questions[order[i]].isCorrect();
    }
  }

  void printQuestion(qs) {
    var q = _questions[qs];
    print('Q$qs. ${q.question}');
    var orderO = genrateRandomOptions(q.options.length);
    for (var i = 0; i < orderO.length; i++) {
      print('${i + 1}. ${q.options[orderO[i]]}');
    }
    stdout.write('Enter your options: ');
    var inp = int.parse(stdin.readLineSync()) - 1;
    if (inp < 4) {
      var o = orderO[inp];
      q.seletedOptions = o;
    }
  }

  void results() {
    print('\n\n\u001B[0mYour Final Result is $result out of ${_totalMarks()}');
    printCorrect();
  }

  void printCorrect() {
    for (var i = 0; i < order.length; i++) {
      print('Q${i + 1}. ${_questions[order[i]].question}');
      _questions[order[i]].getCorrectOption();
    }
    if (result < (_totalMarks() * 0.5)) {
      print(
          '\n\n\u001B[31mYour Final Result is $result out of ${_totalMarks()}');
    } else {
      print(
          '\n\n\u001B[33mYour Final Result is $result out of ${_totalMarks()}');
    }
  }

  bool printRemaining() {
    var flag = false;
    for (var i = 0; i < order.length; i++) {
      if (_questions[order[i]].seletedOptions == null) {
        print('${i + 1} is remaining');
        flag = true;
      }
    }
    return flag;
  }

  bool checkIfComplete() {
    var flag = false;
    for (var i = 0; i < order.length; i++) {
      if (_questions[order[i]].seletedOptions == null) {
        flag = true;
      }
    }
    return flag;
  }

  @override
  void parseQuestions(String qs) {
    _questions = <Question>[];
    List questions = gson.decode(qs);
    for (var q in questions) {
      _questions.add(Question(q['q'], q['options'], 1, 1));
    }
    test();
    results();
  }
}
