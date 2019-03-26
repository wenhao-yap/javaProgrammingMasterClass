package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
  @FXML
  private TextField nameField;
  @FXML
  private Button helloBtn;
  @FXML
  private Button byeBtn;
  @FXML
  private CheckBox ourCheckBox;
  @FXML
  private Label ourLabel;

  @FXML
  public void initialize(){
    helloBtn.setDisable(true);
    byeBtn.setDisable(true);
  }

  @FXML
  public void onButtonClicked(ActionEvent e){
    if(e.getSource().equals(helloBtn)){
	  System.out.println("Hello, " + nameField.getText());
	} else if(e.getSource().equals(byeBtn)){
	  System.out.println("Bye, " + nameField.getText());
	}

    Runnable task = new Runnable() {
	  @Override
	  public void run() {
		try{
		  String s = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
		  System.out.println("I'm going to sleep on the: " + s);
		  Thread.sleep(10000);
		  Platform.runLater(() -> {
			String s1 = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
			System.out.println("I'm updating the label on the: " + s1);
			ourLabel.setText("We did something!");
		  });
		} catch(InterruptedException evt){
		  //don't care about this
		}
	  }
	};
    new Thread(task).start();

    if(ourCheckBox.isSelected()){
      nameField.clear();
      helloBtn.setDisable(true);
      byeBtn.setDisable(true);
	}
  }

  @FXML
  public void handleKeyReleased(){
    String text = nameField.getText();
    boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
	helloBtn.setDisable(disableButtons);
	byeBtn.setDisable(disableButtons);
  }

  @FXML
  public void handleChange(){
	System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
  }
}
