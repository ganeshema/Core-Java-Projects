package realtimeexampleofsinglylinklist;

//node class
class Node<A> {
    A data; //generic type A
    Node<A> next;  //reference to the next node in the list

    public Node(A data) {  //constructor node class
        this.data = data;
        this.next=null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", Next=" + next +
                '}';
    }
}
class SinglyLink<A> {
    private Node<A> head;

    public SinglyLink() {
        this.head = null;
    }

    //Insert from the beginning
    public void addFromBeginning(A data) {
        Node<A> newNode = new Node<>(data); //new object node of class node
        newNode.next = this.head;   //new node is pointing head of existing list
        this.head = newNode;  //now new node becomes head of the list
    }

    public void displayElements() {
        if (this.head == null) {
            System.out.println("The list is empty");
        }
        Node<A> temp = this.head;
        while (temp != null) {
            System.out.print(temp.data + "==>");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public void insertFromEnd(A data) {
        Node<A> newNode = new Node<>(data);
        Node<A> temp=this.head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=newNode;
        newNode.next=null;
    }
    public void deleteFromBeginning(){
       if(this.head==null){
           System.out.println("The list is empty");
       }
       this.head=head.next;
    }
    public void deleteFromLast(){
        if(this.head==null){
            System.out.println("the list is empty");
        }
        Node<A> temp=this.head;
        Node<A> seondLastElement=null; //initializing second last element of the list as null
        while(temp.next!=null){
            seondLastElement=temp;
            temp=temp.next;
        }
        seondLastElement.next=null; //pointing second last element to null
    }
}

public class SinglyLinkList{
    public static void main(String[] args) {
        SinglyLink<Integer> sl=new SinglyLink<>();
        sl.addFromBeginning(25);
        sl.addFromBeginning(50);
        sl.addFromBeginning(10);
        sl.addFromBeginning(5);
        sl.addFromBeginning(40);
        sl.insertFromEnd(15);
        sl.insertFromEnd(60);
        sl.deleteFromBeginning();
        sl.deleteFromLast();
        sl.displayElements();

    }
}



