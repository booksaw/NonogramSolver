# NonogramSolver
 Attempt at making a project which can solve a nonogram 
 
## How it works

For this explanation, some basic terms need to be defined: 
* A node is either an individual column or row
* An element is a square within the solution grid
* A individual element  on the grid can have 3 states: Unknown, Filled or Empty 

Each node of the nonogram is looped through systematically and the following checks are carried out for each node. 

```
Loop through all combinations of filled and empty states // This is done with disregard to the current state of any of the elements within the node 
    If the combiation conflicts with the current set states of the elements
        Continue to the next combiation
    Check if the combiation is valid in regard to the node headers (ie if the header is 5,3 it will check if there are 5 filled elements, a gap then 3 filled elemenmts) 
    If the combiation is valid
       Store the combination 
END OF LOOP 

Check if there are any elements which remain the same throughout every valid combiation
For every constant element through the stored combiations
    Set the final state of that element to the found state
```

This test is run for every node in order. At the end of each pass, the nonogram is checked to see if it is solved. In the case where it is not solved, the iterative process returns to the first node for another pass. This continues until the nonogram is solved. 

### Additional details 

While finding combinations, a binary representation is used within the combination calculations, where 1 represents filled and 0 represents a blank square. This can be done as there is only 2 states that an element can be assigned to instead of the three across the program as a whole (this is because the unknown state is not needed when listing all possible combinations as each element will have a final known state). This makes the looping through every possible combination very simple, as we can just calculate how many possible combinations exist, loop through them from `0` to `1 - (total possible combinations)` in base 10 and then convert the number into binary using the Java inbuilt method.  
 
## Loading Nonograms

Several methods for loading a nonogram into the system are planned for the future of this probject, currently there is only a single way which works fully: 

### Text Loader

This method of loading is the most basic and requires the user to convert the image into a text string. There are a few formatting rules which dictacte how this is done. 
If you do not want the image to be rotated, ensure that the columns are put before the rows else the final result will be rotated 90 degrees. 
* The column and row details are seperated by a ':'
* Each individual node (this is either an individual column or row) is seperated by the symbol ';'
* Each individual value within a node is seperated by the symbol ','

It is important that the imputted string does not contain any spaces or invalid characters (ie letters). 
NOTE: it is very easy to make a mistake while inputting a nonogram in this way, this method was never intended to be a long term solution, and was only initally implemeneted to test the solver component of this project. 

This method is used as it is very simple to convert those details into a format that the solver can handle. 
An example of a nonogram string: 

![Image of Nonogram](https://www.puzzle-nonograms.com/art/og/puzzle-nonograms.png)

The first column node would be: `1,4,1`. 
All columns combined would be: `1,4,1;3,4,1;1,3;1,1;3,1;5;5,1;4,1,1;5,1;3`
And the entire nonogram string would be: `1,4,1;3,4,1;1,3;1,1;3,1;5;5,1;4,1,1;5,1;3:3,5;1,5;1,6;5;2,4,1;2,1;3;5,1;1;2,1,1`
