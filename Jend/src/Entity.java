import java.util.ArrayList;
import java.io.Serializable;

public class Entity implements Serializable{
    public boolean DestroyQue = false;
    public String name;
    private int behaviorid;
    public float health;
    public float defence;
    public float speed;
    public float x;
    public float y;
    public float xvol;
    public float yvol;
    public ArrayList<Item> inventory = new ArrayList<Item>();
    public Entity(String name, float health, int behaviorid){
        this.name = name;
        this.health = health;
        this.behaviorid = behaviorid;
    }public void global(){
        x+=xvol;
        y+=yvol;
    }public void groundpassive(){
        
    }public void groundagressive(){
        
    }public void flyingpassive(){
        
    }public void flyingagressive(){
        
    }public void pet(){
        
    }public void object(){
        
    }public void npc(){
        
    }public void friend(){
        
    }
}
