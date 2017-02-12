function arrFactory() {
	var retArray = new (Java.extend(java.util.ArrayList)) {
		add: function(x) {
			print('Adding ' + x);
			return Java.super(retArray).add(x)
		}
	}
	return retArray
}
var a = arrFactory()
a.add('aaaa')
var b = arrFactory()
b.add('bbbb')
