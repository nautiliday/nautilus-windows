/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMIMainboardPanel;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMINetworkPanel;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMIOperatingSystemPanel;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMIPhysicalMemoryPanel;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMIProcessorPanel;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMIStoragePanel;
import io.github.eggy03.nautilus.windows.ui.primary.panels.WMIVideoControllerPanel;
import io.github.eggy03.nautilus.windows.ui.secondary.AboutUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public class MainUI extends JFrame {

    public MainUI initUI() {

        setTitle("Nautilus");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 650);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/icons/icon_main.png")));
        setLayout(new BorderLayout(0, 0));

        return this;
    }

    public MainUI initComponents() {

       add(createMenu(), BorderLayout.NORTH);
       add(createTabbedPane(), BorderLayout.CENTER);

       return this;

    }

    private JMenuBar createMenu() {

        final JMenuBar menuBar = new JMenuBar();

        final JMenu helpMenu = new JMenu("Help");

        final JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setIcon(new FlatSVGIcon(MainUI.class.getResource("/icons/general_icons/about.svg")));
        aboutMenuItem.addActionListener(event -> new AboutUI().setVisible(true));

        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        return menuBar;

    }

    private JTabbedPane createTabbedPane() {

        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);

        tabbedPane.addTab(
                "CPU",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/CPU.svg")),
                new WMIProcessorPanel().initUI().initComponents().initWorkers(),
                null
        );

        tabbedPane.addTab(
                "Memory",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/RAM.svg")),
                new WMIPhysicalMemoryPanel().initUI().initComponents().initWorkers(),
                null
        );

        tabbedPane.addTab(
                "Mainboard",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/MainBoard.svg")),
                new WMIMainboardPanel().initUI().initComponents().initWorkers(),
                null
        );

        tabbedPane.addTab(
                "GPU",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/GPU.svg")),
                new WMIVideoControllerPanel().initUI().initComponents().initWorkers(),
                null
        );

        tabbedPane.addTab(
                "Network",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/Network.svg")),
                new WMINetworkPanel().initUI().initComponents().initWorkers(),
                null
        );

        tabbedPane.addTab(
                "Storage",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/Storage.svg")),
                new WMIStoragePanel().initUI().initComponents().initWorkers(),
                null
        );

        tabbedPane.addTab(
                "OS",
                new FlatSVGIcon(MainUI.class.getResource("/icons/tab_icons_material_green/OS.svg")),
                new WMIOperatingSystemPanel().initUI().initComponents().initWorkers(),
                null
        );

        return tabbedPane;
    }

}
