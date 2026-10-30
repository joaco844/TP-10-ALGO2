/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    @Override
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        current = super.insertRecursive(current, value);
        return balance(current);
    }

    @Override
    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        current = super.removeRecursive(current, value);
        return balance(current);
    }

    // Factor de balanceo = altura subarbol izquierdo - altura subarbol derecho
    protected int getBalanceFactor(TreeNode<E> current) {
        if (current == null)
            return 0;
        return getHeightRecursive(current.left) - getHeightRecursive(current.right);
    }

    protected TreeNode<E> balance(TreeNode<E> current) {
        if (current == null)
            return null;

        int fb = getBalanceFactor(current);

        if (fb > 1) {
            if (getBalanceFactor(current.left) < 0)        
                current.left = rotateLeft(current.left);
            return rotateRight(current);                  
        }


        if (fb < -1) {
            if (getBalanceFactor(current.right) > 0)          
                current.right = rotateRight(current.right);
            return rotateLeft(current);                        
        }

        return current;
    }

    protected TreeNode<E> rotateRight(TreeNode<E> y) {
        TreeNode<E> x = y.left;
        TreeNode<E> t2 = x.right;
        x.right = y;
        y.left = t2;
        return x;
    }

    protected TreeNode<E> rotateLeft(TreeNode<E> x) {
        TreeNode<E> y = x.right;
        TreeNode<E> t2 = y.left;
        y.left = x;
        x.right = t2;
        return y;
    }
}
