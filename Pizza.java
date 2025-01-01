import  java.util.*;
class  Pizza
{
   private  int pizzaid;
   private  String pizzaname, description ;
   private  int  cost;
   private double discountPercentage = 0.0; 
   private double finalPrice; 

     //Builder Pattern 

    public  Pizza(int pid, String pn, String  des,  int  cs, double dp, double fp)
    {
       pizzaid = pid;
       pizzaname = pn;
       description = des;
       cost =  cs;
       discountPercentage = dp;
       finalPrice = fp;
       
     }

    public  void  displayPizza()
    {
       System.out.println(pizzaid+"    "+pizzaname);
    }


    public  String  getPizzaName()
    {
       return  pizzaname;
     }

    public  int  getCost()
    {
       return  cost;
    }  

    public int getPizzaId(){
      return pizzaid;
    }

    public  void  displayPizzaDetails()
    {
       System.out.println("Pizza ID     "+pizzaid);
       System.out.println("Pizza Name   "+pizzaname);
       System.out.println("Description "+description);
       System.out.println("Pizza Price        "+cost);
      if (discountPercentage > 0) {
         System.out.println("Discount Percentage: " + discountPercentage + "%");
         System.out.println("Total Price after Discount: " + finalPrice);
     } else {
         System.out.println("No Discount Applied");
         System.out.println("Price: " + finalPrice);
     }

    }  
       
    public double getDiscountPercentage() {
      return discountPercentage;
  }

  public void setDiscountPercentage(double discountPercentage) {
      this.discountPercentage = discountPercentage;
  }

  public double getFinalPrice() {
      return finalPrice;
  }

  public void setFinalPrice(double finalPrice) {
      this.finalPrice = finalPrice;
  }

}//end pizza



