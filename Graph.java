// FILE: Graph.java
// John Payne
// Transylvania University
// CS3234, Fall 2019
//
// File containing class and methods for the Graph object

import java.io.*;
import java.util.ArrayList;

public class Graph {

    final int INF = -1;

    private ArrayList <Edge> graph;
    private ArrayList <Edge> connectedList;
    private ArrayList <Edge> tempList;
    private ArrayList <String> qList;

    public Graph(String filename){
        graph = new ArrayList <Edge> ();
        readInFile(filename);
        }
    
    //readInFile
    // Takes in the input file and fills the graph object
    public void readInFile(String filename) {
        String lineOfData = new String ("");
        boolean done = false;
        //set up the BufferedReader
        BufferedReader fin = null;
	try {
        fin = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        lineOfData = fin.readLine();
	}
        catch(Exception c) {
            System.out.println("ERROR: Input read failed!");
        }

        //read in additional strings until the file is empty
        while (done != true){
            String temp[] = lineOfData.split(" ");
            graph.add(new Edge(temp[0], temp[1], Integer.parseInt(temp[2]))); //add current string to graph
            try {
                lineOfData = fin.readLine();
                if (lineOfData == null){
                done = true;
                }
            }
            catch(IOException ioe){
                System.out.println("ERROR: Input read failed!");
            }
        }
    }

    //prims
    // Takes in the root
    // Creates the initial tree and Qlist 
    // Then extracts the minimum vertex until the Qlist is empty
    public Tree prims (String root){
        
        Tree tempTree = new Tree();
        qList = new ArrayList <String> ();
        connectedList = new ArrayList <Edge> ();
        
        //create the QList
        createQList(qList);
        
        //initialize the tree
        tempTree.initTree(qList, root);

        //extract the minimum from qList until empty
        while (qList.size() != 0){
            
            //find and extract the min
            Vertex min = tempTree.findMinimum(qList);
            extractMin(tempTree, qList, min);

            //remove min from Qlist
            qList.remove(min.getName());
        }

        //return the finished tree
        return tempTree;
    }

    //print
    // Calls the print method of each individual edge in the graph
    public void print (){
        for (int i = 0; i < graph.size(); ++i){
            graph.get(i).print();
            }
        }   

    //createQList
    // Takes in the empty Qlist
    // Returns a Qlist populated with the elements from the graph
    public void createQList(ArrayList <String> qList) {

        for (int i = 0; i < graph.size(); ++i){
            if (qList.contains(graph.get(i).getStart()) == false){
                qList.add(graph.get(i).getStart());
            }
            else if (qList.contains(graph.get(i).getEnd()) == false){
                qList.add(graph.get(i).getEnd());
            }
        }
    }

    //allConnected
    // Takes in the minimum vertex
    // Returns a list of all the connected edges from the graph
    public ArrayList <Edge> allConnected (Vertex min) {
        
        tempList = new ArrayList <Edge> (); 
        
        for (int i = 0; i < graph.size(); ++i){
            if (graph.get(i).getStart().equals(min.getName())){
                tempList.add(graph.get(i));
            }
            else if (graph.get(i).getEnd().equals(min.getName())){
                tempList.add(graph.get(i));
            }
        }
        return tempList;
    }

    //extractMin
    // Takes in the tree and the Qlist 
    // Finds the minimum vertex from the tree and updates the tree if needed
    public void extractMin (Tree tempTree, ArrayList <String> qList, Vertex min) {

        //find all connected verticies to the minimum
        connectedList = allConnected(min);
        
        
        //for each connected vertex
        for (int i = 0; i < connectedList.size(); ++i){

            //check  if the connected edge is in the Qlist
            if (qList.contains(connectedList.get(i).getStart()) == true || qList.contains(connectedList.get(i).getEnd()) == true) {
                tempTree.checkWeight(connectedList.get(i), min);
            }
        }
    }
}
