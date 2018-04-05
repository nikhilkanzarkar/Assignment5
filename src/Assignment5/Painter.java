/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2015
 */
package Assignment5;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static Assignment5.Params.world_height;
import static Assignment5.Params.world_width;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class Painter {

    static int numRows = world_width/12;
    static int numCols = world_height/10;
    static int size = 300 / numRows;
    static boolean diagonal = true;
    static boolean setGrud = false;

    /*
     * Paint the grid lines in something not yellow.  The purpose is two-fold -- to indicate boundaries of
     * icons, and as place-holders for empty cells.  Without placeholders, grid may not display properly.
     */

    private static void paintGridLines(GridPane grid) {

        numRows = world_width / 12;
        numCols = world_height / 12;
        size = world_width / numRows;

        for (int c = 0; c < numCols; c++) {
            ColumnConstraints column = new ColumnConstraints(size);
            column.setPercentWidth(30);
            grid.getColumnConstraints().add(column);
        }
        for (int r = 0; r < numRows; r++) {
            RowConstraints row = new RowConstraints(size);
            row.setPercentHeight(30);
            grid.getRowConstraints().add(row);
        }
        grid.setPrefSize(world_width, world_height);
        grid.setMaxSize(world_width, world_height);
        if (!setGrud) {
            grid.setGridLinesVisible(true);
            setGrud = true;
        }
    }


    /*
     * Paints the icon shapes on a grid.
     */
    public static void paint(GridPane grid) {
        grid.getChildren().clear();
        paintGridLines(grid);
    }

    /*
     * Returns a square or a circle depending on the shapeIndex parameter
     *
     */
    static Shape getIcon(int r, int c, int shapeIndex) {
        Shape s = null;

        switch(shapeIndex) {
            case 0: s = new Rectangle((r*size+size)/2, c*size, size/2, size/2);
                s.setFill(Color.RED); break;
            case 1: s = new Circle(size/2);
                s.setFill(Color.GREEN);
        }
        // set the outline
        s.setStroke(Color.BLUE);
        return s;
    }

}
