module timerDemo {
  requires javafx.fxml;
  requires javafx.controls;

  opens com.justin to javafx.fxml;
  exports com.justin;
}