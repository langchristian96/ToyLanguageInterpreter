package main;

import java.util.*;
import controller.Controller;
import model.PrgState;

/**
 * Created by langchristian96 on 12/6/2016.
 */
public class DeSerialize extends Command {
    private Controller ctrl;
    public DeSerialize(Controller ctrl,String key,String desc){
        super(key,desc);
        this.ctrl=ctrl;
    }



    @Override
    public void execute() {
        Scanner scanner=new Scanner(System.in);
        String fname=scanner.nextLine();
        PrgState p=this.ctrl.deserialize(fname);
        System.out.println(p);

    }
}
