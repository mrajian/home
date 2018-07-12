package home.app.prepare.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author liangyongjian
 * @desc 二叉树
 * @create 2018-05-19 16:54
 **/
public class BinaryTree {

    public static void main(String[] args) {

        // 以一棵完全二叉树为例，构造二叉树，并实现各种遍历
        // 这个初始数组是按照广度优先遍历的方式，将二叉树各个节点放入数组中
        // 对于完全二叉树，若某个非叶子结点的下标为i，则其左子结点的下标为 2i+1，右子结点的下标为 2i+2
        int[] treeValue = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<BinaryTreeNode> binaryTree = new ArrayList<BinaryTreeNode>();

        //将所有的结点保存进一个List集合里面
        for (int i = 0; i < treeValue.length; i++) {
            BinaryTreeNode treeNode = new BinaryTreeNode(treeValue[i]);
            binaryTree.add(treeNode);
        }
        
        // 根据完全二叉树的性质，i结点的左孩子结点是2*i+1，右孩子结点字数是2*i+2
        // 这里，i的取值，最大取到 数组长度的一半减1
        for (int i = 0; i < binaryTree.size() / 2; i++) {
            BinaryTreeNode treeNode = binaryTree.get(i);
            //判断左子树是否越界
            if ((i * 2 + 1) < binaryTree.size()) {
                BinaryTreeNode leftTreeNode = binaryTree.get(i * 2 + 1);
                treeNode.setLeftChild(leftTreeNode);
            }
            //判断右子树是否越界
            if ((i * 2 + 2) < binaryTree.size()) {
                BinaryTreeNode rightTreeNode = binaryTree.get(i * 2 + 2);
                treeNode.setRightChild(rightTreeNode);
            }
        }

        // 通过上面的逻辑，binaryTree 目前已经称为一棵完全二叉树，下面开始各种遍历
        // 递归实现的前序、中序、后序遍历，非递归（迭代）实现的前序、中序、后序遍历，以及广度优先遍历

        // 递归实现 前序遍历 结果是 [0 1 3 7 8 4 2 5 6]
        System.out.print("递归实现 前序遍历: ");
        dlrOrderByRecursive(binaryTree.get(0));

        System.out.println();
        // 迭代实现 前序遍历 结果是 [0 1 3 7 8 4 2 5 6]
        System.out.print("迭代实现 前序遍历: ");
        dlrOrderByStack(binaryTree.get(0));

        System.out.println();

        // 递归实现 中序遍历 结果是 [7 3 8 1 4 0 5 2 6]
        System.out.print("递归实现 中序遍历: ");
        ldrOrderByRecursive(binaryTree.get(0));

        System.out.println();
        System.out.print("迭代实现 中序遍历: ");
        ldrOrderByStack(binaryTree.get(0));

        System.out.println();

        // 递归实现 后序遍历 结果是 [7 8 3 4 1 5 6 2 0]
        System.out.print("递归实现 后序遍历: ");
        lrdOrderByRecursive(binaryTree.get(0));
        System.out.println();
        System.out.print("迭代实现 后序遍历: ");
        lrdOrderByTowStack(binaryTree.get(0));


        System.out.println();
        // 广度优先遍历 采用先进先出的队列方式实现
        System.out.print("队列 广度优先遍历: ");
        layerTraversal(binaryTree.get(0));

    }

    /**
     * 递归实现 前序遍历(DLR) 根左右
     * @param node
     */
    private static void dlrOrderByRecursive(BinaryTreeNode node) {
        if (node == null)
            return;
        // 输出根结点
        System.out.print(node.getValue() + " ");
        // 遍历左子树
        dlrOrderByRecursive(node.getLeftChild());
        // 遍历右子树
        dlrOrderByRecursive(node.getRightChild());
    }

    /**
     * 非递归方式（迭代实现）实现 前序遍历 根左右
     * @param node
     */
    private static void dlrOrderByStack(BinaryTreeNode node) {
        if (node == null)
            return;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(node);
        while (!stack.isEmpty()) {
            BinaryTreeNode cur = stack.pop();     // 出栈栈顶元素
            System.out.print(cur.getValue() + " ");

            // 先压右子树入栈
            if(cur.getRightChild() != null){
                stack.push(cur.getRightChild());
            }
            // 再压左子树入栈
            if(cur.getLeftChild() != null){
                stack.push(cur.getLeftChild());
            }
        }
    }

    /**
     * 递归实现 中序遍历(LDR) 左根右
     * @param node
     */
    private static void ldrOrderByRecursive(BinaryTreeNode node) {
        if (node == null)
            return;
        // 遍历左子树
        ldrOrderByRecursive(node.getLeftChild());
        // 新输出根结点
        System.out.print(node.getValue() + " ");
        // 遍历右子树
        ldrOrderByRecursive(node.getRightChild());
    }

    /**
     * 非递归方式（迭代实现）实现 中序遍历 左根右
     * @param root
     */
    private static void ldrOrderByStack(BinaryTreeNode root) {

        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = root;
        while (node != null || !stack.isEmpty()) {

            // 先将左子树入栈
            while (node != null) {
                stack.push(node);
                node = node.getLeftChild();
            }

            // 访问栈中元素，并且再将右子树在下个循环中入栈
            if (!stack.isEmpty()) {
                BinaryTreeNode cur = stack.pop();
                System.out.print(cur.getValue() + " ");
                node = cur.getRightChild();
            }
        }
    }

    /**
     * 递归实现 后序遍历 左右根
     * @param node
     */
    private static void lrdOrderByRecursive(BinaryTreeNode node) {
        if (node == null)
            return;
        // 遍历左子树
        lrdOrderByRecursive(node.getLeftChild());
        // 遍历右子树
        lrdOrderByRecursive(node.getRightChild());
        // 新输出根结点
        System.out.print(node.getValue() + " ");
    }

    /**
     * 非递归方式（迭代实现）实现 后序遍历 左右根
     * 使用单栈的方式逻辑 太难以理解，采用双栈的方式，一个栈负责不断出入，另一个能维护好后序遍历的顺序
     * @param root
     */
    private static void lrdOrderByTowStack(BinaryTreeNode root) {

        Stack<BinaryTreeNode> temp = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = root;
        while (node != null || !temp.isEmpty()) {

            // 先将右子树入栈
            while (node != null) {
                temp.push(node);
                stack.push(node);
                node = node.getRightChild();
            }

            // 处理栈顶节点的左子树
            if (temp.size() > 0) {
                node = temp.pop();
                node = node.getLeftChild();
            }
        }

        // 现在stack已经维护好了后序遍历的顺序
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getValue() + " ");
        }
    }

    /**
     * 广度优先遍历 采用队列的方式实现遍历
     * 队列是先进先出的模式
     * @param root
     */
    private static void layerTraversal(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // 取出最新入队列的元素，并从队列中删除
            BinaryTreeNode node = queue.poll();
            System.out.print(node.getValue() + " ");

            // 将左子树的根结点入队列
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }

            // 将右子树的根结点入队列
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }
    }

}

/**
 * 二叉树的结点 数据结构
 */
class BinaryTreeNode{

    private int value;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
