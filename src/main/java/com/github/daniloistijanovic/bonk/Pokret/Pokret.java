import java.util.ArrayList;
import java.util.Formatter;

import javax.xml.crypto.dsig.keyinfo.KeyName;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import java.io.FileWriter;


public class Pokret extends Application{

    public static void main(String[] args){
        try{
            launch(args);
        }
        catch(Exception error){
            error.printStackTrace();
        }
        finally{
            System.exit(0);
        }
    }

    int score;
    int score2;

    public void start(Stage mainStage) throws IOException{
        mainStage.setTitle("Boonk.io");
        
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        ArrayList<String> keyPressedList = new ArrayList<String>();
        ArrayList<String> keyPressedListJednom = new ArrayList<String>();

        mainScene.setOnKeyPressed(
            (KeyEvent event) ->{
                String keyName = event.getCode().toString();
                if(!keyPressedList.contains(keyName)){
                    keyPressedList.add(keyName);
                    keyPressedListJednom.add(keyName);
                }
            }
        );

        score = 0;
        score2 = 0;

        mainScene.setOnKeyReleased(
            (KeyEvent event) ->{
                String keyName = event.getCode().toString();
                if(keyPressedList.contains(keyName)){
                    keyPressedList.remove(keyName);
                }
            }
        );
        
        Sprite background = new Sprite("background.png");
        background.position.set(400, 300);

        /*Sprite background2 = new Sprite("background2.png");
        background2.position.set(400, 300);
        background2.render(context);*/

        Sprite ground = new Sprite("ground.png");
        ground.position.set(400, 570);

        Sprite gras8x1 = new Sprite("grass_8x1.png");
        gras8x1.position.set(300, 400);

        Sprite gras4x1 = new Sprite("grass_4x1.png");
        gras4x1.position.set(700, 300);

        Sprite gras2x1 = new Sprite("grass_2x1.png");
        gras2x1.position.set(500, 200);

        /*Sprite background2 = new Sprite("background2.png");
        background2.position.set(400, 300);
        background2.render(context);*/

        Sprite ball = new Sprite("RedBall1.png");
        ball.position.set(100, 500);

        ArrayList<Sprite> enemyList = new ArrayList<Sprite>();

        int enemyCount = 5;

        for(int n = 0; n < enemyCount; n++){
            Sprite enemy = new Sprite("enemy1.png");
            double x = 500 * Math.random() + 300;
            double y = 400 * Math.random() + 100;
            enemy.position.set(x, y);
            double ugao = 360 * Math.random();
            enemy.velocity.setLength(100);
            enemy.velocity.setUgao(ugao);
            enemyList.add(enemy);

        }

        ArrayList<Sprite> coinList = new ArrayList<Sprite>();

        int coinCount = 6;

        for(int n = 0; n < coinCount; n++){
            Sprite coin = new Sprite("coin.png");
            double x = 700 * Math.random() + 300;
            double y = 400 * Math.random() + 100;
            coin.position.set(x, y);
            coinList.add(coin);

        }
        
        ArrayList<Sprite> heartList = new ArrayList<Sprite>();

        int hartCount = 3;

        for(int n = 0; n < hartCount; n++){
            Sprite heart = new Sprite("heart.png");
            double x = 800 - (n*45) - 30;
            double y = 45;
            heart.position.set(x, y);
            heartList.add(heart);
        }

        File file = new File("score.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(file);

        AnimationTimer gameloop = new AnimationTimer(){
            public void handle(long nanotime){

                if(keyPressedList.contains("LEFT")){
                    ball.rotation -= 6;
                    ball.velocity.set(-150, 0);
                }else{

                    if(keyPressedList.contains("RIGHT")){
                        ball.rotation += 6;
                        ball.velocity.set(150, 0);
                    }else{
                        if(keyPressedListJednom.contains("UP")){
                            int i = 1;
                            for(i = 1; i < 100; i++){
                                ball.velocity.set(0, -30*i);
                            }
                        }else{
                
                            if(keyPressedList.contains("DOWN")){
                                ball.velocity.set(0, 150);
                            }else{
                                if(keyPressedList.contains("SPACE")){
                                    ball.velocity.setLength(150);
                                    ball.velocity.setUgao(ball.rotation);
                                }
                                else{
                                    ball.velocity.setLength(0);
                                }
                            }
                        }
                    }
                }

                if(ball.position.y + 25 > 385 && ball.position.y - 25 < 420 && ball.position.x + 25 > 130 && ball.position.x - 25 < 468){
                    if(ball.position.y + 25 > 385){
                        ball.position.y = 360;
                    }
                }

                if(ball.position.y + 25 > 530){
                    ball.position.y = 515;
                }

                if(ball.position.y + 25 > 282 && ball.position.y - 25 < 322 && ball.position.x + 25 > 615 && ball.position.x - 25 < 785){
                    if(ball.position.y + 25 > 282){
                        ball.position.y = 267;
                    }
                }

                if(ball.position.y + 25 > 182 && ball.position.y - 25 < 220 && ball.position.x + 25 > 459 && ball.position.x - 25 < 541){
                    if(ball.position.y + 25 > 182){
                        ball.position.y = 167;
                    }
                }

                keyPressedListJednom.clear();

                ball.update(1/60.0);

                for(Sprite heart : heartList){
                    heart.update(1/60.0);
                }

                for(Sprite enemy : enemyList){
                    enemy.update(1/60.0);
                }

                for(Sprite coin : coinList){
                    coin.update(1/60.0);
                }

                for(Sprite coin : coinList){
                    coin.update(1/60.0);
                }

                for( int coinBr = 0; coinBr < coinList.size(); coinBr++){
                    Sprite coin = coinList.get(coinBr);
                    if(ball.overlaps(coin)){
                        coinList.remove(coinBr);
                        score += 100;
                        score2 += 100;
                    }
                }

                int hr = 0;
                for( int enemyBr = 0; enemyBr < enemyList.size(); enemyBr++){
                    Sprite enemy = enemyList.get(enemyBr);
                    if(ball.overlaps(enemy)){
                        if(heartList.size() > 0 ){
                            score2 -= 10;
                            if(score2 < 0){
                              heartList.remove(hr);
                              hr++;
                              score2 = 100;
                              if(hr == 3){
                                  pw.println(score);
                                  pw.close();
                              }
                            }
                        }
                    }
                }

                background.render(context);
                ground.render(context);
                gras8x1.render(context);
                gras4x1.render(context);
                gras2x1.render(context);
                ball.render(context);
                for(Sprite enemy : enemyList){
                    enemy.render(context);
                }
                for(Sprite coin : coinList){
                    coin.render(context);
                }
                for(Sprite heart : heartList){
                    heart.render(context);
                }
                context.setFill(Color.WHITE);
                context.setStroke(Color.PINK);
                context.setFont( new Font("Arial", 40));
                context.setLineWidth(3);
                String text = "Score:" + score;
                int textX = 10;
                int textY = 50;
                context.fillText(text, textX, textY);
                context.strokeText(text, textX, textY);
            }
        };

        gameloop.start();
        
        mainStage.show();
    }
}