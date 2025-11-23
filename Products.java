public class Products {
  private int productId;
  private String productName;
  private double price;
  private int quantity;
  private String category;
  
  
  Products(int productId, String productName, double price, int quantity, String category) {
    this.productId = productId;
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
    this.category = category;
  }
  
  public int getProductId(){
    return productId;
    
  }
  
  public String getProductName() {
    return productName;
  }
  
  public double getPrice(){
    return price;
    
  }
  
  public int getQuantity(){
    return quantity;
  }
  public String getCategory(){
    return category;
  }

  public void setPrice(double price) {
    this.price = price;
  }
    
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
    
  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setCategory(String category) {
    this.category = category;
  }
  
  public void displayProductInfo(){
    System.out.println();
    System.out.println("\n---Product Information---");
    System.out.println("Product ID: " + productId);
    System.out.println("Name: " + productName);
    System.out.println("Price: AED " + price);
    System.out.println("Quantity: " + quantity);
    System.out.println("Category: " + category);

  }
}

