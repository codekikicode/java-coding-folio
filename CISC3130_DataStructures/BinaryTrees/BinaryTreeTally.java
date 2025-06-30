public class BinaryTreeTally {

    static class Node {
        int info;
        Node left, right;

        Node(int val) {
            info = val;
            left = right = null;
        }
    }

    public static Node insert(Node root, int key) {
        if (root == null)
            return new Node(key);
        if (key < root.info)
            root.left = insert(root.left, key);
        else if (key > root.info)
            root.right = insert(root.right, key);
        return root;
    }

    private static int minValue(Node root) {
        int minv = root.info;
        while (root.left != null) {
            minv = root.left.info;
            root = root.left;
        }
        return minv;
    }

    public static Node delete(Node root, int key) {
        if (root == null) return null;
        if (key < root.info)
            root.left = delete(root.left, key);
        else if (key > root.info)
            root.right = delete(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.info = minValue(root.right);
            root.right = delete(root.right, root.info);
        }
        return root;
    }

    public static void inorder(Node tree) {
        if (tree != null) {
            inorder(tree.left);
            System.out.print(tree.info + " ");
            inorder(tree.right);
        }
    }

    public static void preorder(Node tree) {
        if (tree != null) {
            System.out.print(tree.info + " ");
            preorder(tree.left);
            preorder(tree.right);
        }
    }

    public static void postorder(Node tree) {
        if (tree != null) {
            postorder(tree.left);
            postorder(tree.right);
            System.out.print(tree.info + " ");
        }
    }

    public static int countNodes(Node tree) {
        if (tree == null)
            return 0;
        return 1 + countNodes(tree.left) + countNodes(tree.right);
    }

    public static void printChildren(Node tree) {
        if (tree != null) {
            int children = 0;
            if (tree.left != null) children++;
            if (tree.right != null) children++;

            if (children == 0)
                System.out.println("Node " + tree.info + " has 0 children.");
            else
                System.out.println("Node " + tree.info + " has " + children + " child(ren).");

            printChildren(tree.left);
            printChildren(tree.right);
        }
    }

    public static void processSet(int[] initialValue, int[] ins, int[] del, String setName) {
        System.out.println("\n~^*^~ " + setName + " ~^*^~");

        Node root = null;
        for (int val : initialValue) {
            root = insert(root, val);
        }

        System.out.println("\nPrimary Traversals:");
        System.out.print("Inorder: "); inorder(root); System.out.println();
        System.out.print("Preorder: "); preorder(root); System.out.println();
        System.out.print("Postorder: "); postorder(root); System.out.println();

        System.out.println("\nInitial Node Count: " + countNodes(root));
        printChildren(root);

        for (int val : ins) {
            root = insert(root, val);
        }
        for (int val : del) {
            root = delete(root, val);
        }

        System.out.println("\nAdjusted Traversals (Ins/Del):");
        System.out.print("Inorder: "); inorder(root); System.out.println();
        System.out.print("Preorder: "); preorder(root); System.out.println();
        System.out.print("Postorder: "); postorder(root); System.out.println();

        System.out.println("\nFinal Node Count: " + countNodes(root));
        printChildren(root);
    }

    public static void main(String[] args) {

        processSet(
                new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20},
                new int[]{21,30},
                new int[]{16,10,12,2},
                "Set #1:");

        processSet(
                new int[]{3,1,5},
                new int[]{3,14,33,2,6},
                new int[]{},
                "Set #2:");

        processSet(
                new int[]{11,25,75,12,37,60,90,8,15,32,45,50,67,97,95},
                new int[]{21,30},
                new int[]{60,45,97,25},
                "Set #3:");

        processSet(
                new int[]{150,40,60,39,34,27,10,82,15},
                new int[]{21,34,12},
                new int[]{139,27,82},
                "Set #4:");

        processSet(
                new int[]{2},
                new int[]{},
                new int[]{2},
                "Set #5:");

        processSet(
                new int[]{34,65,3,7,48,15,16,92,56,43,74},
                new int[]{21,30,10,12,2},
                new int[]{34},
                "Set #6:");
    }
}
