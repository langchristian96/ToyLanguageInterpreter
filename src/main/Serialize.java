package main;
import java.io.IOException;
import java.util.*;
import controller.Controller;
import utils.InterpretorException;

/**
 * Created by langchristian96 on 12/6/2016.
 */
public class Serialize extends Command {

    private Controller ctrl;

    public Serialize(Controller ctrl,String key,String desc){
        super(key,desc);
        this.ctrl=ctrl;
    }


    @Override
    public void execute() {

        Scanner scanner =new Scanner(System.in);
        String filename=scanner.nextLine();
        try{
           // this.ctrl.serialize(filename);

        }catch(InterpretorException e){
            System.out.println(e.getMessage());
        }
//        catch(IOException e){
//            System.out.println(e);
//        }
    }
}
