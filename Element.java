package com.company;

/**
 * Created by itslehcim on 10/25/2016.
 */
public class Element implements Comparable<Element> {

    private int ind;
    private double val;

    public Element(){
        ind = 0;
        val = 0;
    }

    //need to make sure its a valid index
    public Element(int x, double y){
        if(x<0){throw new IndexOutOfBoundsException();}
        ind = x;
        val = y;
    }

    public Element(Element x){
        ind = x.getInd();
        val = x.getVal();
    }

    public int getInd(){return ind;}
    public double getVal(){return val;}
    public void setInd(int x){ind = x;}
    public void setVal(double x){val = x;}

    //need to make sure element actually exists
    //need to make sure its the same type of object
    public int compareTo(Element x){
        if (x == null) {throw new NullPointerException("No such element.");}
        if (!(x instanceof Element)){throw new ClassCastException("Not the same object type.");}

        final int before = -1;
        final int equal = 0;
        final int after = 1;

        if (this.getInd() < x.getInd()) {
            return before;
        } else if (this.getInd() > x.getInd()) {
            return after;
        }
        return equal;
    }

    public String toString(){
        return "[" + this.ind + "," + this.val + "]";
    }
}
