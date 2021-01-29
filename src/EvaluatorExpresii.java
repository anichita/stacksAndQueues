import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EvaluatorExpresii {
	
	public static int precedenta(char key) {
		HashMap<Character, Integer> precedenta = new HashMap<Character, Integer>();
		precedenta.put('+', 11);
		precedenta.put('-', 11);
		precedenta.put('*', 12);
		precedenta.put('/', 12);
		precedenta.put('^', 13);
		
		if (!precedenta.containsKey(key))
			return -1;
		
		return precedenta.get(key);
		
	}
	
	
	public static String shuntingYard(String input) {
		Deque<Character> stack = new ArrayDeque<Character>();
		StringBuilder output = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++) {
			char read = input.charAt(i);
			if (read >= '0' && read <= '9') {  // daca este operand
				output.append(read);
			} else if (precedenta(read) > 0){ 
				// daca este operator
				while (!stack.isEmpty() && (stack.peek() != '(') &&
						(precedenta(read) < precedenta(stack.peek()) 
								|| (precedenta(read) == precedenta(stack.peek()) && stack.peek() != '^'))) {  // ultima conditie de asociativitate stanga?
					output.append(stack.pop());		
				}
				stack.push(read);
			}
			
			// daca este paranteza stanga
			if (read == '(' || read == '[' || read == '{') {
				stack.push(read);
			}
			
			// daca este paranteza dreapta
			if (read == ')') {
				while (stack.peek() != '(') {
					output.append(stack.pop());
					if (stack.isEmpty()) {
						return "Eroare: Expresia avea paranteze gresite.";
					}	
				}
				
				if (!stack.isEmpty())
					stack.pop();
			}
			
		}
		
		//2 nu mai sunt entitati de citit
		
		while (!stack.isEmpty()) {
			if (stack.peek() == '(') {
				return "Eroare: Expresia avea paranteze gresite.";
			}
			output.append(stack.pop());
				
		}
		
		return output.toString();
		
	}
	
	public static Integer operation(int op1, int op2, char op) {
		switch (op) {
		case '+':
			return op2 + op1;
		case '-':
			return op2 - op1;
		case '*':
			return op2 * op1;
		case '/':
			return op2 / op1;
		case '^':
			int result = op2;
			while (op1 > 1) {
				result *= op2;
				op1--;
			}
			return result;
		default:
			return null;
		}
	}
	
	
	public static String postfixEval(String input) {
		Deque<String> stack = new LinkedList<String>();
		
		for (int i = 0; i < input.length(); i++) {
			char read = input.charAt(i);
			if (read >= '0' && read <= '9') {
				stack.push("" + read);
			} else {
				int op1;
				int op2;
				try {
					op1 = Integer.valueOf(stack.pop());
					op2 = Integer.valueOf(stack.pop());
				} catch (NoSuchElementException e) {
					return "Eroare: Expresia postfixata este gresita.";
				}
				int result = operation(op1, op2, read);
				stack.push("" + result);	
			}
			
		}
		String res = stack.pop();
		
		if (!stack.isEmpty())
			return "Eroare: expresia postfixata este gresita.";
		
		return res;
		
	}
	

	public static void main(String[] args) {
		String input = "3+(2+1)*2^3^2-8/(5-1*2/2)";
		System.out.println("Input: " + input);
		String shuntingYardResult = shuntingYard(input);
		System.out.println("\nShunting-yard result: " + shuntingYardResult);
		System.out.println("\nPostfix Evaluation result: " + postfixEval(shuntingYardResult));
		
	}

}
