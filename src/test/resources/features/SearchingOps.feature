@ui @searching

Feature:  Performing activities on website after searching product

Background: User searching item and performing operations
Given       User navigated to url
When        Select the category as T-Shirts
And         Click on the product: Faded Short Sleeve T-shirts


@share
Scenario: Send a Friend Feature
When      Click Send to a Friend Link, fill the details and click on Send.
Then      Check the Message appeared that Email sent in a pop up

@review
Scenario:  Write a Review 
When       Click on Send a Review and enter the details in the form.
Then       Check the Message appeared that New comment added.

@colour
Scenario:  Change in the image using Color Feature
When       Click on Color Blue link
Then       Check the Image is changed
