package ca_2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * EmployeeBinaryTree represents the organizational structure using a binary
 * tree.
 */

public class EmployeeBinaryTree {
    private EmployeeTreeNode root; // The top node of the tree
    private int nodeCount; // Keeps track of how many employees are in the tree

    /**
     * Constructor - starts with an empty tree
     */
    public EmployeeBinaryTree() {
        this.root = null;
        this.nodeCount = 0;
    }

    // Level-order insertion (breadth-first)
    public void insertLevelOrder(Employee employee) {
        EmployeeTreeNode newNode = new EmployeeTreeNode(employee);

        // If tree is empty, new employee becomes the root
        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }

        // Used a queue to keep track of where to add the next employee
        Queue<EmployeeTreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        // Look for the first available spot in the tree
        while (!queue.isEmpty()) {
            EmployeeTreeNode current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                nodeCount++;
                return;
            } else {
                queue.add(current.left);
            }

            // If left is taken, try right child
            if (current.right == null) {
                current.right = newNode;
                nodeCount++;
                return;
            } else {
                queue.add(current.right);
            }
        }
    }

    // Calculating how many levels deep the organizational tree goes
    public int getHeight() {
        return calculateHeight(root);
    }

    /**
     * @return Recursively calculates tree height
     */
    private int calculateHeight(EmployeeTreeNode node) {
        if (node == null)
            return 0; // Base case: empty spot has zero height
        // Tree height is 1 (current node) plus the taller of left or right subtrees
        return 1 + Math.max(calculateHeight(node.left), calculateHeight(node.right));
    }

    /**
     * @return The total number of employees currently in the organizational tree
     */

    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Displays all employees in the tree, level by level
     * Shows the organizational structure from top to bottom
     */
    public void displayLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        // Use a queue to process employees level by level
        Queue<EmployeeTreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        int level = 0; // Track which level we're displaying

        System.out.println("\n=== Employee Hierarchy (Level Order) ===");

        // Process each level of the tree
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // How many employees at this level
            System.out.println("\n--- Level " + level + " ---");

            // Display all employees at the current level
            for (int i = 0; i < levelSize; i++) {
                EmployeeTreeNode current = queue.poll();
                System.out.println(current.employee.getDetails());

                // Add children to queue for next level processing
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            level++; // Moviing to the next level
        }
    }
}
