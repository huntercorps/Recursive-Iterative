# Recursive-Iterative
## CMIS 242 Project 3
A program that demonstrates iterative and recursive methods by calculating the terms of the following sequence of numbers: 0 1 2 5 12 29 ... Each term of the sequence is twice the previous term plus the second previous term.
### Program Description
A Java program that implements a GUI to calculate the value and efficiency of finding the user selected term of the sequence. The program contains two classes, View and Sequence
### View Class
Contains a constructor that creates the GUI and provides event handling of user interaction. allows the user to choose whether to perform the calculate action to either recursively or iteratively. In addition to displaying the value of the term the class also displays the efficiency(number of calls) of the chosen calculating method. Upon closing the GUI Window, View creates a file with a comma separator displaying the results of calculating terms 0-10.
### Sequence Class
A utility class containing static fields and methods. The class is declared abstract to avoid instantiation while providing methods to calculate the sequence’s term value and efficiency recursively and iteratively. Furthermore a method to retrieve the efficiency data field is included.
