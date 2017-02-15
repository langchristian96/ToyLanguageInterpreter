package View;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import controller.*;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;
import repository.*;
import model.*;
import utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



//First problem - lock
public class AllView {

    private GridPane pane;
    private TextField nrOfPrgs;
    private TableView<Map.Entry<Integer,Integer>> heapview;
    private TableView<Map.Entry<Integer,FileData>> filetableview;
    private TableView<Map.Entry<Integer,Integer>> lockview;
    private ListView<Integer> outputview;
    private ListView<PrgState> prgStateListView;
    private TableView<Map.Entry<String,Integer>> symbolview;
    private ListView<Statement> exestackview;
    private ListView<Map.Entry<String,ProcData>> procView;
    private Button run;




    public AllView(Stage primaryStage){

        primaryStage.setTitle("Test");
        BorderPane pane=getView();
        Scene scene=new Scene(pane,800,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initGridPane(){
        pane=new GridPane();
        pane.setPadding(new Insets(20));
    }

    private void initNrPrgs(Controller ctrl){

        //adding number of prgstates textfield
        nrOfPrgs=new TextField("0");
        pane.add(new Label("# of PrgStates"),0,0);
        nrOfPrgs.setText(""+ctrl.getRepo().getPrgStates().size());
        pane.add(nrOfPrgs,1,0);

    }

    private void initHeapTable(Controller ctrl){
        //adding tableview of heap table
        heapview=new TableView<>();
        TableColumn<Map.Entry<Integer,Integer>,Integer> column1=new TableColumn<>("Address");

        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer> param) {
                return new SimpleObjectProperty<Integer>(param.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<Integer,Integer>,Integer> column2=new TableColumn<>("Value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer> param) {
                return new SimpleObjectProperty<Integer>(param.getValue().getValue());
            }
        });
        heapview.getColumns().addAll(column1,column2);
        List<Map.Entry<Integer,Integer>> ls=new ArrayList<Map.Entry<Integer,Integer>>();
        for(Map.Entry<Integer,Integer> m:ctrl.getRepo().getPrgStates().get(0).getHeap().getContent().entrySet()){
            ls.add(m);
        }

        ObservableList<Map.Entry<Integer,Integer>> heapentries= FXCollections.observableList(ls);
        System.out.println(ls.toString());
        heapview.setItems(heapentries);

        pane.add(heapview,4,0);


    }

    private void initOutView(Controller ctrl){
        //output list view
        outputview=new ListView<>(FXCollections.observableArrayList());
        ObservableList<Integer> observableList=FXCollections.observableArrayList(ctrl.getRepo().getPrgStates().get(0).getOutput().getList());
        outputview.setItems(observableList);

        pane.add(outputview,7,0);


    }

    private void initFileTableView(Controller ctrl){
        //filetable view

        filetableview=new TableView<>();
        TableColumn<Map.Entry<Integer,FileData>,Integer> column11=new TableColumn<>("Number:");

        column11.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, Integer> param) {
                return new SimpleObjectProperty<Integer>(param.getValue().getKey());
            }
        });
        TableColumn<Map.Entry<Integer,FileData>,FileData> column22=new TableColumn<>("File Data:");
        column22.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, FileData>, ObservableValue<FileData>>() {
            @Override
            public ObservableValue<FileData> call(TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, FileData> param) {
                return new SimpleObjectProperty<FileData>(param.getValue().getValue());
            }
        });
        filetableview.getColumns().addAll(column11,column22);
        List<Map.Entry<Integer,FileData>> ls1=new ArrayList<Map.Entry<Integer,FileData>>();
        for(Map.Entry<Integer,FileData> m:ctrl.getRepo().getPrgStates().get(0).getFileTable().getAll()){
            ls1.add(m);
        }

        ObservableList<Map.Entry<Integer,FileData>> fileentries=FXCollections.observableList(ls1);

        filetableview.setItems(fileentries);

        pane.add(filetableview,9,0);


    }

    private void initPrgsView(Controller ctrl){
        //the list of PrgState identifiers as a ListView

        prgStateListView =new ListView<>(FXCollections.observableArrayList());

        List<PrgState> lss=new ArrayList<PrgState>();
        for(PrgState m:ctrl.getRepo().getPrgStates()){
            lss.add(m);
        }

        ObservableList<PrgState> prgst=FXCollections.observableList(lss);


        prgStateListView.setItems(prgst);


        prgStateListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->
        {
            System.out.println(newValue);
            fillAll(newValue);

        }
        ));



        pane.add(prgStateListView,12,0);


    }

    private void initSymbolTableView(Controller ctrl){
        //symbol table view


        symbolview=new TableView<>();
        TableColumn<Map.Entry<String,Integer>,String> colum1=new TableColumn<>("Var. name");

        colum1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> param) {
                return new SimpleObjectProperty<String>(param.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<String,Integer>,Integer> colum2=new TableColumn<>("Value");
        colum2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer> param) {
                return new SimpleObjectProperty<Integer>(param.getValue().getValue());
            }
        });
        symbolview.getColumns().addAll(colum1,colum2);

        pane.add(symbolview,15,0);



    }

    private void initExeStackView(Controller ctrl){
        //exestackview


        exestackview=new ListView<>(FXCollections.observableArrayList());
        pane.add(exestackview,18,0);

        PrgState p=ctrl.getRepo().getPrgStates().get(0);
        List<Statement> ls2=new ArrayList<Statement>();
        for(Statement s:p.getExeStack().getAll()){
            ls2.add(s);
        }
        System.out.println(p.getExeStack().getAll());
        ObservableList<Statement> entrs=FXCollections.observableList(ls2);

        Collections.reverse(entrs);
        exestackview.setItems(entrs);



    }

    private void initRunButton(Controller ctrl){
        run=new Button("Run one step");
        run.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ctrl.execOneStepGUI();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
                updateAll(ctrl);
            }
        });
        pane.add(run,1,10);

    }


    public void startNewStage(Controller ctrl) {
        initGridPane();

        //adding number of prgstates textfield
        initNrPrgs(ctrl);



        //adding tableview of heap table
        initHeapTable(ctrl);


        //output list view
        initOutView(ctrl);


        //filetable view
        initFileTableView(ctrl);

        //the list of PrgState identifiers as a ListView
        initPrgsView(ctrl);


        //symbol table view
        initSymbolTableView(ctrl);

        //exestackview
        initExeStackView(ctrl);

       //init run button
        initRunButton(ctrl);

        //init proc view
//        procView=new ListView<Map.Entry<String,ProcData>>();
//        for(Map.Entry<String,ProcData> me:ctrl.getRepo().getPrgStates().get(0).getProcTable().getAll())
//            procView.getItems().add(me);
//        pane.add(procView,12,1);


        //init lock table view

        //adding tableview of heap table
        lockview=new TableView<>();
        TableColumn<Map.Entry<Integer,Integer>,Integer> column1=new TableColumn<>("Location");

        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer> param) {
                return new SimpleObjectProperty<Integer>(param.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<Integer,Integer>,Integer> column2=new TableColumn<>("Value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer> param) {
                return new SimpleObjectProperty<Integer>(param.getValue().getValue());
            }
        });
        lockview.getColumns().addAll(column1,column2);
        List<Map.Entry<Integer,Integer>> ls=new ArrayList<Map.Entry<Integer,Integer>>();
        for(Map.Entry<Integer,Integer> m:ctrl.getRepo().getPrgStates().get(0).getLockTable().getContent().entrySet()){
            ls.add(m);
        }

        ObservableList<Map.Entry<Integer,Integer>> lockentries= FXCollections.observableList(ls);

        lockview.setItems(lockentries);

        pane.add(lockview,4,1);




        Scene scene=new Scene(pane,800,500);
        Stage newst=new Stage();
        newst.setScene(scene);
        newst.show();

    }

    BorderPane getView(){


        //initializing the programs
        //Statement prog = new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(0)),new WhileStmt(new LessThanExp(new VarExp("v"),new ConstExp(3)), new CompStmt(new ForkStmt(new CompStmt(new PrintStatement(new VarExp("v")),new AssignStmt("v",new ArithExp('+',new VarExp("v"),new ConstExp(1) )))),new AssignStmt("v",new ArithExp('+',new VarExp("v"),new ConstExp(1) ))) )),new CompStmt(new SleepStmt(5),new PrintStatement(new ArithExp('*',new VarExp("v"),new ConstExp(10)))));

        IRepository repo = new Repository("test1.log");
        Controller ctrl= new Controller(repo);

        IProcTable procTable=new ProcTable();
        String[] vars={"a","b"};
        procTable.add("sum",new ProcData(vars,new CompStmt(new AssignStmt("v",new ArithExp('+',new VarExp("a"),new VarExp("b"))),new PrintStatement(new VarExp("v")))));

        procTable.add("product",new ProcData(vars,new CompStmt(new AssignStmt("v",new ArithExp('*',new VarExp("a"),new VarExp("b"))),new PrintStatement(new VarExp("v")))));




        Expression[] exprs={new ArithExp('*',new VarExp("v"),new ConstExp(10)),new VarExp("w")};
        Expression[] exprs2={new VarExp("v"),new VarExp("w")};
        //Statement prog=new CompStmt(new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(2)),new AssignStmt("w",new ConstExp(5)) ),   new CompStmt(new CallStmt("sum",exprs),new PrintStatement(new VarExp("v")))  )  ,     new CompStmt(new ForkStmt(new CallStmt("product",exprs2)),new ForkStmt(new CallStmt("sum",exprs2)))  );
//        Statement prog=new CompStmt(
//                new CompStmt(new CompStmt(new CompStmt(new NewStmt("v1",new ConstExp(20)),new NewStmt("v2",new ConstExp(30))), new NewLockStmt("x")),                             new CompStmt(new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new CompStmt(new CompStmt(new LockStmt("x"),new WriteHeapStmt("v1",new ArithExp('-',new ReadHeapExp("v1"),new ConstExp(1)))),new UnlockStmt("x"))),(new CompStmt(new CompStmt(new LockStmt("x"),new WriteHeapStmt("v1",new ArithExp('-',new ReadHeapExp("v1"),new ConstExp(1)))),new UnlockStmt("x"))))), new ForkStmt(new ForkStmt(new WriteHeapStmt("v2",new ArithExp('+',new ReadHeapExp("v2"),new ConstExp(1)))))),               new CompStmt(new WriteHeapStmt("v2",new ArithExp('+',new ReadHeapExp("v2"),new ConstExp(1))),new UnlockStmt("x")) )),
//                new CompStmt(new PrintStatement(new ReadHeapExp("v1")),new PrintStatement(new ReadHeapExp("v2"))));
//


        Statement prog=new CompStmt(new CompStmt(new CompStmt(new NewStmt("v1",new ConstExp(20)),new NewStmt("v2",new ConstExp(30))),new NewLockStmt("x")),
                                        new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new CompStmt(new CompStmt(new LockStmt("x"),new WriteHeapStmt("v1",new ArithExp('-',new ReadHeapExp("v1"),new ConstExp(1)))),new UnlockStmt("x"))),new CompStmt(new CompStmt(new LockStmt("x"),new WriteHeapStmt("v1",new ArithExp('+',new ReadHeapExp("v1"),new ConstExp(1)))),new UnlockStmt("x")))),
                                                    new CompStmt(
                                                            new NewLockStmt("q"),
                                                            new CompStmt(
                                                                        new ForkStmt(
                                                                                new CompStmt(
                                                                                            new ForkStmt(new CompStmt(new LockStmt("q"),new CompStmt(new WriteHeapStmt("v2",new ArithExp('+',new ReadHeapExp("v2"),new ConstExp(5))),new UnlockStmt("q"))  )),
                                                                                                new CompStmt(new CompStmt(new AssignStmt("m",new ConstExp(100)),new LockStmt("q")),            new CompStmt(new WriteHeapStmt("v2",new ArithExp('+',new ReadHeapExp("v2"),new ConstExp(1))),new UnlockStmt("q")))
                                                                                            )
                                                                        ),
                                                                            new CompStmt(new AssignStmt("z",new ConstExp(200)),
                                                                                        new CompStmt(new AssignStmt("z",new ConstExp(300)),
                                                                                                new CompStmt(new AssignStmt("z",new ConstExp(400)),
                                                                                                        new CompStmt(new AssignStmt("z",new ConstExp(500)),
                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                new CompStmt(new PrintStatement(new ReadHeapExp("v1")),
                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                        new CompStmt(new PrintStatement(new ReadHeapExp("v2")),
                                                                                                                                                new UnlockStmt("q"))
                                                                                                                                        ))))))))

                                                                        )


                                                                 )


                                                    )
                                        );

        //third statement
//        Statement prog=new CompStmt(new NewLockStmt("q"),new CompStmt(new ForkStmt(
//                                                                        new CompStmt(new AssignStmt("v",new ConstExp(1)),
//                                                                                new CompStmt(new PrintStatement(new VarExp("v")),
//                                                                                        new LockStmt("q"))
//
//                                                                        )
//        ),new LockStmt("q")));


        //fourth statement

       // Statement prog=new LockStmt("q");

        PrgState initialPrgState = new PrgState(new ExeStack<Statement>(), new SymbolTable<String, Integer>(), new Output<Integer>(), prog, new FileTable<Integer, FileData>(), new Heap(),procTable,new LockTable());
        repo.addPrgState(initialPrgState);
        ctrl.initGuiCtrl();




        ListView<Statement> lview=new ListView<>(FXCollections.observableArrayList());
        lview.getItems().addAll(prog);

        lview.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->
        {

            startNewStage(ctrl);


        }
        ));

        return new BorderPane(lview);

    }

    private void updateAll(Controller ctrl){


        if(ctrl.getRepo().getPrgStates().isEmpty())
        {
            nrOfPrgs.setText("0");
            heapview.getItems().clear();
            lockview.getItems().clear();
            outputview.getItems().clear();
            filetableview.getItems().clear();
            prgStateListView.getItems().clear();
            symbolview.getItems().clear();
            exestackview.getItems().clear();
            return;
        }

        //
        nrOfPrgs.setText("0");
        heapview.getItems().clear();
        lockview.getItems().clear();
        outputview.getItems().clear();
        filetableview.getItems().clear();
        prgStateListView.getItems().clear();
        symbolview.getItems().clear();
        exestackview.getItems().clear();

        //


        nrOfPrgs.setText(""+ctrl.getRepo().getPrgStates().size());

        List<Map.Entry<Integer,Integer>> ls=new ArrayList<Map.Entry<Integer,Integer>>();
        for(Map.Entry<Integer,Integer> m:ctrl.getRepo().getPrgStates().get(0).getHeap().getContent().entrySet()){
            ls.add(m);
        }

        ObservableList<Map.Entry<Integer,Integer>> heapentries=FXCollections.observableList(ls);
        System.out.println(ls.toString());
        heapview.setItems(heapentries);



        //lockview update

        System.out.println("LOCKING TABLE: \n"+ctrl.getRepo().getPrgStates().get(0).getLockTable().getContent().toString());
        List<Map.Entry<Integer,Integer>> ls23=new ArrayList<Map.Entry<Integer,Integer>>();
        for(Map.Entry<Integer,Integer> m:ctrl.getRepo().getPrgStates().get(0).getLockTable().getContent().entrySet()){
            ls23.add(m);
        }

        ObservableList<Map.Entry<Integer,Integer>> lockentries=FXCollections.observableList(ls23);
        lockview.setItems(lockentries);



        ObservableList<Integer> observableList=FXCollections.observableArrayList(ctrl.getRepo().getPrgStates().get(0).getOutput().getList());
        outputview.setItems(observableList);






        List<Map.Entry<Integer,FileData>> ls1=new ArrayList<Map.Entry<Integer,FileData>>();
        for(Map.Entry<Integer,FileData> m:ctrl.getRepo().getPrgStates().get(0).getFileTable().getAll()){
            ls1.add(m);
        }

        ObservableList<Map.Entry<Integer,FileData>> fileentries=FXCollections.observableList(ls1);

        filetableview.setItems(fileentries);

        List<PrgState> lss=new ArrayList<PrgState>();
        for(PrgState m:ctrl.getRepo().getPrgStates()){
            lss.add(m);
        }

        ObservableList<PrgState> prgst=FXCollections.observableList(lss);


        prgStateListView.setItems(prgst);



        PrgState p=ctrl.getRepo().getPrgStates().get(0);
        ISymbolTable<String,Integer> ist=p.getSymbolTable();
        List<Map.Entry<String,Integer>> lsss=new ArrayList<Map.Entry<String,Integer>>();
        for(Map.Entry<String,Integer> m:ist.getAll()){
            lsss.add(m);
        }

        ObservableList<Map.Entry<String,Integer>> entries=FXCollections.observableList(lsss);

        symbolview.setItems(entries);



        List<Statement> ls2=new ArrayList<Statement>();
        for(Statement s:p.getExeStack().getAll()){
            ls2.add(s);
        }
        ObservableList<Statement> entrs=FXCollections.observableList(ls2);
        Collections.reverse(entrs);
        exestackview.setItems(entrs);



    }

    private void fillAll(PrgState p){

        if(p==null)
            return;
        ISymbolTable<String,Integer> ist=p.getSymbolTable();
        List<Map.Entry<String,Integer>> ls=new ArrayList<Map.Entry<String,Integer>>();
        for(Map.Entry<String,Integer> m:ist.getAll()){
            ls.add(m);
        }

        ObservableList<Map.Entry<String,Integer>> entries=FXCollections.observableList(ls);

        symbolview.setItems(entries);



        List<Statement> ls2=new ArrayList<Statement>();
        for(Statement s:p.getExeStack().getAll()){
            ls2.add(s);
        }
        System.out.println(p.getExeStack().getAll());
        ObservableList<Statement> entrs=FXCollections.observableList(ls2);
        Collections.reverse(entrs);
        exestackview.setItems(entrs);


    }


}
