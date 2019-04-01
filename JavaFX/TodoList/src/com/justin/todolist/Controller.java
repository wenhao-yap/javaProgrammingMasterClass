package com.justin.todolist;

import com.justin.todolist.datamodel.TodoData;
import com.justin.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {
  private List<TodoItem> todoItems;
  @FXML
  private ListView<TodoItem> todoListView;
  @FXML
  private TextArea itemDetailsTextArea;
  @FXML
  private Label deadLineLabel;
  @FXML
  private BorderPane mainBorderPane;

  public void initialize(){
	todoListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
	  if(newValue != null){
	    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
	    itemDetailsTextArea.setText(item.getDetails());
		DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d,yyyy");
	    deadLineLabel.setText(df.format(item.getDeadline()));
	  }
	});

	todoListView.setItems(TodoData.getInstance().getTodoItems());
	todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	todoListView.getSelectionModel().selectFirst();
  }

  @FXML
  public void showNewItemDialog(){
	Dialog<ButtonType> dialog = new Dialog<>();
	dialog.initOwner(mainBorderPane.getScene().getWindow());
	dialog.setHeaderText("Use this dialog to create a new todo item");
	dialog.setTitle("Add New Todo Item");
	FXMLLoader fxmlLoader = new FXMLLoader();
	fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
	try{
	  dialog.getDialogPane().setContent(fxmlLoader.load());
	} catch(IOException e){
	  System.out.println("Couldn't load the dialog");
	  e.printStackTrace();
	  return;
	}

	dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
	dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

	Optional<ButtonType> result = dialog.showAndWait();
	if(result.isPresent() && result.get() == ButtonType.OK){
	  DialogController controller = fxmlLoader.getController();
	  TodoItem newItem = controller.processResults();
	  todoListView.getSelectionModel().select(newItem);
	}
  }
}
