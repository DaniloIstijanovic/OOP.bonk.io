import java.nio.channels.NonReadableChannelException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    
    public Vector position;
    public Vector velocity;
    public double rotation;
    public Kvadrat granica;
    public Image image;
    public double Time;

    public Sprite(){
        this.position = new Vector();
        this.velocity = new Vector();
        this.rotation = 0;
        this.granica = new Kvadrat();
        this.Time = 0;
    }

    public Sprite(String imageFileName){
        this();
        setImage(imageFileName);
    }

    public void setImage(String imageFileName){
        this.image = new Image(imageFileName);
        this.granica.setSize(this.image.getWidth(), this.image.getHeight());
    }
    
    public Kvadrat getGranica(){
        this.granica.setPosition(this.position.x, this.position.y);
        return this.granica;
    }

    public boolean overlaps(Sprite other){
        return this.getGranica().overlaps(other.getGranica());
    }

    public void wrap(double screenWidth, double screenHeight){
        if(this.position.x + this.image.getWidth()/2 < 0){
            this.position.x = screenWidth + this.image.getWidth()/2;
        }
        if(this.position.x > screenWidth + this.image.getWidth()/2){
            this.position.x = -this.image.getWidth()/2;
        }

        if(this.position.y + this.image.getHeight()/2 < 0){
            this.position.y = screenHeight + this.image.getHeight()/2;
        }
        if(this.position.y > screenHeight + this.image.getHeight()/2){
            this.position.y = - this.image.getHeight()/2;
        }
    }

    public void update(double deltaTime){
        this.Time += deltaTime;
        this.position.add(this.velocity.x * deltaTime, this.velocity.y * deltaTime);
        this.wrap(800, 600);
    }

    public void render(GraphicsContext context){
        context.save();

        context.translate(this.position.x, this.position.y);
        context.rotate(this.rotation);
        context.translate(-this.image.getWidth()/2, -this.image.getHeight()/2);
        context.drawImage(this.image, 0, 0);

        context.restore();
    }

}
