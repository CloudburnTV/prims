// FILE: Tree.java
// John Payne
// Transylvania University
// CS3234, Fall 2019
//
// File containing class and methods for the Tree object
//
// Decided to use a PrintWriter after reading https://howtodoinjava.com/java/io/java-write-to-file/
//
// https://www.geeksforgeeks.org/decision-making-javaif-else-switch-break-continue-jump/
// I used this site to understand "continue" in findMinimum

import java.io.*;
import java.util.ArrayList;

public class Tree{

    final int INF = -1; //used to set the key of non-root verticies

    private ArrayList <Vertex> tree;
    private ArrayList <Vertex> smallTree;

    public Tree(){
        tree = new ArrayList <Vertex> ();
    }

    //print
    // Calls the print method of each individual vertex in the tree.
    public void print (String root){

        int totalWeight = getTotalWeight();

        System.out.println("\nHere is your tree: \n");
        System.out.println("*Vertex <- Key -> Parent*");

        for (int i = 0; i < tree.size(); ++i){
            tree.get(i).print();
        }

        System.out.println("\nTree root: " + root + "\n");
        System.out.println("Total tree weight: " + totalWeight + "\n");
    } 

    //writeToFile
    // Writes the contents of the tree to the specified output file.
    public void writeToFile (String filename, String root){
        try{

            //set up the filewriter and printwriter with the output file from main
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);


            //write contents to file
            int totalWeight = getTotalWeight();

            printWriter.println("\nHere is your tree: \n");
            printWriter.println("*Vertex <- Key -> Parent*");
            for (int i = 0; i < tree.size(); ++i){
                tree.get(i).write(printWriter);
            }
            printWriter.println("\nTree root: " + root + "\n");
            printWriter.println("Total tree weight: " + totalWeight + "\n");

            //close the output file
            printWriter.close();
            
        }
        catch (Exception o){
            System.out.println("ERROR: Output file write failed!");
        }
    }

    //initTree
    // Takes in the qList and the given root. It then creates the initial Tree for prims.
    public void initTree (ArrayList <String> qList, String root){
        for (int i = 0; i < qList.size(); ++i ) {
            if (qList.get(i).equals(root)){
                tree.add(new Vertex (qList.get(i), "nil", 0)); 
            }
            else {
                tree.add(new Vertex (qList.get(i), "nil", INF));  
            }
        }
    }

    //findMinimum
    // Takes in a tree and returns the name of the vertex with
    // the lowest-value key
    public Vertex findMinimum(ArrayList <String> qList){

        //build a list of verticies from the qList
        smallTree = new ArrayList <Vertex> ();
        initSmallTree (smallTree, qList);
        
        //set min to the first vertex in the list
        Vertex min = smallTree.get(0);

        //find the min
        for (int i = 1; i < smallTree.size(); ++i){
            if (min.getKey() == INF){
                min = smallTree.get(i);
            }
            if (smallTree.get(i).getKey() == INF){
                continue;
            }
            if (min.getKey() < smallTree.get(i).getKey()){
                continue;
            }
            if (smallTree.get(i).getKey() < min.getKey()){
                min = smallTree.get(i);
            }
        }
        return min;
    }

    //checkWeight
    // Takes in a connected edge, the minimum vertex, and the tree
    // Checks if the edge weight is less than the key of the minimum vertex,
    // If so, calls the updateVertex method
    public void checkWeight (Edge connected, Vertex min) {
        for (int i = 0; i < tree.size(); ++i) {
            if (tree.get(i).getName().equals(min.getName())){
                if (connected.getWeight() < tree.get(i).getKey() || tree.get(i).getKey() == INF){
                    tree.get(i).updateVertex(connected);
                }
            }
        }
    }

    //initSmallTree
    // Takes in the qList
    // Returns a list of verticies that are present in the Qlist
    public ArrayList <Vertex> initSmallTree (ArrayList <Vertex> smallTree, ArrayList <String> qList){
        for (int i = 0; i < tree.size(); ++i){
            if (qList.contains(tree.get(i).getName())){
                smallTree.add(tree.get(i));
            }
        }
        return smallTree;
    }

    public int getTotalWeight() {
        int totalWeight = 0;

        for (int i = 0; i < tree.size(); ++i){
            totalWeight = totalWeight + tree.get(i).getKey();
        }

        return totalWeight;
    }

}

