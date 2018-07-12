package home.app.prepare.tree;

/**
 * @author liangyongjian
 * @desc 左倾红黑树
 * @create 2018-05-30 0:27
 **/
public class LeftLeaningRedBlackTree<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BST

    private static final Boolean RED = true;

    private static final Boolean BLACK = false;

    private class Node{
        private Key key;           // sorted by key
        private Value value;         // associated data
        private Node leftNode;         // left subtrees
        private Node rightNode;        // right subtrees
        private Boolean color; // 结点颜色 如果结点是红色，表示该结点的父结点到此结点的连接是红色的

        public Node(Key key, Value value, Boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
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

        public Boolean getColor() {
            return color;
        }

        public void setColor(Boolean color) {
            this.color = color;
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
    }

    public static void main(String[] args) {
        LeftLeaningRedBlackTree leftRBTree = new LeftLeaningRedBlackTree();
        leftRBTree.insertNewNode(6, 6);

        leftRBTree.insertNewNode(5, 5);
        leftRBTree.insertNewNode(4, 4);
        leftRBTree.insertNewNode(3, 3);
        leftRBTree.insertNewNode(2, 2);
        leftRBTree.insertNewNode(1, 1);
        leftRBTree.insertNewNode(20, 20);

        System.out.print("\n前序遍历: ");
        leftRBTree.preOrder(leftRBTree.root);
        System.out.print("\n中序遍历: ");
        leftRBTree.midOrder(leftRBTree.root);
        System.out.print("\n后序遍历: ");
        leftRBTree.postOrder(leftRBTree.root);
    }

    /**
     * 查找指定节点
     * @param key
     * @return
     */
    public Node find(Key key) {
        Node currentNode = root;
        while(currentNode != null) {
            int comp = currentNode.key.compareTo(key);
            if (comp > 0) {
                // 当前节点大于指定结点，继续找当前结点的左子树
                currentNode = currentNode.leftNode;
            } else if (comp < 0) {
                // 当前结点小于指定节点，继续找当前结点的右子树
                currentNode = currentNode.rightNode;
            } else if (comp == 0) {
                // 当前结点的值等于指定结点的值，找到了
                break;
            }
        }
        return currentNode;
    }

    private Node singleRotateLeft(Node subRootNode){
        // 把 rotateNode 结点旋转为根结点  subRootNode 成为 rotateNode 的左子树
        Node rotateNode = subRootNode.rightNode;

        // 同时 rotateNode 的左子树变为 subRootNode 的右子树
        subRootNode.rightNode = rotateNode.leftNode;

        // subRootNode 变为 rotateNode 的左子树，即 rotateNode 成为这棵子树的根
        rotateNode.leftNode = subRootNode;

        // rotateNode 变为原来根的颜色
        rotateNode.color = subRootNode.color;

        // subRootNode 成为 rotateNode 的左子结点后，颜色变为红色
        subRootNode.color = RED;

        //返回新的根结点
        return rotateNode;
    }

    private Node singleRotateRight(Node subRootNode) {
        // 把 rotateNode 结点旋转为根结点  subRootNode 成为 rotateNode 的右子树
        Node rotateNode = subRootNode.leftNode;

        // 同时 rotateNode 的右子树变为 subRootNode 的左子树
        subRootNode.leftNode = rotateNode.rightNode;

        // subRootNode 变为 rotateNode 的右子树，即 rotateNode 成为这棵子树的根
        rotateNode.rightNode = subRootNode;

        // rotateNode 变为原来根的颜色
        rotateNode.color = subRootNode.color;

        // subRootNode 成为 rotateNode 的右子结点后，颜色变为红色
        subRootNode.color = RED;

        //返回新的根结点
        return rotateNode;
    }

    /**
     * 当 subRootNode 的左右子结点都是红色时，将 subRootNode 的颜色变为红色，
     * 同时将 subRootNode 的左右子结点颜色变为黑色
     * 这个操作对应 4-结点 向上分裂的操作
     * 向上分裂的中间元素与其父结点组成 3-结点（也可能组成 4-结点，此时还需向上分裂）
     * @param subRootNode
     */
    private void flipColor(Node subRootNode) {
        subRootNode.color = RED;
        if (subRootNode.leftNode != null)
            subRootNode.leftNode.color = BLACK;
        if (subRootNode.rightNode != null)
            subRootNode.rightNode.color = BLACK;
    }

    private void insertNewNode(Key key, Value value) {
        root = insert(key, value ,root);
        root.color = BLACK;
    }

    private Node insert(Key key, Value value, Node node) {
        if (node == null) {
            return new Node(key, value, RED);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // 小于当前结点，进入当前结点的左子树
            node.leftNode = insert(key, value, node.leftNode);
        } else if (cmp > 0) {
            // 大于当前结点，进入当前结点的右子树
            node.rightNode = insert(key, value, node.rightNode);
        } else {
            // 等于当前结点，直接替换值即可
            node.value = value;
            return node;
        }

        // 开始维护平衡 逐步向上 fixBalance
        node = fixBalanceNode(node);

        return node;
    }

    /**
     * 维护平衡
     * @param node
     * @return
     */
    private Node fixBalanceNode(Node node) {
        // 开始维护平衡
        if (isRed(node.leftNode) && isRed(node.rightNode)) {
            // 左右两个子结点都是红色，则向上分裂：将左右子结点变为黑色，自己变为红色
            flipColor(node);
        } else if (isRed(node.rightNode) && !isRed(node.leftNode)) {
            // 右子结点是红色，左子结点不是红色，则左旋
            node = singleRotateLeft(node);
        }else if (isRed(node.leftNode) && node.leftNode != null && isRed(node.leftNode.leftNode)) {
            // 左子结点为红色，左子结点的左子结点依然为红色，则右旋
            node = singleRotateRight(node);
        }
        return node;
    }

    /**
     * 判断结点的颜色是否为红色
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node != null && node.color == RED) {
            return true;
        }
        return false;
    }

    /**
     * 先序遍历树
     * @param node
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + "(" + (node.color == RED ? "red" : "black") + ") ");
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
        System.out.print(node.value + "(" + (node.color == RED ? "red" : "black") + ") ");
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
        System.out.print(node.value + "(" + (node.color == RED ? "red" : "black") + ") ");
    }


}
