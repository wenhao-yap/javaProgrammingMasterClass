module TodoList {
  requires javafx.fxml;
  requires javafx.controls;

  opens com.justin.todolist to javafx.fxml;
  exports com.justin.todolist;
}