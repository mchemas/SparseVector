package com.company;

import java.util.Iterator;

/**
 * Created by itslehcim on 10/25/2016.
 */
public class SparseVector {

    private DoublyLinkedList<Element> spar;

    public SparseVector(){
        spar = new DoublyLinkedList<Element>();
    }

    public SparseVector(DoublyLinkedList<Element> x){
        setVector(x);
        this.sort();
    }

    public DoublyLinkedList<Element> getVector(){return spar;}
    public void setVector(DoublyLinkedList<Element> newVector){ spar = newVector;}

    private Iterator iterator(){return this.getVector().iterator();}

    public Element getElement(int i){return this.getVector().get(i);}

    public int size(){return spar.size();}

    //determines which type of operation
    public void operation(String op, SparseVector sv){
        //if says something else throw exception - operation not supported
        if(op.equals("add")){
            this.add(op, sv);
        }/*else if(op.equals("subtract")){
            this.subtract(sv);
        }else if(op.equals("dot")){
            this.dot(sv);
        }*/
    }

    //makes the setup for output and sends each SparseVector to toString
    public void print(String op, SparseVector sv, SparseVector result){
        char dotCharacter = (char) 183;
        this.toString();
        if(op.equals("add")){
            System.out.println("+");
        }else if(op.equals("subtract")){
            System.out.println("-");
        }else if(op.equals("dot")){
            System.out.println(dotCharacter);
        }
        sv.toString();
        System.out.println("=");
        result.toString();
    }

    //ADD METHOD
    //creates a linked list
    //creates two iterators,one for each SV getting added
    //while BOTH iterators have a next,
    //compare element1 to element2 (index),
    //whichever is less, put in linked list, increment count+iterator
    //if equal, add together, put in linked list, increment both counts&iterators
    //if two values add to zero, do not include in result vector
    //IF EITHER ITERARTOR DOES NOT HAVE A NEXT BREAK OUT OF LOOP
    //while loops for each iterator, only one while loop will run
    //this while loop will put in the remaining elements into the linked list
    //create a new result Sparsed Vector using this linked list
    //print & return result
    public SparseVector add(String op, SparseVector sv){
        DoublyLinkedList<Element> newVector = new DoublyLinkedList<>();
        Iterator thisItr = this.iterator();
        Iterator svItr = sv.iterator();
        int count1 = 0, count2 = 0;

        Element e1 = this.getElement(count1);
        Element e2 = sv.getElement(count2);

        while(thisItr.hasNext() && svItr.hasNext()){

            if ((count1 != this.size())||(count2 != sv.size())){
                e1 = this.getElement(count1);
                e2 = sv.getElement(count2);
            }else{
                break;
            }

            if (e1.compareTo(e2) == -1){
                newVector.add(new Element(e1));
                thisItr.next();
                count1++;
            }else if (e1.compareTo(e2) == 0){
                int index = e1.getInd();
                double newVal = e1.getVal() + e2.getVal();
                if(newVal!=0){
                    newVector.add(new Element(index, newVal));
                }
                thisItr.next();
                svItr.next();
                count1++;
                count2++;
            }else{
                newVector.add(new Element(e2));
                svItr.next();
                count2++;
            }
        }

        while((thisItr.hasNext())) {
            e1 = this.getElement(count1);
            newVector.add(new Element(e1));
            count1++;
            thisItr.next();
        }
        while((svItr.hasNext())){
            e2 = this.getElement(count2);
            newVector.add(new Element(e2));
            count2++;
            svItr.next();
        }

        SparseVector result = new SparseVector(newVector);

        this.print(op, sv, result);
        return result;
    }

    /*public SparseVector subtract(SparseVector sv){

    }

    /*public double dot(SparseVector sv){

     */

    //runs through string received from main, creates elements, sets data for elements
    public void addFromString(String[] x){
        for(int i = 0; i < x.length; i++){
            Element element = new Element();

            element.setInd(Integer.parseInt(x[i]));
            element.setVal(Double.parseDouble(x[++i]));
            spar.add(element);
            this.sort();
        }
    }

    //actually prints out the SparseVector
    public String toString(){
        for(int i = 0; i < spar.size(); i++){
            System.out.print(spar.get(i) + " ");
        }
        System.out.println("");
        return null;
    }

    //sorts a sparse vector
    public void sort(){
        DoublyLinkedList<Element> temp = new DoublyLinkedList<>();

        while(!spar.isEmpty()){
            //sets min to first index in original and compares it to next index
            int min=0;

            for(int j = 1; j<spar.size() ; j++){
                if (spar.get(j).getInd() < spar.get(min).getInd()){
                    min = j;
                }
            }
            temp.add(spar.get(min));
            spar.remove(min);
        }
        spar=temp;
    }

}
