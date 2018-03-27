import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> st = new Stack<Character>();
		ArrayList<String> al = new ArrayList<String>();

		String equation = br.readLine();
		String ans = "";
		boolean bracket = false;
		for(int i = 0; i < equation.length(); i++) {
			switch(equation.charAt(i)) {
				case ')':
					bracket = false;
					String tmp = "";
					int cnt = 0;
					for(int j = al.size() - 1; j >= 0; j--) {
						if(al.get(j).equals("(")) {
							for(int k = 0; k < cnt - 1; k++) {
								tmp += Character.toString(st.pop());
							}
							al = new ArrayList<String>(al.subList(0, j));
							break;
						} else {
							tmp = al.get(j) + tmp;
							cnt++;
						}
					}
					al.add(tmp);
					break;
				case '*':
				case '/':
					if(!bracket && st.size() != 0 && st.peek() != '+' && st.peek() != '-') {
						compress(al, st);
					}
					bracket = false;
					st.push(equation.charAt(i));
					break;
				case '+':
				case '-':
					while(!bracket && st.size() != 0) {
						String res = compress(al, st);
						if(res.equals("")) break;
					}
					bracket = false;
					st.push(equation.charAt(i));
					break;
				default:
					if(equation.charAt(i) == '(') {
						bracket = true;
					}
					al.add(Character.toString(equation.charAt(i)));
			}
		}

		while(al.size() != 1) {
			ans = compress(al, st);
		}
		System.out.println(al.get(0));
	}

	public static String compress(ArrayList<String> al, Stack<Character> st) {
		String ret = "";
		for(int j = 0; j < 2; j++) {
			if(al.get(al.size() - j - 1).equals("(")) return "";
			ret = al.get(al.size() - j - 1) + ret;
		}
		for(int j = 0; j < 2; j++) {
			al.remove(al.size() - 1);
		}
		if(!st.isEmpty()) {
			ret += Character.toString(st.pop());
		}

		al.add(ret);

		return ret;

	}
}
