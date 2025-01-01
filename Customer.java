import java.util.ArrayList;
import java.util.List;

class  Customer implements Observer
{
   private  String  custid;
   private  String  name, address, contact, email;
   private  int  points;
   private List<PizzaOrdering> favoriteOrders;

    //Builder Pattern 
   public  Customer (String  cid, String na, String add, String con, String em)
   {
      custid = cid ;
      name  = na;
      address = add;
      contact = con;
      email = em;
      points = 0 ;
      this.favoriteOrders = new ArrayList<>();
    }

   public  void  addPoints( int  p )
   {
     points = points + p ;
   }

   public List<PizzaOrdering> getFavoriteOrders() {
    return favoriteOrders;
   }

   public  String   getCustID()
   {
      return  custid;
    }
  


    public  void   showDetails()
    {
      System.out.println("Customer ID    :"+custid);
      System.out.println("Name           :"+name);
      System.out.println("Address        :"+address);
      System.out.println("Contact No     :"+contact);
      System.out.println("Email          :" + email);
      System.out.println("Loyalty Points :"+points);
         
    }

    // Implement the update method to receive notifications
    @Override
    public void update(String message) {
        System.out.println("Notification for Customer " + name + ": " + message);
    }


}//end Customer     

















