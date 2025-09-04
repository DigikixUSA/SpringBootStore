# WESTERN GOVERNOR UNIVERSITY 
## D287 – JAVA FRAMEWORKS

## C.  Customize the HTML user interface
 * In /src/main/resources/templates/mainscreen.html, changed the title(line 14) and the header(line 19) of the page to match my chosen shop type and name. Updated README.md to reflect changes.

## D.  Add an “About” page
 * Added /src/main/java/com/example/demo/controllers/AboutUsController.java as a Spring controller for the About Us page. This utilized the @GetMapping Spring annotation so the page can be found with /about appended to the url.
 * Added /src/main/resources/templates/about.html as a thymeleaf template html file for the About Us page. Added an about us content for the page as well as an href link back to the main page on line 56&57.
 * Modified mainscreen.html (lines 89 to 94) to add an About Us button that will bring you to the about.html page.
* Updated README.md to reflect changes.

## E.  Add a sample inventory 
* Modified /src/main/java/com/example/demo/bootstrap/BootStrapData.java as follows:
* Lines 42 to 79 now contain default parts definitions as Outsourced Parts and the repository was saved - only if the existing quantity is 0.
* Lines 82 to 85 printed the contents of the list to the console for debugging puropses.
* Lines 89 to 101 set up default products - only if the quantity is 0.
* Updated README.md to reflect changes.


## F.  Add a “Buy Now” button
###  The “Buy Now” button must be next to the buttons that update and delete products.
* Modified mainscreen.hml, line 85 to add a Buy Now button to each listing, just after the Update & Delete button.
### The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
* Added /src/main/java/com/example/demo/controllers/BuyNowController.java as a Spring controller for the Buy Now button.
* Lines 27  to 30 find the repository, get if present, check if inventory is not 0 then,
* Lines 31, 32 decrement the quantity by 1 and save back to the repository.
### Display a message that indicates the success or failure of a purchase.
* A debug console message is added and a string "confirmationbuyproduct" is returned to trigger the success message.
* If not successful, line 43 is reached and the string "buyproducterror" is returned to trigger the failed message.
* Added /src/main/resources/templates/confirmationbuyproduct.html and buyproducterror.html are added to handle the messaging for success or failure of buying a product.
* Updated README.md to reflect changes.

## G.  Modify the parts to track
### Add additional fields to the part entity for maximum and minimum inventory.
* Modified /src/main/java/com/example/demo/domain/Part.java lines 31 to 34, added min and max fields to the part class.
* Modified the constructors in Part.java to include options for setting min and max.
* Lines 78 to 95 in Part.java added getters and setters for the min and max fields.
* Lines 126 to 135 in Part.java, modified the setInv() method to check inv value against the min and max.
* * Added Max and Min columns to mainscreen.html for the parts listings and connected them to the .min and .max fields
- This was also added to the Product form in error and was removed in later commits
### Modify the sample inventory to include the maximum and minimum fields.
* Added set min and max fields to the default parts in BootStrapData.java. Lines 47 & 48, 58 & 59, 68 & 69, 78 & 79, 88 & 89.
### Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
* Modified /src/main/resources/templates/InhousePartForm.html, lines 21 to 29, added input fields so that min and max can be set.
* Modified /src/main/resources/templates/OutsourcedPartForm.html, lines 25 to 30 to add input fields for max and min values.
### Rename the file the persistent storage is saved to.
* Copied the spring-boot-he-db102 database file to a new location /src/main/resources/ and renamed the fiie to spring-boot.
* Modified/src/main/resources/application.properties file to point to the new database file.
### Modify the code to enforce that the inventory is between or at the minimum and maximum value.
* Modified AddOutSourcedPartController.java, line 50 to 55, to check inventory levels against the max value.
 

## H.  Add validation 
* Rewrote README.md to be more in line with each step and more compliant with task instructions.
### Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
### Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
### Display error messages when adding and updating parts if the inventory is greater than the maximum.
These three points are solved by the following refactoring:
* Refactored to use basic Validators for min and max inventory values as follows:
*  1. Removed the check of inv against max in AddOutsourcedPartController.java, line 50-52.
*  2. Removed the check of inv against max in setters for .inv, .min, .max fields in Part.java, lines 91, 100, and 134 to 137.
*  3. Also in Part.java, added @ValidMinMax on line 21 to connect the custom validator to the part class.
*  4. Added default built-in validators for .inv field
* Added /src/main/java/com/example/demo/validators/MinMaxValidator.java as my custom validator implementation to check the inventory value against min and max.
* Added /src/main/java/com/example/demo/validators/ValidMinMax.java as the validator used with a string message about the inventory value must be between min and max.
* Added to InhousePartForm.html, lines 15, a thymeleaf message if there was an error from the custom validator.
* Added the same thymeleaf message on error from the custom validator to OutsourcedPartForm.html, line 16.
* EnufPartsValidator.java line 37: added checking inventory against minimum level.
* Added method invValid() to Part.java, line 103-109 to return if inventory is valid or not based on min and max levels. 
* Cleaned up isValid method in MinMaxValidator.java line 31 to use the new method .invValid() from Part.java. 
* Added max inv check line 39 in EnufPartsValidator.java. 
* Cleaned up BootStrapData.java and modified default values to make it easier for testing. 
* Added addproductoutofpart.html file as a message when not enough inventory of an associated part is available to add product.
* Modified AddProductController.java lines 84 to 94 so that if the number of associated parts falls below minimum, then the user will be brought to an error page(addproductoutofpart.html) and the quantities will not be affected.
* Updated README.md to reflect changes. 

## I.  Add at least two unit tests
* Added two unit tests in /src/test/java/com/example/demo/domain/PartTest.java with @Test annotation, lines 104 to 120.
* void setMin() checks if the min value is set with assertEquals
* void setMax() checks if the max value is set with assertEquals
* Ran each test separately and they both passed.
* Updated README.md to reflect changes.

## J.  Remove the class files for any unused
* Removed /src/main/java/com/example/demo/validators/DeletePartValidator.java as it has no uses
* Removed database clearing code used for testing only in BootStrapData.java
* Updated README.md to reflect changes.