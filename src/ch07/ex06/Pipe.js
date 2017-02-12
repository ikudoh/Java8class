// コマンドスクリプトから
// jjs -scripting
// をして↓を実行する

//linux 用
//pipe('find .', 'grep -v class', 'sort')

// コピペで使うときは1行にする
//function pipe() { var input; input = $EXEC(arguments[0]); for(var i = 1; i < arguments.length; i++) { input = $EXEC(arguments[i], input);}return input;}

function pipe() {
	var input = $EXEC(arguments[0]);
	for(var i = 1; i < arguments.length; i++) {
		input = $EXEC(arguments[i], input);
	}
	return input;
}
var p = pipe('cmd /k find .', 'grep -v class', 'sort')
print(p)