package master.view;


import master.collection.list.MyList;
import master.controller.Controller;
import master.model.exceptions.BadInputException;
import master.model.statement.IStatement;

import java.util.Scanner;

public class Console {
    private final Controller ctrl;
    private final MyList<IStatement> statements;

    public Console(Controller ctrl, MyList<IStatement> statements){
        this.ctrl = ctrl;
        this.statements = statements;
    }

    private void printMenu(){
        System.out.println("\n=========Toy Language Interpretor=========");
        for (int i = 0; i < statements.size(); i++)
        {
            System.out.println(String.format("%d: %s", i, statements.get(i).toString()));
        }
        System.out.println("-1: Exit.");
    }

    private int getInteger(Scanner scanner) throws BadInputException {
        try {
            return Integer.parseInt((scanner.nextLine()));
        }
        catch (NumberFormatException e){
            throw new BadInputException("Invalid integer");
        }
    }

    private void infiniteLoop(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            printMenu();
            System.out.print("Choose one oprion: ");
            int option = -1;
            try{
                option = getInteger(scanner);
                if(option == -1) break;
                if(option < statements.size()) try {
                    //ctrl.setProgram(new ProgramState(statements.get(option)));
                    ctrl.allSteps();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (BadInputException e){
                System.out.print(e.getMessage());
            }


        }

        scanner.close();
    }

    public void runApp(){
        infiniteLoop();
    }
}
