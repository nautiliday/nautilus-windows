/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.worker.WMINetworkPanelWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.util.List;

public class WMINetworkPanelUI extends JPanel {

    private JComboBox<Long> networkInterfaceIdComboBox;

    private JTextField networkDeviceIdTextField;
    private JTextField networkNameTextField;
    private JTextField networkDriverVersionTextField;
    private JTextField networkDriverDateTextField;
    private JTextField networkTypeTextField;
    private JTextField networkLinkLayerAddressTextField;
    private JTextField networkLinkSpeedTextField;
    private JTextField networkMediaConnectStateTextField;
    private JTextField networkMediaConnectTypeTextField;
    private JTextField networkReceiveLinkSpeedTextField;
    private JTextField networkTransmitLinkSpeedTextField;
    private JTextField networkDuplexityTextField;
    private JTextField networkVirtualityTextField;
    private JTextField networkStatusTextField;
    private JTextField networkPnPDeviceIdTextField;

    private JEditorPane adapterIpEditorPane;
    private JEditorPane adapterDnsEditorPane;
    private JEditorPane adapterConnectionProfileEditorPane;

    public WMINetworkPanelUI() {
        setLayout(new GridLayout(2, 1, 0, 0));

        add(createAdapterPanel());
        add(createAdapterPropertyTabbedPane());
        setWorker();
    }

    public JPanel getPanel() {
        return this;
    }

    private JScrollPane createAdapterPanel() {

        JPanel adapterPanel = new JPanel();
        adapterPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Adapter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        adapterPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][][][][][][]"));

        JLabel networkInterfaceIdLabel = new JLabel("Interface#");
        adapterPanel.add(networkInterfaceIdLabel, "cell 0 0,alignx leading");

        networkInterfaceIdComboBox = new JComboBox<>();
        adapterPanel.add(networkInterfaceIdComboBox, "cell 1 0,growx");

        JLabel networkDeviceIdLabel = new JLabel("DeviceID");
        adapterPanel.add(networkDeviceIdLabel, "cell 0 1,alignx leading");

        networkDeviceIdTextField = new JTextField();
        networkDeviceIdTextField.setEditable(false);
        adapterPanel.add(networkDeviceIdTextField, "cell 1 1,growx");

        JLabel networkNameLabel = new JLabel("Name");
        adapterPanel.add(networkNameLabel, "cell 0 2,alignx leading");

        networkNameTextField = new JTextField();
        networkNameTextField.setEditable(false);
        adapterPanel.add(networkNameTextField, "cell 1 2,growx");

        JLabel networkDriverVersionLabel = new JLabel("Driver Version");
        adapterPanel.add(networkDriverVersionLabel, "cell 0 3,alignx leading");

        networkDriverVersionTextField = new JTextField();
        networkDriverVersionTextField.setEditable(false);
        adapterPanel.add(networkDriverVersionTextField, "cell 1 3,growx");

        JLabel networkDriverDateLabel = new JLabel("Driver Date");
        adapterPanel.add(networkDriverDateLabel, "cell 0 4,alignx leading");

        networkDriverDateTextField = new JTextField();
        networkDriverDateTextField.setEditable(false);
        adapterPanel.add(networkDriverDateTextField, "cell 1 4,growx");

        JLabel networkTypeLabel = new JLabel("Type");
        adapterPanel.add(networkTypeLabel, "cell 0 5,alignx leading");

        networkTypeTextField = new JTextField();
        networkTypeTextField.setEditable(false);
        adapterPanel.add(networkTypeTextField, "cell 1 5,growx");

        JLabel networkLinkLayerAddressLabel = new JLabel("MAC");
        adapterPanel.add(networkLinkLayerAddressLabel, "cell 0 6,alignx leading");

        networkLinkLayerAddressTextField = new JTextField();
        networkLinkLayerAddressTextField.setEditable(false);
        adapterPanel.add(networkLinkLayerAddressTextField, "cell 1 6,growx");

        JLabel networkLinkSpeedLabel = new JLabel("Link Speed");
        adapterPanel.add(networkLinkSpeedLabel, "cell 0 7,alignx leading");

        networkLinkSpeedTextField = new JTextField();
        networkLinkSpeedTextField.setEditable(false);
        adapterPanel.add(networkLinkSpeedTextField, "cell 1 7,growx");

        JLabel networkMediaConnectStateLabel = new JLabel("Connect State");
        adapterPanel.add(networkMediaConnectStateLabel, "cell 0 8,alignx leading");

        networkMediaConnectStateTextField = new JTextField();
        networkMediaConnectStateTextField.setEditable(false);
        adapterPanel.add(networkMediaConnectStateTextField, "cell 1 8,growx");

        JLabel networkMediaConnectTypeLabel = new JLabel("Connect Type");
        adapterPanel.add(networkMediaConnectTypeLabel, "cell 0 9,alignx leading");

        networkMediaConnectTypeTextField = new JTextField();
        networkMediaConnectTypeTextField.setEditable(false);
        adapterPanel.add(networkMediaConnectTypeTextField, "cell 1 9,growx");

        JLabel networkReceiveLinkSpeedLabel = new JLabel("Receive Speed");
        adapterPanel.add(networkReceiveLinkSpeedLabel, "cell 0 10,alignx leading");

        networkReceiveLinkSpeedTextField = new JTextField();
        networkReceiveLinkSpeedTextField.setEditable(false);
        adapterPanel.add(networkReceiveLinkSpeedTextField, "cell 1 10,growx");

        JLabel networkTransmitLinkSpeedLabel = new JLabel("Transmit Speed");
        adapterPanel.add(networkTransmitLinkSpeedLabel, "cell 0 11,alignx leading");

        networkTransmitLinkSpeedTextField = new JTextField();
        networkTransmitLinkSpeedTextField.setEditable(false);
        adapterPanel.add(networkTransmitLinkSpeedTextField, "cell 1 11,growx");

        JLabel networkDuplexityLabel = new JLabel("Full Duplex");
        adapterPanel.add(networkDuplexityLabel, "cell 0 12,alignx leading");

        networkDuplexityTextField = new JTextField();
        networkDuplexityTextField.setEditable(false);
        adapterPanel.add(networkDuplexityTextField, "cell 1 12,growx");

        JLabel networkVirtualityLabel = new JLabel("Virtual");
        adapterPanel.add(networkVirtualityLabel, "cell 0 13,alignx leading");

        networkVirtualityTextField = new JTextField();
        networkVirtualityTextField.setEditable(false);
        adapterPanel.add(networkVirtualityTextField, "cell 1 13,growx");

        JLabel networkStatusLabel = new JLabel("Status");
        adapterPanel.add(networkStatusLabel, "cell 0 14,alignx leading");

        networkStatusTextField = new JTextField();
        networkStatusTextField.setEditable(false);
        adapterPanel.add(networkStatusTextField, "cell 1 14,growx");

        JLabel networkPnPDeviceIdLabel = new JLabel("PnP DeviceID");
        adapterPanel.add(networkPnPDeviceIdLabel, "cell 0 15,alignx leading");

        networkPnPDeviceIdTextField = new JTextField();
        networkPnPDeviceIdTextField.setEditable(false);
        adapterPanel.add(networkPnPDeviceIdTextField, "cell 1 15,growx");

        return new JScrollPane(adapterPanel);
    }

    private JTabbedPane createAdapterPropertyTabbedPane() {

        JTabbedPane adapterPropertyTabbedPane = new JTabbedPane(SwingConstants.TOP);

        // ip panel
        JPanel adapterIpPanel = new JPanel();
        adapterIpPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "IP Address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        adapterIpPanel.setLayout(new GridLayout(1, 1, 0, 0));

        adapterIpEditorPane = new JEditorPane();
        adapterIpEditorPane.setEditable(false);
        adapterIpEditorPane.setContentType("text/html");

        adapterIpPanel.add(new JScrollPane(adapterIpEditorPane));

        // dns panel
        JPanel adapterDnsPanel = new JPanel();
        adapterDnsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "DNS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        adapterDnsPanel.setLayout(new GridLayout(1, 1, 0, 0));

        adapterDnsEditorPane = new JEditorPane();
        adapterDnsEditorPane.setEditable(false);
        adapterDnsEditorPane.setContentType("text/html");

        adapterDnsPanel.add(new JScrollPane(adapterDnsEditorPane));

        // connection profile
        JPanel netConnectionProfilePanel = new JPanel();
        netConnectionProfilePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Connection Profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        netConnectionProfilePanel.setLayout(new GridLayout(1, 1, 0, 0));

        adapterConnectionProfileEditorPane = new JEditorPane();
        adapterConnectionProfileEditorPane.setEditable(false);
        adapterConnectionProfileEditorPane.setContentType("text/html");

        netConnectionProfilePanel.add(new JScrollPane(adapterConnectionProfileEditorPane));

        // add panels to the tabbed pane
        adapterPropertyTabbedPane.addTab("Adapter IP", new FlatSVGIcon(WMINetworkPanelUI.class.getResource("/icons/tab_icons_material_green/IP.svg")), adapterIpPanel, null);
        adapterPropertyTabbedPane.addTab("Adapter DNS", new FlatSVGIcon(WMINetworkPanelUI.class.getResource("/icons/tab_icons_material_green/DNS.svg")), adapterDnsPanel, null);
        adapterPropertyTabbedPane.addTab("Connection Profile", new FlatSVGIcon(WMINetworkPanelUI.class.getResource("/icons/tab_icons_material_green/ConnectionProfile.svg")), netConnectionProfilePanel, null);

        return adapterPropertyTabbedPane;
    }

    private void setWorker() {

        List<JTextField> adapterFields = List.of(networkDeviceIdTextField, networkNameTextField,
                networkDriverVersionTextField, networkDriverDateTextField, networkTypeTextField,
                networkLinkLayerAddressTextField, networkLinkSpeedTextField, networkMediaConnectStateTextField,
                networkMediaConnectTypeTextField, networkReceiveLinkSpeedTextField, networkTransmitLinkSpeedTextField,
                networkDuplexityTextField, networkVirtualityTextField, networkStatusTextField, networkPnPDeviceIdTextField);

        List<JEditorPane> editorPanes = List.of(adapterIpEditorPane, adapterDnsEditorPane, adapterConnectionProfileEditorPane);

        new WMINetworkPanelWorker(networkInterfaceIdComboBox, adapterFields, editorPanes).execute();
    }

}
