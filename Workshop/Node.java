package Workshop;

public class Node {
    public int currValue;
    public Node next;
    public Node prev;

    public Node(int value){
        this.currValue = value;
    }

    @Override
        public String toString(){
        return  "" + this.currValue;
    }
}
 