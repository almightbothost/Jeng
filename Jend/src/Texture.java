import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import java.awt.Color;
import static org.lwjgl.opengl.GL11.*;

public class Texture {
    private int width;
    private int height;
    private int textureID;
    public Texture(File img) throws IOException{
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
            }
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

        }
        glEnable(GL_TEXTURE_2D);
        bybuf.flip();
        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,  textureID);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_WRAP_S,  GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_WRAP_T,  GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8,  width, height, 0,  GL_RGBA, GL_UNSIGNED_BYTE, bybuf);
    }public void render(float v0x, float v0y, float v1x, float v1y, float v2x, float v2y, float v3x, float v3y, int dx, int dy, int dw, int dh){
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
    }
}
