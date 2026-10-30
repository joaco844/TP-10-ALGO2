/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author joacodiaz
 */

public class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Entry<K, V> other) {
        return key.compareTo(other.key);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}