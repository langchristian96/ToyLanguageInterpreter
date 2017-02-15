package utils;

/**
 * Created by langchristian96 on 12/13/2016.
 */
public class Generator {
    private static int fileTableID=0,heapID=0,PrgStateID=0;
    public static int generatePrgStateID(){
        return ++PrgStateID;
    }
    public static int generateFileTableID(){
        return ++fileTableID;
    }
    public static int generateHeapID(){
        return ++heapID;
    }

}
