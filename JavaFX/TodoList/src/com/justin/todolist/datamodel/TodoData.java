package com.justin.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

//Not a right way to store data. This is usually done by database or XML
//example of a singleton class
public class TodoData {
  private static TodoData instance = new TodoData();
  private static String filename = "TodoListItems.txt";

  private ObservableList<TodoItem> todoItems;
  private DateTimeFormatter formatter;

  public static TodoData getInstance(){
    return instance;
  }

  private TodoData() {
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  }

  public ObservableList<TodoItem> getTodoItems() {
	return todoItems;
  }

  public void addTodoItem(TodoItem todoItem) {
	todoItems.add(todoItem);
  }

  public void loadTodoItems() throws IOException{
    todoItems = FXCollections.observableArrayList();
    Path path = Paths.get(filename);
    String input;
    try(BufferedReader br = Files.newBufferedReader(path)){
      while((input=br.readLine())!=null){
		String[] itemPieces = input.split("\t");

		String shortDescription = itemPieces[0];
		String details = itemPieces[1];
		String dateString = itemPieces[2];

		LocalDate date = LocalDate.parse(dateString,formatter);
		TodoItem todoItem = new TodoItem(shortDescription,details,date);
		todoItems.add(todoItem);
	  }
	}
  }

  public void storeTodoItems() throws IOException{
	Path path = Paths.get(filename);
	String input;
	try(BufferedWriter bw = Files.newBufferedWriter(path)){
	  Iterator<TodoItem> iter = todoItems.iterator();
	  while(iter.hasNext()){
	    TodoItem item = iter.next();
	    bw.write(String.format("%s\t%s\t%s",
				item.getShortDescription(),
				item.getDetails(),
				item.getDeadline().format(formatter)));
	    bw.newLine();//which goes to the new line to the text file
	  }
	}
  }
}
