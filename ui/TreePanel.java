package ui;

import structures.BinarySearchTree;
import structures.Node;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

public class TreePanel extends JPanel {
    private final BinarySearchTree tree;
    private Node highlightedNode;
    private boolean highlightVisible = true;
    private final int canvasWidth = 8000;
    private final int canvasHeight = 1500;
    private final int initialOffset = 1800;
    private final int verticalGap = 80;
    private final int nodeWidth = 75;
    private final int nodeHeight = 32;
    private final Color defaultFill = new Color(135, 206, 250);
    private final Color highlightFill = new Color(255, 200, 60);

    public TreePanel(BinarySearchTree tree) {
        this.tree = tree;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    public int getRootX() {
        return canvasWidth / 2;
    }

    public void centerOnNode(Node target) {
        if (target == null) return;
        int targetX = findNodeX(tree.getRoot(), target, canvasWidth / 2, initialOffset);
        if (targetX < 0) return;
        JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, this);
        if (scrollPane == null) return;
        int viewportWidth = scrollPane.getViewport().getWidth();
        int x = Math.max(0, targetX - viewportWidth / 2);
        int currentY = scrollPane.getViewport().getViewPosition().y;
        scrollPane.getViewport().setViewPosition(new Point(x, currentY));
    }

    private int findNodeX(Node current, Node target, int x, int xOffset) {
        if (current == null) return -1;
        if (current == target) return x;
        int leftResult = findNodeX(current.getLeft(), target, x - xOffset, xOffset / 2);
        if (leftResult >= 0) return leftResult;
        return findNodeX(current.getRight(), target, x + xOffset, xOffset / 2);
    }

    public void clearHighlight() {
        this.highlightedNode = null;
        this.highlightVisible = true;
        repaint();
    }

    public void blinkNode(Node node) {
        this.highlightedNode = node;
        this.highlightVisible = true;
        repaint();
        final int[] count = {0};
        Timer timer = new Timer(280, null);
        timer.addActionListener(e -> {
            highlightVisible = !highlightVisible;
            repaint();
            count[0]++;
            if (count[0] >= 6) {
                timer.stop();
                highlightVisible = true;
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Node root = tree.getRoot();
        if (root == null) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        drawNode(g2, root, canvasWidth / 2, 50, initialOffset);
    }

    private void drawNode(Graphics2D g, Node node, int x, int y, int xOffset) {
        if (node == null) {
            return;
        }

        int childY = y + verticalGap;

        if (node.getLeft() != null) {
            int leftX = x - xOffset;
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y, leftX, childY);
            drawNode(g, node.getLeft(), leftX, childY, xOffset / 2);
        }

        if (node.getRight() != null) {
            int rightX = x + xOffset;
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y, rightX, childY);
            drawNode(g, node.getRight(), rightX, childY, xOffset / 2);
        }

        int circleX = x - nodeWidth / 2;
        int circleY = y - nodeHeight / 2;

        boolean isHighlighted = (node == highlightedNode) && highlightVisible;
        g.setColor(isHighlighted ? highlightFill : defaultFill);
        g.fillOval(circleX, circleY, nodeWidth, nodeHeight);
        g.setColor(Color.BLACK);
        g.drawOval(circleX, circleY, nodeWidth, nodeHeight);

        String name = node.getPlayer().getNickname();
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(name);
        int textX = x - textWidth / 2;
        int textY = y + fm.getAscent() / 2 - 2;
        g.drawString(name, textX, textY);
    }
}
