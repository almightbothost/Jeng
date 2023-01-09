import org.lwjgl.glfw.GLFW;

public class Game {
    public static float playerx;
    public static float playery;
    public static long frametime;
    public static long ms = 0;
    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(Main.window, key) == GLFW.GLFW_PRESS;
    }public static void nextInstance() throws Exception{
        frametime=System.nanoTime()-(ms);
        ms=System.nanoTime();
        System.out.println(1000000000/frametime);
        Render.rendermap(Map.Maps.get(0),10,10);
    }
}
