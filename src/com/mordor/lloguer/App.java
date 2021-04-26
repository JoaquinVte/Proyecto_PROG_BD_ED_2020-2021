package com.mordor.lloguer;

import javax.swing.SwingUtilities;
import com.alee.laf.WebLookAndFeel;
import com.mordor.lloguer.controller.MainController;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.MyOracleDB;
import com.mordor.lloguer.view.JFMain;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			try {
				
                // Install WebLaF as application LaF
                WebLookAndFeel.install ();
				
				JFMain view = new JFMain();
				Model model = new MyOracleDB();

				MainController controller = new MainController(view, model);
				controller.go();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

}
