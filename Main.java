import structures.BinarySearchTree;
import structures.Node;
import structures.Player;
import io.CSVLoader;
import ui.TreePanel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Point;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        CSVLoader loader = new CSVLoader();
        loader.load("players.csv", tree);

        JFrame frame = new JFrame("Ranking de Jogadores - ABB");
        TreePanel panel = new TreePanel(tree);
        JScrollPane scroll = new JScrollPane(panel);

        JPanel topBar = new JPanel();
        JButton btnInsert = new JButton("Inserir");
        JButton btnSearch = new JButton("Buscar");
        JButton btnRemove = new JButton("Remover");
        JButton btnLoad = new JButton("Carregar CSV");
        topBar.add(btnInsert);
        topBar.add(btnSearch);
        topBar.add(btnRemove);
        topBar.add(btnLoad);

        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.setSize(1200, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        scroll.getViewport().setViewPosition(new Point(panel.getRootX() - 600, 0));

        btnInsert.addActionListener(e -> {
            String nickname = JOptionPane.showInputDialog(frame, "Nickname:");
            if (nickname == null || nickname.trim().isEmpty()) return;
            String rankingStr = JOptionPane.showInputDialog(frame, "Ranking (numero inteiro):");
            if (rankingStr == null) return;
            try {
                int ranking = Integer.parseInt(rankingStr.trim());
                tree.insert(new Player(nickname.trim(), ranking));
                panel.clearHighlight();
                panel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ranking invalido: " + rankingStr);
            }
        });

        btnSearch.addActionListener(e -> {
            String nickname = JOptionPane.showInputDialog(frame, "Nickname para buscar:");
            if (nickname == null || nickname.trim().isEmpty()) return;
            Node found = tree.findNode(nickname.trim());
            if (found == null) {
                panel.clearHighlight();
                JOptionPane.showMessageDialog(frame, "Jogador nao encontrado.");
            } else {
                panel.blinkNode(found);
                SwingUtilities.invokeLater(() -> panel.centerOnNode(found));
            }
        });

        btnRemove.addActionListener(e -> {
            String nickname = JOptionPane.showInputDialog(frame, "Nickname para remover:");
            if (nickname == null || nickname.trim().isEmpty()) return;
            Player removed = tree.remove(nickname.trim());
            panel.clearHighlight();
            panel.repaint();
            if (removed == null) {
                JOptionPane.showMessageDialog(frame, "Jogador nao encontrado.");
            } else {
                JOptionPane.showMessageDialog(frame, "Removido: " + removed);
            }
        });

        btnLoad.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(new File("."));
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                tree.clear();
                loader.load(chooser.getSelectedFile().getAbsolutePath(), tree);
                panel.clearHighlight();
                panel.repaint();
            }
        });

    }
}
