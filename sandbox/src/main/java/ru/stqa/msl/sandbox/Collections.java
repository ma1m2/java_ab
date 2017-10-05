package ru.stqa.msl.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "Ruby"};
    List<String> languages = new ArrayList<String>(Arrays.asList(langs));

    languages.add("PHP");

    for (int i=0; i < languages.size(); i++){
      System.out.println("I want to lear " + languages.get(i));
    }
    /*for (String l: langs) {
      System.out.println("I want to lear " + l);
    }*/
    /*for (int i = 0; i < langs.length; i++){
      System.out.println("I want to lear " + langs[i]);
    }*/
    /*String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "Ruby";*/
    System.out.println(Arrays.asList(langs));
    System.out.println(languages);
  }
}
