package com.example.learning.java;

public class Test26 implements Cloneable {

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        printLinkedList(linkedList);
        printLinkedList2(linkedList);
        Utils.println();

        linkedList.addAtTail(3);
        printLinkedList(linkedList);
        printLinkedList2(linkedList);
        Utils.println();

        linkedList.addAtIndex(1, 2);
        printLinkedList(linkedList);
        printLinkedList2(linkedList);
        Utils.println(linkedList.get(1));
        Utils.println();


        linkedList.deleteAtIndex(1);
        Utils.println(linkedList.get(1));
        printLinkedList(linkedList);
        printLinkedList2(linkedList);
    }

    private static void printLinkedList(MyLinkedList linkedList) {
        int count = linkedList.getCount();
        for (int i = 0; i < count; i++) {
            Utils.print(linkedList.get(i) + " -> ");
        }

        Utils.println("NULL");
    }

    private static void printLinkedList2(MyLinkedList linkedList) {
        Utils.print("NULL");

        for (int i = linkedList.getCount() - 1; i >= 0; i--) {
            Utils.print(" <- " + linkedList.get(i));
        }

        Utils.println();
    }

    static class MyLinkedList {
        private Node head;
        private Node tail;
        private int count;

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {
            this.head = null;
            this.tail = null;
            this.count = 0;
        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public int get(int index) {
            if (index >= count || index < 0) {
                return -1;
            }

            int mid = count / 2;
            Node p;
            if (index < mid) {
                int i = 0;
                p = head;
                while (i < index) {
                    p = p.next;
                    i++;
                }
            } else {
                int i = count - 1;
                p = tail;
                while (i > index) {
                    p = p.pre;
                    i--;
                }
            }

            return p.val;
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            Node node = new Node(val);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.pre = node;
                head = node;
            }
            count++;
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            Node node = new Node(val);
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                node.pre = tail;
                tail.next = node;
                tail = node;
            }

            count++;
        }

        public int getCount() {
            return count;
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public void addAtIndex(int index, int val) {
            if (index > count || index < 0) {
                return;
            }

            if (index == count) {
                addAtTail(val);
                return;
            }

            if (index == 0) {
                addAtHead(val);
                return;
            }

            int mid = count / 2;
            Node p;
            if (index < mid) {
                int i = 0;
                p = head;
                while (i < index) {
                    p = p.next;
                    i++;
                }
            } else {
                int i = count - 1;
                p = tail;
                while (i > index) {
                    p = p.pre;
                    i--;
                }
            }

            Node node = new Node(p.val);
            p.val = val;
            if (p.next == null) {
                tail = node;
            }else {
                node.next = p.next;
                p.next.pre = node;
            }
            p.next = node;
            node.pre = p;

            count++;
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            if (index >= count || index < 0) {
                return;
            }

            int mid = count / 2;
            Node p;
            if (index < mid) {
                int i = 0;
                p = head;
                while (i < index) {
                    p = p.next;
                    i++;
                }
            } else {
                int i = count - 1;
                p = tail;
                while (i > index) {
                    p = p.pre;
                    i--;
                }
            }

            if (p.pre == null) {
                head = p.next;
                if (head != null) {
                    head.pre = null;
                }
                p.next = null;
            } else if (p.next == null) {
                tail = p.pre;
                tail.next = null;
                p.pre = null;
            } else {
                p.pre.next = p.next;
                p.next.pre = p.pre;
                p.next = null;
                p.pre = null;
            }

            count--;
        }

        class Node {
            int val;
            Node next;
            Node pre;

            Node(int val) {
                this.val = val;
            }
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
