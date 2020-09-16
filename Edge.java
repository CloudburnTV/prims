// FILE: Edge.java
// John Payne
// Transylvania University
// CS3234, Fall 2019
//
// File containing class and methods for the Edge object
// 
// A single edge is made up of the name of a start vertex, an end vertex, and the weight between the two verticies

import java.io.*;

public class Edge {
    
    private String start;
    private String end;
    private int weight;

    public Edge(String newStart, String newEdge, int newWeight) {
       start = newStart;
       end = newEdge;
       weight = newWeight;
    }
    // Returns the start of the current edge
    public String getStart(){
    return start;
    }
    // Returns the end of the current edge 
    public String getEnd(){
    return end;
    }
    // Returns the weight of the current edge
    public int getWeight(){
    return weight;
    }
    // Prints the current edge
    public void print(){
        System.out.println(start + " " + weight + " " + end);
    }
}