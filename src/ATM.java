import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class ATM {
    public static void main(String[]args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","yourpassword");
            Statement stmt=con.createStatement();

            Scanner sc=new Scanner(System.in);
            System.out.println("Welcome to all in our ATM");
            System.out.println("Enter your pin number:");
            int pin= sc.nextInt();

            ResultSet rs= stmt.executeQuery("SELECT * FROM list WHERE ac_no=" +pin);

            String name =null;
            int count=0;
            int balance=0;
            while (rs.next()){
                name= rs.getNString(3);
                balance=rs.getInt(4);
                count++;
            }


            int choice;
            int amount=0;
            int take_amount=0;

            if(count>0){
                System.out.println("Hello"+name);
                while (true){
                    System.out.println("Press  1  to Check balance");
                    System.out.println("Press  2  to Add amount");
                    System.out.println("Press  3  to Take amount");
                    System.out.println("Press  4  to Print the receipt");
                    System.out.println("Press  5  to Exit");

                    System.out.println();
                    System.out.println("-Enter your Choice-");
                    choice=sc.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Your balance is :"+balance);
                            break;
                        case 2:
                            System.out.println("How much amount do you want to add:");
                            amount= sc.nextInt();
                            balance=balance+amount;
                            int bal=stmt.executeUpdate("UPDATE list SET balance ="+ balance +" WHERE ac_no ="+pin);
                            System.out.println("Successfully added now your current balance is:"+balance);
                            break;
                        case 3:
                            System.out.println("How much amount do you want to take:");
                            take_amount=sc.nextInt();
                            if(take_amount>balance){
                                System.out.println("Your balance is insufficient");
                            }
                            else{
                                balance = balance - take_amount;
                                int sub=stmt.executeUpdate("UPDATE list SET balance ="+ balance +" WHERE ac_no ="+pin);
                                System.out.println("Your amount is successfully changed "+balance);
                            }

                            break;
                        case  4:
                            System.out.println("Thanks for coming");
                            System.out.println("Your current balance is :"+balance);
                            System.out.println("Amount taken :" + take_amount);
                            break;


                    }
                    if(choice==5){
                        break;
                    }
                }
            }
            else{
                System.out.println("Wrong pin number");
            }
            con.close();
            sc.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }

}
