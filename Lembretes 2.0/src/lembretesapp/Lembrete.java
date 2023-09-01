/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lembretesapp;

import java.util.Comparator;

/**
 *
 * @author Christian
 */
public class Lembrete implements Comparable<Lembrete> {

    private String title;
    private String desc;
    private int day;
    private int month;
    private int year;
    private Data d = new Data(day, month, year);
    /* ###################################### */
    //  Getters and setters //

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Data getData() {
        return d;
    }

    public String getTitle() {
        return title;
    }

    public Lembrete(String title, String desc, Data d)            //Constructor method
    {
        this.title = title;
        this.desc = desc;
        this.d = d;
    }

    @Override
    public String toString()                                        //Override toString
    {                                                                          //Isn't necessary
        return getData() + " - " + getDesc() + "\n";
    }

    @Override
    public int compareTo(Lembrete l)                        //Compare all notes
    {
        if (this.d.getYear() < l.d.getYear())
            return -1;
        else if (this.d.getYear() > l.d.getYear())
            return 1;
        else {
            if (this.d.getMonth() < l.d.getMonth())
                return -1;
            else if (this.d.getMonth() > l.d.getMonth())
                return 1;
            else
                return Integer.compare(this.d.getDay(), l.d.getDay());
        }
    }

    static Comparator<Lembrete> getComparadorTitulo() { //Compare all notes title
        return new Comparator<Lembrete>() {
            @Override
            public int compare(Lembrete a, Lembrete b) {
                String A = a.getTitle().toLowerCase();
                String B = b.getTitle().toLowerCase();
                return A.compareTo(B);
            }

            ;
        };
    }
}

