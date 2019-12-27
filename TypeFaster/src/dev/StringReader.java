package dev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StringReader {
    public static void main(String[] args) throws IOException {
        char buffer[] = new char[100];
        FileReader fr = new FileReader("Paragraph/textSample.txt");
        int num;
        for( int i = 0; i < 400; i+= 100){
            num = fr.read(buffer);
            String str = new String(buffer,0,num);
            str = str.toLowerCase();
            System.out.println(str);
            System.out.println(num);
        }
        fr.close();
    }
}
