public class Feedback {
    private String pizzaName;
    private int rating;
    private String comments;

     //Builder Pattern 

    public Feedback(String pizzaName, int rating, String comments) {
        this.pizzaName = pizzaName;
        this.rating = rating;
        this.comments = comments;
    }

    public void displayFeedback() {
        System.out.println("Feedback for " + pizzaName + ":");
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comments: " + comments);
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

}//end feedback
