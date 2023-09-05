/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lembretesapp;

/**
 *
 * @author Christian
 */
public class Data {
    
       private int day; 
       private int month; 
       private int year; 
       private static final String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril",
                                           "Maio", "Junho", "Julho", "Agosto",
                                           "Setembro", "Outubro", "Novembro", "Dezembro"};
       
        
        public Data(int day, int month, int year)
        {
            this.day = day;
            this.month = month; 
            this.year = year; 
        }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

        @Override
        public String toString()
        { 
            return (day < 10 ? "0"+day : day) + "/" + (month < 10 ? "0"+month : month) + "/" + year;
            //return "Dia:" + getDay() + " Mes:" + getMonth() + " Ano:" + getYear(); 
        }
}
        