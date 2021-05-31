package com.github.daniloistijanovic.bonk.scenesold;

import com.github.daniloistijanovic.bonk.Main;
import com.github.daniloistijanovic.bonk.MyButton;
import com.github.daniloistijanovic.bonk.singleplayer.Singleplayer;
import com.github.daniloistijanovic.bonk.utils.MiscUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static com.github.daniloistijanovic.bonk.utils.DebugUtil.debugger;

/*
 * render za pocetni meni
 */
@Deprecated
public class MainMenu {

    public static final MyButton playButton = new MyButton("Play");
    public static final MyButton optButton = new MyButton("Options");
    public static final MyButton quitButton = new MyButton("Quit");
    //proba za animacije
    private static final List<List<Point>> zmijice = new ArrayList<>();
    private static final int kolikoTacaka = 10000;
    private static final int kolikoRefresh = 100;
    private static final Color[] boje = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};

    private static GraphicsContext gc;

    static {
        playButton.center();
        playButton.setLocation(playButton.getLocation().x, playButton.getLocation().y - (int) (1.5 * MyButton.BUTTONSIZE.y));
        optButton.center();
        quitButton.center();
        quitButton.setLocation(quitButton.getLocation().x, quitButton.getLocation().y + (int) (1.5 * MyButton.BUTTONSIZE.y));

        for (int i = 0; i < 7; i++) {
            List<Point> zmijica = new ArrayList<>();
            zmijica.add(new Point((int) (Math.random() * Main.instance.WINDOWSIZE.x), (int) (Math.random() * Main.instance.WINDOWSIZE.y)));
            for (int j = 0; j < kolikoTacaka - 1; j++) {
                zmijica.add(MiscUtil.withinWindow(MiscUtil.randomAdjacent(zmijica.get(j))));
            }
            zmijice.add(zmijica);
        }
    }

    public MainMenu() {
        BorderPane pane = new BorderPane();
        Canvas canvas = new Canvas(Main.instance.WINDOWSIZE.x, Main.instance.WINDOWSIZE.y);
        gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        canvas.setFocusTraversable(true);

        pane.setCenter(canvas);
        Scene scene = new Scene(pane, Main.instance.WINDOWSIZE.x, Main.instance.WINDOWSIZE.y);

        canvas.setOnMouseClicked(e -> {
            int mx = (int) e.getX();
            int my = (int) e.getY();

            if (MainMenu.playButton.isInsideButton(mx, my)) {
                new Singleplayer().start();
            } else if (MainMenu.optButton.isInsideButton(mx, my)) {
            } else if (MainMenu.quitButton.isInsideButton(mx, my)) {
                System.exit(0);
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> draw()), new KeyFrame(Duration.millis(16)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Main.instance.setScene(scene);

    }

    public void draw() {
        gc.clearRect(0, 0, Main.instance.WINDOWSIZE.x, Main.instance.WINDOWSIZE.y);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, Main.instance.WINDOWSIZE.x, Main.instance.WINDOWSIZE.y);
        renderBackground();
        gc.setFont(Main.instance.fontBig);
        gc.setFill(Color.WHITE);
        gc.fillText("Bonk", Main.instance.WINDOWSIZE.x / 2, Main.instance.WINDOWSIZE.y / 5);
        renderButtons();
    }

    public void renderButtons() {
        gc.setFont(Main.instance.fontSmall);
        playButton.draw(gc);
        optButton.draw(gc);
        quitButton.draw(gc);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        debugger.memory("Finalize " + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }

    private void mutirajZmijicu(List<Point> zmijica, int n) {
        for (int i = 0; i < n; i++) {
            zmijica.add(MiscUtil.withinWindow(MiscUtil.randomAdjacent(zmijica.get(kolikoTacaka - 1))));
            zmijica.remove(0);
        }
    }

    private void renderBackground() {
        for (int i = 0; i < 7; i++) {
            gc.setFill(boje[i]);
            mutirajZmijicu(zmijice.get(i), kolikoRefresh);
            for (Point p : zmijice.get(i)) {
                gc.fillRect(p.x, p.y, 1, 1);
            }
        }
    }

}
