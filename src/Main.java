import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        Solution ob =new Solution();
        ob.evaluate(exp);
    }
}

class Solution{

    public int calculate(int val1,int val2,char op){
        if(op=='+') return val1+val2;
        else if(op=='-') return val1-val2;
        else if(op=='*') return val1*val2;
        return val1/val2;
    }

    public int precendence(char c){
        if(c=='+') return 1;
        else if(c=='-') return 1;
        else if(c=='*') return 2;
        return 2;
    }

    public void solveInfix(String exp){

        Stack<Integer> st1=new Stack<>();
        Stack<Character> st2=new Stack<>();
        for(int i=0;i<exp.length();i++){
            char ch=exp.charAt(i);
            if(ch=='('){
                st2.push(ch);
            }
            else if(Character.isDigit(ch)){
                st1.push(ch-'0');
            }
            else if (ch==')') {
                while(st2.peek()!='('){
                    char op=st2.pop();
                    int val2=st1.pop();
                    int val1=st1.pop();

                    int cal=calculate(val1,val2,op);
                    st1.push(cal);
                }
                st2.pop();
            }
            else if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
                while(!st2.isEmpty() && st2.peek()!='(' && precendence(ch)<=precendence(st2.peek())){
                        char op=st2.pop();
                        int val2=st1.pop();
                        int val1=st1.pop();

                        int cal=calculate(val1,val2,op);
                        st1.push(cal);
                }
                st2.push(ch);
            }
        }
        while(!st2.isEmpty()){
            char op=st2.pop();
            int val2=st1.pop();
            int val1=st1.pop();

            int cal=calculate(val1,val2,op);
            st1.push(cal);
        }
        System.out.println(st1.peek());
    }

    public StringBuilder infixToPostfix(String exp){
        Stack<Character> st=new Stack<>();
        StringBuilder ans=new StringBuilder();

        for(int i=0;i<exp.length();i++){
            char ch=exp.charAt(i);
            if(ch=='('){
                st.push(ch);
            }
            else if(Character.isDigit(ch)){
                ans.append(ch);
            }
            else if(ch==')'){
                while(!st.isEmpty() && st.peek()!='('){
                    ans.append(st.pop());
                }
                st.pop();
            }
            else if(ch=='+' || ch=='-' || ch=='*' || ch=='/'){
                while(!st.isEmpty() && st.peek()!='(' && precendence(ch)<=precendence(st.peek())){
                    ans.append(st.pop());
                }
                st.push(ch);
            }
        }

        while(!st.isEmpty()){
            ans.append(st.pop());
        }

        return ans;
    }

    public void infixToPrefix(String exp){
        int n=exp.length()-1;
        StringBuilder ans=new StringBuilder();

        for(int i=n;i>=0;i--){
            char ch=exp.charAt(i);
            if(ch==')'){
                ans.append('(');
            }
            else if(ch=='('){
                ans.append(')');
            }
            else ans.append(ch);
        }

        String newExp=ans.toString();
        StringBuilder finalAns=infixToPostfix(newExp);
        System.out.println(finalAns.reverse());
    }

    public void evaluate(String exp){
        solveInfix(exp);
        StringBuilder post=infixToPostfix(exp);
        System.out.println(post);
        infixToPrefix(exp);
    }
}