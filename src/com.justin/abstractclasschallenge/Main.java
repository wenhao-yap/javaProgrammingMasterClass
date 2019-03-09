package com.justin.abstractclasschallenge;

public class Main {
  public static void main(String[] args) {
	CustomLinkedList newList = new CustomLinkedList();
	newList.insert("choa");
	newList.insert("justin");
	newList.insert("sana");
	newList.traverse();

	System.out.println("Retrieving from index 1 in list: " + newList.getAt(1).getValue());

	newList.remove(1);
	newList.traverse();
  }
}
