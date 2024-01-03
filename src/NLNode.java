import java.util.Comparator;
import java.util.Iterator;
public class NLNode <T> {

    private NLNode<T> parent;
    private ListNodes<NLNode<T>> children;
    private T data;
//    setting our private instance variables

    public NLNode() {
        this.parent = null;
        this.data = null;
        this.children = new ListNodes<NLNode<T>>();
//      making a new node
    }

    public NLNode(T d, NLNode<T> p) {
        this.children = new ListNodes<NLNode<T>>();
        this.data = d;
        this.parent = p;
//        making a new node initializing d and p
    }

    public void setParent(NLNode<T> p) {
        this.parent = p;
    }
//    setting this.p to parent

    public NLNode<T> getParent() {
        return this.parent;
//        the getter method for parent
    }

    public void addChild(NLNode<T> newChild) {
        newChild.setParent(this);
        this.children.add(newChild);
//        adding a child into the node by setting the parent equal to this node and then adding a new child to the current child.
    }

    public Iterator<NLNode<T>> getChildren() {
        return this.children.getList();
//        return the list of child nodes at that node
    }

    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
        return this.children.sortedList(sorter);
//        return the list of child nodes at that node but using sorter
    }

    public T getData() {
        return this.data;
//        get data returns this.data
    }
    public void setData(T d){
        this.data = d;
//        set data is a method used to set data to parameter d we chose
    }
}
