/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lembretesapp;

/**
 *
 * @author Christian
 */
public class Lembrete implements Comparable<Lembrete>{

    public String getDesc() {
        return desc;
    }

    public Data getData() {
        return d;
    }

    public String getTitle(){ return title; }

    private String title;
    private String desc;
    private int day; 
    private int month; 
    private int year;
    private Data d = new Data(day, month, year);
    
    public Lembrete(String title,String desc, Data d)            //Metodo construtor de Lembrete
    {
        this.title = title;                                      //Passa como parametro uma data(Dia,mes,ano) e uma String que é a descrição do lembrete
        this.desc = desc; 
        this.d = d; 
    }  
    
    @Override
    public String toString()                                        //Sobrescrita no metodo toString
    {                                                               //Imprime toString da Data + a descrição do Lembrete
        return getTitle()+ "\n" + getData() + " - " + getDesc() + "\n";
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
        //return this.getDay() - l.getDay();
    }
    }
   /* @Override 
public int compareTo(lembrete l, int dia)
{
    for(Lembrete l : this.l)
        
        
}*/
}

