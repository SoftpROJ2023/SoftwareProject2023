Feature: Product Catalog

 Scenario: View Product Categories
  Given the user is on the Product Catalog page
  When they access the Categories section
  Then they should see categories like "Interior", "Exterior", and "Electronics"

 Scenario: Browse Products within a Category
  Given the user is on the Product Catalog page
  When they click on the "Electronics" category
  Then they should see a list of products related to electronics

 Scenario: View Product Details
  Given the user is on the Product Catalog page
  When they select a product from the catalog
  Then they should see detailed product information including description, images, price, and availability

 Scenario: Search for a Product
  Given the user is on the Product Catalog page
  When they enter "smartphone" into the search bar
  Then they should see a list of products related to smartphones


 Scenario: Filter Products by Availability
  Given the user is on the Product Catalog page
  When they select the "Availability" filter and choose "In Stock"
  Then they should see a list of products that are currently in stock
