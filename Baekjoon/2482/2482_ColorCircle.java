import java.util.*;
import java.io.*;

public class Main {

	final static int DIV = 1000000003;
	static int n;
	static int k;
	static long[][] reg;
	static long ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		reg = new long[n + 1][k + 1];

		for(int i = 0; i < n + 1; i++) {
			Arrays.fill(reg[i], -1);
		}

		ans = find(n, k);
		
		System.out.println(ans);
	}

	public static long find(int a, int b) {
		if(a < 0 || b < 0) return 0;
		if(reg[a][b] != -1) return reg[a][b];
		if(b == 1) return reg[a][b] = a;
		if(a / 2 < b) return reg[a][b] = 0;

		reg[a][b] = (find(a - 2, b - 1) + find(a - 1, b)) % DIV;
		return reg[a][b];
	}
}
