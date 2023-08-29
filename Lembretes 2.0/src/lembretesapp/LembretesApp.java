/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lembretesapp;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Christian
 */
public class LembretesApp {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);      //Variavel para scanner
        int search;
        int day, month, year;
        String desc;
        //Data d;
        Screen s = new Screen();
        
        BlocoDeLembretes b = new BlocoDeLembretes();    //Instancia um bloco 
        LinkedList<Lembrete> list = new LinkedList<>();       //Instancia  uma lista
        LembretesApp L = new LembretesApp();
        boolean exitSubMenu = false; // Um jeito para sair do subMenu de buscas 
        int mainmenu;
        int SearchMenu;
        for (;;) {
            mainmenu = L.menu();

            switch (mainmenu) {
                case 1 -> {             //Adiciona um novo Lembrete, escaneando e passando para o Construtor
                    System.out.println("Digite uma descricao para o Lembrete: ");
                    desc = scan.nextLine();
                    do {                //Verificação para Fevereiro 
                        do {            //Verificação de dia válido -> até 31 e no minimo 1
                            System.out.println("Digite um dia: ");
                            day = scan.nextInt();
                            if (day > 31 || day < 1) {
                                System.out.println("Digite um dia valido");
                            }
                        } while (day > 31 || day < 1);
                        do {            //Verificação de mês valido -> entre 1 e 12
                            System.out.println("Digite um mes: ");
                            month = scan.nextInt();
                            if (month > 12 || month < 1) {
                                System.out.println("Digite um mes valido");
                            }
                        } while (month > 12 || month < 1);
                        if(day > 29 && month == 2)
                            System.out.println("Fevereiro nao possui mais de 29 dias");
                    } while (month == 2 && day > 29);

                    System.out.println("Digite um ano: ");
                    year = scan.nextInt();
                    Data d = new Data(day, month, year);
                  //  b.insert(new Lembrete(desc, d));
                    scan.nextLine();
                    
                }
                case 2 -> {
                    System.out.println("Digite o indice do lembrete a ser removido: ");
                    search = scan.nextInt();
                    b.remove(search);
                }

                case 3 -> {            //Case de busca, faz as buscas conforme a escolha do usuário
                    SearchMenu = L.SearchMenu();
                    switch (SearchMenu) {
                        case 1 -> {
                            System.out.println("Digite uma Sub String para buscar: ");
                            desc = scan.nextLine();
                            list = b.searchSubString(desc);
                                if(list.isEmpty())
                                        System.out.println("Nao ha lembretes com essa Sub String");
                                else
                                  System.out.println("Lista retornada!");
                                scan.nextLine();
                        }
                        case 2 -> {
                            System.out.println("Digite um mes para buscar: ");
                            month = scan.nextInt();
                            System.out.println("Digite um ano para buscar: ");
                            year = scan.nextInt();
                            list = b.searchForMonth(month, year);
                            if (list.isEmpty()) {
                                System.out.println("Nao ha lembretes neste mes");
                                scan.nextLine();
                            } else {
                                System.out.println("Lista retornada!");
                                scan.nextLine();                                    //Estava bugando o enter
                            }
                        }
                        case 3 -> {
                            System.out.println("Digite um dia para buscar: ");
                            day = scan.nextInt();
                            System.out.println("Digite um mes para buscar: ");
                            month = scan.nextInt();
                            System.out.println("Digite um ano para buscar: ");
                            year = scan.nextInt();
                            list = b.searchForDay(day, month, year);
                            if (list.isEmpty()) {
                                System.out.println("Nao ha lembretes neste dia\n");
                                scan.nextLine();
                            } 
                            else {
                                System.out.println("Lista retornada!");
                                scan.nextLine();
                            }
                        }
                        case 4 ->
                            exitSubMenu = true;  // Para sair do menu, forma que encontrei para isto 
                        //Algum return ...
                    }
                }
                case 4 -> {      // Printa toda a lista
                  //for(Lembrete l: list)
                    System.out.println(b);
                }
                case 5-> {
                       b.order();
                       System.out.println(b);
                }
                case 6 -> {
                    System.out.println("Digite um mes para buscar: ");
                            month = scan.nextInt();
                            System.out.println("Digite um ano para buscar: ");
                            year = scan.nextInt();
                            list = b.searchForMonth(month, year);
                            if (list.isEmpty()) {
                                System.out.println("Nao ha lembretes neste mes");
                                scan.nextLine();
                            } 
                            else {
                                b.order();
                                System.out.println(list + "\n");
                                scan.nextLine();
                            }
                }
                case 7 -> {
                    System.out.println("Digite um dia para buscar: ");
                    day = scan.nextInt();
                    System.out.println("Digite um mes para buscar: ");
                    month = scan.nextInt();
                    System.out.println("Digite um ano para buscar: ");
                    year = scan.nextInt();
                    list = b.searchForDay(day, month, year);
                    if (list.isEmpty()) {
                        System.out.println("Nao ha lembretes neste dia especifico\n");
                        scan.nextLine();
                    } 
                    else {
                        b.order();
                        System.out.println(list + "\n");
                        scan.nextLine();
                    }
                }
                case 8-> {
                            System.out.println("Digite um ano para buscar: ");
                            year = scan.nextInt();
                            list = b.searchForYear(year);
                            if (list.isEmpty()) {
                                System.out.println("Nao ha lembretes neste ano");
                                scan.nextLine();
                            } 
                            else {
                                b.order();
                                System.out.println(list + "\n");
                                scan.nextLine();
                            }
                }
                case 9 -> {
                            System.out.println("Digite uma Sub String para buscar: ");
                            desc = scan.nextLine();
                            list = b.searchSubString(desc);
                                if(list.isEmpty())
                                        System.out.println("Nao ha lembretes com essa Sub String\n");
                                else
                               System.out.println(list);
                        }
                case 10-> {
                    System.exit(0);
                }
            }
        }
    }
    
    public int menu() {                 //Menu principal com as opções
        Scanner scan = new Scanner(System.in);
        int c;
        do {
            System.out.println("\t---------- BEM VINDO AO APLICATIVO DE LEMBRETES!! ----------");
            System.out.println("1- Inserir novo lembrete");
            System.out.println("2- Excluir um lembrete");
            System.out.println("3- Buscar lembrete");
            System.out.println("4- Imprimir todos os lembretes");
            System.out.println("5- Imprimir todos os lembretes em ordem cronologica");
            System.out.println("6- Imprimir todos os lembretes de um mes");
            System.out.println("7- Imprimir todos os lembretes de um dia");
            System.out.println("8- Imprimir todos os lembretes de um ano");
            System.out.println("9- Imprimir todos os lembretes contendo determinada Sub String");
            System.out.println("10- Sair da aplicacao");
            System.out.println("Digite sua opcao: ");
            c = scan.nextInt();
        } while (c < 0 || c > 10);       //Enquanto for digitado um caractere diferente do disponivel no menu, segue apresentado 
        scan.nextLine();                // Pegar enter
        return c;                            //Retorna o valor da escolha para usar no switch/case
    }

    
    
    public int SearchMenu() {       //Menu secundário para buscas de Strings por mês/dia/ subString de descrição
        Scanner scan = new Scanner(System.in);
        int c;
        do {
            System.out.println("----- Menu de Buscas -----");
            System.out.println("1- Buscar por uma SubString");
            System.out.println("2- Buscar por um mes especifico");
            System.out.println("3- Buscar por um dia especifico");
            System.out.println("4- Voltar para o menu anterior");
            System.out.println("Digite sua opcao: ");
            c = scan.nextInt();
        } while (c <= 0 || c > 4);  //Funciona igual o menu anterior 
        scan.nextLine();
        return c;
    }
}
