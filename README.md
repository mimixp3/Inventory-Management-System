# Inventory-Management-System
Console application for managing inventory using JAVA with CRUD operations, activity logging, and CSV export features

## Features

- **Product Management**: Add, view, update, delete, and search products
- **Perishable Items**: Hndling products with expiration dates
- **Activity Logging**: Logs of all changes
- **CSV Export**: Export activity logs as Excel-compatible format
- **Category Analytics**: Automatic product counting and grouping
- **Inventory Valuation**: Real-time calculation of total inventory worth
- **Smart Validations**: 
  - Preventing negative values
  - Duplicate ID's
  - Confirmation when deleting
  - Warnings about low stock
    
## Class Structure
- **Products** (Parent Class): Core product attributes and methods
- **PerishableProducts** (Child class): Extends Products + has expiry date
- **InventoryManager**: Handles CRUD operation
- **ActivityLog**: Tracks + exports all inventory changes
- **Main**: User interface and menu system
- **Custom Exceptions**: ItemNotFound, StorageFull, InvalidInput

### Design Principles
- **Encapsulation**: All data protected with private fields
- **Inheritance**: Code reuse through parent-child relationships
- **Polymorphism**: Method overriding for specialized behavior



## Menu Options

1. Add Product
2. View All Products
3. Update Product
4. Delete Product
5. Search Product by ID
6. Category Amount
7. View Activity Log
8. Export Activity Log to CSV
9. View Total Inventory Value
10. Exit

## Exceptions

- **InputMismatchException**: Handles invalid input types
- **ItemNotFound**: Thrown when product ID doesn't exist
- **StorageFull**: Thrown when inventory reaches maximum capacity (50 items)
- **InvalidInput**: Custom validation errors
  
Team
- [Nazla Chy]-[20240004973]
- [Elham Abbasi]-[20240005699]
- [Negin Mirzajani]-[20240004675]

---

## **Step 6: Copy the URL**

Your repo URL will be:
```
https://github.com/[your-username]/inventory-management-system
