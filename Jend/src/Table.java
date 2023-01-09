import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Table<E> implements Serializable{
    private int width;
    private int height;
    private ArrayList<E> Data;
    public Table(int width, int height, E... data)throws Exception{
        if(width<=0||height<=0)throw new Exception("Table size cannot be zero or below");
        Data = new ArrayList<E>(width*height);
        Data.addAll(Arrays.asList(data));
        this.width = width;
        this.height = height;
    }public Table(int width, int height)throws Exception{
        if(width<=0||height<=0)throw new Exception("Table size cannot be zero or below");
        Data = new ArrayList<E>(width*height);
        this.width = width;
        this.height = height;
    }public Table(){
    }public E get(int x, int y)throws Exception{
        if(x>=width||x<0||y>=height||y<0)throw new Exception("Table selection out of range."); 
        return Data.get(width*y+x);
    }public void set(int x, int y, E... data)throws Exception{
        if(x>=width||x<0||y>=height||y<0)throw new Exception("Table selection out of range."); 
        for(int i=1; i<data.length; i++)Data.set(width*y+x,data[i]);
    }public int getWidth(){return(width);}
    public int getHeight(){return(height);}
}
