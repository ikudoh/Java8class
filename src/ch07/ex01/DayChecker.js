// DayChecker.java相当のことをJSでしてみる。

var BIRTHDAY = java.time.LocalDate.of(1986,  2, 18)

var days = BIRTHDAY.until(java.time.LocalDate.now(), java.time.temporal.ChronoUnit.DAYS)
print("生きてきた日数：" + days)

// Q. Javaでテストプログラムを書くより簡単か？
// A. main()を書かなくてよい分は簡単。パスを指定するのは面倒。