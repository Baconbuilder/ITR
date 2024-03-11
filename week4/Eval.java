package week4;
import java.util.Stack;
import java.util.Scanner;

public class Eval {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        Scanner scanner = new Scanner(System.in); 

        while (scanner.hasNext()) {
            String s = scanner.next();
            if (s.equals("(")) ;
            else if (s.equals("+") || s.equals("*")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("*")) vals.push(vals.pop() * vals.pop());
                else if (op.equals("+")) vals.push(vals.pop() + vals.pop());
            } else {
                vals.push(Double.parseDouble(s));
            }
        }
        scanner.close(); 
        System.out.println(vals.pop()); 
    }
}

