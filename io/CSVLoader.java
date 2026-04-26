package io;

import structures.BinarySearchTree;
import structures.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVLoader {
    public void load(String path, BinarySearchTree tree) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int ranking = Integer.parseInt(parts[1]);
                Player p = new Player(parts[0], ranking);
                tree.insert(p);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
