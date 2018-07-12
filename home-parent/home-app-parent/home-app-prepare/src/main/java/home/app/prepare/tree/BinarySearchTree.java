package home.app.prepare.tree;

/**
 * @author liangyongjian
 * @desc 二叉搜索树 BST
 * 假设树中没有重复的Key值
 * @create 2018-05-21 18:22
 **/
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;             // root of BST

    // BST NODE
    private class Node{
        private Key key;           // sorted by key
        private Value value;         // associated data
        private Node leftNode;         // left subtrees
        private Node rightNode;        // right subtrees

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
    }


    public static void main(String[] args) {

        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree();
        // 初始化根节点
        BinarySearchTree.Node rootNode = bst.new Node(6, 6);

        BinarySearchTree.Node node1 = bst.new Node(3, 3);
        rootNode.setLeftNode(node1);

        BinarySearchTree.Node node2 = bst.new Node(14, 14);
        rootNode.setRightNode(node2);

        BinarySearchTree.Node node3 = bst.new Node(10, 10);
        node2.setLeftNode(node3);

        BinarySearchTree.Node node4 = bst.new Node(9, 9);
        node3.setLeftNode(node4);

        BinarySearchTree.Node node5 = bst.new Node(13, 13);
        node3.setRightNode(node5);

        BinarySearchTree.Node node6 = bst.new Node(11, 11);
        node5.setLeftNode(node6);

        BinarySearchTree.Node node7 = bst.new Node(12, 12);
        node6.setRightNode(node7);

        BinarySearchTree.Node node8 = bst.new Node(16, 16);
        node2.setRightNode(node8);

        bst.root = rootNode;

        System.out.print("\n前序遍历: ");
        bst.preOrder(bst.root);
        System.out.print("\n中序遍历: ");
        bst.midOrder(bst.root);
        System.out.print("\n后序遍历: ");
        bst.postOrder(bst.root);

        System.out.println();
        BinarySearchTree.Node searchNode = bst.find(16);
        System.out.println(searchNode == null ? "not found" : searchNode.value);

        bst.insert(20, 20);
        System.out.print("\n中序遍历: ");
        bst.midOrder(bst.root);

        bst.delete(20);
        System.out.print("\n删除 Key=20 结点后中序遍历: ");
        bst.midOrder(bst.root);

        System.out.println();
        bst.delete(13);
        System.out.print("\n删除 Key=13 结点后中序遍历: ");
        bst.midOrder(bst.root);

        System.out.println();
        BinarySearchTree.Node maxNode = bst.findMaxNode(bst.root);
        System.out.println("二叉搜索树中的最大值: " + (maxNode == null ? " not found " : maxNode.key));

        System.out.println();
        BinarySearchTree.Node minNode = bst.findMinNode(bst.root);
        System.out.println("二叉搜索树中的最小值: " + (minNode == null ? " not found " : minNode.key));
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

    /**
     * 插入新节点
     * @param key
     * @param value
     */
    public void insert(Key key, Value value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }

        Node currentNode = root;
        // 最终为待插入结点的父结点
        Node parentNode = root;
        // 确定所插入结点属于左子结点还是右子结点
        boolean isLeftNode = false;

        while (currentNode != null) {
            parentNode = currentNode;
            int comp = currentNode.key.compareTo(key);
            if (comp > 0) {
                // 当前节点大于指定结点，继续找当前结点的左子树
                currentNode = currentNode.leftNode;
                isLeftNode = true;
            } else if (comp < 0) {
                // 当前结点小于指定节点，继续找当前结点的右子树
                currentNode = currentNode.rightNode;
                isLeftNode = false;
            } else if (comp == 0) {
                // 当前结点的值等于新插入结点的值，无需插入，操作结束
                return;
            }
        }

        Node newNode = new Node(key, value);
        if (isLeftNode) {
            parentNode.leftNode = newNode;
        } else {
            parentNode.rightNode = newNode;
        }
    }

    /**
     * 删除指定节点
     * @param key
     * @return
     */
    public boolean delete(Key key) {
        // 先找到这个结点 从根开始找
        Node currentNode = root;
        // 这个结点的父结点
        Node parentNode = root;
        // 确定所找结点属于左子结点还是右子结点
        boolean isLeftNode = false;

        while(currentNode != null) {
            int comp = currentNode.key.compareTo(key);
            if (comp > 0) {
                parentNode = currentNode;
                // 当前节点大于指定结点，继续找当前结点的左子树
                currentNode = currentNode.leftNode;
                isLeftNode = true;
            } else if (comp < 0) {
                parentNode = currentNode;
                // 当前结点小于指定节点，继续找当前结点的右子树
                currentNode = currentNode.rightNode;
                isLeftNode = false;
            } else if (comp == 0) {
                // 当前结点的值等于指定结点的值，找到了
                break;
            }
        }

        if (currentNode == null) {
            // 没找到这个结点
            return false;
        }

        // 既然不是根，那这个被删除的结点就是有父结点的，需要判断下它属于左子树还是右子树
        if (currentNode.leftNode == null && currentNode.rightNode == null) {
            // 第一种情况，要删除的结点是叶子结点，它没有左右子树，直接删除掉就可以，注意同步更新下父结点
            // 首先是判断要删除结点是否是整棵树的根，如果是，直接将根置为null
            if (currentNode == root) {
                System.out.println("被删除的结点是整棵树的根");
                root = null;
            } else {
                System.out.println("被删除的结点是Key为 " + parentNode.key + " 的叶子结点，" +
                        "它属于其父结点的" + (isLeftNode ? "左子树" : "右子树"));
                if (isLeftNode) {
                    parentNode.leftNode = null;
                } else {
                    parentNode.rightNode = null;
                }
            }
        } else if (currentNode.rightNode == null) {
            // 第二种情况 2.1 ，要删除的结点不是叶子结点，但它仅有左子树
            // 此时，处理也很简单，只需要将待删除结点的左子树根结点 放到 被删除的结点的原有位置
            if (currentNode == root) {
                System.out.println("被删除的结点是整棵树的根，根仅有左子树，使用根的左子树代替根");
                root = currentNode.leftNode;
            } else if (isLeftNode) {
                // 被删除结点 属于父结点的左子树
                System.out.println("被删除的结点仅有左子树，是Key为 " + parentNode.key + " 的左子树根结点");
                parentNode.leftNode = currentNode.leftNode;
            } else {
                // 被删除结点 属于父结点的右子树
                System.out.println("被删除的结点仅有左子树，是Key为 " + parentNode.key + " 的右子树根结点");
                parentNode.rightNode = currentNode.leftNode;
            }
        } else if (currentNode.leftNode == null) {
            // 第二种情况 2.2 ，要删除的结点不是叶子结点，但它仅有右子树
            // 此时，处理也很简单，只需要将待删除结点的右子树根结点 放到 被删除的结点的原有位置
            if (currentNode == root) {
                System.out.println("被删除的结点是整棵树的根，根仅有右子树，使用根的右子树代替根");
                root = currentNode.rightNode;
            } else if (isLeftNode) {
                // 被删除结点 属于父结点的左子树
                System.out.println("被删除的结点仅有右子树，是Key为 " + parentNode.key + " 的左子树根结点");
                parentNode.leftNode = currentNode.rightNode;
            } else {
                // 被删除结点 属于父结点的右子树
                System.out.println("被删除的结点仅有右子树，是Key为 " + parentNode.key + " 的右子树根结点");
                parentNode.rightNode = currentNode.rightNode;
            }
        } else {
            // 第三种情况，要删除的结点不是叶子结点，左子树和右子树它都有
            // 思路：用待删除结点右子树中的key值最小结点的值来替代要删除的结点的值,然后删除右子树中key值最小的结点
            // 右子树key最小的结点一定不含左子树,所以删除这个key最小的结点一定是属于叶结点点或者只有右子树的结点
            Node directPostNode = getDirectPostNode(currentNode);
            currentNode.key = directPostNode.key;
            currentNode.value = directPostNode.value;
        }

        return true;
    }

    /**
     * 找到以待删除节点为根的中序遍历后待删除结点的第一个后继节点
     * 需要用以此结点为根，进行中序遍历后的第一个后续结点来替代此结点，
     * 并将后继结点从原位置移除。由二叉搜索树和中序遍历相关规则可知，
     * 这个后继结点必然是要删除结点右子树中 key 值最小的结点，
     * 所以此后继节点一定不会有左子树（因为左子树会比它更小），
     * 它本身就是要删除结点的右子树中最左的结点
     * 但它可能有右子树。于是，删除此后继节点就属于上面情况1和2。
     * @param delNode
     * @return
     */
    private Node getDirectPostNode(Node delNode) {
        Node parentNode = delNode;
        // 需要从右子树开始找
        Node directPostNode = delNode.rightNode;
        Node currentNode = delNode;
        while (currentNode != null) {
            parentNode = directPostNode;
            directPostNode = currentNode;
            currentNode = currentNode.leftNode;
        }

        // 这时找到了中序遍历的第一个后续结点，需要把这个后续结点从其父结点中删除
        // 但是要注意，这个后续结点如果直接是被删除结点的子结点，就不必再从父结点中删除了，直接替换父结点即可
        if (directPostNode != delNode.rightNode) {
            // 这个后继结点肯定是其父结点的左子树，而且这个后继结点肯定没有左子树，
            // 但可能有右子树，于是就是下面的这样代码
            parentNode.leftNode = directPostNode.rightNode;
        }

        return directPostNode;
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

}
