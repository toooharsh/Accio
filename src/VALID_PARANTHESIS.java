import java.io.*;
import java.util.*;

class Solution{
    public boolean expBalanced(String str){
        // write code here

        int n=str.length();
        Stack<Character> st=new Stack<>();

        for(int i=0;i<n;i++){
            char c=str.charAt(i);
            if(c>='a' && c<='z') continue;
            if(c=='+' || c=='-' || c=='/' || c=='*') continue;
            if(c=='(' || c=='{' || c=='['){
                st.push(c);
            }
            else{
                if(st.isEmpty()) return false; //to handle more closing brackets than opening
                char top = st.peek();

                if((c == ')' && top == '(') ||
                        (c == '}' && top == '{') ||
                        (c == ']' && top == '[')){
                    st.pop();  // They match, remove the opening bracket
                }
                else{
                    return false;
                }
            }
        }
        return st.isEmpty(); //for handling more open brackets than closing
    }
}

public class VALID_PARANTHESIS {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Solution Obj = new Solution();
        System.out.println(Obj.expBalanced(str));
    }
}