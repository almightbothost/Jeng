import java.util.ArrayList;
import java.io.Serializable;

public class Entity implements Serializable{
    public boolean DestroyQue = false;
    public String name;
    public float health;
    public float defence;
    public float speed;
    public float x;
    public float y;
    public float xvol;
    public float yvol;
    public ArrayList<Item> inventory = new ArrayList<Item>();
    public Entity(String name, float health){
        this.name = name;
        this.health = health;
    }
}
