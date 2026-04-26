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

    public boolean search(Player p) {
        return search(root, p) != null;
    }

    private Node search(Node current, Player p) {
        if (current == null) {
            return null;
        }
        if (p.getRanking() == current.getPlayer().getRanking()) {
            return current;
        }
        if (p.getRanking() < current.getPlayer().getRanking()) {
            return search(current.getLeft(), p);
        }
        return search(current.getRight(), p);
    }

}
