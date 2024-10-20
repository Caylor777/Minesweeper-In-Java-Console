import java.util.*;

public class mineSweeperGame {

  static mineSweeperGame go = new mineSweeperGame();
  static mineSweeperBoard board = new mineSweeperBoard(9, 9);
  Scanner scan = new Scanner(System.in);

public void runner(){
  while (board.getFlags() > 0){
    board.printBoard(board.getVisableBoard());
    System.out.println();
    System.out.println("---------------");
    System.out.println(board.getFlags() + " flags left");
    System.out.print("Enter [x]: ");
    int x = scan.nextInt() - 1;
    System.out.print("Enter [y]: ");
    int y = scan.nextInt() - 1;
    System.out.print("Enter \"f\" for flag or \"d\" for dig: ");
    scan.nextLine();
    String action = scan.nextLine();
    System.out.println();

    if (action.equals("f")){
      try{
        board.flag(y, x);
      }catch(Exception e){;}
    }
    else if (action.equals("d")){
      try{
        board.dig(y, x);
      }catch(Exception e){;}
    }
    else{
      System.out.println("Invalid input");
    }
  }
  for(int i = 0; i < board.getVisableBoard().length; i++){
    for (int j = 0; j < board.getVisableBoard()[0].length; j++){
      if(board.getVisableTile(i, j).equals("f") && !(board.getKeyTile(i, j).equals("B"))){
        System.out.println("You Lose: Misplaced Flag(s)");
        System.exit(0);
      }
    }
  }
  System.out.println("You Win: All bombs flaged");
  System.exit(0);
}

  public static void main(String args[]) {
    board.setUpBoard();
    go.runner();
  }
}