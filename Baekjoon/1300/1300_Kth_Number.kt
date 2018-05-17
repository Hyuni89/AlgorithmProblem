import kotlin.math.*

fun main(args: Array<String>) {
	val n = readLine()!!.toInt()
	val k = readLine()!!.toInt()

	var left = 1
	var right = k
	var ans = 0
	while(left <= right) {
		val mid = left + (right - left) / 2
		var cnt: Long = 0

		for(i in 1 .. n) {
			cnt += min(mid / i, n).toLong()
		}

		if(cnt < k) {
			left = mid + 1
		} else {
			right = mid - 1
			ans = mid
		}
	}

	println(ans)
}
