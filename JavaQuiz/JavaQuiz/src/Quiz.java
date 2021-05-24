import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class Quiz implements Test {
  Scanner scanner;
  int result;
  private ArrayList<Question> _questions;
  public ArrayList<Integer> order;

  Quiz(Scanner scanner) {
    result = 0;
    order = new ArrayList<>();
    this.scanner = scanner;
  }

  private int _totalMarks() {
    int total = 0;
    for (Question q : _questions){
      total += q.getMarks();
    }
    return total;
  }

  private void genrateRandomOrder() {
    while (order.size() < _questions.size()) {
      Random rand = new Random();
      int r = rand.nextInt(_questions.size()); 
      if (!order.contains(r)) {
        order.add(r);
      }
    }
    // System.out.println(order);
  }

  private ArrayList<Integer> genrateRandomOptions(int n) {
    ArrayList<Integer> k = new ArrayList<>();
    Random rand = new Random();
    while (k.size() < n) {
      int r = rand.nextInt(n);
      if (!k.contains(r)) {
        k.add(r);
      }
    }
    return k;
  }

  private void printNumberOfOptions() {
    System.out.print("Questions: ");
    for (int i = 0; i < order.size(); i++) {
      if (_questions.get(order.get(i)).seletedOptions != 0) {
        System.out.print("\u001B[0m" + (i + 1) + "  ");
      } else {
        System.out.print("\u001B[33m" + (i + 1) + "  ");
      }
    }
    System.out.println();
  }

  private void test() {
    genrateRandomOrder();
    while (true) {
      printNumberOfOptions();
      System.out.print("\u001B[0m");
      System.out.print("\u001B[33mEnter the choice: ");
      String val = scanner.next();
      int qs = Integer.parseInt(val) - 1;
      if (!order.contains(qs)) {
        System.out.println("Invalid Input");
        continue;
      }
      printQuestion(order.get(qs));
      if (!checkIfComplete()) {
        printRemaining();
        submit();
        return;
      }
    }
  }

  private void submit() {
    for (int i = 0; i < order.size(); i++) {
      result += _questions.get(i).isCorrect();
    }
  }

  private void printQuestion(int qs) {
    Question q = _questions.get(qs);
    System.out.println("Q" + qs + ". " + q.question);
    ArrayList<Integer> orderO = genrateRandomOptions(q.options.size());
    for (int i = 0; i < orderO.size(); i++) {
      System.out.println((i + 1) + ". " + q.options.get(orderO.get(i)));
    }
    System.out.print("Enter your options: ");
    int inp = (scanner.nextInt()) - 1;
    if (inp < 4) {
      int o = orderO.get(inp);
      q.seletedOptions = o;
    }
  }

  private void results() {
    System.out.println("\n\n\u001B[0mYour Final Result is " + result +  " out of " + _totalMarks());
    printCorrect();
  }

  private void printCorrect() {
    for (int i = 0; i < order.size(); i++) {
      System.out.println("Q" + (i + 1) + ". " + _questions.get(order.get(i)).question);
      _questions.get(order.get(i)).getCorrectOption();
    }
    if (result < (_totalMarks() * 0.5)) {
      System.out.println(
          "\n\n\u001B[31mYour Final Result is " + result +  " out of " + _totalMarks());
    } else {
      System.out.println(
          "\n\n\u001B[33mYour Final Result is " + result +  " out of " + _totalMarks());
    }
  }

  private boolean printRemaining() {
    boolean flag = false;
    for (int i = 0; i < order.size(); i++) {
      if (_questions.get(order.get(i)).seletedOptions == 0) {
        System.out.println((i + 1) + " is remaining");
        flag = true;
      }
    }
    return flag;
  }

  private boolean checkIfComplete() {
    boolean flag = false;
    for (int i = 0; i < order.size(); i++) {
      if (_questions.get(order.get(i)).seletedOptions == 0) {
        flag = true;
      }
    }
    return flag;
  }

  @Override
  public void parseQuestions(String api) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(new File(api)));
    _questions = new ArrayList<>();
    Gson gson = new Gson();
    java.lang.reflect.Type type = new TypeToken<ArrayList<Question>>() {}.getType();
    ArrayList<Question> data = gson.fromJson(reader, type);
    _questions.addAll(data);
    test();
    results();
  }
}
