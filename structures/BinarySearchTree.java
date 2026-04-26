package structures;

public class BinarySearchTree {
    private Node root;

    public void insert(Player p) {
        root = insert(root, p);
    }

    private Node insert(Node current, Player p) {
        if (current == null) {
            return new Node(p);
        }
        if (p.getRanking() < current.getPlayer().getRanking()) {
            current.setLeft(insert(current.getLeft(), p));
        } else {
            current.setRight(insert(current.getRight(), p));
        }
        return current;
    }
}
