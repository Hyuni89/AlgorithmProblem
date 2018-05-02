class FriendTree {
    var relationship: [[Int]]
    var n: Int
    var visit: [Bool]
    var reg: [[Int]]

    init(n: Int, rel: [[Int]]) {
        self.n = n
        self.relationship = rel
        self.visit = [Bool](repeating: false, count: n)
        self.reg = [[Int]](repeating: [Int](repeating: -1, count: n), count: 2)
    }

    func searchTree(pos: Int) {
    	visit[pos] = true
    	reg[0][pos] = 0
    	reg[1][pos] = 1

    	for next in relationship[pos] {
    		if visit[next] == false {
    			searchTree(pos: next)
    			reg[0][pos] += reg[1][next]
    			reg[1][pos] += min(reg[0][next], reg[1][next])
			}
		}
    }

    func getAns() -> Int {
        searchTree(pos: 0)

		return min(reg[0][0], reg[1][0])
    }
}

let n = Int(readLine()!)!
var rel = [[Int]](repeating: [Int](), count: n)

for _ in 0 ..< n - 1 {
    let tmp = readLine()!.split(separator: " ").map {Int($0)!}
    rel[tmp[0] - 1].append(tmp[1] - 1)
    rel[tmp[1] - 1].append(tmp[0] - 1)
}

let ft = FriendTree(n: n, rel: rel)
print(ft.getAns())
