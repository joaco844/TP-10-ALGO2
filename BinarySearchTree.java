/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class BinarySearchTree<E extends Comparable<E>> {

    protected TreeNode<E> root;
    protected int size = 0;


    public void insert(E value) {
        if (value == null) throw new IllegalArgumentException("El valor no puede ser nulo");
        if (contains(value))
            return; // no admitimos duplicados
        root = insertRecursive(root, value);
        size++;
    }

    public boolean remove(E value) {
        if (value == null) throw new IllegalArgumentException("El valor no puede ser nulo");
        if (!contains(value))
            return false;
        root = removeRecursive(root, value);
        size--;
        return true;
    }

    public boolean contains(E value) {
        return find(value) != null;
    }

    public E find(E value) {
        if (value == null) throw new IllegalArgumentException("El valor no puede ser nulo");
        TreeNode<E> node = findRecursive(root, value);
        if (node == null)
            return null;
        return node.value;
    }

    public int height() {
        return getHeightRecursive(root);
    }

    // Devuelve los elementos ordenados (recorrido in-order)
    public SimpleList<E> inOrder() {
        SimpleList<E> result = new SimpleArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        if (current == null)
            return new TreeNode<>(value);

        int cmp = value.compareTo(current.value);
        if (cmp < 0)
            current.left = insertRecursive(current.left, value);
        else if (cmp > 0)
            current.right = insertRecursive(current.right, value);

        return current;
    }

    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        if (current == null)
            return null;

        int cmp = value.compareTo(current.value);
        if (cmp < 0) {
            current.left = removeRecursive(current.left, value);
        } else if (cmp > 0) {
            current.right = removeRecursive(current.right, value);
        } else {
            if (current.left == null)
                return current.right;
            if (current.right == null)
                return current.left;
            TreeNode<E> successor = findMin(current.right);
            current.value = successor.value;
            current.right = removeRecursive(current.right, successor.value);
        }
        return current;
    }

    protected TreeNode<E> findRecursive(TreeNode<E> current, E value) {
        if (current == null)
            return null;

        int cmp = value.compareTo(current.value);
        if (cmp == 0)
            return current;
        if (cmp < 0)
            return findRecursive(current.left, value);
        return findRecursive(current.right, value);
    }

    protected TreeNode<E> findMin(TreeNode<E> current) {
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Altura = cantidad de aristas hasta la hoja mas lejana (null = -1, un nodo solo = 0)
    protected int getHeightRecursive(TreeNode<E> current) {
        if (current == null)
            return -1;
        return 1 + Math.max(getHeightRecursive(current.left), getHeightRecursive(current.right));
    }

    protected void inOrderRecursive(TreeNode<E> current, SimpleList<E> result) {
        if (current == null)
            return;
        inOrderRecursive(current.left, result);
        result.add(current.value);
        inOrderRecursive(current.right, result);
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }
}