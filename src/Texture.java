import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import java.awt.Color;
import static org.lwjgl.opengl.GL13.*;

public class Texture {
    private int width, height, textureID;
    public static ArrayList<Texture> Textures = new ArrayList<Texture>();
    public Texture(ByteBuffer bybuf, int width, int height) throws IOException{
        System.out.println("created tile");
        this.width = width;
        this.height = height;
        glEnable(GL_TEXTURE_2D);
        bybuf.flip();
        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,  textureID);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_WRAP_S,  GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_WRAP_T,  GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8,  width, height, 0,  GL_RGBA, GL_UNSIGNED_BYTE, bybuf);
    }public Texture(File img) throws IOException{
        ByteBuffer bybuf;
        try {
            BufferedImage buimg = ImageIO.read(img);
            width = buimg.getWidth();
            height = buimg.getHeight();
            bybuf = BufferUtils.createByteBuffer(width*height*4);
            for (int  y = 0; y < height; y++){
                for (int x = 0; x <  width; x++){
                    Color c = new Color(buimg.getRGB(x, y));
                    bybuf.put((byte)c.getRed());
                    bybuf.put((byte)c.getGreen());
                    bybuf.put((byte)c.getBlue());
                    bybuf.put((byte)c.getAlpha());
                }
            }glEnable(GL_TEXTURE_2D);
            bybuf.flip();
            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D,  textureID);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        } catch (Exception e) {
            width=2;
            height=2;
            bybuf = BufferUtils.createByteBuffer(16);
            bybuf.put((byte)255);
            bybuf.put((byte)0);
            bybuf.put((byte)255);
            bybuf.put((byte)255);

            bybuf.put((byte)0);
            bybuf.put((byte)0);
            bybuf.put((byte)0);
            bybuf.put((byte)255);

            bybuf.put((byte)0);
            bybuf.put((byte)0);
            bybuf.put((byte)0);
            bybuf.put((byte)255);
            
            bybuf.put((byte)255);
            bybuf.put((byte)0);
            bybuf.put((byte)255);
            bybuf.put((byte)255);

            glEnable(GL_TEXTURE_2D);
            bybuf.flip();
            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D,  textureID);
            glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_WRAP_S,  GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_WRAP_T,  GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        }glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8,  width, height, 0,  GL_RGBA, GL_UNSIGNED_BYTE, bybuf);
    }public void render4v(float v0x, float v0y, float v1x, float v1y, float v2x, float v2y, float v3x, float v3y, int dx, int dy, int dw, int dh){
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D,  textureID);
        glBegin(GL_QUADS);

            glTexCoord2f((float)dx/width,(float)(dy+dh)/height);
            glVertex2f(v0x,v0y);

            glTexCoord2f((float)(dx+dw)/width,(float)(dy+dh)/height);
            glVertex2f(v1x,v1y);
            
            glTexCoord2f((float)(dx+dw)/width,(float)dy/height);
            glVertex2f(v2x,v2y);
            
            glTexCoord2f((float)dx/width,(float)dy/height);
            glVertex2f(v3x,v3y);

        glEnd();
    }public void render(float x, float y, float w, float h, int dx, int dy, int dw, int dh, float angle, boolean centered){
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
        }render4v(
            (float)(x+(x0*Math.cos(Math.toRadians(-angle)))-(y0*Math.sin(Math.toRadians(-angle)))),(float)(y+(x0*Math.sin(Math.toRadians(-angle)))+(y0*Math.cos(Math.toRadians(-angle)))),
            (float)(x+(x1*Math.cos(Math.toRadians(-angle)))-(y0*Math.sin(Math.toRadians(-angle)))),(float)(y+(x1*Math.sin(Math.toRadians(-angle)))+(y0*Math.cos(Math.toRadians(-angle)))),
            (float)(x+(x1*Math.cos(Math.toRadians(-angle)))-(y1*Math.sin(Math.toRadians(-angle)))),(float)(y+(x1*Math.sin(Math.toRadians(-angle)))+(y1*Math.cos(Math.toRadians(-angle)))),
            (float)(x+(x0*Math.cos(Math.toRadians(-angle)))-(y1*Math.sin(Math.toRadians(-angle)))),(float)(y+(x0*Math.sin(Math.toRadians(-angle)))+(y1*Math.cos(Math.toRadians(-angle)))),
            dx,dy,dw,dh
        );
    }public void render(float x, float y, float w, float h, int dx, int dy, int dw, int dh){render4v(

        x,y,
        x+w,y,
        x+w,y+h,
        x,y+h,
        dx,dy,dw,dh

    );}public void render(float x, float y, float w, float h){render4v(

        x,y,
        x+w,y,
        x+w,y+h,
        x,y+h,
        0,0,width,height

    );}public void render(float x, float y){render4v(

        x,y,
        width/100+x,y,
        width/100+x,height/100+y,
        x,height/100+y,
        0,0,width,height

    );}public static void loadTextures(String... dirs) throws IOException{for(int i = 0; i < dirs.length; i++)Textures.add(new Texture(new File(dirs[i])));}
}
