import java.io.*;
import java.util.*;

public class MazeRunner {

    Maze myMap = new Maze();

    public void intro(){
        System.out.println("Welcome to the Maze Runner");
        System.out.println("Here is your current position");
        myMap.printMap();
    }

    public static void movesMessage(int moves){
        if(moves==50)
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        else if(moves==75)
            System.out.println("25 move moves left");
        else if(moves==90)
            System.out.println("10 more moves only");
        else if(moves==100)
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
    }
    public void main(System args[]){
        intro();
        int moves=0;
        Scanner input=new Scanner(System.in);
        while(!myMap.didIWin()) {
            if (moves == 100) {
                System.out.println("You couln't make it in 100 moves!!! Try Again.");
                break;
            }
            System.out.println("Where would you like to move? (R, L, U, D)");

            String inp = input.next();
            if (myMap.isThereAPit(inp)) {
                System.out.println("Watch out! There's a pit ahead, jump it?");
                String inp2=input.next();
                if(inp2.startsWith("y"))
                    myMap.jumpOverPit(inp);
                else{
                    System.out.print("You fell in a pit!!! You lose!");
                    break;
                }

            }
            else {
                if (inp.compareTo("R") == 0 && myMap.canIMoveRight())
                    myMap.moveRight();
                else if (inp.compareTo("L") == 0 && myMap.canIMoveLeft())
                    myMap.moveLeft();
                else if (inp.compareTo("U") == 0 && myMap.canIMoveUp())
                    myMap.moveUp();
                else if (inp.compareTo("D") == 0 && myMap.canIMoveDown())
                    myMap.moveDown();
                else System.out.println("Sorry, you’ve hit a wall.");
                moves++;
                myMap.printMap();
                movesMessage(moves);
            }
        }
        if(myMap.didIWin()) {
            System.out.println("Congratulations, you made it out alive!");
            System.out.println("You did it in " + moves + " steps only!!!");
        }
    }
}
