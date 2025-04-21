package HMS;

import java.util.Scanner;
import HMS.HotelDAO;
import HMS.DBConnection;

public class Hotel_Management {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelDAO dao = new HotelDAO();
        int choice;

        do {
            System.out.println("\n===== Hotel Management Menu =====");
            System.out.println("1. Add Guest");
            System.out.println("2. Delete Guest by ID");
            System.out.println("3. Get Guest by ID");
            System.out.println("4. Get All Guests");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    dao.insertGuest(id, name, email);
                    break;

                case 2:
                    System.out.print("Enter ID to delete: ");
                    dao.deleteGuestById(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    dao.getGuestById(sc.nextInt());
                    break;

                case 4:
                    dao.getAllGuests();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
