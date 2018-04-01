package Assignment5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    static GridPane grid = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        try {
            FlowPane fp= new FlowPane();
            Canvas pic = new Canvas(500,500);
            GraphicsContext gc= pic.getGraphicsContext2D();

            Button move = new Button("MOVE");
            move.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e)
                {

                }
            });
            grid.setGridLinesVisible(true);

            Scene scene = new Scene(grid, 500, 500);
            primaryStage.setScene(scene);

            primaryStage.show();

            // Paints the icons.
            Painter.paint();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
