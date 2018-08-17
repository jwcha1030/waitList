package waiting;



import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WaitListController
{
    @FXML
    public ListView waitingList;
    @FXML
    public Label waitingNumb;
    @FXML
    public TextField nameTextField;
    @FXML
    public Button addButton;
    @FXML
    public Button removeButton;

    private int index=0;

    @FXML
    public void onEnterKeyPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            this.onAddButtonClicked();
        }
    }
    @FXML
    public void onAddButtonClicked()
    {
        String name= this.nameTextField.getText().trim();
        if(name.length()==0)
        {
            this.nameTextField.setPromptText("내용이 없습니다.");
        }
        else
        {
            this.index++;
            String listName= this.index + ". "+name;
            this.waitingList.getItems().add(listName);
            this.nameTextField.requestFocus();
            this.nameTextField.clear();
            this.waitingNumb.setText(Integer.toString(waitingList.getItems().size()));
            this.printScene(name);

        }
    }
    @FXML
    public void onRemoveButtonClicked()
    {
        if(this.waitingList.getItems().isEmpty())
        {
            new Alert(Alert.AlertType.WARNING,"대기자가 없습니다!").show();
            return;
        }
        else
        {
            this.waitingList.getItems().remove(0);
            this.waitingNumb.setText(Integer.toString(waitingList.getItems().size()));
        }
    }
    private void printScene(String newName)
    {
        Text topDeco= new Text("================");
        Text waitingTitle= new Text("대기번호: ");
        waitingTitle.setFont(new Font(15));
        Text waitingNumber= new Text(Integer.toString(this.index));
        waitingNumber.setFont(new Font(25));
        Text nameText = new Text(newName+" 님");
        Text remaingP = new Text(String.format("대기인원: %d명", this.waitingList.getItems().size()-1));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss");
        Date currentDate= new Date();
        Text dateText = new Text(dateFormat.format(currentDate));
        Text bottomDeco= new Text("================\n.");
        VBox vBox = new VBox(10,topDeco,waitingTitle,waitingNumber,nameText,remaingP,dateText,bottomDeco);
        this.print(vBox);
    }
    private void print(Node node)
    {
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job!=null)
        {
            if (job.printPage(node))
            {
                job.endJob();
            }
            else
            {
                new Alert(Alert.AlertType.WARNING, "프린트가 실패했습니다").show();
                this.waitingList.getItems().remove(this.index);
                this.index--;
            }
        }
    }
}
