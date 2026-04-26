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

    public boolean search(String name) {
        return search(root, name) != null;
    }

    private Node search(Node current, String name) {
        if (current == null) {
            return null;
        }
        if (current.getPlayer().getNickname().equals(name)) {
            return current;
        }
        Node leftResult = search(current.getLeft(), name);
        if (leftResult != null) {
            return leftResult;
        }
        return search(current.getRight(), name);
    }

    public Player remove(String name) {
        Node found = search(root, name);
        if (found == null) {
            return null;
        }
        Player removed = found.getPlayer();
        root = remove(root, name);
        return removed;
    }

    private Node remove(Node current, String name) {
        Node target = search(current, name);
        if (target == null) {
            return current;
        }
        return removeByRanking(current, target.getPlayer().getRanking());
    }

    private Node removeByRanking(Node current, int ranking) {
        if (current == null) {
            return null;
        }
        if (ranking < current.getPlayer().getRanking()) {
            current.setLeft(removeByRanking(current.getLeft(), ranking));
            return current;
        }
        if (ranking > current.getPlayer().getRanking()) {
            current.setRight(removeByRanking(current.getRight(), ranking));
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
        current.setRight(removeByRanking(current.getRight(), successor.getPlayer().getRanking()));
        return current;
    }

    private Node findMin(Node current) {
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node current) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeft());
        Player p = current.getPlayer();
        System.out.println(p.getRanking() + " - " + p.getNickname());
        inOrder(current.getRight());
    }
}
