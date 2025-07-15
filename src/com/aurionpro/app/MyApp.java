package com.aurionpro.app;

import java.io.IOException;
import java.util.Scanner;
import com.aurionpro.model.login.User;
import com.aurionpro.utils.FileUtil;

public class MyApp {
	public static int foodPreferencechoice = 0;
	public static int latestOrderId;


	public static void main(String[] args) throws IOException, ClassNotFoundException {

		latestOrderId = FileUtil.readLastOrderId();
		Scanner scanner = new Scanner(System.in);
		User currentuser = AuthenticationFunction.authenticateUser(scanner);
		if (currentuser.getUsername() == "admin") {
			AdminMenuFunctions.displayAdminMenu(scanner, currentuser);
			return;
		}
		CustomerMenuFunction.createOrderList(scanner);

	}
}
