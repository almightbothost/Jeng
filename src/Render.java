import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Render {
    private static ArrayList<Texture> imgs = new ArrayList<Texture>();
    public static void loadimages(String... dirs) throws IOException{
        for(int i = 0; i < dirs.length; i++){
            imgs.add(new Texture(new File(dirs[i])));
        }
    }public static void drawimage4v(int id, float tlx , float tly , float blx , float bly , float brx , float bry , float trx , float tr_y, int dx, int dy, int dw, int dh){imgs.get(id).render(tlx ,tly ,blx ,bly ,brx ,bry ,trx ,tr_y, dx, dy, dw, dh);}
    public static void drawimage(int id, float x, float y, float w, float h, int dx, int dy, int dw, int dh, float angle, boolean centered){
        float x0, y0, x1, y1;
    if(centered){
        x0=-w/2;
        y0=-h/2;
        x1=+w/2;
        y1=+h/2;
    }else{
        x0=0;
        y0=0;
        x1=w;
        y1=h;
    }imgs.get(id).render(
        (float)(x+(x0*Math.cos(Math.toRadians(-angle)))-(y0*Math.sin(Math.toRadians(-angle)))),(float)(y+(x0*Math.sin(Math.toRadians(-angle)))+(y0*Math.cos(Math.toRadians(-angle)))),
        (float)(x+(x1*Math.cos(Math.toRadians(-angle)))-(y0*Math.sin(Math.toRadians(-angle)))),(float)(y+(x1*Math.sin(Math.toRadians(-angle)))+(y0*Math.cos(Math.toRadians(-angle)))),
        (float)(x+(x1*Math.cos(Math.toRadians(-angle)))-(y1*Math.sin(Math.toRadians(-angle)))),(float)(y+(x1*Math.sin(Math.toRadians(-angle)))+(y1*Math.cos(Math.toRadians(-angle)))),
        (float)(x+(x0*Math.cos(Math.toRadians(-angle)))-(y1*Math.sin(Math.toRadians(-angle)))),(float)(y+(x0*Math.sin(Math.toRadians(-angle)))+(y1*Math.cos(Math.toRadians(-angle)))),
        dx,dy,dw,dh);
    }public static void rendermap(Map map, float x, float y) throws Exception{
        x = (float)Math.min(Math.max(8.5,x),(float)map.width-6.5001);
        y = (float)Math.min(Math.max(6,y),(float)map.height-4.0001);
        for(int i = 0; i < 11; i++){
            for(int a = 0; a < 16; a++){
                drawimage(map.tilemapid,(((float)a-((x+0.5f)%1))/15)*2-1, -((float)i-(y%1)+1)/5+1, 2f/15, 1f/5, map.gettileid((int)(a+x-8.5f), (int)(i+y-6))%8*16, map.gettileid((int)(a+x-8.5f), (int)(i+y-6))/8*16, 16, 16, 0, false);
            }
        }
    }
}

