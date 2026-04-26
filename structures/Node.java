package structures;

public class Node {
    private Player player;
    private Node left;
    private Node right;

    public Node(Player player){
        this.player = player;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Player getPlayer() {
        return player;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    void setRight(Node right) {
        this.right = right;
    }

    void setPlayer(Player player) {
        this.player = player;
    }
}
