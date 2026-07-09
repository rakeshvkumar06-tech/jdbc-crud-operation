package jdbc;

import java.sql.*;
import java.util.Scanner;
public class testing {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String user = "root";
        String password = "root@123";

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DriverManager.getConnection(url, user, password);

           

            while (true) {

                System.out.println("\n1. Get Vehicle By ID");
                System.out.println("2. Get Vehicle By Brand");
                System.out.println("3. Insert Vehicle");
                System.out.println("4. Update Vehicle");
                System.out.println("5. Delete Vehicle");
                System.out.println("6. Get All Vehicles");
                System.out.println("7. Exit");

                System.out.print("Enter Choice : ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                case 1:

                    System.out.print("Enter ID : ");
                    int id = sc.nextInt();

                    String sql1 = "SELECT * FROM BIKE WHERE ID=?";

                    PreparedStatement ps1 = con.prepareStatement(sql1);
                    ps1.setInt(1, id);

                    ResultSet rs1 = ps1.executeQuery();

                    if (rs1.next()) {

                        System.out.println("\nVehicle Details");
                        System.out.println("---------------------");
                        System.out.println("ID    : " + rs1.getInt("ID"));
                        System.out.println("Brand : " + rs1.getString("BRAND"));
                        System.out.println("Name  : " + rs1.getString("NAME"));

                    } else {

                        System.out.println("Vehicle Not Found.");

                    }

                    break;

                case 2:

                    System.out.print("Enter Brand : ");
                    String brand = sc.nextLine();

                    String sql2 = "SELECT * FROM BIKE WHERE BRAND=?";

                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.setString(1, brand);

                    ResultSet rs2 = ps2.executeQuery();

                    boolean found = false;

                    while (rs2.next()) {

                        found = true;

                        System.out.println("---------------------");
                        System.out.println("ID    : " + rs2.getInt("ID"));
                        System.out.println("Brand : " + rs2.getString("BRAND"));
                        System.out.println("Name  : " + rs2.getString("NAME"));

                    }

                    if (!found) {

                        System.out.println("No Vehicle Found.");

                    }

                    break;

                case 3:

                    System.out.print("Enter ID : ");
                    int newId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Brand : ");
                    String newBrand = sc.nextLine();

                    System.out.print("Enter Name : ");
                    String newName = sc.nextLine();

                    String sql3 = "INSERT INTO BIKE(ID,BRAND,NAME) VALUES(?,?,?)";

                    PreparedStatement ps3 = con.prepareStatement(sql3);

                    ps3.setInt(1, newId);
                    ps3.setString(2, newBrand);
                    ps3.setString(3, newName);

                    int insert = ps3.executeUpdate();

                    if (insert > 0) {

                        System.out.println("Vehicle Inserted Successfully.");

                    } else {

                        System.out.println("Insert Failed.");

                    }

                    break;

                case 4:

                    System.out.print("Enter ID : ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Brand : ");
                    String updateBrand = sc.nextLine();

                    System.out.print("Enter New Name : ");
                    String updateName = sc.nextLine();

                    String sql4 = "UPDATE BIKE SET BRAND=?, NAME=? WHERE ID=?";

                    PreparedStatement ps4 = con.prepareStatement(sql4);

                    ps4.setString(1, updateBrand);
                    ps4.setString(2, updateName);
                    ps4.setInt(3, updateId);

                    int update = ps4.executeUpdate();

                    if (update > 0) {

                        System.out.println("Vehicle Updated Successfully.");

                    } else {

                        System.out.println("Vehicle ID Not Found.");

                    }

                    break;

                case 5:

                    System.out.print("Enter ID to Delete : ");
                    int deleteId = sc.nextInt();

                    String sql5 = "DELETE FROM BIKE WHERE ID=?";

                    PreparedStatement ps5 = con.prepareStatement(sql5);

                    ps5.setInt(1, deleteId);

                    int delete = ps5.executeUpdate();

                    if (delete > 0) {

                        System.out.println("Vehicle Deleted Successfully.");

                    } else {

                        System.out.println("Vehicle ID Not Found.");

                    }

                    break;
                case 6:

                    String sql6 = "SELECT * FROM BIKE";

                    PreparedStatement ps6 = con.prepareStatement(sql6);

                    ResultSet rs6 = ps6.executeQuery();

                    System.out.println("\n--------------------------------------");
                    System.out.printf("%-10s %-15s %-15s\n", "ID", "BRAND", "NAME");
                    System.out.println("--------------------------------------");

                    while (rs6.next()) {

                        System.out.printf("%-10d %-15s %-15s\n",
                                rs6.getInt("ID"),
                                rs6.getString("BRAND"),
                                rs6.getString("NAME"));

                    }

                    break;

                case 7:

                    con.close();
                    sc.close();

                    System.out.println("Thank You...");
                    return;

                default:

                    System.out.println("Invalid Choice.");

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

}
