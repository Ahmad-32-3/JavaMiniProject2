/*
Filename: W8U2A4_MuhammadAhmad_Employees
Author:  Muhammad Ahmad
Date: Monday, April 15, 2024
Purpose: To create a program that tracks the number of hours worked over 4 separate weeks from 3 employees at a particular business.
*/

// Import scanner
import java.util.Scanner;

public class Main {

  // Print method
  public static void print(String message) {
    System.out.println(message);
  }

  public static void main(String[] args) {

    // Initialize Variables
    int optionChoice;
    int weekChoice;
    String employeeInfo;
    String holdingValue = "";
    double totaleHours;
    double totalcHours = 0;
    double weekHours;
    Boolean eFound;

    // Set scanner to keyedInput
    Scanner keyedInput = new Scanner(System.in);

    // Create database
    String [][] dataBase = new String[5][8];

    // Set column headers
    dataBase[0][0] = "Employee Name";
    dataBase[0][1] = "Employee ID";
    dataBase[0][2] = "Week 1 Hours";
    dataBase[0][3] = "Week 2 Hours";
    dataBase[0][4] = "Week 3 Hours";
    dataBase[0][5] = "Week 4 Hours";
    dataBase[0][6] = "Total Hours Worked";
    dataBase[0][7] = "Average Hours Worked";

    // Set more positions to avoid null values
    dataBase[4][0] = "";
    dataBase[4][1] = "Total Company Hours";
    dataBase[4][7] = "";

    // Iterate through rows 1-3 and ask for corrosponding employee name
    for (int row = 1; row <= 3; row++) {
      for (int col = 0; col <= 5; col++) {
        // Check if employee name column (column 0)
        if (col == 0) {
          // Ask for name of employee
          print("Enter the name of employee " + row + ":");
          // Check if employee ID colum (column 1)
        } else if (col == 1) {
          // Ask for id of employee
          print("Enter the employee ID (4 digits) of employee " + row + ":");
        } else {
          // Week hours
          print("Enter the hours in week " + (col - 1) + " by employee " + row + ":");
        }
        dataBase[row][col] = keyedInput.next();
      }
    }

    // Solve for totalHours in a week per employee (Iterate by employee)
    for (int row = 1; row <= 3; row++) {
      // Make totalHours set to 0 before each reiteration for a fresh reset before next week
      totaleHours = 0;
      // Iterate through the columns set for week 1 hours to week 4 hours (Iterate by week)
      for (int col = 2; col <= 5; col++) {
        // Add each individual week hours to totalHours
        totaleHours += Double.parseDouble(dataBase[row][col]);
      }
      // At the end of the week iteration, set the current totalHours to the corrosponding employee as designated by their row
      dataBase[row][6] = Double.toString(totaleHours);
      // Solve for average hours via the totalHours
      dataBase[row][7] = Double.toString(Math.round((totaleHours / 4)*100.0)/100.0);
    }

    // New for loop to solve for total company hours (iterate by week)
    for (int col = 2; col <= 5; col++) {
      // Set weekly hours to 0 after each week iteration
      weekHours = 0;
      // Iterate by employee
      for (int row = 1; row <= 3; row++) {
        weekHours += Double.parseDouble(dataBase[row][col]);
      }
      // At the end of each employee iteration, set the current week hours to total company hours
      totalcHours += weekHours;
      // Set my weekly hours
      dataBase[4][col] = Double.toString(weekHours);
    }

    // Set monthly hours
    dataBase[4][6] = Double.toString(totalcHours);

    // While loop to keep giving user a menu of options
    while (true) {

      // Give user options
      System.out.print(
          "\nInput the corrosponding number for the following executables (1-5)\n" +
              "1. List All Database Info\n" +
              "2. List Employee Info\n" +
              "3. List Company Weekly Hours Stats\n" +
              "4. Company Monthly Hours Stats\n" +
              "5. Exit program\n\n" +
              "Enter the number: ");

      // Take user input
      optionChoice = keyedInput.nextInt();

      // Use a switch statement to enact on chosen option
      switch (optionChoice) {

        // List all database info
        case 1:
          // Iterate through array and find position with most characters
          for (int row = 0; row <= 4; row++) {
            for (int col = 0; col <= 7; col++) {
              // Find max length of characters in the array
              if (dataBase[row][col].length() > holdingValue.length()) {
                holdingValue = dataBase[row][col];
              }
            }
          }

          // Iterate through array
          for (int row = 0; row <= 4; row++) {
            for (int col = 0; col <= 7; col++) {
              // Print according to max length found
              System.out.print(dataBase[row][col]);

              int distance = holdingValue.length() - dataBase[row][col].length();

              for (int space = 0; space < distance; space++) {
                System.out.print(" ");
              }
            }
            // Add row
            System.out.println();
          }
          break;
          
        // List Employee info
        case 2:
          // Ask for input
          print("Enter the last name or employee ID (4 digits, e.g: 1234) of the desired employee: ");
          employeeInfo = keyedInput.next();
        
          // Set to false as default
          eFound = false;

           // Iterate through dataBase
          for (int row = 0; row <= 4; row++) {
            // If it finds the name/ID is in the array, it outputs the following
            if (dataBase[row][0].equals(employeeInfo) || dataBase[row][1].equals(employeeInfo)) {
              print("\nThe employee's last name is: " + dataBase[row][0]);
              print("The employee's ID is: " + dataBase[row][1]);
              print("The employee worked " + dataBase[row][2] + " hours in week 1");
              print("The employee worked " + dataBase[row][3] + " hours in week 2");
              print("The employee worked " + dataBase[row][4] + " hours in week 3");
              print("The employee worked " + dataBase[row][5] + " hours in week 4");
              print("The employee worked a total of " + dataBase[row][6] + " hours over the course of the month.");
              print("The employee worked an average of " + dataBase[row][7] + " hours a week for the past month.");
              eFound = true;
            }
          }
      
          // If eFound is never changed to true, then that means that no employee was found
          if (eFound==false) {
            print("\nAn employee with the information you inputted was not found in database");
          }
          break;

        // List Company Weekly Hours Stats
        case 3:
          // Give user options
          System.out.print(
              "\nInput the corrosponding number (1-4) for the corrosponding week information\n" +
                  "1. Week 1\n" +
                  "2. Week 2\n" +
                  "3. Week 3\n" +
                  "4. Week 4\n\n" +
                  "Enter the number: ");
          // Take user input
          weekChoice = keyedInput.nextInt();

          // Use a switch statement to enact on chosen option
          switch (weekChoice) {
            // List week 1 hour info
            case 1:
              print("\nThe total hours worked by all employees in week 1 are: " + dataBase[4][2] + " hour(s)");
              print(dataBase[1][0] + " worked for " + dataBase[1][2] + " hour(s)");
              print(dataBase[2][0] + " worked for " + dataBase[2][2] + " hour(s)");
              print(dataBase[3][0] + " worked for " + dataBase[3][2] + " hour(s)");
              print("The average hours worked per employee was " + Double.toString(Math.round(Double.parseDouble(dataBase[4][2])/3 * 100.0) / 100.0) + " hour(s)");
              break;
            // List week 2 hour info
            case 2:
              print("\nThe total hours worked by all employees in week 2 are: " + dataBase[4][3] + " hour(s)");
              print(dataBase[1][0] + " worked for " + dataBase[1][3] + " hour(s)");
              print(dataBase[2][0] + " worked for " + dataBase[2][3] + " hour(s)");
              print(dataBase[3][0] + " worked for " + dataBase[3][3] + " hour(s)");
              print("The average hours worked per employee was " + Double.toString(Math.round(Double.parseDouble(dataBase[4][3])/3 * 100.0) / 100.0) + " hour(s)");
              break;
            // List week 3 hour info
            case 3:
              print("\nThe total hours worked by all employees in week 3 are: " + dataBase[4][4] + " hour(s)");
              print(dataBase[0][1] + " worked for " + dataBase[1][4] + " hour(s)");
              print(dataBase[0][2] + " worked for " + dataBase[2][4] + " hour(s)");
              print(dataBase[0][3] + " worked for " + dataBase[3][4] + " hour(s)");
              print("The average hours worked per employee was " + Double.toString(Math.round(Double.parseDouble(dataBase[4][4])/3 * 100.0) / 100.0) + " hour(s)");
              break;
            // List Week 4 hour info
            case 4:
              print("\nThe total hours worked by all employees in week 4 are: " + dataBase[4][5] + " hour(s)");
              print(dataBase[1][0] + " worked for " + dataBase[1][5] + " hour(s)");
              print(dataBase[2][0] + " worked for " + dataBase[2][5] + " hour(s)");
              print(dataBase[3][0] + " worked for " + dataBase[3][5] + " hour(s)");
              print("The average hours worked per employee was " + Double.toString(Math.round(Double.parseDouble(dataBase[4][5])/3 * 100.0) / 100.0) + " hour(s)");
              break;

            // Default case
            default:
              print("Invalid input, to return to this window, enter 3. Remember to read the instructions carefully upon your return.");
          }
          break;
          
        // List Company Monthly Hours Stats
        case 4:
          print("\nThe total hours worked by the company in the past month are: " + dataBase[4][6] + " hour(s)");
          break;
          
        // Exit Program
        case 5:
          print("\nThe program has ended, have a nice day.");
          // Exit Code
          System.exit(0);
          
        // Default case if a number between 1 and 5 is not input
        default:
          print("Invalid input. Enter a number between 1 and 5 to execute the corrosponding action.");
          break;
      }
    }
  }
}