import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;

public class Render {
    public static ArrayList<ArrayList<Integer>> imgdata = new ArrayList<ArrayList<Integer>>();
    public static ArrayList<File> imgs = new ArrayList<File>();
    public static void loadimages() throws Exception {

        imgs.add(new File(Main.game+"/rsrc/img/tile/download.png"));
        imgs.add(new File(Main.game+"/rsrc/img/tile/tilemap.png"));

        for(int i=0; i<imgs.size(); i++){
                BufferedImage img = ImageIO.read(imgs.get(i));
                ArrayList<Integer> curimgdata = new ArrayList<Integer>();
                curimgdata.add(img.getWidth());
                curimgdata.add(img.getHeight());
                for(int y=0; y<img.getHeight(); y++){
                    for(int x=0; x<img.getWidth(); x++){
                        Color c = new Color(img.getRGB(x,y));
                        curimgdata.add(c.getRed());
                        curimgdata.add(c.getGreen());
                        curimgdata.add(c.getBlue());
                        curimgdata.add(c.getAlpha());
                    }
                }imgdata.add(curimgdata);

        }
    }public static void drawimage(int id, double x, double y, double w, double h, int dx, int dy, int dw, int dh, double cx, double cy, double angle)throws Exception {
        if(imgdata.get(id).get(0)==-1){
            for(int i=0; i<dh; i++){
                for(int a=0; a<dw; a++){
                    glBegin(GL_QUADS);
                        if(a%2==0^i%2==0)glColor4d(1,0,1,0);
                        else glColor4d(0,0,0,0);
                        glVertex2d((x+(a*(w/dw)-(w/2)))/120,(-y+(i*(-h/dh)+(h/2)))/80);
                        glVertex2d((x+((a+1)*(w/dw)-(w/2)))/120,(-y+(i*(-h/dh)+(h/2)))/80);
                        glVertex2d((x+((a+1)*(w/dw)-(w/2)))/120,(-y+((i+1)*(-h/dh)+(h/2)))/80);
                        glVertex2d((x+(a*(w/dw)-(w/2)))/120,(-y+((i+1)*(-h/dh)+(h/2)))/80);
                    glEnd();
                }
            }
        }else for(int i=0; i<dh; i++){
            if(dx+dw>imgdata.get(id).get(0)){
                System.err.println("selection out of bounds!");
                return;
            }if(dy+dh>imgdata.get(id).get(1)){
                System.err.println("selection out of bounds!");
                return;
            }for(int a=0; a<dw; a++){
                double r = (double)imgdata.get(id).get(4*(a+(i*imgdata.get(id).get(0)))+2)/255;
                double g = (double)imgdata.get(id).get(4*(a+(i*imgdata.get(id).get(0)))+3)/255;
                double b = (double)imgdata.get(id).get(4*(a+(i*imgdata.get(id).get(0)))+4)/255;
                double alf = (double)imgdata.get(id).get(4*(a+(i*imgdata.get(id).get(0)))+5)/255;
                glBegin(GL_QUADS);
                    glColor4d(r,g,b,alf);
                    glVertex2d((x+(a*(w/dw)))/120,(-y+(i*(-h/dh)))/80);
                    glVertex2d((x+((a+1)*(w/dw)))/120,(-y+(i*(-h/dh)))/80);
                    glVertex2d((x+((a+1)*(w/dw)))/120,(-y+((i+1)*(-h/dh)))/80);
                    glVertex2d((x+(a*(w/dw)))/120,(-y+((i+1)*(-h/dh)))/80);
                glEnd();
            }
        }
    }public static void loadmap(int x, int y)throws Exception {
        for(int i=0; i<16; i++){

        }
    }
}
