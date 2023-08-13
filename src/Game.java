import java.io.File;

import org.lwjgl.glfw.GLFW;

public class Game {
    public static float playerx = 10;
    public static float playery = 10;
    public static long frametime;
    public static long ms = 0;
    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(Main.window, key) == GLFW.GLFW_PRESS;
    }public static void init() throws Exception{
        Texture.loadTextures(
            Main.game + "/rsrc/img/character/Altio.png"
            );
        Tilemap.Tilemaps.add(new Tilemap(new File(Main.game + "/rsrc/img/tile/tilemap.png"), 16, 16));
        Map.loadMaps(Main.game + "/rsrc/maps/map.dat");
        Player p1 = new Player("Altio", 100, 100, 100, 100);
    }public static void nextInstance() throws Exception{
        frametime=System.nanoTime()-(ms);
        ms=System.nanoTime();
        System.out.println(1000000000/frametime);
        if(isKeyDown(GLFW.GLFW_KEY_W))playery-=(float)frametime/250000000;
        if(isKeyDown(GLFW.GLFW_KEY_A))playerx-=(float)frametime/250000000;
        if(isKeyDown(GLFW.GLFW_KEY_S))playery+=(float)frametime/250000000;
        if(isKeyDown(GLFW.GLFW_KEY_D))playerx+=(float)frametime/250000000;
        Map.Maps.get(0).render(playerx, playery);
    }
}
