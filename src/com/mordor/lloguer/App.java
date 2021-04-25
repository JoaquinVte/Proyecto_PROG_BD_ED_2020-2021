package com.mordor.lloguer;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.mordor.lloguer.controller.MainController;
import com.mordor.lloguer.model.Model;
import com.mordor.lloguer.model.MyOracleDB;
import com.mordor.lloguer.view.JFMain;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					JFMain view = new JFMain();
					Model model = new MyOracleDB();	
					
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						
						//System.out.println(info.getName());
						
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							SwingUtilities.updateComponentTreeUI(view);
							break;
						}
					}
					
					MainController controller = new MainController(view,model);
					controller.go();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
