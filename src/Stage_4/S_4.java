package Stage_4;

import java.util.*;

class expression{
    String exp;
    expression(String exp){
        this.exp = exp;
    }

    public void solution(){
        String exp = this.exp;
        System.out.println("Expression: "+exp);
        stackEvaluation(exp);
    }
    public void stackEvaluation(String exp) {
        try {
            exp = "( " + exp + " )";
            String[] expNew = exp.split(" ");
            Stack<String> expStack = new Stack<>();
            ArrayList<Double> finalExp = new ArrayList<>();

            String previous = "";
            for (String string : expNew) {
                if (!string.matches("([+\\-*/()]|-?\\d+(\\.\\d+)?)")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                } else if (string.matches("[+\\-*/]") && previous.matches("[+\\-*/]")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                } else if (string.matches("-?\\d+(\\.\\d+)?") && previous.matches("-?\\d+(\\.\\d+)?")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                } else if (previous.equals("(") && string.matches("[+\\-*/]")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                } else if (previous.equals(")") && string.matches("-?\\d+(\\.\\d+)?")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                } else if (previous.equals(")") && string.equals("(")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must contain some operator between the brackets.");
                } else if (previous.matches("[+\\-*/]") && string.equals(")")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                } else if (previous.equals("(") && string.equals(")")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must not contain empty brackets.");
                } else if (string.equals("(") && previous.matches("-?\\d+(\\.\\d+)?")) {
                    throw new IllegalArgumentException("Invalid expression: The expression must be in the form of a number, an operator and then a number.");
                }
                previous = (string);
            }

            if(expNew[expNew.length - 2].matches("[+\\-*/]")){
                throw new IllegalArgumentException("Invalid Expression: The expression must be in the form of a number, an operator and then a number.");
            }

            for (String string : expNew) {
                if (string.matches("-?\\d+(\\.\\d+)?")) {
                    finalExp.add(Double.parseDouble(string));
                } else if (string.equals(")") && !expStack.empty()) {
                    while (!expStack.empty() && !expStack.peek().equals("(")) {
                         if (expStack.peek().equals("+")) {
                            int k = finalExp.size() - 1;
                            double a = add(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, a);
                            finalExp.remove(k);
                            expStack.pop();
                        } else if (expStack.peek().equals("-")) {
                            int k = finalExp.size() - 1;
                            Double s = sub(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, s);
                            finalExp.remove(k);
                            expStack.pop();
                        } else if (expStack.peek().equals("*")) {
                            int k = finalExp.size() - 1;
                            double m = mul(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, m);
                            finalExp.remove(k);
                            expStack.pop();
                        } else if (expStack.peek().equals("/")) {
                            int k = finalExp.size() - 1;
                            double d = div(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, d);
                            finalExp.remove(k);
                            expStack.pop();
                        }
                    }
                    expStack.pop();
                } else if ((string.equals("+") || string.equals("-")) && !expStack.empty()) {
                    while(!expStack.empty() && (expStack.peek().equals("*") || expStack.peek().equals("/") || expStack.peek().equals("+") || expStack.peek().equals("-"))) {
                        if (expStack.peek().equals("*")) {
                            int k = finalExp.size() - 1;
                            double m = mul(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, m);
                            finalExp.remove(k);
                            expStack.pop();
                        } else if (expStack.peek().equals("/")) {
                            int k = finalExp.size() - 1;
                            Double d = div(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, d);
                            finalExp.remove(k);
                            expStack.pop();
                        } else if (expStack.peek().equals("+")) {
                            int k = finalExp.size() - 1;
                            Double a = add(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, a);
                            finalExp.remove(k);
                            expStack.pop();
                        }  else if (expStack.peek().equals("-")) {
                            int k = finalExp.size() - 1;
                            Double s = sub(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, s);
                            finalExp.remove(k);
                            expStack.pop();
                        }
                    }
                    expStack.push(string);
                } else if (!expStack.empty() && (string.equals("*") || (string.equals("/")))){
                    while(!expStack.empty() && (expStack.peek().equals("*") || expStack.peek().equals("/") )){
                        if (expStack.peek().equals("/")) {
                            int k = finalExp.size() - 1;
                            Double d = div(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, d);
                            finalExp.remove(k);
                            expStack.pop();
                        } else if (expStack.peek().equals("*")) {
                            int k = finalExp.size() - 1;
                            double m = mul(finalExp.get(k - 1), finalExp.get(k));
                            finalExp.set(k - 1, m);
                            finalExp.remove(k);
                            expStack.pop();
                        }
                    }
                    expStack.push(string);
                } else if (string.equals(")") && expStack.empty()) {
                    throw new IllegalArgumentException("The expression should contain both left parenthesis and right parenthesis.");
                } else {
                    expStack.push(string);
                }
            }
            if(!expStack.empty()){
                throw new IllegalArgumentException("The expression should contain both left parenthesis and right parenthesis.");
            }
            System.out.println("Solution: " + finalExp.get(0));
        }
        catch (ArithmeticException | IllegalArgumentException | IndexOutOfBoundsException e){
            System.out.println("Error caught: "+e.getMessage());
        }
    }
    public double add(double a, double b){
        return a + b;
    }
    public double sub(double a, double b){
        return a - b;
    }
    public double mul(double a, double b){
        return a * b;
    }
    public double div(double a, double b){
        if(b == 0){
            throw new ArithmeticException("Invalid Expression: Cannot Divide a number by 0.");
        }
        return a / b;
    }
}
public class S_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Choose from the menu");
            System.out.println("1.Calculate Expression \n2.Exit");
            int menu = input.nextInt();
            input.nextLine();
            if (menu == 1) {
                System.out.println("Enter the expression to evaluate");
                String exp = input.nextLine();
                Stage_4.expression exp1 = new Stage_4.expression(exp);
                exp1.solution();
            }
            else if (menu == 2) {
                System.out.println("Exiting the program ...");
                break;
            }
            else {
                System.out.println("Invalid input");
            }
        }
        input.close();
    }
}
