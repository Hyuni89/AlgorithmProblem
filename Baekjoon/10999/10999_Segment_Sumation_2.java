import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(tk.nextToken());
		int m = Integer.parseInt(tk.nextToken());
		int k = Integer.parseInt(tk.nextToken());
		int[] arr = new int[n];

		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		SegmentTree st = new SegmentTree(arr, n);

		for(int i = 0; i < m + k; i++) {
			tk = new StringTokenizer(br.readLine());
			int behave = Integer.parseInt(tk.nextToken());
			int from = Integer.parseInt(tk.nextToken());
			int to = Integer.parseInt(tk.nextToken());

			if(behave == 1) {
				long num = Long.parseLong(tk.nextToken());
				st.update(from - 1, to - 1, num);
			} else if(behave == 2) {
				System.out.println(st.getSum(from - 1, to - 1));
			}
		}
	}

	static class SegmentTree {
		int[] num;
		long[] tree;
		long[] lazy;

		SegmentTree(int[] arr, int n) {
			num = arr;

			int cnt = 0;
			int tmp = n;
			while(tmp != 0) {
				tmp /= 2;
				cnt++;
			}

			int size = (int)Math.pow(2, cnt + 1);
			tree = new long[size + 1];
			lazy = new long[size + 1];

			makeTree(1, 0, num.length - 1);
		}

		long makeTree(int index, int start, int end) {
			if(start == end) {
				tree[index] = num[start];
				return tree[index];
			}

			int mid = (start + end) / 2;
			tree[index] = makeTree(index * 2, start, mid) + makeTree(index * 2 + 1, mid + 1, end);

			return tree[index];
		}

		long getSum(int from, int to) {
			return getSum(1, 0, num.length - 1, from, to);
		}

		long getSum(int index, int start, int end, int from, int to) {
			tree[index] += lazy[index] * (end - start + 1);
			if(start != end) {
				lazy[index * 2] += lazy[index];
				lazy[index * 2 + 1] += lazy[index];
			}
			lazy[index] = 0;

			if(start > to || end < from) return 0;

			if(from <= start && end <= to) {
				return tree[index];
			}

			int mid = (start + end) / 2;
			return getSum(index * 2, start, mid, from, to) + getSum(index * 2 + 1, mid + 1, end, from, to);
		}

		void update(int from, int to, long delta) {
			update(1, 0, num.length - 1, from, to, delta);
		}

		void update(int index, int start, int end, int from, int to, long delta) {
			tree[index] += lazy[index] * (end - start + 1);
			if(start != end) {
				lazy[index * 2] += lazy[index];
				lazy[index * 2 + 1] += lazy[index];
			}
			lazy[index] = 0;

			if(start > to || end < from) return;

			if(from <= start && end <= to) {
				tree[index] += delta * (end - start + 1);

				if(start != end) {
					lazy[index * 2] += delta;
					lazy[index * 2 + 1] += delta;
				}

				return;
			}

			int mid = (start + end) / 2;
			update(index * 2, start, mid, from, to, delta);
			update(index * 2 + 1, mid + 1, end, from, to, delta);

			tree[index] = tree[index * 2] + tree[index * 2 + 1];
		}
	}
}
