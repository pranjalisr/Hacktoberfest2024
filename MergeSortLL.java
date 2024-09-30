// Java program for merge sort on doubly 
// linked list

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class GfG {

    // Function to split the doubly 
   // linked list into twohalves
    static Node split(Node head) {
        Node fast = head;
        Node slow = head;

        // Move fast pointer two steps and slow pointer one
        // step until fast reaches the end
        while (fast != null && fast.next != null
               && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // Split the list into two halves
        Node temp = slow.next;
        slow.next = null;
        if (temp != null) {
            temp.prev = null;
        }
        return temp;
    }

    // Function to merge two sorted doubly linked lists
    static Node merge(Node first, Node second) {

        // If either list is empty, return the other list
        if (first == null)
            return second;
        if (second == null)
            return first;

        // Pick the smaller value between first and second
        // nodes
        if (first.data < second.data) {

            // Recursively merge the rest of the lists and
            // link the result to the current node
            first.next = merge(first.next, second);
            if (first.next != null) {
                first.next.prev = first;
            }
            first.prev = null;
            return first;
        }
        else {
            // Recursively merge the rest of the lists and
            // link the result to the current node
            second.next = merge(first, second.next);
            if (second.next != null) {
                second.next.prev = second;
            }
            second.prev = null;
            return second;
        }
    }

    // Function to perform merge sort on 
      // a doubly linked list
    static Node MergeSort(Node head) {
      
        // Base case: if the list is empty or has only one
        // node, it's already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node second = split(head);

        // Recursively sort each half
        head = MergeSort(head);
        second = MergeSort(second);

        // Merge the two sorted halves
        return merge(head, second);
    }

    static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Create a hard-coded doubly linked list:
        // 10 <-> 8 <-> 5 <-> 2
        Node head = new Node(10);
        head.next = new Node(8);
        head.next.prev = head;
        head.next.next = new Node(5);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(2);
        head.next.next.next.prev = head.next.next;

        head = MergeSort(head);

        printList(head);
    }
}
