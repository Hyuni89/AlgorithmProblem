class Point {
	var x: Int
	var y: Int
	var dx: Int
	var dy: Int

	init(x: Int, y: Int) {
		self.x = x
		self.y = y
		self.dx = 0
		self.dy = 0
	}
}

func ccw(base: Point, first: Point, second: Point) -> Int {
	return (first.x - base.x) * (second.y - base.y) - (first.y - base.y) * (second.x - base.x)
}

let n = Int(readLine()!)!
var point = [Point]()

for _ in 0 ..< n {
	let tmp = readLine()!.split(separator: " ").map {Int($0)!}
	point.append(Point(x: tmp[0], y: tmp[1]))
}

point.sort(by: {(a: Point, b: Point) -> Bool in
	if a.y < b.y {
		return true
	} else if a.y > b.y {
		return false
	} else {
		if a.x < b.x {
			return true
		} else {
			return false
		}
	}
})

for i in 1 ..< point.count {
	point[i].dx = point[i].x - point[0].x
	point[i].dy = point[i].y - point[0].y
}

var sub = point[1 ..< point.count]
sub.sort(by: {(a: Point, b: Point) -> Bool in
	let tmp = a.dx * b.dy - a.dy * b.dx
	if tmp == 0 {
		if a.y != b.y {
			return a.y < b.y
		} else {
			return a.x < b.x
		}
	}
	return tmp > 0
})
point = Array(point[0 ... 0]) + sub

var st = [Int]()
st.append(0)
st.append(1)
var next = 2

while next < n {
	while st.count >= 2 {
		let prev = st.removeLast()
		let base = st.last!

		if ccw(base: point[base], first: point[prev], second: point[next]) > 0 {
			st.append(prev)
			break
		}
	}

	st.append(next)
	next += 1
}

print(st.count)
