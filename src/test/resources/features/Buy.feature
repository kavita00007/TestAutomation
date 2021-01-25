@ui @buy

Feature: Start to end shopping process.

Scenario:  End To End User Journey Feature
Given      Login to url
When       Select the category as T-Shirts
And        Click on the product: Faded Short Sleeve T-shirts
And        Fetch the Amount of the product in a variable
And        Increase Quantity to 2
And        Increase Size to L
And        Click on Add to Cart
And      Check the User sees the Pop Up: Product Successfully Added to Cart
And       Click on Proceed to Checkout.
And        Click on Proceed to Check out again and reach till payment and click on Terms and condition check box.
And        On Payment Page click on Pay by bank wire and Click on I confirm my Order
Then       Check the order submit page and message "Your order on My Store is complete." also check is amount is right.

       