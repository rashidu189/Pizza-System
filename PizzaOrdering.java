import java.time.LocalDate;  
import  java.util.*;
class  PizzaOrdering 
{
   private  int orderingid;   
   private  int  pizzaid;
   private  String pizzaname;
   private  String  customer_id; 
   private  int  pizzacost ;
   private  LocalDate  orderingdate; 
   private  String  status ;
   private  String favoritename ; 
   private  int DeliverCost;
   private  int finalamount;
   private  List<PizzaCustomization> pizzaCustomizationlist;
   private Pizza pizza;

   // List of observers (customers)
   private List<Observer> observers;

    //Builder Pattern 
public PizzaOrdering(int  oid, int pid, String pn, String cid, int cost,LocalDate d)
 {
       orderingid = oid ;
       pizzaid = pid ; 
       pizzaname  = pn;
       customer_id =  cid;
       pizzacost = cost ;  
       orderingdate = d ;
       status = "PLANNED";
       DeliverCost = 0 ;
       favoritename = null ;
       pizzaCustomizationlist = new ArrayList();
       this.finalamount = pizzacost;
       observers = new ArrayList<>();

  }

 public  void  addPizzaCustomization ( PizzaCustomization  pc )
 {
    pizzaCustomizationlist.add(pc);
         
 }

 public  void  setStatus(String st)
 {
         status =  st; 
         notifyObservers();
  }


  public  void  setDeliverCost( int  dc )
  {
        DeliverCost =  dc ;
   } 

    public  void  setFavoriteName( String  sn)
   {
        favoritename = sn ;
    }   

    public String getFavoriteName() {
      return favoritename;
  }

 public  void  display()
 {
  System.out.println("Ordering ID :"+orderingid);
  System.out.println("Customer ID [NIC/Passport]:"+customer_id);
  System.out.println("Pizza Name :"+pizzaname);
  System.out.println("Ordering Date :"+orderingdate);
  System.out.println("------------------");
  }
  

   public  int  getorderingid()
   {
      return  orderingid;
   }


 public  void  reviewPizza()
 {


  System.out.println("Ordering ID :"+orderingid);
  System.out.println("Customer ID [NIC/Passport]:"+customer_id);
  System.out.println("Pizza ID :"+pizzaid);
  System.out.println("Pizza Name :"+pizzaname);
  System.out.println("Pizza Cost :"+pizzacost);
  System.out.println("Delivery Cost :"+DeliverCost);
  System.out.println("Ordering Date :"+orderingdate);
  System.out.println("Favorite Name of Pizza Ordering :"+favoritename);  
  System.out.println("Pizza Status  :"+status);
  int tot = 0;
  for (PizzaCustomization pc : pizzaCustomizationlist) {
      pc.displayPizzaCustomization();
      tot = tot + pc.getCost();
  }

  // Calculate the new final amount
  finalamount = DeliverCost + tot + pizzacost;

  System.out.println("Total cost of the pizza package " + finalamount);
  }

  public  int  getFinalAmount()
  {
      return  finalamount;
   }


   public void setFinalAmount(int finalAmount) {
      this.finalamount = finalAmount;
  }


  public String getCustomerId(){
    return this.customer_id;
  }


  public int getPizzaId() {
   return this.pizzaid;
}


public String getPizzaName() {
   return this.pizzaname;
}

public int getCost() {
   return this.finalamount;
}


    public int getDeliverCost() {
      return this.DeliverCost;
  }


  public int getPizzaCost() {
   return pizzacost;
}

public void setPizzaCost(int cost) {
   this.pizzacost = cost;
}


  public List<PizzaCustomization> getCustomizations() {
   return pizzaCustomizationlist;  
}


  public void addObserver(Observer observer) {
   observers.add(observer);
}

public void removeObserver(Observer observer) {
   observers.remove(observer);
}

private void notifyObservers() {
   for (Observer observer : observers) {
       observer.update("Order ID " + orderingid + " status updated to: " + status);
   }
}




}//end pizza ordering



