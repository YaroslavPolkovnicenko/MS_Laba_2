package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Metod m = new Metod();

        FileWriter f = new FileWriter("C:/Users/21091/Downloads/SM_2/Ksi.csv");

        m.GET();
        m.Percent();
        m.MaxMin();
        m.GetInt();
        m.Intervals();
        m.ToFile(f);
        m.MD();
    }
}
