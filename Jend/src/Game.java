import org.lwjgl.glfw.GLFW;

public class Game {
    public static float playerx = 10;
    public static float playery = 10;
    public static long frametime;
    public static long ms = 0;
    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(Main.window, key) == GLFW.GLFW_PRESS;
    }public static void nextInstance() throws Exception{
        frametime=System.nanoTime()-(ms);
        ms=System.nanoTime();
        System.out.println(1000000000/frametime);
        if(isKeyDown(GLFW.GLFW_KEY_W))playery-=(float)frametime/250000000;
        if(isKeyDown(GLFW.GLFW_KEY_A))playerx-=(float)frametime/250000000;;
        if(isKeyDown(GLFW.GLFW_KEY_S))playery+=(float)frametime/250000000;;
        if(isKeyDown(GLFW.GLFW_KEY_D))playerx+=(float)frametime/250000000;;
        Render.rendermap(Map.Maps.get(0),playerx,playery);
    }
}
