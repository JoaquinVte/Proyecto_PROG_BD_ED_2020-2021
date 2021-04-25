package com.mordor.lloguer;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.UIManagers;
import com.mordor.lloguer.controller.MainController;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.MyOracleDB;
import com.mordor.lloguer.view.JFMain;

public class App {

	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					
//					JFMain view = new JFMain();
//					Model model = new MyOracleDB();	
//					
//					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//						
//						System.out.println(info.getName());
//						// Metal
//						// Nimbus
//						// CDE/Motif
//						// GTK+
//						
//						if ("GTK+".equals(info.getName())) {
//							//UIManager.setLookAndFeel(info.getClassName());
//							UIManagers.initialize();
//			                UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
//							SwingUtilities.updateComponentTreeUI(view);
//							//break;
//						}
//					}
//					
//					MainController controller = new MainController(view,model);
//					controller.go();
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

		SwingUtilities.invokeLater(() -> {
			try {
				
                // Install WebLaF as application LaF
                WebLookAndFeel.install ();

//				UIManager.setLookAndFeel(new WebLookAndFeel());
				
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
