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
    public String getDesc() {
        return desc;
    }

    public Data getData() {
        return d;
    }
    public String getTitle() {
        return title;
    }

    public Lembrete(String title, String desc, Data d)            //Metodo construtor de Lembrete
    {
        this.title = title;                                         //Passa como parametro uma data(Dia,mes,ano) e uma String que é a descrição do lembrete
        this.desc = desc;
        this.d = d;
    }

    @Override
    public String toString()                                        //Sobrescrita no metodo toString
    {                                                                          //Imprime toString da Data + a descrição do Lembrete
        return getData() + " - " + getDesc() + "\n";
    }
    @Override
    public int compareTo(Lembrete l)                        //Compara os lembretes para ordena-los no Bloco
    {
        if(this.d.getYear() < l.d.getYear())
            return -1;
        else if(this.d.getYear() > l.d.getYear())
            return 1;
        else
        {
            if(this.d.getMonth() < l.d.getMonth())
                return -1;
            else if(this.d.getMonth() > l.d.getMonth())
                return 1;
            else
                return Integer.compare(this.d.getDay(), l.d.getDay());
        }
    }
    static Comparator<Lembrete> getComparadorTitulo() {
        return new Comparator<Lembrete>() {
            @Override
            public int compare(Lembrete a, Lembrete b) {
                return a.getTitle().compareTo(b.getTitle());
            };
        };
    }

//    static Comparator<Lembrete> getComparadorData() {
//        return new Comparator<Lembrete>() {
//            @Override
//            public int compare(Lembrete a, Lembrete b) {
//                if (b.d.getYear() < a.d.getYear())
//                    return -1;
//                else if (a.d.getYear() > b.d.getYear())
//                    return 1;
//                else {
//                    if (a.d.getMonth() < b.d.getMonth())
//                        return -1;
//                    else if (a.d.getMonth() > b.d.getMonth())
//                        return 1;
//                    else
//                        return Integer.compare(a.d.getDay(), b.d.getDay());
//                }
//            };
//        };
//    }
}

/*
 */