import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable{
    public static final long serialVersionUID = 42L;
    public static ArrayList<Map> Maps = new ArrayList<Map>();
    public static void loadMaps(String... dirs) throws ClassNotFoundException, IOException{
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
    }public void render(float x, float y) throws Exception{
        for(int i = 0; i < 11; i++){
            for(int a = 0; a < 16; a++){
                try{
                    try{
                        Tilemap.Tilemaps.get(tilemapid).tiles.get(visualdata.get((int)((float)a+x-7.5f),(int)(i+y-5))).render((float)((a-(x+0.5)%1)*2)/15-1, -(float)(i-(y%1)+1)/5+1, 2f/15, 1f/5);
                    }catch(NullPointerException n){}
                }catch(Exception e){
                    try{
                        Tilemap.Tilemaps.get(tilemapid).tiles.get(0).render((float)((a-(x+0.5)%1)*2)/15-1, -(float)(i-(y%1)+1)/5+1, 2f/15, 1f/5);
                    }catch(Exception o){
                        throw new Exception("Fatal error in rendering texture");
                    }
                }
            }
        }
    }
}
