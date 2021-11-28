import java.util.Scanner;

public class Airplane {
    private final int rows = 13;
    private final int columns = 6;
    private char[][] seats = new char[rows][columns];
    private int seatNum;
    private char seatLetter;



    public Airplane(){}

    public char getSeatLetter() {
        return seatLetter;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatLetter(char seatLetter) {
        this.seatLetter = seatLetter;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public char[] convertColumns(int c){
        char[] seatLetters = new char[c];
        for (int i = 0; i < c; i++) {
            seatLetters[i] = (char)(65 + i);

        }
        return seatLetters;
    }


    public void initializeSeats(){ //change this?
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(seats[i][j] != 'X')
                    seats[i][j] = '*';
            }
        }
    }
    public void printSeats(){
        System.out.printf("%-8s", "");

        char[] seatsL = convertColumns(columns);

        //print column letters
        for (int i = 0; i < seatsL.length; i++) {
            System.out.print(seatsL[i] + " ");
            if(i == 2)
                System.out.print(" ");
        }
        System.out.println();

        //print the 2d array
        for (int j = 0; j < rows; j++) {
            System.out.printf("%-3s %-4d", "Row", j + 1);
            for (int k = 0; k < columns; k++) {
                System.out.print(seats[j][k] + " ");
                if(k == 2)
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    public void showMenu(){
        Scanner input = new Scanner(System.in);
        int[] validRows;

        System.out.println("\nRows 1 and 2 are for first class passengers.");
        System.out.println("Rows 3 through 7 are for business class passengers.");
        System.out.println("Rows 8 through 13 are for economy class passengers.");


        //Check if user wants to get a seat
        System.out.print("To reserve a seat enter Y/y(Yes), N/n(No):");
        String s = input.next();
        while(!s.matches("^[ynYN]*$")){
            System.out.println("Incorrect input");

            System.out.print("To reserve a seat enter Y/y(Yes), N/n(No): ");
            s = input.next();
        }
        if(s.matches("^[nN]*$")){
            System.out.println("Program terminated");
            System.exit(0);
        }


        //Check type of ticket
        System.out.print("Enter ticket type: F/f (first class);  (B/b) (business class); E/e (economy class):");
        s = input.next();
        while(!s.matches("^[fbeFBE]*$")){
            System.out.println("Incorrect input");
            System.out.print("Enter ticket type: F/f (first class);  (B/b) (business class); E/e (economy class):");
            s = input.next();
        }
        if(s.matches("^[fF]*$")){
            validRows = new int[]{1,2};
        }
        else if(s.matches("^[bB]*$")){
            validRows = new int[]{3,4,5,6,7};
        }
        else{
            validRows = new int[]{8,9,10,11,12,13};
        }


        //check seat number
        int seatRow = 0;
        System.out.print("Enter Row number " + validRows[0] + "-" + validRows[validRows.length-1] + ":");
        s = input.next();

        boolean b = false;
        while(!b){
            for (int validRow : validRows) {
                if (Integer.parseInt(s) == validRow) {
                    b = !b;
                    seatRow = Integer.parseInt(s);
                    break;
                }

            }
            if(b)
                break;
            System.out.println("Invalid input");
            System.out.print("Enter Row number " + validRows[0] + "-" + validRows[validRows.length-1] + ":");
            s = input.next();
        }

        //ask for letter
        System.out.print("Enter Seat Column:(A-F):");
        s = input.next();
        while(!s.matches("^[abcdefABCDEF]*$")){
            System.out.println("Incorrect input");
            System.out.print("Enter Seat Column:(A-F):");
            s = input.next();
        }

        char a = s.charAt(0);
        int x = 0;
        setSeatNum(seatRow);
        setSeatLetter(a);

        if(Character.isUpperCase(a))
            x = (int)a - 65;
        else x = (int)a - 97;

        try{
            assignSeat(seatRow, x);
        }
        catch (Exception ex){
            System.out.println("*****Seat already occupied! -Try again*****");
            return;
        }

        System.out.println("The seat " + Character.toUpperCase(a) + seatRow + " is reserved for you");
    }

    private void assignSeat(int seatRow, int seatCol) throws Exception{
        if(seats[seatRow - 1][seatCol] == 'X')
            throw new Exception();
        else seats[seatRow - 1][seatCol] = 'X';
    }

    public static void main(String[] args) {
        Airplane A = new Airplane();

        A.initializeSeats();
        while (true){
            A.printSeats();
            A.showMenu();
        }


    }

}
