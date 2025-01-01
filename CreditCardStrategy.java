// Strategy Pattern
public class CreditCardStrategy extends PaymentStrategy 
{

   private int cardNumber;

    //Builder Pattern 
   public CreditCardStrategy(int ccNum)
    {
      this.cardNumber=ccNum;
    }

    public void pay(int  amt) 
    {
      System.out.println(amt+" paid with credit card");
    }

}//end Credit Card Strategy 



	