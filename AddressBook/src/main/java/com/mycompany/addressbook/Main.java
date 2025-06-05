package com.mycompany.addressbook;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook agenda = new AddressBook("agenda.csv");
        agenda.load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nAgenda Telefonica");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agenda.list();
                    break;
                case 2:
                    System.out.print("Ingrese el numero: ");
                    String numero = sc.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String nombre = sc.nextLine();
                    agenda.create(numero, nombre);
                    break;
                case 3:
                    System.out.print("Ingrese el numero a eliminar: ");
                    String numEliminar = sc.nextLine();
                    agenda.delete(numEliminar);
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
