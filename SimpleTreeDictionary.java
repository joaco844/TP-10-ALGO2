/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joacodiaz
 */
public class SimpleTreeDictionary<K extends Comparable<K>, V> implements SimpleDictionary<K, V> {

    private BinarySearchTree<Entry<K, V>> tree = new AVLTree<>();

    @Override
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");

        Entry<K, V> existing = tree.find(new Entry<>(key, null));
        if (existing != null) {
            V previous = existing.value;
            existing.value = value;
            return previous;
        }

        tree.insert(new Entry<>(key, value));
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");
        return tree.remove(new Entry<>(key, null));
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");
        return tree.contains(new Entry<>(key, null));
    }

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");
        Entry<K, V> entry = tree.find(new Entry<>(key, null));
        if (entry == null)
            return null;
        return entry.value;
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public SimpleList<K> keys() {
        SimpleList<Entry<K, V>> entries = tree.inOrder();
        SimpleList<K> result = new SimpleArrayList<>();
        for (int i = 0; i < entries.size(); i++)
            result.add(entries.get(i).key);
        return result;
    }

    @Override
    public SimpleList<V> values() {
        SimpleList<Entry<K, V>> entries = tree.inOrder();
        SimpleList<V> result = new SimpleArrayList<>();
        for (int i = 0; i < entries.size(); i++)
            result.add(entries.get(i).value);
        return result;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public String toString() {
        return tree.toString();
    }
}