package main;

import View.AllView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.Global;
import model.*;
import repository.*;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import controller.*;
import utils.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//
//public class Main {
//
//    public static void main(String[] args) {
//
////		Statement prog = new CompStmt(new AssignStmt("x",new ConstExp(12)), new AssignStmt("y",new ConstExp(1)) );
////		IRepository repo = new Repository();
////		Controller ctrl= new Controller(repo);
////		PrgState initialPrgState= new PrgState(new ExeStack<Statement> () , new SymbolTable <String,Integer> ()  , new Output<Integer> () ,prog );
////		repo.addPrgState(initialPrgState);
////		ctrl.executeAll();
//        //Main.test1();
//
//
//        //Statement prog=new CompStmt(new CompStmt(new OpenFileStmt("test.in","var1"),new ReadFileStmt("var1","var2")),new CompStmt(new ReadFileStmt("var1","var3"),new CloseFileStmt("var1")));
//
////
////        Statement prog = new CompStmt(new CompStmt(new NewStmt("a",new ConstExp(12) ),new WriteHeapStmt("a",new ConstExp(14))),new AssignStmt("a",new ConstExp(2)));
////
////        IRepository repo = new Repository("test1.log");
////        Controller ctrl = new Controller(repo);
////        PrgState initialPrgState = new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(), new Output<Integer>(), prog, new FileTable<Integer, FileData>(), new Heap());
////        repo.addPrgState(initialPrgState);
//
////        LessThanOrEqExp e=new LessThanOrEqExp(new ConstExp(2),new ConstExp(2));
////        System.out.println(e.evaluate(null,null));
//
//
//
//		Statement prog = new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(10)),new NewStmt("a",new ConstExp(22))),new CompStmt(new ForkStmt(new CompStmt(new NewStmt("b",new ConstExp(30)),new NewStmt("c",new ConstExp(12) ))),new AssignStmt("v",new ConstExp(32))));
//		IRepository repo = new Repository("test1.log");
//		Controller ctrl= new Controller(repo);
//
//        PrgState initialPrgState = new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(), new Output<Integer>(), prog, new FileTable<Integer, FileData>(), new Heap());
//        repo.addPrgState(initialPrgState);
////        try {
////            ctrl.executeAll();
////        } catch (IOException e) {
////            System.out.println("Something failed");
////        } catch (InterruptedException e) {
////            System.out.println("Something failed");
////        }
//
//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "exit"));
//        menu.addCommand(new RunExample("1", prog.toString(), ctrl));
////        menu.addCommand(new Serialize(ctrl,"2","Serialize"));
////        menu.addCommand(new DeSerialize(ctrl,"3","Deserialize"));
////
////        Statement prog2=new CompStmt(new AssignStmt("a",new ConstExp(2)),new WhileStmt(new LargerThanExp(new VarExp("a"),new ConstExp(1)),new AssignStmt("a",new ConstExp(1)) ));
////        IRepository repo2=new Repository("test2.log");
////        Controller ctrl2=new Controller(repo2);
////
////
////        PrgState initialPrgState2 = new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(), new Output<Integer>(), prog2, new FileTable<Integer, FileData>(), new Heap());
////        repo2.addPrgState(initialPrgState2);
////
////
////        menu.addCommand(new RunExample("4", prog2.toString(), ctrl2));
////
//
//        menu.show();
//
//
//    }
//
////	public static void test(){
////
////		Statement prog=new IfStmt(new ConstExp(1), new CompStmt(new AssignStmt("x", new ConstExp(2)), new PrintStatement(new ArithExp('+',new ConstExp(2), new ConstExp(4)))), new CompStmt(new AssignStmt("a", new ConstExp(10)), new PrintStatement(new VarExp("a"))));
//////		Statement prog = new CompStmt(new AssignStmt("x",new ConstExp(12)), new AssignStmt("y",new ConstExp(1)) );
//////		Statement prog = new CompStmt(new AssignStmt("a",new ArithExp('/', new ConstExp(10), new ConstExp(1))),new AssignStmt("aa",new VarExp("b")));
//////		Statement prog = new CompStmt(new AssignStmt("a",new ArithExp('/', new ConstExp(10), new ConstExp(0))),new AssignStmt("aa",new ConstExp(2)));
////		IRepository repo = new Repository("test.log");
////		Controller ctrl= new Controller(repo);
////		PrgState initialPrgState= new PrgState(new ExeStack<Statement> () , new SymbolTable <String,Integer> ()  , new Output<Integer> () ,prog,new FileTable<Integer,FileData>() );
////		repo.addPrgState(initialPrgState);
////		try
////		{
////			ctrl.executeAll();
////		}
////		catch(IOException e){
////			System.out.println("Something went wrong");
////		}
////	}
////
////	public static void test1(){
////		Statement prog=new CompStmt(new CompStmt(new OpenFileStmt("test.in","var1"),new ReadFileStmt("var1","var2")),new CompStmt(new ReadFileStmt("var1","var3"),new CloseFileStmt("var1")));
////
////		IRepository repo = new Repository("test1.log");
////		Controller ctrl= new Controller(repo);
////		PrgState initialPrgState= new PrgState(new ExeStack<Statement> () , new SymbolTable <String,Integer> ()  , new Output<Integer> () ,prog,new FileTable<Integer,FileData>() );
////		repo.addPrgState(initialPrgState);
////		try
////		{
////			ctrl.executeAll();
////		}
////		catch(IOException e){
////			System.out.println("Something went wrong");
////		}
////	}
//
//
//}


public class Main extends Application{

    public void start(Stage primaryStage) throws Exception{
        GlobalLock.gLock=new ReentrantLock();
        AllView allView=new AllView(primaryStage);


    }



    public static void main(String[] args){
        launch(args);
    }


}