// FILE: Prims.java
// John Payne
// Transylvania University
// CS3234, Fall 2019
//
// Driver program for the prims algorithm
//

import java.io.*;

public class Prims {

    public static void main (String[] args){
       
    //constants for error checking
    final String DEFAULT_ROOT = "a";
    final String DEFAULT_INPUT = "input.in";
    final String DEFAULT_OUTPUT = "output.out";

    String root = null;
    String inputFile = null;
    String outputFile = null;

        //if all needed information is passed into the command line
        if (args.length == 3){
            root = args[0];
            inputFile = args[1];
            outputFile = args[2]; 
        }
        //if no output is specified in the command line
        else if (args.length == 2){
            root = args[0];
            inputFile = args[1];
            outputFile = DEFAULT_OUTPUT;
        }
        //if no input is specified in the command line
        else if (args.length == 1){
            root = args[0];
            inputFile = DEFAULT_INPUT;
            outputFile = DEFAULT_OUTPUT;
        }
        //if no root is specified in the command line
        else if (args.length == 0){
            root = DEFAULT_ROOT;
            inputFile = DEFAULT_INPUT;
            outputFile = DEFAULT_OUTPUT;
        }

        //main program
        Graph g = new Graph(inputFile);
        Tree t = new Tree();
        t = g.prims(root);
        t.print(root);
        t.writeToFile(outputFile, root);
        
    }
        
}
