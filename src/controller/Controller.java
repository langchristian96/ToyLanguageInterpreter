package controller;

import javafx.scene.control.Alert;
import utils.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import model.*;
import repository.*;

import static java.lang.System.exit;


public class Controller {

    private IRepository repo;
    List<PrgState> prgListt;
    private ExecutorService executer;
    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    public IRepository getRepo(){
        return repo;
    }
//    public void executeOneStep(PrgState state) {
//        IExeStack<Statement> stack = state.getExeStack();
//        if (!stack.isEmpty()) {
//            Statement statem = stack.pop();
//            statem.execute(state);
//        }
//    }

    public void oneStepForAll(List<PrgState> those) throws IOException, InterruptedException {


//        those.forEach(prg -> {
//            try {
//                this.repo.logPrgStateExec(prg);
//            } catch (IOException e) {
//                System.out.println("One step for all failed");
//            }
//        });
        List<Callable<PrgState>> callList = those.stream().map((PrgState p) -> (Callable<PrgState>) (() -> {return p.oneStep();})).collect(Collectors.toList());
        List<PrgState> these=executer.invokeAll(callList).stream().map(future->{try{return future.get();}catch(Exception e)
                {System.out.println("Something went wrong (one step for all)");Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Error info:");
                    alert.setContentText(e.getMessage());

                    alert.showAndWait();executer.shutdownNow();exit(0);}return null;}).filter(p->p!=null).collect(Collectors.toList());
        those.addAll(these);
        if(!these.isEmpty())
            System.out.println(these.get(0));
        repo.setPrgStates(those);
        those.forEach(prg -> {
            try {
                this.repo.logPrgStateExec(prg);
            } catch (IOException e) {
                System.out.println("One step for all failed");
            }
        });
    }


    Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                       Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public void executeAll() throws InterpretorException, IOException, InterruptedException {
//
//        PrgState crt = repo.getCurrent();
//        IExeStack<Statement> stack = crt.getExeStack();
//
//        while (!stack.isEmpty()){//true) {
//
//            executeOneStep(crt);
//
//
//            try {
//
//                crt.getHeap().setContent(conservativeGarbageCollector(
//                        crt.getSymbolTable().getContent().values(),
//                        crt.getHeap().getContent()));
//
//                repo.logPrgStateExec();
//                System.out.println(crt);
//            } catch (IOException e) {
//                throw new InterpretorException(e.getMessage());
//            }
//        }

        executer= Executors.newFixedThreadPool(2);
        while(true){
            List<PrgState> prgList=removeCompletedPrg(repo.getPrgStates());
            if(prgList.size()==0)
                break;
            oneStepForAll(prgList);
        }
        executer.shutdownNow();

    }

    public void initGuiCtrl(){

        executer= Executors.newFixedThreadPool(6);
        prgListt=removeCompletedPrg(repo.getPrgStates());

    }

    public void execOneStepGUI() throws IOException,InterruptedException{


        try {
            prgListt = removeCompletedPrg(repo.getPrgStates());
            oneStepForAll(prgListt);
            if (prgListt.size() == 0) {
                //System.out.println("TEST");
                executer.shutdownNow();
                return;
            }
        }
        catch(InterpretorException e){

            System.out.println("TESTER");
            executer.shutdownNow();
        }
    }


//    public void serialize(String filename) throws IOException{
//        this.repo.Serialize(this.repo.getCurrent(),filename);
//    }

    public PrgState deserialize(String filename){
        return this.repo.deSerialize(filename);
    }


}
