package master.sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import master.controller.Controller;
import master.model.ProgramState;
import master.model.expression.*;
import master.model.statement.*;
import master.model.statement.File.CloseStatement;
import master.model.statement.File.OpenStatement;
import master.model.statement.File.ReadStatement;
import master.model.statement.Heap.NewStatement;
import master.model.statement.Heap.WriteStatement;
import master.repository.IRepository;
import master.repository.Repository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectFormController implements Initializable {
    private List<IStatement> programStatements;
    private RunFormController mainWindowController;

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button executeButton;

    public void setMainWindowController(RunFormController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    private void buildProgramStatements() {
        IStatement ex0 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(2)),
                new PrintStatement(new VariableExpression("v")));

        IStatement ex1 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new
                ArithmeticExpression(new ConstantExpression(3), new ConstantExpression(5), OperationEnum.MULTIPLY), OperationEnum.PLUS)),
                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression(new VariableExpression("a"), new
                        ConstantExpression(1), OperationEnum.PLUS)), new PrintStatement(new VariableExpression("b"))));

        IStatement ex2 = new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ConstantExpression(2), new ConstantExpression(2), OperationEnum.MINUS)),
                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ConstantExpression(2)),
                        new AssignStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));

        IStatement ex3 = new CompoundStatement(new OpenStatement("var_f", "test.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(new IfStatement(new VariableExpression("var_c"),
                                        new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"),
                                                new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0))),
                                        new CloseStatement(new VariableExpression("var_f"))))));


        IStatement ex4 = new CompoundStatement(new AssignStatement("v",
                new ConstantExpression(10)), new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new PrintStatement(new VariableExpression("v")))));


        IStatement ex5 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(new PrintStatement(
                                        new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("v"), OperationEnum.PLUS)),
                                        new PrintStatement(new ArithmeticExpression(new ConstantExpression(100), new HeapReadingExpression("a"), OperationEnum.PLUS))))));

        IStatement ex6 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new HeapReadingExpression("a")))))));

        IStatement ex7 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(new NewStatement("a", new ConstantExpression(20)),
                                new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                new CompoundStatement(new PrintStatement(new HeapReadingExpression("a")),
                                                        new AssignStatement("a", new ConstantExpression(0))))))));

        IStatement ex8 = new PrintStatement(new ArithmeticExpression(new ConstantExpression(10),
                new BooleanExpression(new ConstantExpression(2), new ConstantExpression(6), "<"), OperationEnum.PLUS));


        IStatement ex9 = new PrintStatement(new BooleanExpression(new ArithmeticExpression(new ConstantExpression(10), new ConstantExpression(2), OperationEnum.PLUS),
                new ConstantExpression(6), "<"));


        IStatement ex10 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(6)),
                new CompoundStatement(new WhileStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(4), OperationEnum.MINUS),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",
                                new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new PrintStatement(new VariableExpression("v"))));

        IStatement ex11 = new CompoundStatement(new OpenStatement("var_f", "test2.in"),
                new CompoundStatement(new ReadStatement(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))));

        IStatement ex12 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new NewStatement("a", new ConstantExpression(22)),
                        new CompoundStatement(
                                new ForkStatement(new CompoundStatement(new CompoundStatement(new WriteStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(new AssignStatement("v", new ConstantExpression(32)),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression("a"))))), new PrintStatement(new ArithmeticExpression(new ConstantExpression(23), new VariableExpression("c"), OperationEnum.PLUS)))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression("a"))))));

        IStatement ex13 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(0)),
                new CompoundStatement(new RepeatStatement(new CompoundStatement(new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)))), new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS))), new BooleanExpression(new VariableExpression("v"), new ConstantExpression(3), "==")),
                        new CompoundStatement(new AssignStatement("x", new ConstantExpression(1)),
                                new CompoundStatement(new AssignStatement("y", new ConstantExpression(2)),
                                        new CompoundStatement(new AssignStatement("z", new ConstantExpression(3)),
                                                new CompoundStatement(new AssignStatement("w", new ConstantExpression(4)),
                                                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY))))))));

        IStatement ex14 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(20)),
                new CompoundStatement(new ForStatement("v", new ConstantExpression(0), new ConstantExpression(3), new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS),
                        new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.PLUS))))),
                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY))));

        IStatement ex15 = new ForStatement("a", new ConstantExpression(0), new ConstantExpression(10),
                new ArithmeticExpression(new VariableExpression("a"), new ConstantExpression(1), OperationEnum.PLUS),
                new PrintStatement(new VariableExpression("a")));

        IStatement ex16 = new CompoundStatement(
                new AssignStatement("a", new ConstantExpression(1)), new CompoundStatement(
                new AssignStatement("b", new ConstantExpression(2)), new CompoundStatement(
                new ConditionalAssignStatement("c", new VariableExpression("a"), new ConstantExpression(100), new ConstantExpression(200)), new CompoundStatement(
                new PrintStatement(new VariableExpression("c")), new CompoundStatement(
                new ConditionalAssignStatement("c", new ArithmeticExpression(new VariableExpression("b"), new ConstantExpression(2), OperationEnum.MINUS), new ConstantExpression(100), new ConstantExpression(200)),
                new PrintStatement(new VariableExpression("c")))))));

        IStatement ex17 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(10)),
                new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)),
                        new CompoundStatement(new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(1), OperationEnum.MINUS)),
                                new PrintStatement(new VariableExpression("v"))))),
                        new CompoundStatement(new SleepStatement(10), new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ConstantExpression(10), OperationEnum.MULTIPLY)))));

        IStatement ex18 = new CompoundStatement(new AssignStatement("a", new ConstantExpression(1)),
                new CompoundStatement(new AssignStatement("b", new ConstantExpression(2)),
                        new CompoundStatement(new AssignStatement("c", new ConstantExpression(5)),
                                new CompoundStatement(new SwitchStatement(new ArithmeticExpression(new VariableExpression("a"), new ConstantExpression(10), OperationEnum.MULTIPLY),
                                        new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("c"), OperationEnum.MULTIPLY), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))),
                                        new ConstantExpression(10), new CompoundStatement(new PrintStatement(new ConstantExpression(100)), new PrintStatement(new ConstantExpression(300))),
                                        new PrintStatement(new ConstantExpression(300))),
                                        new PrintStatement(new ConstantExpression(300))))));

        IStatement ex19 = new CompoundStatement(new AssignStatement("v", new ConstantExpression(1)),
                new CompoundStatement(new ForkStatement(new AssignStatement("v", new ConstantExpression(2))),
                        new ForkStatement(new AssignStatement("v", new ConstantExpression(3)))));


        programStatements = new ArrayList<>(Arrays.asList(ex0, ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14, ex15, ex16, ex17, ex18, ex19));
    }

    private List<String> getStringRepresentations() {
        return programStatements.stream().map(IStatement::toString).collect(Collectors.toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildProgramStatements();
        programListView.setItems(FXCollections.observableArrayList(getStringRepresentations()));

        executeButton.setOnAction(actionEvent -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if (index < 0)
                return;

            ProgramState initialProgramState = new ProgramState(programStatements.get(index));
            IRepository repository = new Repository("log" + index + ".txt");
            repository.addProgramState(initialProgramState);
            Controller ctrl = new Controller(repository);

            FXMLLoader mainLoader = new FXMLLoader();
            mainLoader.setLocation(getClass().getResource("runForm.fxml"));
            Parent mainWindow = null;
            try {
                mainWindow = mainLoader.load();


                mainWindowController = mainLoader.getController();

                Stage primaryStage = new Stage();
                primaryStage.setTitle("Main Window");
                primaryStage.setScene(new Scene(mainWindow, 620, 600));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainWindowController.setController(ctrl);


        });
    }
}
