package io;

import structures.BinarySearchTree;
import structures.Player;
import Structures.MyArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVLoader {

    public static void load(String path, BinarySearchTree tree) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            MyArrayList<String> lines = new MyArrayList<>(128);

            reader.readLine();

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
