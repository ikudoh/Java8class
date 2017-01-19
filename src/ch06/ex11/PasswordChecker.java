package ch06.ex11;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PasswordChecker {
	public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
		return CompletableFuture.supplyAsync(action)	// 非同期
				.thenComposeAsync(t -> {
					while(!until.test(t)) {
						repeat(action, until);
					}
					return CompletableFuture.completedFuture(t);
				});
	};

	//　できてないです…
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
		CompletableFuture<PasswordAuthentication> pw = repeat(
				() -> {
					System.out.println("ユーザー名を入力してください：");
					String user = scanner.next();	// 受け付けられない？
					System.out.println("パスワードを入力してください：");
					String password = scanner.next();
					scanner.close();
					return new PasswordAuthentication(user, password.toCharArray());
				},
				pwResult -> {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					return "secret".equals(pwResult.getPassword().toString());
				});
		pw.thenAccept(ret -> {
			System.out.println("ユーザー名：" + ret.getUserName().toString() +
					"パスワード：" + ret.getPassword().toString());
		});
	}

}
