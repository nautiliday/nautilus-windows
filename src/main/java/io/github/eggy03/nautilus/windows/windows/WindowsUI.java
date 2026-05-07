/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.windows;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.common.ui.AboutUI;
import io.github.eggy03.nautilus.windows.common.ui.ThemeManagerUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMIMainboardPanelUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMINetworkPanelUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMIOperatingSystemUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMIPhysicalMemoryPanelUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMIProcessorPanelUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMIStoragePanelUI;
import io.github.eggy03.nautilus.windows.windows.panels.WMIVideoControllerPanelUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JMenuItem;

public class WindowsUI extends JFrame {

	/**
	 * Create the frame.
	 */
	public WindowsUI() {

		setTitle("Nautilus");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 650);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowsUI.class.getResource("/icons/icon_main.png")));
        
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(createMenu(), BorderLayout.NORTH);
		contentPane.add(createTabbedPane(), BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}
	
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setIcon(new FlatSVGIcon(WindowsUI.class.getResource("/icons/general_icons/about.svg")));
		aboutMenuItem.addActionListener(event -> new AboutUI().setVisible(true));
		helpMenu.add(aboutMenuItem);

		JMenu appearanceMenu = new JMenu("Appearance");
		menuBar.add(appearanceMenu);

		JMenuItem themeMenuItem = new JMenuItem("Theme");
		themeMenuItem.setIcon(new FlatSVGIcon(WindowsUI.class.getResource("/icons/general_icons/theme.svg")));
		themeMenuItem.addActionListener(event -> new ThemeManagerUI().setVisible(true));
		appearanceMenu.add(themeMenuItem);
		
		return menuBar;
		
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		
		tabbedPane.addTab("CPU", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/CPU.svg")), new WMIProcessorPanelUI().getPanel(), null);
		tabbedPane.addTab("Memory", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/RAM.svg")), new WMIPhysicalMemoryPanelUI().getPanel(), null);
		tabbedPane.addTab("Mainboard", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/MainBoard.svg")), new WMIMainboardPanelUI().getPanel(), null);
		tabbedPane.addTab("GPU", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/GPU.svg")), new WMIVideoControllerPanelUI().getPanel(), null);
		tabbedPane.addTab("Network", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/Network.svg")), new WMINetworkPanelUI().getPanel(), null);
		tabbedPane.addTab("Storage", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/Storage.svg")), new WMIStoragePanelUI().getPanel(), null);
		tabbedPane.addTab("OS", new FlatSVGIcon(WindowsUI.class.getResource("/icons/tab_icons_material_green/OS.svg")), new WMIOperatingSystemUI().getPanel(), null);
		
		return tabbedPane;
	}

}
