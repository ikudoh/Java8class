var str = 'abcdefg'.split('d')[0]
print(str.getClass())	//class java.lang.String
java.lang.String.class.cast(str)
print(str.getClass())	//class java.lang.String (何も起こらない…)