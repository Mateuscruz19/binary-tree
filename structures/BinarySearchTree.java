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

    public Player remove(Player p) {
        Node found = search(root, p);
        if (found == null) {
            return null;
        }
        Player removed = found.getPlayer();
        root = remove(root, p);
        return removed;
    }

    private Node remove(Node current, Player p) {
        if (current == null) {
            return null;
        }
        if (p.getRanking() < current.getPlayer().getRanking()) {
            current.setLeft(remove(current.getLeft(), p));
            return current;
        }
        if (p.getRanking() > current.getPlayer().getRanking()) {
            current.setRight(remove(current.getRight(), p));
            return current;
        }

        if (current.getLeft() == null) {
            return current.getRight();
        }
        if (current.getRight() == null) {
            return current.getLeft();
        }

        Node successor = findMin(current.getRight());
        current.setPlayer(successor.getPlayer());
        current.setRight(remove(current.getRight(), successor.getPlayer()));
        return current;
    }

    private Node findMin(Node current) {
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

}
