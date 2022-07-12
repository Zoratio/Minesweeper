import java.util.Scanner;

public class ScannerInputs {
    static private Scanner myScanner = new Scanner(System.in);
    static public void CheckRange(){
    }
    static public void CheckDifficulty(){
    }
    static public int[] CheckInput(int[] pos){
        System.out.print("Please input the row coordinates of your next input:");
        pos[0] = Integer.parseInt(myScanner.nextLine());
        //CHECK IF VALID

        System.out.print("Please input the column coordinates of your next input:");
        pos[1] = Integer.parseInt(myScanner.nextLine());
        //CHECK IF VALID

        return pos;
    }
}
