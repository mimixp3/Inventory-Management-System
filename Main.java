import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    
    InventoryManager manager = new InventoryManager();
    ActivityLog activityLog = new ActivityLog();
    
    while(true){
      System.out.println("\n===== MENU =====");
      System.out.println("1: Add Product");
      System.out.println("2: View All Products");
      System.out.println("3: Update Product");
      System.out.println("4: Delete Product");
      System.out.println("5: Search Product by ID");
      System.out.println("6: Category Amount");
      System.out.println("7: View Activity");
      System.out.println("8: Export Activity Log to CSV");
      System.out.println("9: View Total Inventory Value");
      System.out.println("10: Exit");
      System.out.print("Please enter your choice: ");
            
      int userChoice = -1;
      try {
        userChoice = input.nextInt();
        input.nextLine();
      } catch (InputMismatchException e) {
          System.out.println("Invalid ! Please enter a number between 1-10 ONLY");
          input.nextLine();
        }
            
      switch (userChoice){
        case 1:
          try{
            //creating new products + also requirements
            System.out.println();
                  
            int id;
            String name;
            double price;
            int quantity;
            String category;
            String expiryDate;

            System.out.println("\n--Add Product--");
            System.out.println("Enter your item ID: ");
            id = input.nextInt();
            input.nextLine();
            if (id <= 0) {
              System.out.println("Invalid ! ID has to be positive");
              break;
            }
            //duplicate check
            try {
              manager.searchProduct(id);
              System.out.println("Error! Product with ID " + id + " already exists.");
              break;
            } catch (ItemNotFound e) {
              }

            System.out.println("Enter your item name: ");
            name = input.nextLine();
                  
            System.out.println("Enter item's price: ");
            price = input.nextDouble();
            if (price < 0) {
              System.out.println("Invalid ! Only enter positive price");
              break;
            }
                  
            System.out.println("Enter the quantity: ");
            quantity = input.nextInt();
            input.nextLine();
            if (quantity < 0) {
              System.out.println("Invalid ! Quantity has to be positive.");
              break;
            }

            System.out.println("Enter the category of your item: ");
            category = input.nextLine();
            

            System.out.print("Is your item perishable? (Yes/No): ");
            String isItPerishable = input.nextLine();

            if (!isItPerishable.equalsIgnoreCase("Yes") && !isItPerishable.equalsIgnoreCase("No")) {
              System.out.println("Invalid! Please enter Yes or No only.");
              break;
            }
                  
            if (isItPerishable.equalsIgnoreCase("Yes")) {
              System.out.print("Enter Date of Expiration: ");
              expiryDate = input.nextLine();
                          
              PerishableProducts pp = new PerishableProducts(id, name, price, quantity, category, expiryDate);
              manager.addProduct(pp);
              activityLog.addLog("ADDED," + name + "," + id + "," + quantity + "," + price+","+category);
            } else {
                Products p = new Products(id, name, price, quantity, category);
                manager.addProduct(p);
                activityLog.addLog("ADDED," + name + "," + id + "," + quantity + "," + price+","+category);
              }
          } catch(StorageFull e){
              System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers for ID, price, and quantity.");
                input.nextLine();
              } catch (Exception e) {
                  System.out.println("Something went wrong: " + e.getMessage());
                  input.nextLine();
                }
                break;
                  
              case 2:
                //view product
                manager.viewProducts();
                break;
                    
              case 3:
                try{
                  //updating product things
                  System.out.print("Enter the product ID to be updated : ");
                  int updateId = input.nextInt();
                  input.nextLine();

                  Products productToUpdate = manager.searchProduct(updateId); //searching forst
                  System.out.println("Product found: " + productToUpdate.getProductName());
                  
                  System.out.print("Enter the updated price: ");
                  double newPrice = input.nextDouble();
                  
                  System.out.print("Enter the new quantity: ");
                  int newQuantity = input.nextInt();
                  input.nextLine();

                  System.out.print("Enter the new category: ");
                  String newCategory = input.nextLine();
                  
                  manager.updateProduct(updateId, newPrice, newQuantity, newCategory);
                  activityLog.addLog("UPDATED," + productToUpdate.getProductName() + "," + updateId + ","+ newQuantity+ "," + newPrice+","+newCategory);
                } catch(ItemNotFound e){
                  System.out.println("Error: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter numbers for ID, price, and quantity.");
                    input.nextLine();
                  } catch(Exception e){
                      System.out.println("ItemNotFound. Enter correct ID !");
                      input.nextLine();
                    }
                    break;
                    
              case 4:
                try{
                  //deleting my product part
                  System.out.println("DEBUG: Starting delete..");
                  System.out.print("Enter the Product ID to be deleted: ");
                  int deleteId = input.nextInt();
                  input.nextLine();
                  
                  System.out.println("DEBUG: Searching for product..");
                  Products productToDelete = manager.searchProduct(deleteId);
                  System.out.println("DEBUG: Product found yay!");
                  System.out.print("Are you sure you want to delete '"+productToDelete.getProductName()+"' (ID: " + deleteId + ") ? (Yes/No): ");
                  String confirming = input.nextLine();

                  if (confirming.equalsIgnoreCase("Yes")) {
                    manager.deleteProduct(deleteId);
                    activityLog.addLog("DELETED," + productToDelete.getProductName() + "," + deleteId + ",,");
                  } else {
                      System.out.println("Item removal cancelled ! ");
                    }
                } catch (ItemNotFound e){
                    System.out.println("Error: " + e.getMessage());
                  } catch (InputMismatchException e) {
                      System.out.println("Invalid input! Please enter a number for ID.");
                      input.nextLine();
                    } catch(Exception e){
                        System.out.println("ItemNotFound. Enter the correct ID !");
                        //System.out.println("DEBUGGING: Exception was: " + e.getMessage());
                        input.nextLine();
                      }
                      break;

              case 5:
                try{
                  //search for products
                  System.out.print("Enter the Product ID to search: ");
                  int searchId = input.nextInt();
                  
                  Products foundProduct = manager.searchProduct(searchId);
                  System.out.println("Product has been found: ");
                  foundProduct.displayProductInfo();
                } catch (ItemNotFound e){
                  System.out.println("Error: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number for ID.");
                    input.nextLine();
                  }catch(Exception e){
                    System.out.println("Invalid ! Enter the correct ID");
                    input.nextLine();
                  }
                  break;
              
              case 6:
                manager.countByCategory();
                break;

              case 7:
                try {
                  //viewing activitylogs
                  System.out.print("How many logs would you like to view? (Enter 0 for everything): ");
                  int howMuch = input.nextInt();
                  
                  if (howMuch == 0) {
                      activityLog.viewLog(activityLog.getCount());  //show all
                  } else {
                      activityLog.viewLog(howMuch); //show last x ones
                  }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    input.nextLine();
                  }
                  break;

              case 8:
                //export
                if (activityLog.getCount() == 0) {
                  System.out.println("You have no activities to export !");
                  break;
                }
                
                try {
                    PrintWriter w = new PrintWriter("activity_log.csv");
                    w.println("Action,Product Name,Product ID,Quantity,Price,Category");

                    int i = 0;
                    while (i < activityLog.getCount()) {
                        w.println(activityLog.getLog(i));
                        i++;
                    }
                    w.close();
                    System.out.println("Exported ! You can now find 'activity_log.csv' in your project folder !");

                } catch (java.io.FileNotFoundException e) {
                    System.out.println("Error: Could not create file!");
                  } catch (Exception e){
                    System.out.println("Error exporting: " + e.getMessage());
                    }
                    break;
              case 9:
                manager.getTotalWorth();
                break;
    
              case 10:
                //leave
                System.out.println("Great Session.");
                System.out.println("Thank you for using our Inventory Management System !");
                System.out.println("Have a great day ! :D");
                break;

              default:
                if (userChoice != -1) {
                  System.out.println("Invalid choice ! Try again.");
                }
                
            }
            if (userChoice==10){
              break;
            }
            
    }
    input.close();
  }
}
