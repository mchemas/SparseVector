package com.company;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        FileReader input;
        BufferedReader inFile;
        String line;
        SparseVector x1, x2;
        String path = "project1.txt";
            try{
                input = new FileReader(path);
                inFile = new BufferedReader(input);
                while((line = inFile.readLine())!=null){
                    x1 = new SparseVector();
                    x2 = new SparseVector();

                    //reads first line
                    String[] numbers = line.split(" ");
                    x1.addFromString(numbers);

                    line = inFile.readLine();

                    //reads second line
                    numbers = line.split(" ");
                    x2.addFromString(numbers);

                    //reads third line
                    line = inFile.readLine();

                    x1.operation(line, x2);
                }

            } catch(Exception e){
                System.out.println(e);
            }


    }
}
