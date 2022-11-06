import org.lwjgl.glfw.GLFW;

public class Game {
    public static boolean pointColliding(int id, float x, float y){
        return(rooms.rooms.get(id).get((int)Math.floor(x)+((int)Math.floor(y)*rooms.rooms.get(id).get(0))+2)==1);
    }public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(Main.window, key) == GLFW.GLFW_PRESS;
    }
}
