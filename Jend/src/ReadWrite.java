import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class ReadWrite{ 
    public static void Write(Object serObj, String filepath)throws Exception {
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filepath));
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }public static <E> E Read(String filepath) throws ClassNotFoundException, IOException {
        ObjectInputStream objectIn;
        objectIn = new ObjectInputStream(new FileInputStream(filepath));
        E object = (E)objectIn.readObject();
        objectIn.close();
        return(object);
    }
}

