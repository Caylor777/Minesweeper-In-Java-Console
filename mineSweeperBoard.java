import java.util.*;

public class mineSweeperBoard{
    
    private String[][] visableBoard;
    private String[][] keyBoard;
    private String[][] numberBoard;
    private Random rand = new Random();
    private int flags;
    
    public mineSweeperBoard(int gameW, int gameH){
        visableBoard = new String[gameW][gameH];
        keyBoard = new String[gameW][gameH];
        numberBoard = new String[gameW][gameH];
    }

    public String[][] getVisableBoard(){
        return visableBoard;
    }

    public String[][] getKeyBoard(){
        return keyBoard;
    }

    public String[][] getNumberBoard(){
        return numberBoard;
    }

    public void printBoard(String[][] board){
        for(int i = 0; i < 50; i++){
          System.out.println();
        }
    
        System.out.print("  ");
        for(int i = 0; i < board.length; i++){
          if (i < 9){
            System.out.print(" " + (i + 1) + " ");
          }
          else{
            System.out.print(" " + (i + 1));
          }
        }
    
        for (int i = 0; i < board.length; i++){
            System.out.println();
            if (i < 9){
                System.out.print((i + 1) + " ");
            }
            else{
                System.out.print(i + 1);
            }
            for (int j = 0; j < board[0].length; j++){
                System.out.print("[" + board[i][j] + "]");
            }
        }
      }
    
    
    public void setUpBoard(){
        for (int i = 0; i < visableBoard[0].length; i++){
            for (int j = 0; j < visableBoard.length; j++){
                visableBoard[i][j] = " ";
            }
       }
       int bombFactor = 5;
       int bombs = ((visableBoard.length * visableBoard[0].length) / bombFactor) + 1;
       flags = bombs;
       int safe = (visableBoard.length * visableBoard[0].length) - (((visableBoard.length * visableBoard[0].length) / bombFactor) + 1);
        for (int i = 0; i < visableBoard.length; i++){
            for (int j = 0; j < visableBoard.length; j++){
                int random = rand.nextInt(bombs + safe);
                if (random > bombs){
                    keyBoard[i][j] = "s";
                    safe--;
                }
                else{
                    keyBoard[i][j] = "B";
                    bombs--;
                }
            }
       }
       for (int i = 0; i < visableBoard[0].length; i++){
        for (int j = 0; j < visableBoard.length; j++){
            if (keyBoard[i][j].equals("B")){
                numberBoard[i][j] = "B";
            }
            else{
                int counter = 0;
                try{
                    if (getKeyTile(i+1, j).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i, j+1).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i-1, j).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i, j-1).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i+1, j+1).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i-1, j-1).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i-1, j+1).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                try{
                    if (getKeyTile(i+1, j-1).equals("B")){
                        counter = counter + 1;
                    }
                }
                catch(Exception e){;}
                numberBoard[i][j] = Integer.toString(counter);
            }
        }   
        }
        boolean placed = false;
        for (int i = 0; i < visableBoard[0].length; i++){
            for (int j = 0; j < visableBoard.length; j++){
                if(!placed){  
                    if (numberBoard[i][j].equals("0")){
                        visableBoard[i][j] = "0";
                        try{
                        visableBoard[i+1][j] = numberBoard[i+1][j];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i][j+1] = numberBoard[i][j+1];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i-1][j] = numberBoard[i-1][j];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i][j-1] = numberBoard[i][j-1];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i+1][j+1] = numberBoard[i+1][j+1];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i-1][j-1] = numberBoard[i-1][j-1];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i+1][j-1] = numberBoard[i+1][j-1];
                        }catch(Exception e){;}
                        try{
                        visableBoard[i-1][j+1] = numberBoard[i-1][j+1];
                        }catch(Exception e){;}
                        placed = true;
                    }
                }
            }
        }

    }

    public int getFlags(){
        return flags;
    }

    public String getKeyTile(int x, int y){
        return keyBoard[x][y];
    }

    public String getVisableTile(int x, int y){
        return visableBoard[x][y];
    }

    public String getNumberTile(int x, int y){
        return numberBoard[x][y];
    }

    public void setTileDisplay(int x, int y, String value){
        visableBoard[x][y] = value;
    }

    public void dig(int x, int y){
        if(!(getVisableTile(x, y).equals("f"))){
            setTileDisplay(x, y, getNumberTile(x, y));
        }
        if (getKeyTile(x, y).equals("B")){
          printBoard(numberBoard);
          System.out.println();
          System.out.println("You hit a mine");
          System.exit(0);
        }
      }

      public void flag(int x, int y){
        if (getVisableTile(x, y).equals(" ")){
            setTileDisplay(x, y, "f");
            flags = flags - 1;
        }
        else if (getVisableTile(x, y).equals("f")){
            setTileDisplay(x, y, " ");
            flags = flags + 1;
        }
        else{}
      }
}