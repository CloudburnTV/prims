// FILE: Vertex.java
// John Payne
// Transylvania University
// CS3234, Fall 2019
//
// File containing class and methods for the Vertex object
//
// A single vertex is made up of a name, key, and parent.

import java.io.*;
import java.util.ArrayList;

public class Vertex {

    private String name;
    private String parent;
    private int key;

    public Vertex(String newName, String newParent, int newKey) {
        name = newName;
        parent = newParent;
        key = newKey;
    }
    //getName
    // Returns the name of the current vertex
    public String getName(){
        return name;
    }
    //getParent
    // Returns the parent of the current vertex
    public String getParent(){
        return parent;
    }
    //getKey
    // Returns the key of the current vertex
    public int getKey(){
        return key;
    }
    //print
    // Prints the current vertex
    public void print(){
        System.out.println(name + " <- " + key + " -> " + parent);
    }
    //write
    // Writes the current vertex to the output file
    public void write(PrintWriter printWriter){
        printWriter.println(name + " <- " + key + " -> " + parent);
    }
    //updateVertex
    // Takes in a connected edge and updates the current vertex based on the
    // edge's contents
    public void updateVertex (Edge connected){
        key = connected.getWeight();

        if (connected.getStart().equals(name)){
            parent = connected.getEnd();
        }
        if (connected.getEnd().equals(name)){
            parent = connected.getStart();
        }
    }
}