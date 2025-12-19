import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str=scn.nextLine();
        valueOfExpression(str);

    }
    public static boolean isOperator(char c){
        return (c=='%' || c=='*' || c=='-' || c=='+' || c=='/');
    }
    public static int calculate(int opr1,int opr2,char c){
        if(c=='+') return opr1+opr2;
        if(c=='-') return opr1-opr2;
        if(c=='*') return opr1*opr2;
        if(c=='/') return opr1/opr2;
        return opr1%opr2;
    }
    public static void solve(String str){
        Stack<Integer> st=new Stack<>();
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(isOperator(c)){
                int val1=st.pop();
                int val2=st.pop();
                int ans=calculate(val1,val2,c);
                st.push(ans);
            }
            else{
                st.push(c-'0');
            }
        }

        System.out.println(st.peek());
    }
    public static void prefixToInfix(String str){
        Stack<String> st=new Stack<>();
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(isOperator(c)){
                String val1=st.pop();
                String val2=st.pop();
                st.push("("+val1+c+val2+")");
            }
            else{
                st.push(c+"");
            }
        }
        System.out.println(st.peek());
    }
    public static void prefixToPostfix(String str){
        Stack<String> st=new Stack<>();
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(isOperator(c)){
                String val1=st.pop();
                String val2=st.pop();
                st.push(val1+val2+c);
            }
            else{
                st.push(c+"");
            }
        }
        System.out.println(st.peek());
    }
    public static void valueOfExpression(String str)
    {
        // your code here
        solve(str);
        prefixToInfix(str);
        prefixToPostfix(str);

    }
}