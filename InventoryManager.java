class InventoryManager {
    Products[] inventory;
    private int productCount;
    private static final int MAX_AMOUNT_PROD = 50;
    
    public InventoryManager() {
        inventory = new Products[MAX_AMOUNT_PROD];
        productCount = 0;
    }
    
    //adding
    public void addProduct(Products product) throws StorageFull {
        if (productCount>= MAX_AMOUNT_PROD) {
            throw new StorageFull("Sorry, inventory is full ! Please increase your storage capacity if you wish to add more !");
        }
    
        inventory[productCount] = product;
        productCount++;
        System.out.println("Product has been added !");
    }
    
    //viewing
    public void viewProducts() {
        if (productCount == 0) {
            System.out.println("Inventory is empty !");
            return;
        }
        
        System.out.println("\n=====ALL PRODUCTS=====");
        for(int productIndex = 0; productIndex < productCount; productIndex++) {
            if(inventory[productIndex] != null) {
                inventory[productIndex].displayProductInfo();
                if(inventory[productIndex].getQuantity() < 10){
                    System.out.println("WARNING: This items stock is LOW !");
                }
            }
        }
    }

    //amount in each category
    public void countByCategory(){
        if (productCount == 0) {
            System.out.println("Inventory is empty!");
            return;
        }
    
        String[] categoryNames = new String[10];
        int[] categoryCounts = new int[10];
        int categoryTotal = 0;

        int i = 0;
        while (i <productCount) {
            if (inventory[i] != null) {
                String cat = inventory[i].getCategory();
                
                // Check if category already exists
                boolean found = false;
                int j = 0;
                while (j < categoryTotal){
                    if (categoryNames[j].equals(cat)) {
                        categoryCounts[j] = categoryCounts[j] + 1;
                        found = true;
                    }
                    j++;
                }
                if (found == false) {
                categoryNames[categoryTotal] = cat;
                categoryCounts[categoryTotal] = 1;
                categoryTotal++;
                }
            }
            i++;
        }
        System.out.println("\n===== PRODUCTS BY CATEGORY =====");
        int k = 0;
        while (k< categoryTotal) {
            System.out.println(categoryNames[k] + ": " + categoryCounts[k] + " product/products");
            k++;
        }
    }
    
    //updating
    public void updateProduct(int productId, double newPrice, int newQuantity, String newCategory) throws ItemNotFound {
        Products product = searchProduct(productId);  //search but if not found will throw not found error
        //prod found
        product.setPrice(newPrice);
        product.setQuantity(newQuantity);
        product.setCategory(newCategory);
        System.out.println("Product updated successfully!");
    }
    
    //deleting
    public void deleteProduct(int productId) throws ItemNotFound {
        //find
        int indexToDelete = -1;
        for (int productIndex = 0; productIndex< productCount; productIndex++) {
            if (inventory[productIndex] != null && inventory[productIndex].getProductId() == productId) {
                indexToDelete = productIndex;
                break;
            }
        }

        //exception
        if (indexToDelete==-1) {
            throw new ItemNotFound("Product with ID " + productId + " not found !");
        }
        
        for (int productIndex=indexToDelete; productIndex<productCount-1; productIndex++) { //shift left
            inventory[productIndex] = inventory[productIndex + 1];
        }

        inventory[productCount-1] = null; //last prod nullifying
        productCount--;

        System.out.println("The product was deleted successfully !");
    }
    
    //searching
    public Products searchProduct(int productId) throws ItemNotFound{
        for(int productIndex = 0; productIndex<productCount; productIndex++) {
            if (inventory[productIndex] != null && inventory[productIndex].getProductId() == productId) {
                return inventory[productIndex];  //yay found
            }
        }
        //exception error message
        throw new ItemNotFound("The given product with ID " + productId + " has not been found!");
    }

    //my stores value
    public void getTotalWorth(){
        if (productCount == 0) {
            System.out.println("Inventory is empty !");
            return;
        }
        double total = 0;
        int i = 0;
        while (i<productCount) {
            if (inventory[i] != null) {
                total = total + (inventory[i].getPrice()*inventory[i].getQuantity());
            }
            i++;
        }
        System.out.println("\n===== INVENTORY VALUE =====");
        System.out.println("Total Inventory Value: AED " + total);
    }
}

    