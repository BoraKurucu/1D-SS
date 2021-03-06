package View;

import Models.Object.AbstractPower;
import Models.Object.AbstractRelic;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;

import static View.Main.game;

public class UpperPane extends StackPane {
    private ArrayList<Text> textList;
    private  int width;
    private int height;
    Text healthText;
    Text goldText;
    Text blockText;
    static int padY;
    static int padX;
    HBox box;
    HBox box1;
    HBox block;
    HBox box2;
    HBox box3;
    HBox box4;
    Pane pane;
    boolean relicDesc;
    boolean powerDesc;


    public UpperPane(int width,int height)
    {
        this.setMinSize( width, height );
        System.out.println("UPP");
        pane = new Pane();
        this.width = width;
        this.height = height;
        healthText = new Text();
        goldText = new Text();
        blockText = new Text();
        padY = height;
        padX = width;
        box = new HBox(70);
        box1 = new HBox();
        block = new HBox();
        box2 = new HBox();
        box3 = new HBox(10);
        box4 = new HBox(10);
        relicDesc = false;
        powerDesc = false;
        pane.setBackground( new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)) );
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        this.getChildren().add(imageView);
    }

    static Node relicDescription(ArrayList<AbstractRelic> relic, int ind, int x, int y) {
        Group g = new Group();

        String desc = relic.get(ind).getDescription();
        int len = desc.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(len*(padX/213));
        rect.setHeight(padY/4);
        System.out.println("padY: "+padY);
        rect.setVisible(true);

        Text relicText = new Text(desc);
        relicText.setX(x+5);
        relicText.setY(y+13);
        relicText.setFont(Font.font ("Verdana", padY/8));
        relicText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(relicText);

        return g;
    }

    static Node powerDescription(ArrayList<AbstractPower> power, int ind, int x, int y) {
        Group g = new Group();

        String desc = power.get(ind).getDescription();
        int len = desc.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(len*(padX/213));
        rect.setHeight(padY/4);
        rect.setVisible(true);

        Text powerText = new Text(desc);
        powerText.setX(x+5);
        powerText.setY(y+13);
        powerText.setFont(Font.font ("Verdana", padY/8));
        powerText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(powerText);

        return g;
    }

    public void initialize()
    {
        clearPane();
        // box 1 -- health
        healthText.setText(game.getPlayer().getCurrentHP()+ " / "+ game.getPlayer().getMaxHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, height/5));
        healthText.setFill(Color.RED);
        ImageView heart = new ImageView(new Image("heart.png"));
        heart.setFitWidth(height/2);
        heart.setFitHeight(height/2);
        heart.setVisible(true);
        heart.setPreserveRatio(true);
        box1.getChildren().add(heart);
        box1.getChildren().add(healthText);

        // block box
        blockText.setText("" + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, height/5));
        ImageView blockPic = new ImageView(new Image("blockPic.png"));
        blockPic.setFitWidth(height/2);
        blockPic.setFitHeight(height/2);
        blockPic.setVisible(true);
        blockPic.setPreserveRatio(true);
        block.getChildren().addAll(blockPic, blockText);

        // box 2 -- gold
        goldText.setText("" + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, height/5));
        ImageView gold = new ImageView(new Image("gold.png"));
        gold.setFitWidth(height/2);
        gold.setFitHeight(height/2);
        gold.setVisible(true);
        gold.setPreserveRatio(true);
        box2.getChildren().addAll(gold, goldText);

        // box 3 -- relic
        for(int i=0; i< game.getPlayer().relics.size(); i++){
            String name = game.getPlayer().relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(height/2);

            Node desc = relicDescription(game.getPlayer().relics,i, width/2, height-(height/4));
            pane.getChildren().add(desc);
            desc.setVisible(false);

            if(! this.getChildren().contains(pane)){
                this.getChildren().add(pane);
                relicDesc = true;
            }

            relicImage.setOnMouseEntered(e->{
                    desc.setVisible(true);
                }
            );
            relicImage.setOnMouseExited( e->{
                    desc.setVisible(false);
                }
            );
            box3.getChildren().add(relicImage);
        }

        // box 4 -- power
        for(int i=0; i< game.getPlayer().powers.size(); i++){
            String name = game.getPlayer().powers.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView powerImage = new ImageView(new Image(name));
            powerImage.setPreserveRatio(true);
            powerImage.setFitHeight(height/2);
            box4.getChildren().add(powerImage);

            Node desc = powerDescription(game.getPlayer().powers,i, width/2, height-(height/4));
            pane.getChildren().add(desc);
            desc.setVisible(false);
            if(! this.getChildren().contains(pane)){
                this.getChildren().add(pane);
                relicDesc = true;
            }

            powerImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            powerImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });
        }
        box.getChildren().addAll(box1,block,box2,box3,box4);

        if(! this.getChildren().contains(box)){
            this.getChildren().add(box);
            relicDesc = true;
        }


    }

    private void clearPane(){

        box.getChildren().clear();
        box.getChildren().removeAll();
        box1.getChildren().clear();
        box1.getChildren().removeAll();
        block.getChildren().removeAll();
        block.getChildren().clear();
        box2.getChildren().removeAll();
        box2.getChildren().clear();
        box3.getChildren().removeAll();
        box3.getChildren().clear();
        box4.getChildren().clear();
        box4.getChildren().removeAll();
        box4.getChildren().clear();
    }

    public void draw()
    {
        clearPane();

        // box 1 -- health
        healthText.setText(game.getPlayer().getCurrentHP()+ " / "+ game.getPlayer().getMaxHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, height/5));
        healthText.setFill(Color.RED);
        ImageView heart = new ImageView(new Image("heart.png"));
        heart.setFitWidth(height/2);
        heart.setFitHeight(height/2);
        heart.setVisible(true);
        heart.setPreserveRatio(true);
        box1.getChildren().add(heart);
        box1.getChildren().add(healthText);

        // block box
        blockText.setText("" + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, height/5));
        ImageView blockPic = new ImageView(new Image("blockPic.png"));
        blockPic.setFitWidth(height/2);
        blockPic.setFitHeight(height/2);
        blockPic.setVisible(true);
        blockPic.setPreserveRatio(true);
        block.getChildren().addAll(blockPic, blockText);

        // box 2 -- gold
        goldText.setText("" + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, height/5));
        ImageView gold = new ImageView(new Image("gold.png"));
        gold.setFitWidth(height/2);
        gold.setFitHeight(height/2);
        gold.setVisible(true);
        gold.setPreserveRatio(true);
        box2.getChildren().addAll(gold, goldText);


        // box 3 -- relic
        for(int i=0; i< game.getPlayer().relics.size(); i++){
            String name = game.getPlayer().relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(height/2);

            Node desc = relicDescription(game.getPlayer().relics,i, width/2, height-(height/4));
            pane.getChildren().add(desc);
            desc.setVisible(false);

            if(! this.getChildren().contains(pane)){
                this.getChildren().add(pane);
                relicDesc = true;
            }

            relicImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            relicImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });
            box3.getChildren().add(relicImage);
        }

        // box 4 -- power
        for(int i=0; i< game.getPlayer().powers.size(); i++){
            String name = game.getPlayer().powers.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView powerImage = new ImageView(new Image(name));
            powerImage.setPreserveRatio(true);
            powerImage.setFitHeight(height/2);
            box4.getChildren().add(powerImage);

            Node desc = powerDescription(game.getPlayer().powers,i, width/2, height-(height/4));
            pane.getChildren().add(desc);
            desc.setVisible(false);
            if(! this.getChildren().contains(pane)){
                this.getChildren().add(pane);
                relicDesc = true;
            }

            powerImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            powerImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });
        }
        box.getChildren().addAll(box1,block,box2,box3,box4);
    }


}