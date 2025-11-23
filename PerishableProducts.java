class PerishableProducts extends Products{
  private String expirationDate;
  PerishableProducts(int productId, String productName, double price, int quantity, String category, String expirationDate){
    super(productId, productName, price, quantity, category);
    this.expirationDate = expirationDate; 
  }
  public String getExpiryDate(){
    return expirationDate;
  }
  @Override
    public void displayProductInfo(){
      super.displayProductInfo();
      System.out.println("Expiry Date: " + expirationDate);
  }
}

