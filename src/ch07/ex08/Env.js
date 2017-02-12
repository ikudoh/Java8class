// コマンドスクリプトから
// jjs -scripting
// をして↓を実行する
//var env = $EXEC('printenv');
//print(env)
var env1 = $EXEC('env | grep -i processor_arch');
print(env1)