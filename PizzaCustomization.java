// Decorator Pattern & Chain of Responsibility Pattern
public class PizzaCustomization {
    
    private  int pizzaCustomizationid;
    private  String pizzaCustomizationname;
    private  int  cost;
    
      //Builder Pattern 
     public  PizzaCustomization(int pcid, String pcn,  int  cs)
     {
         pizzaCustomizationid = pcid;
         pizzaCustomizationname = pcn;
         cost =  cs;     
     }
 
     public  void  displayPizzaCustomization()
     {
        System.out.println(pizzaCustomizationid+"    "+pizzaCustomizationname);
     }
  
     public  String  getPizzaCustomizationName()
     {
        return  pizzaCustomizationname;
     }
 
     public  int  getCost()
     {
        return  cost;
     }  
 
     public  void  displayPizzaCustomizationDetails()
     {
        System.out.println("Pizza Customization ID     "+pizzaCustomizationid);
        System.out.println("Pizza Customization Name   "+pizzaCustomizationname);
        System.out.println("Cost        "+cost);
     }  
} //end pizza customization 
