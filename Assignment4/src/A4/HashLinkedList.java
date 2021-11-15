package A4;
// Name: Yi Zhu
// ID: 260716006
public class HashLinkedList<K, V> {
	/*
	 * Fields
	 */
	private HashNode<K, V> head;

	private Integer size;

	/*
	 * Constructor
	 */

	HashLinkedList() {
		head = null;
		size = 0;
	}

	/*
	 * Add (Hash)node at the front of the linked list
	 */

	public void add(K key, V value) {
		// ADD CODE BELOW HERE
		// Add a new hashnode to the front of the linkedlist
		HashNode<K, V> newNode = new HashNode<K, V>(key, value);
		this.size++;
		if (this.head == null) {
			this.head = newNode;
		} else {
			newNode.next = this.head;
			this.head = newNode;
		}
		// ADD CODE ABOVE HERE
	}

	/*
	 * Get Hash(node) by key returns the node with key
	 */

	public HashNode<K, V> getListNode(K key) {
		// ADD CODE BELOW HERE
		// Search the whole linkedlist to find the node with the certain key 
		// If not found return null
		HashNode<K, V> ansNode = this.head;
		while (ansNode != null) {
			if (ansNode.getKey().equals(key)) {
				return ansNode;
			} else {
				ansNode = ansNode.next;
			}
		}
		return null;
		// ADD CODE ABOVE HERE

	}

	/*
	 * Remove the head node of the list Note: Used by remove method and next
	 * method of hash table Iterator
	 */

	public HashNode<K, V> removeFirst() {
		// ADD CODE BELOW HERE
		// If there is no head then return null, else remove and return the first node
		if (this.head == null) {
			return null;
		} else {
			HashNode<K, V> nodeToRemove = this.head;
			this.size--;
			this.head = this.head.next;
			nodeToRemove.next = null;
			return nodeToRemove;
		}
	}

	/*
	 * Remove Node by key from linked list
	 */

	public HashNode<K, V> remove(K key) {
		// ADD CODE BELOW HERE
		// Search the whole linkedlist to find the key and remove it and return it
		// If not found return null
		if (this.head == null) {
			return null;
		} else if (this.head.getKey().equals(key)) {
			this.size--;
			return this.removeFirst();
		} else {
			HashNode<K, V> tmp = this.head;
			while (tmp.next != null) {
				if (tmp.next.getKey().equals(key)) {
					this.size--;
					HashNode<K, V> ansNode = tmp.next;
					tmp.next = tmp.next.next;
					ansNode.next = null;
					return ansNode;
				}
				tmp = tmp.next;
			}
		}
		// ADD CODE ABOVE HERE
		return null;
	}

	/*
	 * Delete the whole linked list
	 */
	public void clear() {
		head = null;
		size = 0;
	}
	/*
	 * Check if the list is empty
	 */

	boolean isEmpty() {
		return size == 0 ? true : false;
	}

	int size() {
		return this.size;
	}

	// ADD YOUR HELPER METHODS BELOW THIS
	// The helper method getHead return allentries of the clone of the hashtable
	public HashNode<K, V> getHead() {
		if (this.head != null) {
			return (HashNode<K, V>) this.head.clone();
		} else {
			return null;
		}
	}

	// ADD YOUR HELPER METHODS ABOVE THIS

}
