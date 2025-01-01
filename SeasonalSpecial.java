import java.time.LocalDate;

public class SeasonalSpecial {
    private String promotionName;
    private String description;
    private double discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private int applicablePizza;  

     //Builder Pattern 

    public SeasonalSpecial(String promotionName, String description, double discountPercentage, LocalDate startDate, LocalDate endDate, int applicablePizza) {
        this.promotionName = promotionName;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicablePizza = applicablePizza;
    }

    // Check if the promotion is currently active
    public boolean isActive(LocalDate currentDate) {
        return (currentDate.isEqual(startDate) || currentDate.isAfter(startDate)) && (currentDate.isEqual(endDate) || currentDate.isBefore(endDate));
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public int getApplicablePizza() {
        return applicablePizza;
    }

    public void displayPromotionDetails() {
        System.out.println("Promotion Name: " + promotionName);
        System.out.println("Description: " + description);
        System.out.println("Discount: " + discountPercentage + "% off");
        System.out.println("Applicable to: " + applicablePizza);
        System.out.println("Valid From: " + startDate);
        System.out.println("To: " + endDate);
    }

}//end Seasonal Special
