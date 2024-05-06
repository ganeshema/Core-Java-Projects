package realtimeexampleofsinglylinklist;
    //node class
class DoublyLinkNode<B> {
        B data; //generic type B
        DoublyLinkNode<B> next;  //reference to the next node in the list
        DoublyLinkNode<B> prev; //reference to the previous node in the list

        public DoublyLinkNode(B data) {  //constructor node class
            this.data = data;
            this.prev=null;
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
    class DoublyLink<B> {
        DoublyLinkNode<B> head;

        public DoublyLink() {
            this.head = null;
        }

        //Insert from the beginning
        public void addFromBeginning(B data) {
            DoublyLinkNode<B> newNode = new DoublyLinkNode<B>(data); //new object node of class node
            newNode.next = this.head;   //new node is pointing head of existing list
            newNode.prev=null; //now pointer of reference previous is pointing null as node became a head
            if (head != null) //change prev of head node to new node
                head.prev = newNode;
            this.head = newNode;  //now new node becomes head of the list
        }

       public void displayElements() {
            if (this.head == null) {
                System.out.println("The list is empty");
            }
           DoublyLinkNode<B> temp = this.head;
            while (temp != null) {
                System.out.print("<=="+temp.data + "==>");
                temp = temp.next;
            }
            System.out.print("null");
            System.out.println();
        }

        public void insertFromEnd(B data) {
            DoublyLinkNode<B> newNode = new DoublyLinkNode<>(data);
            DoublyLinkNode<B> temp=this.head;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=newNode;
            newNode.next=null;
            newNode.prev=temp;
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
            DoublyLinkNode<B> temp=this.head;
            DoublyLinkNode<B> seondLastElement=null; //initializing second last element of the list as null
            while(temp.next!=null){
                seondLastElement=temp;
                temp=temp.next;
            }
            seondLastElement.next=null; //pointing second last element to null
        }
    }

public class DoublyLinkedList {
        public static void main(String[] args) {
           DoublyLink<Integer> sl=new DoublyLink<>();
            sl.addFromBeginning(25);
            sl.addFromBeginning(30);
            sl.insertFromEnd(15);
            sl.displayElements();
            sl.deleteFromBeginning();
            sl.deleteFromLast();

            sl.displayElements();


        }
    }





