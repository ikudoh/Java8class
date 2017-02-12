
var dir = java.lang.System.getProperty("user.dir")
var path = java.nio.file.Paths.get(dir + "/Alice.txt")
var bytes = java.nio.file.Files.readAllBytes(path)
var contents = new java.lang.String(bytes)
var javaArrayType = Java.type("java.lang.String[]")
var jsArray = contents.split(/\W+/)
var words = java.util.Arrays.asList(Java.to(jsArray, javaArrayType))
print("--- Alice over 12 length---")
var wordsStream = words.stream()
var longWords = wordsStream.filter(function (w) w.length() > 12)
longWords = longWords.distinct()
longWords = longWords.sorted()
longWords.forEach(function(longWord) print(longWord))


