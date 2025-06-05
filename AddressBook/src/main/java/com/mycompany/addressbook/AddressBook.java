package com.mycompany.addressbook;

import java.io.*;
import java.util.*;

public class AddressBook {
    private Map<String, String> contacts = new HashMap<>();
    private final String fileName;

    public AddressBook(String fileName) {
        this.fileName = fileName;
    }

    public void load() {
        contacts.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    contacts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
        }
    }

    // Guardar contactos al archivo CSV
    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos.");
        }
    }

    public void list() {
        System.out.println("Contactos (ordenados por nombre):");

        List<Map.Entry<String, String>> sortedList = new ArrayList<>(contacts.entrySet());
        sortedList.sort(Comparator.comparing(Map.Entry::getValue, String.CASE_INSENSITIVE_ORDER));

        for (Map.Entry<String, String> entry : sortedList) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Crear nuevo contacto
    public void create(String number, String name) {
        contacts.put(number, name);
        save();
    }

    // Eliminar un contacto
    public void delete(String number) {
        if (contacts.remove(number) != null) {
            System.out.println("Contacto eliminado.");
            save();
        } else {
            System.out.println("No se encontró el contacto.");
        }
    }
}