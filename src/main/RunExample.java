package main;
import utils.*;
import controller.Controller;

import java.io.IOException;

public class RunExample extends Command {
	 private Controller ctr;
	 public RunExample(String key, String desc,Controller ctr){
	 super(key, desc);
	 this.ctr=ctr;
	 }
	 @Override
	 public void execute() {
	 try{
	 ctr.executeAll();
	 }
	 catch (InterpretorException e) 
	 {
		 System.out.println(e.getMessage());
	 } catch (InterruptedException e) {
		 e.printStackTrace();
	 } catch (IOException e) {
		 e.printStackTrace();
	 }
	 }
}