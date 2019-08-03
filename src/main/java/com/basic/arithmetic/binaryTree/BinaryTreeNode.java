package com.basic.arithmetic.binaryTree;

/**
 * @author xsy
 * @create 2019-05-15 14:37
 * @desc 二叉树节点
 **/
public class BinaryTreeNode {
    /*
     * 一个二叉树包括 数据、左右孩子 三部分
     */
    private int mData;
    private BinaryTreeNode mLeftChild;
    private BinaryTreeNode mRightChild;

    public BinaryTreeNode(int data, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        mData = data;
        mLeftChild = leftChild;
        mRightChild = rightChild;
    }

    public int getData() {
        return mData;
    }

    public void setData(int data) {
        mData = data;
    }

    public BinaryTreeNode getLeftChild() {
        return mLeftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        mLeftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return mRightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        mRightChild = rightChild;
    }
}
