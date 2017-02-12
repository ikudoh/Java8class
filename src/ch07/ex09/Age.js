// コマンドスクリプトから
// jjs -scripting

var input = "${arguments[0]}" // input='undefined'
if(input.length == 0 || input == 'undefined') {
	input = $ENV.AGE // input=undefined(nullと同義)
	if(input == undefined) {
		input = readLine('age : ')
	}
}
var nextAge = java.lang.Integer.valueOf(input) + 1;
print('Next year, you will be ' + nextAge + ' years old.')