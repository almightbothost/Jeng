import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import java.awt.Color;
import java.util.ArrayList;

public class Tilemap{
    public static ArrayList<Tilemap> Tilemaps = new ArrayList<Tilemap>();
    public ArrayList<Texture> tiles = new ArrayList<Texture>();
    public Tilemap(File src, int tileWidth, int tileHeight) throws IOException{
        ByteBuffer bybuf;
        int width;
        int height;
        try {
            BufferedImage buimg = ImageIO.read(src);
            width = buimg.getWidth();
            height = buimg.getHeight();
            for(int i = 0; i < (width*height)/(tileWidth*tileHeight); i++){
                bybuf = BufferUtils.createByteBuffer(tileWidth*tileHeight*4);
                for(int y = 0; y < tileHeight; y++){
                    for(int x = 0; x < tileWidth; x++){
                        Color c = new Color(buimg.getRGB((i%(width/tileWidth))*tileWidth+x, (i/(width/tileWidth))*tileHeight+y));
                        bybuf.put((byte)c.getRed());
                        bybuf.put((byte)c.getGreen());
                        bybuf.put((byte)c.getBlue());
                        bybuf.put((byte)c.getAlpha());
                    }
                }tiles.add(new Texture(bybuf, tileWidth, tileHeight));
            }
        } catch (Exception e) {
            System.err.println(e);
            bybuf = BufferUtils.createByteBuffer(tileWidth*tileHeight*4);
            for(int a = 0; a < tileHeight/2; a++){
                for(int i = 0; i < tileWidth/2; i++){
                    bybuf.put((byte)255);
                    bybuf.put((byte)0);
                    bybuf.put((byte)255);
                    bybuf.put((byte)255);
                }for(int i = 0; i < tileWidth-(tileWidth/2); i++){
                    bybuf.put((byte)0);
                    bybuf.put((byte)0);
                    bybuf.put((byte)0);
                    bybuf.put((byte)255);
                }
            }for(int a = 0; a < tileHeight-(tileHeight/2); a++){
                for(int i = 0; i < tileWidth/2; i++){
                    bybuf.put((byte)0);
                    bybuf.put((byte)0);
                    bybuf.put((byte)0);
                    bybuf.put((byte)255);
                }for(int i = 0; i < tileWidth-(tileWidth/2); i++){
                    bybuf.put((byte)255);
                    bybuf.put((byte)0);
                    bybuf.put((byte)255);
                    bybuf.put((byte)255);
                }
            }tiles.add(new Texture(bybuf, tileWidth, tileHeight));
        }
    }
}