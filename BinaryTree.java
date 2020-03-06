/**
 *   Spoorthi Gowda
 *   
 *   
 *   
 *   Homework 5 Problem 1
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.*;

public class BinaryTree {
    TreeNode root;
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }
    
    
    // traversing through binary tree inorder iteratively
    public List<Integer> inorderIterative(){
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        List<Integer> result = new ArrayList<>();
        
        while (!stack.empty() || curr != null){
            if (curr != null){
                stack.push(curr);
                curr = curr.left;
            } 
            else {
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }

    public int LCA(int v1, int v2) {
        return helperLCA(root,v1,v2); 
    }
    
    // helper LCA method
    private int helperLCA(TreeNode root, int v1, int v2) {
      if (root == null) { // checking if root is null
        return -1;
      }
      
      // if root is not null
      int left = helperLCA(root.left, v1, v2);
      if (left != -1){
          return left;
      }
      int right = helperLCA(root.right, v1, v2);
      if (right != -1){ 
          return right;
        }
    
      if (containsNode(root,v1) && containsNode(root, v2)) {
        return root.val;
      }
      return -1;
      
    }
    
    private boolean containsNode(TreeNode root, int n) {
      if (root == null || n == -1) {
        return false;
      }
      return root.val == n || containsNode(root.left, n) || containsNode(root.right, n);
    }
    
    public List<List<Integer>> levelOrderTraversal() {
       return levelOrderTraversal(root);
    }
    
    // level order traversal
    public List<List<Integer>> levelOrderTraversal(TreeNode root){
    	List<List<Integer>> levelOrderTraversal = new ArrayList<List<Integer>>();
    	List<Integer> currLevel = new ArrayList<Integer>();
    	Queue<TreeNode> queue =  new LinkedList<TreeNode>();
    
    	if(root != null){ // checking if root is not null
    		queue.add(root);
    		queue.add(null);
    	}
    	
    	while(!queue.isEmpty()){
    	        TreeNode queueRoot = queue.poll(); // removes element  
    		if(queueRoot != null){
    			currLevel.add(queueRoot.val);
    			if(queueRoot.left != null)
    			{
    				queue.add(queueRoot.left);
    			} 
    			if(queueRoot.right != null)
    			{
    				queue.add(queueRoot.right);
    			}
    		}
    		else{
    			levelOrderTraversal.add(currLevel);
    			if(!queue.isEmpty())
    			{
    				currLevel = new ArrayList<Integer>();
    				queue.add(null);
    			}
    		}
    	}
    	return levelOrderTraversal;
    }

    // extra credit
    public int nthSmallestInBST(int n) {
       Stack<TreeNode> stack = new Stack();
       TreeNode curr = root;

       int count = 0; // counter
       int small = 0;
        
        while ((!stack.empty() || curr != null) && count != n){
            if (curr != null){
                stack.push(curr);
                curr = curr.left;
            } 
            else {
                curr = stack.pop();
                small=curr.val;
                //result.add(curr.val);
                count++; // incrementing counter
                curr = curr.right;
            }
        }
        return small;
    }

    private void preorderRecursive(TreeNode node, List<Integer> result) {
        if (node== null) {
            return;
        }
        result.add(node.val);
        preorderRecursive(node.left, result);
        preorderRecursive(node.right, result);
    }

    public List<Integer> preorderRecursive() {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    public List<Integer> preorderIterative() {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node= stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
    
   
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    public int height() {
        return height(root);
    }

    
    private Integer LCA_BST( TreeNode node, int v1, int v2) {
        if (node== null) {
            return null;
        }
        int min = Math.min(v1, v2);
        int max = Math.max(v1, v2);
        
        if (node.val >= min && node.val <= max) {
            return node.val;
        }

        if (node.val < min) {
            return LCA_BST(node.right, v1, v2);
        }
        
        if (node.val > max ) {
            return LCA_BST(node.left , v1, v2);
        }
        
        return node.val;
    }
    
    public int LCA_BST(int v1, int v2) {
        return LCA_BST(root, v1, v2);
    }

    public List<Integer> postorderIterative() {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> out = new Stack<>();
        
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node= stack.pop();
            out.push(node);
            
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!out.isEmpty()) {
            TreeNode node1 = out.pop();
            result.add(node1.val);
        }
        return result;
    }
    
    
    private void postorderRecursive( TreeNode node, List<Integer> result) {
        if (node== null) {
            return;
        }
        postorderRecursive(node.left, result);
        postorderRecursive(node.right, result);
        result.add(node.val);
    }

    List<Integer> postorderRecursive() {
        List<Integer> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
    }

    private void inorderRecursive( TreeNode node, List<Integer> result) {
        if (node== null) {
            return;
        }
        inorderRecursive(node.left, result);
        result.add(node.val);
        inorderRecursive(node.right, result);
    }

    public List<Integer> inorderRecursive() {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
    }
    
}
    