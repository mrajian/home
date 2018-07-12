package home.app.prepare.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liangyongjian
 * @desc 平衡二叉搜索树
 * @create 2018-05-23 0:37
 **/
public class BalanceBinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root = null;             // root of BST

    // BBST NODE
    private class Node {
        private Key key;           // sorted by key
        private Value value;         // associated data
        private Node leftNode;         // leftNode subtrees
        private Node rightNode;        // rightNode subtrees
        private int height; // 结点的高度


        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static void main(String[] args) {
        BalanceBinarySearchTree bBst = new BalanceBinarySearchTree();
        bBst.root = bBst.insert(5,5, bBst.root);
        bBst.root = bBst.insert(6,6, bBst.root);
        bBst.root = bBst.insert(3,3, bBst.root);
        bBst.root = bBst.insert(4,4, bBst.root);
        bBst.root = bBst.insert(7,7, bBst.root);
        bBst.root = bBst.insert(8,8, bBst.root);
        bBst.root = bBst.insert(12,12, bBst.root);
        bBst.root = bBst.insert(11,11, bBst.root);
        bBst.root = bBst.insert(0,0, bBst.root);
        bBst.root = bBst.insert(1,1, bBst.root);
        bBst.root = bBst.insert(-1,-1, bBst.root);
        bBst.root = bBst.insert(2,2, bBst.root);

        System.out.print("\n前序遍历: ");
        bBst.preOrder(bBst.root);
        System.out.print("\n中序遍历: ");
        bBst.midOrder(bBst.root);
        System.out.print("\n后序遍历: ");
        bBst.postOrder(bBst.root);
        System.out.print("\n层次遍历: ");
        bBst.layerTraversal(bBst.root);
        System.out.print("\n树的高度: "+bBst.height());
        System.out.print("\n树的最大值: "+bBst.findMaxNode(bBst.root).value);
        System.out.print("\n树的最小值: "+bBst.findMinNode(bBst.root).value);
        System.out.print("\n树的结点个数: "+bBst.size(bBst.root));
    }

    public int height() {
        return height(root);
    }

    /**
     * 获取结点的高度
     * @param node
     * @return
     */
    public int height(Node node){
        return node == null ? -1 : node.height;
    }


    /**
     * 插入新的结点，使用递归的方式
     * @param key
     * @param value
     */
    public Node insert(Key key, Value value, Node node) {
        if (node == null) {
            node = new Node(key, value);
            return node;
        }

        int comp = key.compareTo(node.key);

        if (comp > 0) {
            // 插入node的右子树
            node.rightNode = insert(key, value, node.rightNode);
            // 计算 node 的高度
            node.height = Math.max(height(node.leftNode), height(node.rightNode)) + 1;

            // 插入后计算子树的高度，等于2则需要重新恢复平衡，由于是右边插入，右子树的高度可能大于等于左子树的高度
            if(height(node.rightNode) - height(node.leftNode) == 2){
                // 目前可知 node 是最小非平衡子树的根节点，且新的结点是插入在它的右孩子结点下的子树中的
                // 想要确定是哪种类型的插入，还需要判断新结点位于右孩子结点的左子树还是右子树
                // 于是下一步就要确定 新的结点插入到了右孩子结点的左子树还是右子树
                // 不必判断这个新结点是左叶子还是右叶子，只需要知道它位于右孩子结点的左子树还是右子树即可
                // 如果是右孩子的右子树，那就是RR类型，如果是右孩子的左子树，那就是RL类型
                // 这个判定很容易：新插入的结点和 右孩子结点 比大小就可以得出

                // 判断新结点是 最小非平衡子树的根节点的右孩子 的左子树还是右子树
                if(key.compareTo(node.rightNode.key) > 0){
                    // 新的结点大于 最小非平衡子树的根节点的右孩子 那肯定就是 右孩子的右子树
                    // LR类型 进行RR旋转 左旋一次即可
                    node = singleRotateLeft(node);
                } else {
                    // 新的结点小于 最小非平衡子树的根节点的右孩子 那肯定就是 右孩子的左子树
                    // RL类型 进行右左旋转
                    node = rightLeftType(node);
                }
            }

        } else if (comp < 0) {
            // 插入node的左子树
            node.leftNode = insert(key, value, node.leftNode);
            // 计算 node 的高度
            node.height = Math.max(height(node.leftNode), height(node.rightNode)) + 1;

            // 插入后计算子树的高度，等于2则需要重新恢复平衡，由于是左边插入，左子树的高度可能大于等于右子树的高度
            if(height(node.leftNode) - height(node.rightNode) == 2){
                // 目前 node 是最小非平衡子树的根节点，且新的结点是插入在它的左孩子结点下的子树中的
                // 想要确定是哪种类型的插入，还需要判断新结点位于右孩子结点的左子树还是右子树
                // 于是下一步就要确定 新的结点插入到了左孩子结点的左子树还是右子树
                // 不必判断这个新结点是左叶子还是右叶子，只需要知道它位于左孩子结点的左子树还是右子树即可
                // 如果是左孩子的左子树，那就是LL类型，如果是左孩子的右子树，那就是LR类型
                // 这个判定很容易：新插入的结点和 左孩子结点 比大小就可以得出

                // 判断新结点是 最小非平衡子树的根节点的左孩子 的左子树还是右子树
                if(key.compareTo(node.leftNode.key) < 0){
                    // 新的结点小于 最小非平衡子树的根节点的左孩子 那肯定就是 左孩子的左子树
                    // LL类型 进行LL旋转 右旋一次即可
                    node = singleRotateRight(node);
                }else {
                    // 新的结点大于 最小非平衡子树的根节点的左孩子 那肯定就是 左孩子的右子树
                    // LR类型 进行左右旋转
                    node = leftRightType(node);
                }
            }
        } else {
            // 这个元素存在了，啥也不干
        }
        // 由于旋转，导致结点的高度再次变化，所以再作一次高度计算
        node.height = Math.max(height(node.leftNode), height(node.rightNode)) + 1;
        return node;
    }

    /**
     * 单纯的右旋 参数 subRootNode 是需要旋转的子树根节点
     * subRootNode 的左孩子结点就是这次旋转的中心结点，记为 rotateNode
     * 以 rotateNode 为旋转中心，让 subRootNode 向右旋转，这样 subRootNode 成为 rotateNode 的右子树
     * 同时，让 rotateNode 原来的右子树成为 subRootNode 的左子树，调整完毕
     * @param subRootNode 需要旋转的子树根节点
     * @return
     */
    private Node singleRotateRight(Node subRootNode) {
        // 把 rotateNode 结点旋转为根结点  subRootNode 成为 rotateNode 的右子树
        Node rotateNode = subRootNode.leftNode;

        // 同时 rotateNode 的右子树变为 subRootNode 的左子树
        subRootNode.leftNode = rotateNode.rightNode;

        // subRootNode 变为 rotateNode 的右子树，即 rotateNode 成为这棵子树的根
        rotateNode.rightNode = subRootNode;

        // 重新计算调整后 rotateNode 和 subRootNode 的高度，因为它们的结构发生了变化
        subRootNode.height = Math.max(height(subRootNode.leftNode), height(subRootNode.rightNode)) + 1;
        rotateNode.height = Math.max(height(rotateNode.leftNode), subRootNode.height) + 1;

        //返回新的根结点
        return rotateNode;
    }

    /**
     * 单纯的左旋 参数 subRootNode 是需要旋转的子树根节点
     * subRootNode 的右孩子结点就是这次旋转的中心结点，记为 rotateNode
     * 以 rotateNode 为旋转中心，让 subRootNode 向左旋转，这样 subRootNode 成为 rotateNode 的左子树
     * 同时，让 rotateNode 原来的左子树成为 subRootNode 的右子树，调整完毕
     * @param subRootNode 需要旋转的子树根节点
     * @return
     */
    private Node singleRotateLeft(Node subRootNode){
        // 把 rotateNode 结点旋转为根结点  subRootNode 成为 rotateNode 的左子树
        Node rotateNode = subRootNode.rightNode;

        // 同时 rotateNode 的左子树变为 subRootNode 的右子树
        subRootNode.rightNode = rotateNode.leftNode;

        // subRootNode 变为 rotateNode 的左子树，即 rotateNode 成为这棵子树的根
        rotateNode.leftNode = subRootNode;

        // 重新计算调整后 parentNode 和 subRootNode 的高度，因为它们的结构发生了变化
        subRootNode.height = Math.max(height(subRootNode.leftNode), height(subRootNode.rightNode)) + 1;
        rotateNode.height = Math.max(subRootNode.height, height(rotateNode.rightNode)) + 1;

        //返回新的根结点
        return rotateNode;
    }


    /**
     * LL类型 右旋恢复平衡
     * @param unBalanceNode
     * @return
     */
    private Node leftLeftType(Node unBalanceNode) {
        return singleRotateRight(unBalanceNode);
    }

    /**
     * RR类型 左旋恢复平衡
     * @param unBalanceNode
     * @return
     */
    private Node rightRightType(Node unBalanceNode) {
        return singleRotateLeft(unBalanceNode);
    }

    /**
     * LR类型 ：先左旋 后右旋
     * 处理方式：unBalanceNode 是最小非平衡树的根结点，首先进行左旋的是它的左子树
     * 左子树左旋完成后，实际上就成为了LL类型，于是再对 unBalanceNode 为根的子树进行右旋
     * @param unBalanceNode 最小非平衡树的根结点
     * @return
     */
    private Node leftRightType(Node unBalanceNode) {
        // 先左旋 让最小非平衡树的根结点的左子树进行左旋，并更换左子树的根结点
        unBalanceNode.leftNode = singleRotateLeft(unBalanceNode.leftNode);
        // 后右旋 让最小非平衡树进行右旋
        return singleRotateRight(unBalanceNode);
    }

    /**
     * RL类型 ：先右旋 后左旋
     * 处理方式：unBalanceNode 是最小不平衡子树的根结点，首先进行右旋的是它的右子树
     * 右子树右旋完成后，实际上就成为了RR类型，于是再对 unBalanceNode 为根的子树进行左旋
     * @param unBalanceNode 最小不平衡子树的根结点
     * @return
     */
    private Node rightLeftType(Node unBalanceNode){
        // 先右旋 让最小非平衡树的根结点的右子树进行右旋，并更换右子树的根结点
        unBalanceNode.rightNode = singleRotateRight(unBalanceNode.rightNode);
        // 后左旋 让最小非平衡树进行左旋
        return singleRotateLeft(unBalanceNode);
    }

    /**
     * 先序遍历树
     * @param node
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrder(node.leftNode);
        preOrder(node.rightNode);
    }

    /**
     * 中序遍历树
     * @param node
     */
    public void midOrder(Node node) {
        if (node == null) {
            return;
        }
        midOrder(node.leftNode);
        System.out.print(node.value + " ");
        midOrder(node.rightNode);
    }

    /**
     * 后序遍历树
     * @param node
     */
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftNode);
        postOrder(node.rightNode);
        System.out.print(node.value + " ");
    }

    /**
     * 广度优先遍历 采用队列的方式实现遍历
     * 队列是先进先出的模式
     * @param rootNode
     */
    private void layerTraversal(Node rootNode) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(rootNode);

        while (!queue.isEmpty()) {
            // 取出最新入队列的元素，并从队列中删除
            Node node = queue.poll();
            System.out.print(node.getValue() + " ");

            // 将左子树的根结点入队列
            if (node.getLeftNode() != null) {
                queue.add(node.getLeftNode());
            }

            // 将右子树的根结点入队列
            if (node.getRightNode() != null) {
                queue.add(node.getRightNode());
            }
        }
    }

    /**
     * 获取二叉搜索树中的最大值结点
     * @param rootNode
     * @return
     */
    public Node findMaxNode(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        Node currentNode = rootNode;
        while (true) {
            if (currentNode.rightNode != null) {
                currentNode = currentNode.rightNode;
            } else {
                break;
            }
        }

        return currentNode;
    }

    /**
     * 获取二叉搜索树中的最小值结点
     * @param rootNode
     * @return
     */
    public Node findMinNode(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        Node currentNode = rootNode;
        while (true) {
            if (currentNode.leftNode != null) {
                currentNode = currentNode.leftNode;
            } else {
                break;
            }
        }

        return currentNode;
    }

    /**
     *
     * @param node
     * @return
     */
    public int size(Node node) {
        if (node == null)
            return 0;
        return size(node.leftNode) + 1 + size(node.rightNode);
    }

    private Node remove(Node tree, Node z) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree == null || z == null)
            return null;

        int cmp = z.key.compareTo(tree.key);
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            tree.leftNode = remove(tree.leftNode, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.rightNode) - height(tree.leftNode) == 2) {
                Node r =  tree.rightNode;
                if (height(r.leftNode) > height(r.rightNode))
                    //之字形失衡，双旋转
                    tree = rightLeftType(tree);
                else
                    //一字型失衡，单旋转
                    tree = rightRightType(tree);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            tree.rightNode = remove(tree.rightNode, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.leftNode) - height(tree.rightNode) == 2) {
                Node l =  tree.leftNode;
                if (height(l.rightNode) > height(l.leftNode))
                    //之字形失衡，双旋转
                    tree = leftRightType(tree);
                else
                    //一字型失衡，单旋转
                    tree = leftLeftType(tree);
            }
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((tree.leftNode != null) && (tree.rightNode != null)) {
                if (height(tree.leftNode) > height(tree.rightNode)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    Node max = findMaxNode(tree.leftNode);
                    tree.key = max.key;
                    tree.value = max.value;
                    tree.leftNode = remove(tree.leftNode, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    Node min = findMinNode(tree.rightNode);
                    tree.key = min.key;
                    tree.value = min.value;
                    tree.rightNode = remove(tree.rightNode, min);
                }
            } else {
                tree = (tree.leftNode!=null) ? tree.leftNode : tree.rightNode;
            }
        }

        return tree;
    }


}
