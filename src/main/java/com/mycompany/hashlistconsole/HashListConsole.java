/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hashlistconsole;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author vtrdelfino
 */
public class HashListConsole {
    
    static void menu() {
        System.out.print("""
                         - - - - - - - - - -
                         1) Inserir Pessoa.
                         2) Remover Pessoa.
                         3) Listar Pessoas.
                         4) Sair.
                         - - - - - - - - - -
                         """);
}
    public static void main(String[] args) {
        int opcao = 0;
        
        while (opcao != 4) {
            menu();
            Scanner inputOpcao = new Scanner(System.in);
            
            try {
                opcao = inputOpcao.nextInt();
            } catch (InputMismatchException e){                
            }
            
            switch (opcao) {
                case 1 -> {
                    Functions.novaPessoa();
                }
                case 2 -> {
                    Functions.remover();
                }
                case 3 -> {
                    Functions.litar();
                }
                case 4 -> System.out.println("- - Saindo... - -");
                default -> System.out.println("- - Opção Inválida... - -");
            }
        }
    }
}
