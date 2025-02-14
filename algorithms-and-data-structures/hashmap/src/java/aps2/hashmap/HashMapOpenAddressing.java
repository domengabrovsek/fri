package aps2.hashmap;

/**
 * Hash map with open addressing.
 */
public class HashMapOpenAddressing {
	private Element table[]; // table content, if element is not present, use Integer.MIN_VALUE for Element's key
	private HashFunction.HashingMethod h;
	private CollisionProbeSequence c;

	public static enum CollisionProbeSequence {
		LinearProbing,    // new h(k) = (h(k) + i) mod m
		QuadraticProbing, // new h(k) = (h(k) + i^2) mod m
		DoubleHashing     // new h(k) = (h(k) + i*h(k)) mod m
	};
	
	public HashMapOpenAddressing(int m, HashFunction.HashingMethod h, CollisionProbeSequence c) {
		this.table = new Element[m];
		this.h = h;
		this.c = c;
		
		// init empty slot as MIN_VALUE
		for (int i=0; i<m; i++) {
			table[i] = new Element(Integer.MIN_VALUE, "");
		}
	}

	public Element[] getTable() {
		return this.table;
	}
	
	/**
	 * If the element doesn't exist yet, inserts it into the set.
	 * 
	 * @param k Element key
	 * @param v Element value
	 * @return true, if element was added; false otherwise.
	 */
	public boolean add(int k, String v) {
		if (contains(k)) {
			return false;
		} else {
			for (int i = 1; i < table.length; i++) {
				if (table[i].key == Integer.MIN_VALUE) {
					table[i].key = k;
					table[i].value = v;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes the element from the set.
	 * 
	 * @param k Element key
	 * @return true, if the element was removed; otherwise false
	 */
	public boolean remove(int k) {
		if (contains(k) == false) {
			return false;
		} else {
			for (int i = 1; i < table.length; i++) {
				if (table[i].key == k) {
					table[i].key = Integer.MIN_VALUE;
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Finds the element.
	 * 
	 * @param k Element key
	 * @return true, if the element was found; false otherwise.
	 */
	public boolean contains(int k) {
		for (int i = 0; i < table.length; i++) {
			if (table[i].key == k) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Maps the given key to its value, if the key exists in the hash map.
	 * 
	 * @param k Element key
	 * @return The value for the given key or null, if such a key does not exist.
	 */
	public String get(int k) {
		for (int i = 0; i < table.length; i++) {
			if (table[i].key == k) {
				return table[i].value;
			}
		}
		return null;
	}
}

