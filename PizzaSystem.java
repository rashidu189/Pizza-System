import java.time.LocalDate;  
import  java.util.*;

import javax.xml.catalog.CatalogFeatures.Feature;

class PizzaSystem
{

/* This module serves as a control class to coordinate all pizza orderings functions. This module stores customers, pizzas and orderings in array list data structures.
 A menu is used to select various system functions      */ 

  List<Customer> custlist;
  List<Pizza>  pizzalist;
  List<PizzaOrdering>  orderinglist;  
  List<SeasonalSpecial> seasonalSpecials;

  // Feedback storage for each pizza
  private Map<Integer, List<Feedback>> pizzaFeedbacks; 
 
 public  PizzaSystem()
 {
 //create five containers for customers, feedback, seasonal specials, pizzas & orderings
  custlist=new ArrayList<Customer>();
  pizzalist = new ArrayList<Pizza>();
  orderinglist = new ArrayList<PizzaOrdering>();
  pizzaFeedbacks = new HashMap<>();
  seasonalSpecials = new ArrayList<SeasonalSpecial>();
  
  } 

// Factory Pattern
 public  void addPizzas()  // this is like add pizzas
 {
   Pizza p1 = new Pizza (1,"BBQ Chicken Pizza", "Barbecue sauce base, chicken, red onions, and cilantro", 2000,0,0);
   pizzalist.add(p1);
   Pizza p2 = new Pizza (2, "Seafood Pizza" , "Shrimp, clams, or smoked salmon with a creamy sauce", 2500,0,0);
   pizzalist.add(p2);
   Pizza p3 = new Pizza (3, "Vegan Pizza" , "Dairy-free cheese and plant-based toppings", 4000,0,0);
   pizzalist.add(p3);
   Pizza p4 = new Pizza (4,"Pepperoni Pizza", "A favorite for meat lovers, this pizza is topped with tomato sauce, mozzarella, and plenty of spicy pepperoni slices", 2200,0,0);
   pizzalist.add(p4);
   Pizza p5 = new Pizza (5, "Margherita Pizza" , "A classic pizza topped with tomato sauce, fresh mozzarella, and basil leaves. Simple yet delicious", 2100,0,0);
   pizzalist.add(p5);
   Pizza p6 = new Pizza (6, "Hawaiian Pizza" , "A unique combination of tomato sauce, mozzarella, ham, and pineapple, offering a sweet and savory taste", 3100,0,0);
   pizzalist.add(p6);
   System.out.println("*** Pizzas were added successfully ***");
  }

  public  Pizza  getPizzaByIndex(int x)
  {
    if  (x==1)
        return  pizzalist.get(0);
    else
    if  (x==2) 
        return  pizzalist.get(1);
    else
    if  (x==3) 
        return  pizzalist.get(2);
    else
    if  (x==4) 
        return  pizzalist.get(3);
    else
    if  (x==5) 
        return  pizzalist.get(4);
    else
    if  (x==6) 
        return  pizzalist.get(5);
    else            

     return  null;
   }



   public void registerCustomer() {
    // This function adds a new customer to the system
    Scanner obj = new Scanner(System.in); // Make scanner object for data input
    System.out.print("Enter Customer ID [NIC/PassportNo]: ");
    String id = obj.nextLine();
    
    // Check for duplicate customer ID
    for (Customer existingCustomer : custlist) {
        if (existingCustomer.getCustID().equalsIgnoreCase(id)) { 
            System.out.println("** Error: A customer with this ID already exists! **");
            return;
        }
    }
    
    System.out.print("Enter Name: ");
    String na = obj.nextLine();
    System.out.print("Enter Address: ");
    String add = obj.nextLine();
    System.out.print("Enter Contact No: ");
    String contact = obj.nextLine();
    System.out.print("Enter Email: ");
    String email = obj.nextLine();

    // Add new customer to the list
    Customer c = new Customer(id, na, add, contact, email);
    custlist.add(c); // Add customer to the list
    System.out.println("*** Customer Registered Successfully ***");
}


 public void  displayCustomers() //display  customers
 {
  System.out.println("*** List of Customers ***");     
   for(Customer  c: custlist)
   {  
      c.showDetails(); 
    } 
  System.out.println("*** End of Customers ***");    
 } //end display customers


public void  displayPizzas()  //display  pizzas
 {
 System.out.println("*** List of Pizzas ***"); 
 for(Pizza p: pizzalist)
   {  
      p.displayPizza(); 
    } 
  System.out.println("*** End of Pizzas ***");     
 } //end display pizzas


public void  displayPizzaOrderings() //display orderings line by line
 {
  System.out.println("*** List of Pizza Orderings ***"); 
  for(PizzaOrdering o: orderinglist)
   {  
      o.display(); 
    } 

  System.out.println("*** End of Orderings ***");  
 } //end display orderings




 public Customer searchCustomer(String  searchkey)
 {
 //Search for a Customer by ID 
  
  for(Customer  c: custlist)
  {  
    if (searchkey.equals(c.getCustID()))
           return  c; 
  }    
  return  null;
 }

public PizzaOrdering searchOrderingID(int  searchkey)
 {
 //Search for a given orderingid 
  
  for(PizzaOrdering  o: orderinglist)
  {  
    if (searchkey == o.getorderingid())
           return  o; 
  }    
  return  null;
 }


 public  void pizzaOrdering()  // this is like placing pizza order
 {
   LocalDate today = LocalDate.now();  
   System.out.println("Pizza Ordering Date: " + today);  
   Scanner obj = new Scanner(System.in);//make scanner object for data input
   System.out.print ("Enter Ordering ID:  "); // note: this is like order ID

   int bid = obj.nextInt(); 

    // Check for duplicate order ID
    for (PizzaOrdering existingOrder : orderinglist) {
        if (existingOrder.getorderingid() == bid) { 
            System.out.println("** Error: An order with this ID already exists! **");
            return;
        }
    }

   System.out.print("Enter Customer ID [NIC/PassportNo]: ");
   obj = new Scanner (System.in);
   String  cid = obj.nextLine();  
   Customer  c = searchCustomer(cid);
   if  (c == null)
        System.out.println("Customer does not exist");
   else
   {
     System.out.println("Customer Details");
     System.out.println("---------------");
     c.showDetails(); // show customer details
    
     System.out.println("Select a Pizza");
     displayPizzas(); //list all pizzas
     System.out.print("Enter Pizza ID [1|2|3]4|5|6]:");
     int pizzaid = obj.nextInt();
     Pizza p = getPizzaByIndex(pizzaid); //retrieve chosen pizza object

      // Apply Seasonal Special and display discount information
      int finalPizzaCosts = applySeasonalSpecial(p); 
      if (p.getDiscountPercentage() > 0) {
          System.out.println("** Seasonal Special Applied! **");
      }

     p.displayPizzaDetails(); // show details of chosen pizza
     PizzaOrdering po1 = new PizzaOrdering(bid,pizzaid,p.getPizzaName(),cid,p.getCost(),today); 
     
     // Decorator Pattern & Chain of Responsibility Pattern

     System.out.println("--- Extra Customizations ---");
     System.out.print("Crusts [Y/N] :");
     String answer;
     obj = new Scanner(System.in);
     answer = obj.nextLine();
     if  (answer.equals("Y") || answer.equals("y"))
     {
         PizzaCustomization pc1 = new PizzaCustomization (1,"Crusts", 2500); 
         po1.addPizzaCustomization(pc1); 
     }
     System.out.print("Sauces  [Y/N] :");
     answer = obj.nextLine();
     if  (answer.equals("Y") || answer.equals("y"))
     {     
      PizzaCustomization pc2 = new PizzaCustomization (2,"Sauces", 1500);
         po1.addPizzaCustomization(pc2);
     }  
     System.out.print("Toppings [Y/N] :");
     answer = obj.nextLine();
     if  (answer.equals ("Y") || answer.equals("y"))
     {
      PizzaCustomization pc3 = new PizzaCustomization (3,"Toppings", 3500); 
     	 po1.addPizzaCustomization(pc3);	
     }
     System.out.print("Cheese [Y/N] :");
     answer = obj.nextLine();     
     if  (answer.equals ("Y") || answer.equals("y"))
     {
      PizzaCustomization pc4 = new PizzaCustomization (4,"Cheese", 3500); 
     	 po1.addPizzaCustomization(pc4);	
     }

      // Delivery or Pickup Option
      System.out.println("Select an option: ");
      System.out.println("1. Delivery");
      System.out.println("2. Pickup");
      System.out.print("Enter your choice [1/2]: ");
      int choice = obj.nextInt();
      obj.nextLine(); // Consume newline character

      if (choice == 1) {
          System.out.print("Enter Delivery Cost: ");
          int deliverCost = obj.nextInt();
          obj.nextLine(); // Consume newline character
          po1.setDeliverCost(deliverCost);
      } else if (choice == 2) {
          System.out.println("You selected Pickup. Delivery cost will not be added.");
          po1.setDeliverCost(0);
      } else {
          System.out.println("Invalid choice. Defaulting to Pickup.");
          po1.setDeliverCost(0);
      }    
    
     System.out.print("You want to name this pizza for future reference [Y/N]:");
     obj = new Scanner(System.in);
     answer = obj.nextLine();
     if  (answer.equals ("Y") || answer.equals("y"))
     {
       System.out.print("Enter Favorite name for the pizza :");
       String sn = obj.nextLine();
       po1.setFavoriteName(sn);	
     }    


        // Apply Seasonal Special if available
        int finalPizzaCost = applySeasonalSpecial(p); // Check and apply seasonal discount if applicable
        po1.setFinalAmount(finalPizzaCost); // Set final amount after discount

     System.out.println("*** Review of the Complete Pizza ***");
     po1.reviewPizza(); // display full details of the pizza ordering
     int finalPizzasCost = applySeasonalSpecial(p); 
     if (p.getDiscountPercentage() > 0) {
         System.out.println("** Seasonal Special Applied! **");
         System.out.printf("Discount: %.2f%%\n", p.getDiscountPercentage());
         System.out.println("Discounted Price: " + finalPizzaCosts);
     }

     // Strategy Pattern
     int finalamt = po1.getFinalAmount();
     System.out.println ("Total Payment Due "+finalamt);
     System.out.println ("Payment Method");
     System.out.println ("1 - Paypal");
     System.out.println ("2 - Credit Card");
     System.out.print   ("Enter Choice :");
     int  opt = obj.nextInt();
     if  (opt == 1 )
     {
        System.out.print("Enter Customer Email:");
        obj = new Scanner (System.in);
        String  email  = obj.nextLine();
        System.out.print("Enter Customer Password:");
        String  pw  = obj.nextLine();
        PaypalStrategy  pp = new PaypalStrategy(email, pw);
        pp.pay(finalamt);
      }
      else   
      if  (opt == 2 )
      {
        System.out.print("Enter Credit Card No:");
        int  ccno = obj.nextInt();
        CreditCardStrategy  pp = new CreditCardStrategy(ccno);
        pp.pay(finalamt);
      }   

     
     System.out.print("Confirm Pizza [Y/N]:");
     obj = new Scanner (System.in);
     answer = obj.nextLine();
     if  (answer.equals ("Y") || answer.equals("y"))
     {
           orderinglist.add(po1); 
           System.out.println("Pizza Ordering Created !");
           System.out.println("*********************");

           // Observer Pattern

          // Add Points (Assuming 10 points for every pizza order)
          c.addPoints(10);
          System.out.println("You have earned 10 points for this ordering!");
          

      } else{
        System.out.println("Order Creation Cancelled");
      } 
    }
  }
 
  // Observer Pattern & State Pattern
  public  void  updateStatus()
  {
   System.out.print ("Enter Ordering ID:  "); // note: this is like order ID
   Scanner  obj = new Scanner (System.in);
   int oid = obj.nextInt();  
   PizzaOrdering  po1 = searchOrderingID(oid);
   if  (po1 == null)
        System.out.println("Ordering does not exist");
   else
   {
     System.out.println("Ordering Details");
     System.out.println("---------------");
     po1.reviewPizza(); // show Ordering details  
     System.out.println("Choose Pizza Status:");
     System.out.println("1 - Started");
     System.out.println("2 - Completed");
     System.out.println("3 - Cancelled");
     System.out.print("Choose Pizza Status:");
     int opt = obj.nextInt();
     if (opt == 1)
         po1.setStatus("STARTED");
     else if (opt == 2)
         po1.setStatus("COMPLETED");
     else if (opt == 3)
         po1.setStatus("CANCELLED");
     
    // Print confirmation message with updated details
    System.out.println("\nOrder Status Updated Successfully!");
    System.out.println("Updated Ordering Details:");
    System.out.println("-------------------------");
    po1.reviewPizza();    

     
     }
  }  //end update status
 

// Add a method to retrieve favorite pizza by customer ID 
public PizzaOrdering getFavoritePizza(String customerId) {
  for (PizzaOrdering po : orderinglist) {
      if (po.getCustomerId().equals(customerId) && po.getFavoriteName() != null) {
          return po;  // Found the favorite pizza
      }
  }
  return null; // No favorite pizza found
}


public PizzaOrdering searchOrderingName(String searchkey) {
  // Search for a given ordering name
  for (PizzaOrdering po : orderinglist) {
      if (searchkey.equals(po.getFavoriteName())) {
          return po;  // Return the matching ordering
      }
  }
  return null;  // No matching ordering found
}

private int generateNewOrderId() {
  return orderinglist.size() + 1; // Incremental Order ID based on the size of the ordering list
}



public void reOrderFavoritePizza() {
  Scanner obj = new Scanner(System.in);

  // Generate a New Order ID
  System.out.print("Generating New Order ID...");
  int newOrderId = generateNewOrderId(); // Implement this method to generate a unique Order ID
  System.out.println("New Order ID: " + newOrderId);

  // Input Customer ID (NIC/Passport No.)
  System.out.print("Enter Customer ID (NIC/Passport No.): ");
  String cid = obj.nextLine();

  // Search for the customer
  Customer c = searchCustomer(cid);
  if (c == null) {
      System.out.println("Customer does not exist.");
      return;
  }

  // Display Customer Details
  System.out.println("Customer Details:");
  System.out.println("---------------");
  c.showDetails(); // Show customer details

  // Input Special Name of the Past Pizza
  System.out.print("Enter Special Name of the Favorite Pizza: ");
  String specialName = obj.nextLine();

  // Search for the past pizza ordering by special name
  PizzaOrdering pizzaOrdering = searchOrderingName(specialName);
  if (pizzaOrdering == null) {
      System.out.println("No ordering found with the special name '" + specialName + "'.");
      return;
  }

  // Create a New PizzaOrdering Object for Reordering
  PizzaOrdering newPizzaOrdering = new PizzaOrdering(
      newOrderId,
      pizzaOrdering.getPizzaId(),
      pizzaOrdering.getPizzaName(),
      cid,
      pizzaOrdering.getPizzaCost(),
      LocalDate.now()
  );

  // Copy customizations from the original order
  for (PizzaCustomization customization : pizzaOrdering.getCustomizations()) {
      newPizzaOrdering.addPizzaCustomization(customization);
  }

  // Display details of the past order for review
  System.out.println("*** Review of the Favorite Pizza ***");
  newPizzaOrdering.reviewPizza(); // Display the past order details

  // Proceed to Payment
  int finalamt = newPizzaOrdering.getFinalAmount();
  System.out.println("Total Payment Due: " + finalamt);

  // Strategy Pattern

  // Select Payment Method
  System.out.println("Payment Method:");
  System.out.println("1 - Paypal");
  System.out.println("2 - Credit Card");
  System.out.print("Enter Choice: ");
  int opt = obj.nextInt();

  if (opt == 1) {
      System.out.print("Enter Customer Email: ");
      obj.nextLine(); // Consume newline
      String email = obj.nextLine();
      System.out.print("Enter Customer Password: ");
      String pw = obj.nextLine();
      PaypalStrategy p = new PaypalStrategy(email, pw);
      p.pay(finalamt);
  } else if (opt == 2) {
      System.out.print("Enter Credit Card No: ");
      int ccno = obj.nextInt();
      CreditCardStrategy p = new CreditCardStrategy(ccno);
      p.pay(finalamt);
  } else {
      System.out.println("Invalid Payment Option.");
      return;
  }

  // Confirm the Reordering
  System.out.print("Confirm Reordering [Y/N]: ");
  String answer = obj.nextLine();
  if (answer.equalsIgnoreCase("Y") || answer.equals("y")) {
      System.out.println("Pizza Reordered Successfully!");
      // Add the reordered pizza to the ordering list
      orderinglist.add(newPizzaOrdering);
      // Observer Pattern

      // Add loyalty points (if applicable)
      c.addPoints(10);
      System.out.println("You have earned 10 points for this order!");
  } else if (answer.equalsIgnoreCase("N") || answer.equals("n")) {
      System.out.println("Reordering Cancelled.");
  }
}


    // Method to add feedback for a pizza
    public void addFeedback() {
      Scanner obj = new Scanner(System.in);

      System.out.println("*** Feedback System ***");
      System.out.print("Enter the Pizza ID you want to rate: ");
      int pizzaId = obj.nextInt();
      obj.nextLine();  // Consume newline character

      Pizza pizza = getPizzaByIndex(pizzaId); // Get pizza by ID
      if (pizza != null) {
          System.out.println("You are rating: " + pizza.getPizzaName());
          System.out.print("Enter Rating (1-5): ");
          int rating = obj.nextInt();
          obj.nextLine(); // Consume newline character
          System.out.print("Enter your comments: ");
          String comments = obj.nextLine();

          // Create feedback object
          Feedback feedback = new Feedback(pizza.getPizzaName(), rating, comments);

          // Add feedback to the list for this pizza
          pizzaFeedbacks.putIfAbsent(pizzaId, new ArrayList<>());
          pizzaFeedbacks.get(pizzaId).add(feedback);
          System.out.println("Thank you for your feedback!");
      } else {
          System.out.println("Invalid Pizza ID!");
      }
  }

  // Method to display feedback for a specific pizza
  public void displayFeedbackForPizza(int pizzaId) {
      List<Feedback> feedbacks = pizzaFeedbacks.get(pizzaId);
      if (feedbacks != null && !feedbacks.isEmpty()) {
          System.out.println("*** Feedback for " + pizzalist.get(pizzaId - 1).getPizzaName() + " ***");
          for (Feedback feedback : feedbacks) {
              feedback.displayFeedback();
          }
          System.out.println("*** End of Feedbacks ***");
      } else {
          System.out.println("No feedbacks for this pizza yet.");
      }
  }

  // Method to display feedback for all pizzas
  public void displayAllFeedbacks() {
      System.out.println("*** All Customer Feedbacks ***");
      for (Map.Entry<Integer, List<Feedback>> entry : pizzaFeedbacks.entrySet()) {
          int pizzaId = entry.getKey();
          displayFeedbackForPizza(pizzaId);  // Display feedback for each pizza
      }
  }



    // Method to add a new seasonal special
    public void addSeasonalSpecial() {
      Scanner obj = new Scanner(System.in);
      System.out.println("*** Add Seasonal Special ***");

      System.out.print("Enter Promotion Name: ");
      String promotionName = obj.nextLine();

      System.out.print("Enter Description: ");
      String description = obj.nextLine();

      System.out.print("Enter Discount Percentage (e.g., 10 for 10%): ");
      double discountPercentage = obj.nextDouble();

      System.out.print("Enter Start Date (YYYY-MM-DD): ");
      String startDateStr = obj.next();
      LocalDate startDate = LocalDate.parse(startDateStr);

      System.out.print("Enter End Date (YYYY-MM-DD): ");
      String endDateStr = obj.next();
      LocalDate endDate = LocalDate.parse(endDateStr);

      System.out.print("Enter Applicable Pizza ID: ");
      int applicablePizza = obj.nextInt();

      // Create new SeasonalSpecial object and add it to the list
      SeasonalSpecial special = new SeasonalSpecial(promotionName, description, discountPercentage, startDate, endDate, applicablePizza);
      seasonalSpecials.add(special);

      System.out.println("Seasonal Special added successfully!");
  }

  public void displayActiveSeasonalSpecials() {
      LocalDate today = LocalDate.now();
      System.out.println("*** Active Seasonal Specials ***");

      boolean activeFound = false;
      for (SeasonalSpecial special : seasonalSpecials) {
          if (special.isActive(today)) {
              special.displayPromotionDetails();
              activeFound = true;
          }
      }

      if (!activeFound) {
          System.out.println("No active seasonal specials at the moment.");
      }
  }

  public int applySeasonalSpecial(Pizza pizza) {
      LocalDate today = LocalDate.now();
      for (SeasonalSpecial special : seasonalSpecials) {
          if (special.isActive(today) && pizza.getPizzaId() == special.getApplicablePizza()) {
              // Apply discount to pizza price
              double discountedPrice = pizza.getCost() * (1 - special.getDiscountPercentage() / 100.0);
              int finalPrice = (int) discountedPrice; // Truncate decimals
            pizza.setDiscountPercentage(special.getDiscountPercentage());
            pizza.setFinalPrice(discountedPrice);
              return finalPrice;

          }
      }
          // No discount case
      pizza.setFinalPrice(pizza.getCost()); // Final price is the original cost
      return (int) pizza.getCost(); // No discount applied
  }



 public static  void  main(String  arg[])
 {
    
   PizzaSystem   ps = new PizzaSystem();
   while (true)
   {
     
      System.out.println("Pizza Ordering System");
      System.out.println("1 - Add Pizzas");
      System.out.println("2 - Register Customers");
      System.out.println("3 - Display Customers");
      System.out.println("4 - Pizza Ordering");
      System.out.println("5 - Display Ordering");  
      System.out.println("6 - Update Pizza Status");
      System.out.println("7 - Re-Order Past Pizza Package");
      System.out.println("8 - Leave Feedback"); 
      System.out.println("9 - Display All Feedbacks"); 
      System.out.println("10 - Add Seasonal Special");
      System.out.println("11 - Display Active Seasonal Specials");      
      System.out.println("12 - Exit");
      Scanner obj = new Scanner(System.in);
      System.out.print("Enter Choice :");
      int choice = obj.nextInt(); 
      if (choice == 1)
            ps.addPizzas();
      else if (choice == 2)
            ps.registerCustomer();
      else if (choice == 3)
            ps.displayCustomers();
      else if  (choice==4)
            ps.pizzaOrdering();     
      else if (choice==5)
            ps.displayPizzaOrderings();  
      else if  (choice == 6)
            ps.updateStatus();
      else if  (choice == 7)
            ps.reOrderFavoritePizza();
      else if (choice == 8)
            ps.addFeedback(); 
      else if (choice == 9)
            ps.displayAllFeedbacks(); 
      else if (choice == 10)
            ps.addSeasonalSpecial(); 
      else if (choice == 11)
            ps.displayActiveSeasonalSpecials();           
      else if (choice == 12)
          break;
      else
            System.out.println("Invalid choice, please try again.");
     

     } //end while 
    
   
   }  //end main

} //end pizza system