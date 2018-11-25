package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Metod {

    private double Y;
    private double ksi;
    private double Me;
    private double lyambda;
    private double MO;
    private int kol;
    private double a;
    private double b;
    private double[] Mas_1;
    private double[] Int;
    private int[] Mas_kol;
    private double Disp;
    private double per_usable;
    private double per_correct;
    private double per_defect;

    public double getKsi() {
        return ksi;
    }

    public void setKsi(double ksi) {
        this.ksi = ksi;
    }

    public double getMe() {
        return Me;
    }

    public void setMe(double me) {
        Me = me;
    }

    public double getPer_usable() {
        return per_usable;
    }

    public void setPer_usable(double per_usable) {
        this.per_usable = per_usable;
    }

    public double getPer_correct() {
        return per_correct;
    }

    public void setPer_correct(double per_correct) {
        this.per_correct = per_correct;
    }

    public double getPer_defect() {
        return per_defect;
    }

    public void setPer_defect(double per_defect) {
        this.per_defect = per_defect;
    }

    public double getLyambda() {
        return lyambda;
    }

    public void setLyambda(double lyambda) {
        this.lyambda = lyambda;
    }

    public double getMO() {
        return MO;
    }

    public void setMO(double MO) {
        this.MO = MO;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        this.Y = y;
    }

    public double getDisp() {
        return Disp;
    }

    public void setDisp(double disp) {
        Disp = disp;
    }

    public int getKol() {
        return kol;
    }

    public void setKol(int kol) {
        this.kol = kol;
    }

    public double[] getMas_1() {
        return Mas_1;
    }

    public void setMas_1(double[] mas_1) {
        Mas_1 = mas_1;
    }

    public double[] getInt() {
        return Int;
    }

    public void setInt(double[] anInt) {
        Int = anInt;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int[] getMas_kol() {
        return Mas_kol;
    }

    public void setMas_kol(int[] mas_kol) {
        Mas_kol = mas_kol;
    }

    public Metod() {

        this.Me = 2.0;
        this.lyambda = 0.4;
        this.MO = 1 / lyambda;
        this.Disp = 1 / Math.pow(lyambda, 2);
        this.ksi = 0;
        this.a = 1000;
        this.b = -1000;
        this.kol = 1000;
        this.Mas_1 = new double[kol];
        this.Int = new double[11];
        this.Mas_kol = new int[10];
        this.per_usable = 0;
        this.per_correct = 0;
        this.per_defect = 0;
    }

    public double GetKsi() {
        Random r = new Random();

        ksi = r.nextDouble();
        //System.out.println("Ksi = " + ksi);

        return ksi;
    }

    public double GetY(double Ksi) {
        Y = -MO * Math.log10(Ksi);

        return Y;
    }

    public void GET() {

        for (int i = 0; i < kol; i++) {
            Mas_1[i] = GetY(GetKsi());
            //System.out.println("Y = " + Mas_1[i]);
        }
    }

    public void MaxMin(){

        for(int i = 0; i < kol; i++){
            if(Mas_1[i] <= a){
                a = Mas_1[i];
            } else if(Mas_1[i] >= b){
                b = Mas_1[i];
            }
        }

        //System.out.println("MIN = " + a + " AND MAX = " + b);
    }

    public void GetInt(){
        double h;

        Int[0] = a;
        Int[10] = b;

        h = (b - a) / 10;

        for(int i = 1; i < 10; i++) {
            Int[i] = Int[i - 1] + h;
        }
    }

    public void Intervals() {

        int k = 0;

        for(int j = 9; j >= 0; j--) {
            k = 0;
            for (int i = 0; i < kol; i++) {
                if(Mas_1[i] >= Int[j] && Mas_1[i] <= Int[j + 1])
                {
                    k++;
                    Mas_kol[j] = k;
                }
                else continue;
            }
            //System.out.println(Mas_kol[j]);
        }
    }

    public void Percent() {

        for (int i = 0; i < kol; i++) {
            if (Mas_1[i] < Me) {
                per_usable++;
            } else if (Mas_1[i] < 2 * Me) {
                per_correct++;
            } else {
                per_defect++;
            }
        }

    per_usable /= kol;
    per_correct /= kol;
    per_defect /= kol;

        System.out.println("Процент_пригодных = " + per_usable*100 + " % \n Процент_откорректированных = " + per_correct*100 + " % \n Процент_отбракованных = " + per_defect*100 + " %");
    }

    public void MD(){
        double mo = 0, disp = 0;

        for(int i = 0; i < kol; i++)
        {
            mo += Mas_1[i];
        }

        mo /= kol;
        System.out.println("Математическое ожидание = " + mo);

        for(int i = 0; i < kol; i++){
            disp += Math.pow((Mas_1[i] - mo), 2);
        }

        disp /= kol;
        System.out.println("Дисперсия = " + disp);

    }

    public void ToFile(FileWriter f) throws IOException {

        for (int i = 0; i < kol; i++){
            f.write(";" + Mas_1[i] + "\n");
        }

        for (int i = 0; i < 10; i++){
            f.write(Mas_kol[i] + "\n");
        }

        f.close();
    }
}
