package cinema;
import java.util.Scanner;
public class Cinema {
    
    static Scanner scanner = new Scanner(System.in);
    
    //main method start
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
	    int rows = scanner.nextInt();
	    System.out.println("Enter the number of seats in each row");
	    int cols = scanner.nextInt();
		String [][]seats = new String[rows][cols];
		for(int i = 0; i < rows; i++){
		    for(int j = 0; j < cols; j++){
		        seats[i][j] = "S";
		    }
		}
		while(menu(seats, rows, cols) != 0){
		    menu(seats, rows, cols);
		}
    }
    //Main method end
    
    // prints the cinema to the screen.
	public static void printTheatre(String[][]seats,int rows, int cols){
        System.out.println("Cinema:");
        System.out.print(" ");
        for(int i = 1; i <= cols; i++){
            System.out.print(" "+i);
        }
        System.out.println();
        int rowsNum = 1;
        for(int i = 0; i < rows; i++){
            System.out.print(rowsNum+" ");
            rowsNum += 1;
            for(int j = 0; j < cols; j++){
                System.out.print(seats[i][j]+" ");
            }
            System.out.println();
        }
    }
	

	// checks if the user input is valid.
	public static boolean validInput(int rows, int cols, int seatRow, int seatCol){
	    if(seatRow <= rows && seatCol <= cols){
	        return true;
	    }
	    else{
	        return false;
	    }
	}
	

	
	// checks if the seat is purchased or available.
	public static boolean validSeat(String[][]seats, int seatRow, int seatCol){
	    if(seats[seatRow - 1][seatCol - 1].equals("S")){
	        return true;
	    }
	    else {
	        return false;
	    }
	}

	
	// buys a ticket and check for it's availability.
	public static void buyTicket(String [][] seats, int rows, int cols){
	    
	    System.out.println("Enter a row number: ");
	    int seatRow = scanner.nextInt();
	    System.out.println("Enter a seat number in that row: ");
	    int seatCol = scanner.nextInt();
	    
	    
	    if(validInput(rows, cols, seatRow, seatCol) == true){
	        if(validSeat(seats,seatRow,seatCol) == true){
	        seats[seatRow - 1][seatCol - 1] = "B";
	        System.out.println("Ticket price : $"+singleTicketPrice(rows , cols, seatRow, seatCol));
	        }
	        else {
	            System.out.println("That ticket has already been purchased!");
	            buyTicket(seats,rows,cols);
	        }
	    }
	    else {
	        System.out.println("Wrong input!");
	        buyTicket(seats,rows,cols);
	    }
	}
	
	

	
	// Calculates how much each ticket costs.
	public static int singleTicketPrice(int rows, int cols, int seatRow, int seatCol){
	    int totalSeats = rows*cols;
	    if(totalSeats <= 60){
	        return 10;
	    }
	    else if(seatRow > (rows/2)){
	        return 8;
	    }
	    else{
	        return 10;
	    }
	}
	
	

	
	//Calculates the income expected if the hall is full.
	public static int allSeatsCalc(int rows, int cols){
	   int totalIncome;
	   int totalseats = rows* cols;
	   if(totalseats > 60){
	       totalIncome = -18;
	   }
	   else {
	       totalIncome = 0;
	   }
	    for(int i = 0; i < rows; i++){
		    for(int j = 0; j < cols; j++){
		        totalIncome += singleTicketPrice(rows,cols,i,j);
		    }
		}
		return totalIncome;
	}
	

	// Calculates the current income of the sold tickets.
	public static int currentIncome(String [][]seats,int rows, int cols){
	    int currentIncome = 0;
	    for(int i = 0; i < rows; i++){
		    for(int j = 0; j < cols; j++){
		        if(rows*cols > 60){
		            if(i >= rows/2 && seats[i][j].equals("B")){
		            currentIncome += 8;
    		        }
    		        else if (i < rows/2 && seats[i][j].equals("B")){
    		            currentIncome += 10;
    		        }
		        }
		    }
		}
		return currentIncome;
	}
	
	
	
	// calculates how many tikets are already bought.
	public static int ticketsBoughtNumber(String[][]seats, int rows, int cols){
	    int ticketsBought = 0;
	    for(int i = 0; i < rows; i++){
	        for(int j = 0; j < cols; j++){
	            if(seats[i][j].equals("B")){
	                ticketsBought += 1;
	            }
	        }
	    }
	    return ticketsBought;
	}


	// calculates the percentage of bought tickets.
	public static double percentage(String[][]seats, int rows, int cols){
	    int totalSeats = rows*cols;
	    double percentage = ((float)ticketsBoughtNumber(seats, rows, cols)/(float) totalSeats)*100;
	    return percentage;
	}
	

	// prints the statistics of the current theatre state.
	 public static void statistics(String[][] seats, int rows, int cols){
        System.out.println("Number of purchased tickets : "+(int)ticketsBoughtNumber(seats, rows, cols));
        String s = String.format("percentage : %.2f", percentage(seats, rows, cols));
        System.out.println("Percentage : "+s+"%");
        System.out.println("Current income : $"+currentIncome(seats, rows,cols));
        System.out.println("Total income : $"+allSeatsCalc(rows,cols));   
    }
    
     
    //this is the menu where the user can navigate through the options.
    public static int menu(String[][]seats, int rows, int cols){
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int menu = scanner.nextInt();
        switch(menu){
            case 1 :
                printTheatre(seats,rows,cols);
                return 1;
            case 2 :
                buyTicket(seats,rows,cols);
                return 2;
            case 3 :
                statistics(seats, rows, cols);
                return 3;
            case 0 :
                return 0;
        }
        return 1;
    }
}
