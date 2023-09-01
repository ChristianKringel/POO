/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lembretesapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Christian
 */
public class BlocoDeLembretes {
    private List<Lembrete> list;
    private int position;
    public BlocoDeLembretes()                           // Cria a lista 
    {
        list = new ArrayList<>();
    }
    
       public Lembrete search(int position)             //Busca um Lembrete com base em determinado index
       {
        return list.get(position);
        }
       
       /* public Lembrete searchString(String search)
       {
           //return list.contains(search);
       }    */
    
    public void order(List<Lembrete> l)                                 //Ordena a lista com base no compareTo e no Comparable definidos na classe Lembrete
    {
        Collections.sort(l);
    }

    public void orderTitle(List<Lembrete> l)
    {
        Collections.sort(l, Lembrete.getComparadorTitulo());
    }

    
    public void insert(Lembrete l)                  //Add new note on the list
    {
        list.add(l);
    }
    
    public Lembrete remove(int position)        //delete an note with that index
    {
        return list.remove(position);
    }
    


    /* ####################################################### */
    /* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */
    //Nothing from here on down is used
    @Override
    public String toString() 
    {
        return  list.toString();
    }
    
     public LinkedList<Lembrete> searchForYear(int year) {                  //Método para buscar o lembrete de um determinado ano
        LinkedList<Lembrete> reminderOfTheYear = new LinkedList<>();         //Retorna um objeto do tipo Lembrete; 

        for (Lembrete lembrete : list)
        {
            if (lembrete.getData().getYear() == year) {
                reminderOfTheYear.add(lembrete);
            }
        }
        return reminderOfTheYear;
    }
    public LinkedList<Lembrete> searchForMonth(int month, int year) {         //Método para buscar o lembrete de um determinado mes
        LinkedList<Lembrete> reminderOfTheMonth = new LinkedList<>();         //Retorna um objeto do tipo Lembrete; 

        for (Lembrete lembrete : list)
        {
            if (lembrete.getData().getMonth() == month && lembrete.getData().getYear() == year) {
                reminderOfTheMonth.add(lembrete);
            }
        }
        return reminderOfTheMonth;
    }
        
    public LinkedList<Lembrete> searchForDay(int day, int month, int year) {                             //Método para buscar o lembrete de um determinado dia
        LinkedList<Lembrete> reminderOfTheMonth = new LinkedList<>();            //Retorna um objeto do tipo Lembrete; 
       // list = searchForMonth(month);
        for (Lembrete lembrete : list) 
        {
            if (lembrete.getData().getDay() == day && lembrete.getData().getMonth() == month && lembrete.getData().getYear() == year) {
                reminderOfTheMonth.add(lembrete);
            }
        }

        return reminderOfTheMonth;
    }
    
    public LinkedList<Lembrete> searchSubString(String text)
    {
        LinkedList<Lembrete> reminderSubString = new LinkedList<>();
        for(Lembrete lembrete : list)
        {
                if(lembrete.getDesc().contains(text))
                        reminderSubString.add(lembrete);
        }
        return reminderSubString;
    }
    
}

