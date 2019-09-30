# LMSProject
This is a library management system java app
1. dataAccess package has all the dao classes.
   AuthorDAC -> author dao , BookDAC -> book dao, PublisherDAC -> publisher dao.
   (a)Every dao has one property - lastIndex which keeps track of the last index of a List<Object> to make sure when creating a new object, the primary key is incremental and doesn't have to be auto input by user. The program will take care of assigning id.
   (b) readFromFile() method which reads from the file and returns an ArrayList of objects.
   (c) writeTo() method which after manipulating the arraylist will write that arraylist back to the file.
2. pojo package has all plain old java object classes. All the pojo classes will have properties and generated getter setter method. THese methods will be called when updating, creating or retrieving value.
3. serviceClass package has all the controller/ service class methods that will talk to both view(classes of UserMenu package) and data access object class (classes of dataAccess package). These classes have the following operations:
   (a) CRUD operation for every individual entity.
   (b) When an author or publisher is deleted, any book of that author or publisher will also get deleted.
   (c) When a book with a new author or a new pulisher is created, then the program will ask the user to fill in all the details to create a new author of that name OR a new publisher of that name. User will not have to manually go to author or publisher menu to create a new author or publisher; rather the program will call internally defined functions to do that for the user.
4. userMenu package contains all the view classes. Here, any option or user response required is recorded and used by the user. The welcomePage class and MenuPage class has all the methods to ask for options that user will have to choose to navigate around. THe other 3 classes has methods that asks for user input that controller needs to run the methods in controller classes.
5.Files folder contains all the files that are being worked on.
