package com.justin.todolist;

import com.justin.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
  private List<TodoItem> todoItems;
  @FXML
  private ListView<TodoItem> todoListView;
  @FXML
  private TextArea itemDetailsTextArea;
  @FXML
  private Label deadLineLabel;

  public void initialize(){
    TodoItem item1 = new TodoItem("Mail birthday card","Buy a 30th birthday card for john", LocalDate.of(2019, Month.APRIL,26));
	TodoItem item2 = new TodoItem("Doctor's Appointment","See Dr.Smith at 123 Main Street. Bring paperwork", LocalDate.of(2019, Month.MAY,23));
	TodoItem item3 = new TodoItem("Finish design proposoal for client","I promised Mike I'd email website mockups by 22nd April", LocalDate.of(2019, Month.APRIL,22));
	TodoItem item4 = new TodoItem("Pickup Doug at train station","Doug's arriving on March 23 on the 5:00am train", LocalDate.of(2019, Month.MARCH,23));
	TodoItem item5 = new TodoItem("Pick up dry cleaning","The clothes should be ready by Wednesday", LocalDate.of(2019, Month.JUNE,4));

	todoItems = new ArrayList<>();
	todoItems.add(item1);
	todoItems.add(item2);
	todoItems.add(item3);
	todoItems.add(item4);
	todoItems.add(item5);

	todoListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
	  if(newValue != null){
	    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
	    itemDetailsTextArea.setText(item.getDetails());
		DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d,yyyy");
	    deadLineLabel.setText(df.format(item.getDeadline()));
	  }
	});

	todoListView.getItems().setAll(todoItems);
	todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	todoListView.getSelectionModel().selectFirst();
  }
}
