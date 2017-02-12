// Q. var b = new java.math.BigInteger('1234567890987654321')のbを表示すると何が得られたか。
// A. 1234567890987654400 (最新のJavaバージョンだと1234567890987654321が表示される）
var b = new java.math.BigInteger('1234567890987654321')
print(b)

// Q. b.mod(java.math.BigInteger.TEN) の値は何か。
// A. 1（上記答え1234567890987654400をmodした値ではなく、bの元の値1234567890987654321をmodした値。）
b = b.mod(java.math.BigInteger.TEN)
print(b)

// Q. bはなぜ奇妙に表示されるのか。
// A. 　表示可能な桁数を超えているから。（2^53まで）

