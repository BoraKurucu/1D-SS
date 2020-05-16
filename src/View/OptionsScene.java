package View;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;



public class OptionsScene extends GameScene {
    private List<Pair<String, Runnable>> resolutions = Arrays.asList(
            new Pair<String, Runnable>("1920x1080", () ->{ setSize(1920,1080)  ; draw();} ),
            new Pair<String, Runnable>("1600x900", () -> { setSize(1600,900); draw();} ),
            new Pair<String, Runnable>("1366x768", () -> { setSize(1366,768) ; draw();}),
            new Pair<String, Runnable>("1280x720", () -> { setSize(1280,720) ; draw();} ),
            new Pair<String, Runnable>("800×600", () -> {setSize(800,600) ; draw();} ),
            new Pair<String, Runnable>("Confirm", () -> confirm() )

    );
    VBox resBox;
    StackPane root;
    public OptionsScene(){
        super();
        initialize();
    }
    public void initialize(){
        root = (StackPane) this.getRoot();
        root.setMinSize(width, height);
        root.setBackground( new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        resBox = new VBox();
        resBox.setVisible(true);

        addOptions();
        resBox.setAlignment(Pos.CENTER);
        StackPane.setAlignment(resBox, Pos.CENTER);
    }
    private void addOptions(){

        resolutions.forEach(data -> {
            StsMenuPane item = new StsMenuPane(data.getKey());
            item.setOnAction(data.getValue());


            Rectangle clip = new Rectangle(width/6, height/6);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            resBox.getChildren().addAll(item);
        });
        root.getChildren().add(resBox);
        startAnimation();
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1));
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < resBox.getChildren().size(); i++) {
                Node n = resBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }
    public void draw(){
        Main.window.setScene( new OptionsScene() );
    }
    private void setSize( int width, int height ){
        Main.optionsManager.changeSize( width, height );
    }
    private void confirm(){
        Main.window.setScene( new MenuScene() );
    }

}