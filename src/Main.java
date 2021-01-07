import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application
{
    private int currentIndex=0;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start (Stage stage)
    {
        VBox vbox = new VBox ();
        GridPane gridpane = new GridPane();
        Label label = new Label ();
        ArrayList<String> buttons = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();
        int[] values = new int[100];

        EventHandler<MouseEvent> btn_handler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e) {
                Object node = e.getSource(); //returns the object that generated the event
                Button btn = (Button)node;   //get the button
                String s = btn.getText();    //get text from button

                if (s.equals(" "))
                {
                    System.out.println("wrong Input");
                }
                else if(s.equals("-") || s.equals("+"))
                {
                    values[currentIndex] = Integer.parseInt(label.getText());
                    label.setText("");
                    operators.add(s);
                }
                else if(s.equals("="))
                {
                    int result = -1;
                    if (!label.getText().equals(""))
                    {
                        values[currentIndex] = Integer.parseInt(label.getText());
                    }
                    if (values[0]!=0)
                    {
                        result = values[0];
                        for (int i = 1; i < values.length; i++)
                        {
                            if (operators.get(i-1).equals("+"))
                            {
                                result += values[i];
                            }
                            else if (operators.get(i-1).equals("-"))
                            {
                                result -= values[i];
                            }
                        }
                    }
                    label.setText("" + result);
                }
            }
        };

        buttons.add("7");
        buttons.add("8");
        buttons.add("9");
        buttons.add("4");
        buttons.add("5");
        buttons.add("6");
        buttons.add("1");
        buttons.add("2");
        buttons.add("3");
        buttons.add("+");
        buttons.add("0");
        buttons.add("-");
        buttons.add(" ");
        buttons.add("=");
        buttons.add("");

        for(String s : buttons)
        {
            Button btn = new Button();
            int index = buttons.indexOf(s);
            gridpane.add(btn, index % 3, index / 3);
            btn.setText(s);
            btn.setMinWidth(100);
            btn.setMinHeight(100);
            btn.setStyle("-fx-font-size:45;-fx-font-weight: bold");
            btn.addEventHandler(MouseEvent.MOUSE_CLICKED, btn_handler);
        }
        vbox.getChildren().addAll(label, gridpane);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }
}
