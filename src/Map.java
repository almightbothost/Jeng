import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable{
    public static final long serialVersionUID = 42L;
    public static ArrayList<Map> Maps = new ArrayList<Map>();
    public static void loadmaps(String... dirs) throws ClassNotFoundException, IOException{
        for(int i = 0; i < dirs.length; i++){
            Maps.add(ReadWrite.Read(dirs[i]));
        }
    }private Table<Integer> visualdata;
    private Table<Boolean> collisiondata;
    public ArrayList<Entity> EntityList = new ArrayList<Entity>();
    public int width;
    public int height;
    public int tilemapid;
    public Map(Table<Integer> visualdata, Table<Boolean> collisiondata, int tilemapid){
        this.visualdata = visualdata;
        this.collisiondata = collisiondata;
        this.tilemapid = tilemapid;
        width = collisiondata.getWidth();
        height = collisiondata.getHeight();
    }public boolean pointiscolliding(float x, float y) throws Exception{return(collisiondata.get((int)x,(int)y));}
    public int gettileid(int x, int y) throws Exception{return(visualdata.get(x,y));}
    public void setvisual(int x, int y, int data) throws Exception{
        visualdata.set(x, y, data);
    }public void setcollision(int x, int y, boolean data) throws Exception{
        collisiondata.set(x, y, data);
    }
}
