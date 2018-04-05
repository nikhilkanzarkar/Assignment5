package Assignment5;
/* CRITTERS Main.java
 * EE422C Project 5 submission by
 * Nikhil Kanzarkar
 * nk8357
 * 15466
 * Jack Hammett
 * jdh5529
 * 15466
 * Spring 2018
 */


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import static Assignment5.Painter.numCols;
import static Assignment5.Painter.numRows;
import static Assignment5.Painter.size;
import static Assignment5.Params.world_height;
import static Assignment5.Params.world_width;

public class Main extends Application {
    static GridPane grid = new GridPane();

    public void statsHelper(ArrayList<String> names, FlowPane showStats) {
        showStats.getChildren().clear();

        List<Critter> instance = new ArrayList<Critter>();
        boolean empty = false;

        for(int i = 0; i < names.size(); i ++) {
            try {
                String out = "";
                instance = Critter.getInstances(names.get(i));
                java.lang.reflect.Method method = null;
                Class loadClass = null;

                if(instance.size() == 0) {
                    //have to account for a subclass static runStats method in which there are no instances
                    try {
                        String updatedClass = "Assignment5." + names.get(i);
                        loadClass = Class.forName(updatedClass);

                        method = loadClass.getMethod("runStats", List.class);

                    }
                    catch(ClassNotFoundException v) {
                        System.out.println("error processing");
                    }
                    catch(SecurityException b) {
                        System.out.println("error processing");
                    }
                    catch(NoSuchMethodException n) {
                        System.out.println("error processing");
                    }
                    //if statement to make sure if subclass doesn't have a runStats to skip the invoke and continue
                    try {
                        if(method != null && (method.getDeclaringClass() != Critter.class)) {
                            out = (String) method.invoke(loadClass, instance);
                            empty = true;
                        }

                    }
                    catch (IllegalArgumentException c) {  }
                    catch (IllegalAccessException x) { }
                    catch (InvocationTargetException z) {  }
                }
                if(!empty) {
                    out = Critter.runStats(instance);
                    empty = false;
                }
                Text t = new Text(out);
                t.setTextAlignment(TextAlignment.LEFT);
                showStats.getChildren().add(t);

            }
            catch(InvalidCritterException n) {
                System.out.println("error processing");
            }
            catch(NoClassDefFoundError m) {
                System.out.println("error processing");
            }
        }
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            FlowPane fp= new FlowPane();
            Canvas pic = new Canvas(500,500);
            ArrayList<String> statsShow = new ArrayList<String>();
            FlowPane showStats = new FlowPane(Orientation.VERTICAL);


            Button make = new Button("MAKE");
            Button show = new Button("SHOW");
            Button stats = new Button("STATS");
            Button seed = new Button("SEED");
            Button step = new Button("STEP");
            Button quit = new Button("QUIT");
            Button chooseStep = new Button("SET TIME");

            fp.getChildren().add(make);
            fp.getChildren().add(show);
            fp.getChildren().add(step);
            fp.getChildren().add(chooseStep);
            fp.getChildren().add(stats);
            fp.getChildren().add(seed);
            fp.getChildren().add(quit);

            chooseStep.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e) {
                    TextField enter = new TextField("Enter time for Animation.");
                    Button submit = new Button("SUBMIT");

                    fp.getChildren().add(enter);
                    fp.getChildren().add(submit);

                    submit.setOnAction(new EventHandler<ActionEvent>()
                    {
                        public void handle(ActionEvent u) {



                            Timeline t1 = new Timeline();
                            t1.setCycleCount(Animation.INDEFINITE);
                            KeyFrame world = new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {
                                int time = 0;

                                public void handle(ActionEvent event) {
                                    String numb = enter.getText();
                                    int number = 0;
                                    try {
                                        number = Integer.parseInt(numb);
                                        fp.getChildren().remove(enter);
                                        fp.getChildren().remove(submit);
                                    }
                                    catch(NumberFormatException e) {
                                        System.out.println("error processing");
                                    }
                                    time++;
                                    Critter.worldTimeStep();
                                    if(time%number ==0) {
                                        Critter.displayWorld(grid);
                                        statsHelper(statsShow,showStats);
                                    }
                                }

                            });
                            t1.getKeyFrames().add(world);

                            t1.play();

                            Button stop = new Button("STOP");
                            fp.getChildren().add(stop);
                            stop.setOnAction(new EventHandler<ActionEvent>()
                            {
                                public void handle(ActionEvent e) {
                                    t1.stop();
                                    fp.getChildren().remove(stop);
                                }
                            });
                        }
                    });
                }
            });
            step.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e) {
                    TextField stepper = new TextField("Enter number.");
                    Button submit = new Button("SUBMIT");

                    fp.getChildren().add(stepper);
                    fp.getChildren().add(submit);

                    submit.setOnAction(new EventHandler<ActionEvent>()
                    {
                        public void handle(ActionEvent e) {
                            String numb = stepper.getText();
                            fp.getChildren().remove(stepper);
                            fp.getChildren().remove(submit);

                            int times;
                            try {
                                times = Integer.parseInt(numb);

                                for(int i = 0; i < times; i++) {
                                    Critter.worldTimeStep();
                                }
                                Critter.displayWorld(grid);
                                statsHelper(statsShow,showStats);
                            }
                            catch(NumberFormatException y) {
                                System.out.println("error processing");
                            }
                        }
                    });

                }
            });

            seed.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e) {
                    TextField enter = new TextField("Enter number.");
                    Button submit = new Button("SUBMIT");
                    fp.getChildren().add(enter);
                    fp.getChildren().add(submit);

                    submit.setOnAction(new EventHandler<ActionEvent>()
                    {
                        public void handle(ActionEvent e) {
                            fp.getChildren().remove(submit);
                            String numb = enter.getText();
                            fp.getChildren().remove(enter);
                            try {
                                Critter.setSeed(Long.parseLong(numb));
                            }
                            catch(NumberFormatException o) {
                                System.out.println("error processing");
                            }
                        }
                    });


                }
            });
            stats.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e) {
                    TextField statsName = new TextField("Enter Critter's name to Track.");
                    Button submit = new Button("SUBMIT");
                    fp.getChildren().add(statsName);
                    fp.getChildren().add(submit);

                    submit.setOnAction(new EventHandler<ActionEvent>()
                    {
                        public void handle(ActionEvent e) {
                            String name = statsName.getText();
                            String out = null;
                            fp.getChildren().remove(statsName);
                            fp.getChildren().remove(submit);

                            statsShow.add(name);
                            statsHelper(statsShow,showStats);
                        }

                    });
                }
            });


            quit.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e) {
                    System.exit(0);
                }
            });

            make.setOnAction(new EventHandler<ActionEvent>()
                             {
                                 public void handle(ActionEvent e)
                                 {


                                     TextField namer = new TextField("Enter name.");
                                     fp.getChildren().add(namer);


                                     TextField namer2 = new TextField("Enter Number.");
                                     fp.getChildren().add(namer2);

                                     Button submit = new Button("SUBMIT");
                                     fp.getChildren().add(submit);
                                     submit.setOnAction(new EventHandler<ActionEvent>()
                                     {
                                         public void handle(ActionEvent e) {
                                             String number = namer2.getText();
                                             String name = namer.getText();
                                             fp.getChildren().remove(namer);
                                             fp.getChildren().remove(namer2);
                                             fp.getChildren().remove(submit);
                                             //convert number into an int to make such number of critters
                                             try {
                                                 int times = Integer.parseInt(number);				//converts the input into how many critters to create of that type

                                                 for(int i = 0; i < times; i++) {
                                                     try {
                                                         Critter.makeCritter(name);
                                                     }
                                                     catch(InvalidCritterException a) {
                                                         System.out.println("error processing");
                                                     }
                                                 }
                                             }
                                             catch(NumberFormatException f) {
                                                 System.out.println("error processing");
                                             }
                                         }
                                     });
                                 }
                             }

            );

            show.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent e) {

                   Critter.displayWorld(grid);

                }
            });

            Scene scene2 = new Scene(fp);
            Scene scene3 = new Scene(showStats);

            Stage third = new Stage();
            third.setHeight(400);
            third.setWidth(500);
            third.setResizable(true);
            third.setScene(scene3);
            third.show();


            Stage second = new Stage();
            Scene scene = new Scene(grid, world_width, world_height);
            Critter.makeGrid(grid);
            second.setWidth(world_width);
            second.setHeight(world_height);
            second.setScene(scene);
            second.show();

            primaryStage.setScene(scene2);
            primaryStage.setWidth(575);
            primaryStage.setHeight(500);
            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
