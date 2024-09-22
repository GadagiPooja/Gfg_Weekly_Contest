Q1.

class Solution {
    public static long maxProduct(int n) {
        // code here
         long  a = n / 2;
        long b = n - a;
        
        // Return the maximum product of the two splits
        return a * b;
    }
}


Q2.

class Solution {
    public static long maxSum(int n, int x, int[] arr) {
        // code here
         ArrayList<Integer> setBitGroup = new ArrayList<>();
        ArrayList<Integer> unsetBitGroup = new ArrayList<>();
        
        for (int num : arr) {
            if ((num & (1 << x-1)) != 0) {
                setBitGroup.add(num);
            } else {
                unsetBitGroup.add(num);
            }
        }
        
        if (setBitGroup.isEmpty() || unsetBitGroup.isEmpty()) {
            return -1;  
        }
        
        int maxSetBit = setBitGroup.stream().max(Integer::compareTo).get();
        int maxUnsetBit = unsetBitGroup.stream().max(Integer::compareTo).get();
        
        return maxSetBit + maxUnsetBit;
    }
}


Q3.
  
class Solution {
    static List<List<Integer>> tree;
    static int[] subtreeSize;
    
    public static int dfs(int node, int parent) {
        subtreeSize[node] = 1; 
        for (int neighbor : tree.get(node)) {
            if (neighbor != parent) {
                subtreeSize[node] += dfs(neighbor, node);
            }
        }
        return subtreeSize[node];
    }
    
    public static long getCount(int n, int[][] edges, int x, int y) {
          tree = new ArrayList<>();
        subtreeSize = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        
        dfs(1, -1);
        
        long countX = 0, countY = 0;
        for (int i = 1; i <= n; i++) {
            if (subtreeSize[i] == x) {
                countX++;
            }
            if (subtreeSize[i] == y) {
                countY++;
            }
        }
        
        return countX * countY;
    }
}
