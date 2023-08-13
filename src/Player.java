import java.util.ArrayList;

public class Player {
    public String name;
    public float health;
    public float defence;
    public float speed;
    public float stamina;
    public ArrayList<Item> inventory = new ArrayList<Item>();
    public float x;
    public float y;
    public float xvol = 0;
    public float yvol = 0;
    public Player(String name, float health, float defence, float speed, float stamina){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.speed = speed;
        this.stamina = stamina;
    }
}
