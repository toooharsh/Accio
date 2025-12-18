import java.util.*;

class Solution{
    public void balancedBrackets(String s, int n) {

        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            // If closing bracket
            if(c == '}' || c == ')' || c == ']') {

                if(st.isEmpty()) {
                    System.out.println("NO");
                    return;
                }

                char top = st.peek();

                if((c == '}' && top != '{') ||
                        (c == ')' && top != '(') ||
                        (c == ']' && top != '[')) {
                    System.out.println("NO");
                    return;
                }

                st.pop();
            }
            else {
                st.push(c);
            }
        }

        if(st.isEmpty()) System.out.println("YES");
        else System.out.println("NO");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        Solution Obj = new Solution();
        Obj.balancedBrackets(s, n);
    }
}
