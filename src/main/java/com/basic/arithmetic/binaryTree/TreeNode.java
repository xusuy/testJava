package com.basic.arithmetic.binaryTree;

import java.util.ArrayDeque;

/**
 * @author xsy
 * @create 2019-05-20 20:29
 * @desc
 **/
public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    /**
     * value
     */
    private String value;


    public TreeNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    private static TreeNode root = new TreeNode("1");

    static {
        TreeNode left = new TreeNode("2");
        TreeNode right = new TreeNode("3");
        TreeNode left_left = new TreeNode("4");
        TreeNode left_right = new TreeNode("5");
        root.setLeft(left);
        root.setRight(right);
        left.setLeft(left_left);
        left.setRight(left_right);
    }

    /**
     * 深度优先遍历，相当于先根遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈(后进先出)
     * 利用栈的先进后出的特性，先将根节点入栈，栈不空时pop，然后右子树入栈，再左子树入栈。
     */
    public static void depthOrderTraversal() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.value + "    ");
            //先入右子树
            if (node.right != null) {
                stack.push(node.right);
            }
            //后入左子树
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        //深度遍历
        depthOrderTraversal();
        //广度遍历
        levelOrderTraversal();
    }

    //深度遍历：递归
    public void depthOrderTraversalWithRecursive() {
        depthTraversal(root);
    }

    private void depthTraversal(TreeNode treeNode) {
        if (treeNode != null && !treeNode.equals(null)) {
            System.out.print(treeNode.value + "  ");
            depthTraversal(treeNode.left);
            depthTraversal(treeNode.right);
        }
    }

    /**
     * 广度优先遍历
     * 采用非递归实现
     * 需要辅助数据结构：队列
     * 先将根入队，队列不空时pop，然后入左子树，再入右子树
     */
    public static void levelOrderTraversal() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            System.out.print(node.value + "    ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.print("\n");
    }

}
