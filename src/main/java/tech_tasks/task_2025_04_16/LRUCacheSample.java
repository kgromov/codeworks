package tech_tasks.task_2025_04_16;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheSample {
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head; // Dummy head
    private final Node tail; // Dummy tail

    public LRUCacheSample(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Initialize dummy head and tail nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        // Connect head and tail initially
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Get the node from cache
        Node node = cache.get(key);

        // Move the accessed node to the front (most recently used)
        removeNode(node);
        addToFront(node);

        return node.value;
    }

    public void put(int key, int value) {
        // If key exists, remove the old node
        if (cache.containsKey(key)) {
            removeNode(cache.get(key));
        }

        // Create a new node and add to front
        Node newNode = new Node(key, value);
        addToFront(newNode);
        cache.put(key, newNode);

        // Check if capacity is exceeded
        if (cache.size() > capacity) {
            // Remove the least recently used item (the one before tail)
            Node lruNode = tail.prev;
            removeNode(lruNode);
            cache.remove(lruNode.key);
        }
    }

    // Helper: Remove a node from the doubly linked list
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // Helper: Add a node to the front (after the dummy head)
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // Node class for the doubly linked list
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
}
