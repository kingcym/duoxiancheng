package com.example.demo.shujujiegou.ch二叉树;

import com.example.demo.shujujiegou.ch4链表.Node;

import java.util.Comparator;

/**
 * @Author: Kingcym
 * @Description: 二叉树
 * @Date: 2017/11/24 9:55
 */
//public class BinaryTree<E extends Comparable<? super E>> {
public class BinaryTree<E> {
    // 根节点
    private BTNode<E> root;

    // 当前节点的父节点（作用于contain（E value）方法）
    public BTNode<E> currentParent;
    // 当前节点是否是其父节点的左节点（（作用于contain（E value）方法））
    public boolean isLeftChild = true;

    private Comparator<E> comparator;

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 获取根节点
     */
    public BTNode<E> getRoot() {
        return root;
    }

    /**
     * 插入数据
     * 比当前节点小的放左边，比当前节点大的放右边
     *
     * @param data 数据
     */
    public void insert(E data) {
        BTNode<E> newNode = new BTNode(data);
        if (root == null) {
            root = newNode;
        } else {
            BTNode<E> currentNote = root;
            BTNode<E> parent;
            int compareResult;
            while (true) {
                parent = currentNote;
                compareResult = comparator.compare(data, currentNote.getData());
                if (compareResult > 0) {
                    currentNote = currentNote.getRightChild();
                    if (currentNote == null) {
                        parent.setRightChild(newNode);
                        return;
                    }
                } else {
                    currentNote = currentNote.getLeftChild();
                    if (currentNote == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 查找节点
     * 从根节点开始查找，如果查找的值比当前节点小，
     * 则继续查找左字树，否则查找右子树
     *
     * @param data
     */
    public BTNode<E> contain(E data) {
        if (root == null) {
            return null;
        } else {
            BTNode<E> currentNote = root;
            int compareResult;
            while (currentNote != null) {
                compareResult = comparator.compare(data, currentNote.getData());
                if (compareResult > 0) {
                    currentParent = currentNote; //当前节点父节点
                    currentNote = currentNote.getRightChild();
                    isLeftChild = false;
                } else if (compareResult < 0) {
                    currentParent = currentNote; //当前节点父节点
                    currentNote = currentNote.getLeftChild();
                    isLeftChild = true;
                } else {
                    return currentNote;
                }
            }
            return null;
        }
    }

    /**
     * 删除节点
     * 1.该节点是叶子节点，没有子节点
     * 改变该节点的父节点的引用值，置为null
     * 2.该节点只有一个子节点
     * 将该节点的父节点的引用指向该节点的子节点
     * 3.该节点有两个节点
     *  使用中序后继节点代替当前节点
     *  中序后继节点：当前节点的右节点的左节点的左节点的左节点...（最接近当前节点的节点）
     */
    public boolean delete(E data) {
        //查询当前要被删除的节点
        BTNode<E> current = contain(data);
        if (current == null) return false; //未找到要删除的节点
        //1.删除节点是叶子节点，没有子节点
        if (current.getLeftChild() == null && current.getRightChild() == null) {
            if (current == root) {
                root = null; //删除节点是根节点
            } else {
                if (isLeftChild) {
                    currentParent.setLeftChild(null);
                } else {
                    currentParent.setRightChild(null);
                }
            }
            //3.该节点有两个节点
        } else if (current.getLeftChild() != null && current.getRightChild() != null) {
            //查出中序后继节点
            BTNode<E> replaceNode = getReplaceNode(current);
            replaceNode.setLeftChild(current.getLeftChild());
            if (current == root){ //删除节点是根节点
                root= replaceNode;
            } else {
                if (isLeftChild) {
                    currentParent.setLeftChild(replaceNode);
                } else {
                    currentParent.setRightChild(replaceNode);
                }
            }
        } else { //2.该节点只有一个子节点
            BTNode<E> child = current.getLeftChild() == null ? current.getRightChild() : current.getLeftChild();
            if (current == root) { //删除节点是根节点
                root = child;
            } else {
                if (isLeftChild) {
                    currentParent.setLeftChild(child);
                } else {
                    currentParent.setRightChild(child);
                }
            }
        }
        return true;
    }


    //查找替换的节点
    private BTNode<E> getReplaceNode(BTNode<E> delNode) {
        BTNode<E> current = delNode.getRightChild();
        BTNode<E> replaceNode = delNode; //替换节点
        BTNode<E> replaceNodeParent = replaceNode;
        while (current != null){
            replaceNodeParent = replaceNode;
            replaceNode = current;
            current = current.getLeftChild();
        }
        if (replaceNodeParent != delNode){
            replaceNodeParent.setLeftChild(replaceNode.getRightChild());
            replaceNode.setRightChild(delNode.getRightChild());
        }
        return replaceNode;
    }


    /**
     * 遍历二叉树
     */
    public void frontForEatch() {  //前
        frontForEatch(this.root);
    }

    public void midForEatch() {    //中
        midForEatch(this.root);
    }

    public void lastForEatch() {  //后
        lastForEatch(this.root);
    }

    /**
     * 前序遍历
     * 1.访问根节点
     * 2.前序访问左子树
     * 3.前序访问右子数
     */
    private void frontForEatch(BTNode currentNode) {
        if (currentNode != null) {
            //访问根节点
            System.out.print(currentNode.getData() + ",");
            //遍历左子树
            frontForEatch(currentNode.getLeftChild());
            //遍历右子树
            frontForEatch(currentNode.getRightChild());
        }
    }

    /**
     * 中序遍历 (遍历出来的数值是按照小到大的顺序)
     * 1.中序访问左子树
     * 2.访问根节点
     * 3.中序访问右子数
     */
    private void midForEatch(BTNode currentNode) {
        if (currentNode != null) {
            //遍历左子树
            midForEatch(currentNode.getLeftChild());
            //访问根节点
            System.out.print(currentNode.getData() + ",");
            //遍历右子树
            midForEatch(currentNode.getRightChild());
        }
    }

    /**
     * 后序遍历 (遍历出来的数值是按照小到大的顺序)
     * 1.后序访问左子树
     * 2.后序访问右子数
     * 3.访问根节点
     */
    private void lastForEatch(BTNode currentNode) {
        if (currentNode != null) {
            //遍历左子树
            lastForEatch(currentNode.getLeftChild());
            //遍历右子树
            lastForEatch(currentNode.getRightChild());
            //访问根节点
            System.out.print(currentNode.getData() + ",");
        }
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == null && o2 != null) {
                    return -1;
                }
                if (o2 == null && o1 != null) {
                    return 1;
                }
                if (o2 == null && o1 == null) {
                    return 0;
                }
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });


    }
}
