/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hashlistconsole;

import java.util.Scanner;

/**
 *
 * @author vtrdelfino
 */
public class Functions{
    static Scanner input = new Scanner(System.in);
    static No auxiliar;
    static void novaPessoa(){
        No novo = new No();
        
        System.out.print("Digite o nome: ");
        novo.nome = input.next();
        
        do{
            System.out.print("""
                             Escolha a Prioridade.
                             0: Alta.
                             1: Baixa.
                             """);
            novo.prioridade = input.nextInt();
            String prioridadeInvalida = (novo.prioridade != 0 && novo.prioridade != 1) ? "Prioridade Invalida" : "";
            System.out.print(prioridadeInvalida);
        }while (novo.prioridade != 0 && novo.prioridade != 1);
        
        Chave(novo);
        
        auxiliar = No.hashArray[novo.hash];
        
        if(auxiliar == null){
            No.hashArray[novo.hash] = novo;
        }else{
            switch(novo.prioridade){
                case 0 -> {
                    if (No.hashArray[novo.hash].prioridade == 1){
                        No.hashArray[novo.hash] = novo;
                        novo.proximo = auxiliar;
                        auxiliar.anterior = novo;
                    }else{
                        do{
                            if(auxiliar.proximo == null){
                                auxiliar.proximo = novo;
                                novo.anterior = auxiliar;
                            }else if(auxiliar.proximo.prioridade == 1){
                                novo.anterior = auxiliar;
                                novo.proximo = auxiliar.proximo;
                                auxiliar.proximo.anterior = novo;
                                auxiliar.proximo = novo;
                                break;
                            }
                            auxiliar = auxiliar.proximo;
                        }while(auxiliar.proximo != null);
                    }
                }
                case 1 -> {
                    if(auxiliar.proximo == null){
                        auxiliar.proximo = novo;
                        novo.anterior = auxiliar;
                    }else{
                        while(auxiliar.proximo != null){
                            auxiliar = auxiliar.proximo;
                            if(auxiliar.proximo == null){
                                auxiliar.proximo = novo;
                                novo.anterior = auxiliar;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        System.out.printf("""
                          Nome: %s
                          Chave: %d
                          Prioridade: %d
                          Hash: %d
                          """, novo.nome, novo.chave, novo.prioridade, novo.hash);
    }
    static No Chave(No novo) {
        int ascii = 0, i = 0;
        char character;
        while(i < novo.nome.length()){
            character = novo.nome.charAt(i);
            ascii += character;
            i++;
        }
        novo.chave = ascii;
        novo.hash = ascii % 10;
        
        return novo;
    }
    static void remover(){
        No temporario = new No();
        No auxiliar = new No();
        
        System.out.print("Digite um nome para remover: ");
        temporario.nome = input.next();
        
        Chave(temporario);
        
        auxiliar = No.hashArray[temporario.hash];
        
        if(auxiliar != null){
            if(temporario.chave == auxiliar.chave){
                if(auxiliar.proximo != null){
                    No.hashArray[temporario.hash] = auxiliar.proximo;
                    if(No.hashArray[temporario.hash].anterior != null){
                        No.hashArray[temporario.hash].anterior = null;
                    }
                    System.out.print("Nome: " + temporario.nome + " removido.");
                }else{
                    No.hashArray[temporario.hash] = null;
                    System.out.print("Nome: " + temporario.nome + " removido.");
                }
            }else{
                try{
                    do{
                        if(temporario.chave == auxiliar.proximo.chave){
                            if(auxiliar.proximo.proximo != null){
                                auxiliar.proximo = auxiliar.proximo.proximo;
                            }
                            auxiliar.proximo.anterior.proximo = null;
                            if(auxiliar.proximo != null){
                                auxiliar.proximo.anterior = null;   
                                auxiliar.proximo.anterior = auxiliar;
                            }
                            System.out.print("Nome: " + temporario.nome + "removido.");
                            break;
                        }else{
                            auxiliar = auxiliar.proximo;
                            System.out.print("Nome: " + temporario.nome + "removido.");
                        }
                    }while(auxiliar.chave != temporario.chave);
                }catch(NullPointerException e){
                    System.out.print("Nome não encontrado.");
                }
            }
            if(No.hashArray[temporario.hash] == null){
                System.out.printf("Lista da hash %d vazia.", temporario.hash);
            }
        }else{
            System.out.print("Nome não encontrado.");
        }
    }
    static void litar(){
        int i = 0;
        
        for(No hash : No.hashArray){
            auxiliar = hash;
            
            System.out.print("Fila: " + i);
            
            if(auxiliar != null)
                System.out.print("  |");
            
            while(auxiliar != null){
                System.out.printf("""
                                  Nome: %s
                                  Chave: %d
                                  Prioridade: %d
                                  Hash: %d
                                  """, auxiliar.nome, auxiliar.chave, auxiliar.prioridade, auxiliar.hash);
                if(auxiliar.proximo != null){
                    System.out.print("  ||");
                }
                auxiliar = auxiliar.proximo;
            }
            i++;
            System.out.print("- - - - - - - - - -");
        }
    }
}
