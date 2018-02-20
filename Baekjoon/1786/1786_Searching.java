import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String t = new String(br.readLine());
		String p = new String(br.readLine());
		int[] reg = new int[p.length()];
		ArrayList<Integer> ans = new ArrayList<Integer>();

		int index = 0;
		reg[0] = -1;
		for(int i = 1; i < p.length(); i++) {
			index = reg[i - 1] + 1;
			while(index > 0 && p.charAt(i) != p.charAt(index)) {
				index = reg[index - 1] + 1;
			}
			if(p.charAt(i) == p.charAt(index)) {
				reg[i] = index;
			} else {
				reg[i] = -1;
			}
		}

		index = 0;
		for(int i = 0; i < t.length(); i++) {
			if(index == p.length()) {
				ans.add(i - p.length() + 1);
				index = reg[index - 1] + 1;
			}
			if(p.charAt(index) == t.charAt(i)) {
				index++;
			} else {
				while(index > 0) {
					index = reg[index - 1] + 1;
					if(p.charAt(index) == t.charAt(i)) {
						index++;
						break;
					}
				}
				if(index < 0) {
					index = 0;
				}
			}
		}

		if(index == p.length()) {
			ans.add(t.length() - p.length() + 1);
		}

		System.out.println(ans.size());
		for(int i = 0; i < ans.size(); i++) {
			System.out.printf("%d", ans.get(i));
			
			if(i != ans.size() - 1) {
				System.out.printf(" ");
			} else {
				System.out.println();
			}
		}
	}
}