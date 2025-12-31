import java.util.*;

class Node {

    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) arr[i] = sc.next();
        int k = sc.nextInt();
        sc.close();
        Node tree = levelOrderCreateTree(arr);
        System.out.println(timeToBurnTree(tree, k));
    }

    public static Node findStartNode(Node root,int start){
        if(root==null) return null;
        if(root.data==start) return root;

        Node leftAns=findStartNode(root.left,start);
        if(leftAns!=null) return leftAns;

        return findStartNode(root.right,start);
    }

    public static void getChildParentMap(HashMap<Node,Node> mp,Node root){
        if(root==null) return;

        if(root.left!=null) mp.put(root.left,root);
        if(root.right!=null) mp.put(root.right,root);

        getChildParentMap(mp,root.left);
        getChildParentMap(mp,root.right);
    }


    public static int timeToBurnTree(Node root, int start) {
        if(root==null) return 0;
        //   Write your code here
        HashMap<Node,Node> mp=new HashMap<>();
        getChildParentMap(mp,root);

        Node s=findStartNode(root,start);
        if(s==null) return 0;
        Queue<Node> q=new ArrayDeque<>();
        q.add(s);
        HashSet<Node> st=new HashSet<>();
        int time=-1;

        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Node curr=q.poll();
                st.add(curr);
                if(curr.left!=null && !st.contains(curr.left)) q.add(curr.left);
                if(curr.right!=null && !st.contains(curr.right)) q.add(curr.right);

                if(mp.containsKey(curr)){
                    Node parent=mp.get(curr);
                    if(!st.contains(parent)) q.add(parent);
                }
            }
            time++;
        }
        return time;
    }

    static void createTree(Node node, int i, String[] arr) {
        if (node != null) {
            if (2 * i + 1 < arr.length) {
                if (arr[2 * i + 1].equals("null")) {
                    node.left = null;
                } else {
                    node.left = new Node(Integer.parseInt(arr[2 * i + 1]));
                }
                createTree(node.left, 2 * i + 1, arr);
            }
            if (2 * i + 2 < arr.length) {
                if (arr[2 * i + 2].equals("null")) {
                    node.right = null;
                } else {
                    node.right = new Node(Integer.parseInt((arr[2 * i + 2])));
                }
                createTree(node.right, 2 * i + 2, arr);
            }
        }
    }

    static Node levelOrderCreateTree(String[] arr) {
        if (arr.length == 0) return null;
        Node head = new Node(
                Integer.parseInt(arr[0])
        );
        createTree(head, 0, arr);
        return head;
    }
}


