/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.worker.WMINetworkPanelWorker;
import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

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

@SuppressWarnings("java:S1192")
public class WMINetworkPanel extends JPanel {

    // Init Components in the main panel
    // the main panel consists of 1 sub panel and 1 tabbed pane which has 3 editor panes

    // network panel components
    private final @NonNull JLabel networkInterfaceIdLabel = new JLabel("Interface#");
    private final @NonNull JComboBox<Long> networkInterfaceIdComboBox = new JComboBox<>();

    private final @NonNull JLabel networkDeviceIdLabel = new JLabel("DeviceID");
    private final @NonNull JTextField networkDeviceIdTextField = new JTextField();

    private final @NonNull JLabel networkNameLabel = new JLabel("Name");
    private final @NonNull JTextField networkNameTextField = new JTextField();

    private final @NonNull JLabel networkDriverVersionLabel = new JLabel("Driver Version");
    private final @NonNull JTextField networkDriverVersionTextField = new JTextField();

    private final @NonNull JLabel networkDriverDateLabel = new JLabel("Driver Date");
    private final @NonNull JTextField networkDriverDateTextField = new JTextField();

    private final @NonNull JLabel networkTypeLabel = new JLabel("Type");
    private final @NonNull JTextField networkTypeTextField = new JTextField();

    private final @NonNull JLabel networkLinkLayerAddressLabel = new JLabel("MAC");
    private final @NonNull JTextField networkLinkLayerAddressTextField = new JTextField();

    private final @NonNull JLabel networkLinkSpeedLabel = new JLabel("Link Speed");
    private final @NonNull JTextField networkLinkSpeedTextField = new JTextField();

    private final @NonNull JLabel networkMediaConnectStateLabel = new JLabel("Connect State");
    private final @NonNull JTextField networkMediaConnectStateTextField = new JTextField();

    private final @NonNull JLabel networkMediaConnectTypeLabel = new JLabel("Connect Type");
    private final @NonNull JTextField networkMediaConnectTypeTextField = new JTextField();

    private final @NonNull JLabel networkReceiveLinkSpeedLabel = new JLabel("Receive Speed");
    private final @NonNull JTextField networkReceiveLinkSpeedTextField = new JTextField();

    private final @NonNull JLabel networkTransmitLinkSpeedLabel = new JLabel("Transmit Speed");
    private final @NonNull JTextField networkTransmitLinkSpeedTextField = new JTextField();

    private final @NonNull JLabel networkDuplexityLabel = new JLabel("Full Duplex");
    private final @NonNull JTextField networkDuplexityTextField = new JTextField();

    private final @NonNull JLabel networkVirtualityLabel = new JLabel("Virtual");
    private final @NonNull JTextField networkVirtualityTextField = new JTextField();

    private final @NonNull JLabel networkStatusLabel = new JLabel("Status");
    private final @NonNull JTextField networkStatusTextField = new JTextField();

    private final @NonNull JLabel networkPnPDeviceIdLabel = new JLabel("PnP DeviceID");
    private final @NonNull JTextField networkPnPDeviceIdTextField = new JTextField();

    // network tabbed pane components
    private final @NonNull JEditorPane adapterIpEditorPane = new JEditorPane();
    private final @NonNull JEditorPane adapterDnsEditorPane = new JEditorPane();
    private final @NonNull JEditorPane adapterConnectionProfileEditorPane = new JEditorPane();

    public @NonNull WMINetworkPanel initUI() {
        setLayout(new GridLayout(2, 1, 0, 0));
        return this;
    }

    public @NonNull WMINetworkPanel initComponents() {
        add(new JScrollPane(createAdapterPanel()));
        add(createAdapterPropertyTabbedPane());

        return this;
    }

    private @NonNull JPanel createAdapterPanel() {

        final JPanel adapterPanel = new JPanel();
        adapterPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Adapter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        adapterPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][][][][][][]"));

        adapterPanel.add(networkInterfaceIdLabel, "cell 0 0,alignx leading");
        adapterPanel.add(networkInterfaceIdComboBox, "cell 1 0,growx");

        adapterPanel.add(networkDeviceIdLabel, "cell 0 1,alignx leading");
        networkDeviceIdTextField.setEditable(false);
        adapterPanel.add(networkDeviceIdTextField, "cell 1 1,growx");

        adapterPanel.add(networkNameLabel, "cell 0 2,alignx leading");
        networkNameTextField.setEditable(false);
        adapterPanel.add(networkNameTextField, "cell 1 2,growx");

        adapterPanel.add(networkDriverVersionLabel, "cell 0 3,alignx leading");
        networkDriverVersionTextField.setEditable(false);
        adapterPanel.add(networkDriverVersionTextField, "cell 1 3,growx");

        adapterPanel.add(networkDriverDateLabel, "cell 0 4,alignx leading");
        networkDriverDateTextField.setEditable(false);
        adapterPanel.add(networkDriverDateTextField, "cell 1 4,growx");

        adapterPanel.add(networkTypeLabel, "cell 0 5,alignx leading");
        networkTypeTextField.setEditable(false);
        adapterPanel.add(networkTypeTextField, "cell 1 5,growx");

        adapterPanel.add(networkLinkLayerAddressLabel, "cell 0 6,alignx leading");
        networkLinkLayerAddressTextField.setEditable(false);
        adapterPanel.add(networkLinkLayerAddressTextField, "cell 1 6,growx");

        adapterPanel.add(networkLinkSpeedLabel, "cell 0 7,alignx leading");
        networkLinkSpeedTextField.setEditable(false);
        adapterPanel.add(networkLinkSpeedTextField, "cell 1 7,growx");

        adapterPanel.add(networkMediaConnectStateLabel, "cell 0 8,alignx leading");
        networkMediaConnectStateTextField.setEditable(false);
        adapterPanel.add(networkMediaConnectStateTextField, "cell 1 8,growx");

        adapterPanel.add(networkMediaConnectTypeLabel, "cell 0 9,alignx leading");
        networkMediaConnectTypeTextField.setEditable(false);
        adapterPanel.add(networkMediaConnectTypeTextField, "cell 1 9,growx");

        adapterPanel.add(networkReceiveLinkSpeedLabel, "cell 0 10,alignx leading");
        networkReceiveLinkSpeedTextField.setEditable(false);
        adapterPanel.add(networkReceiveLinkSpeedTextField, "cell 1 10,growx");

        adapterPanel.add(networkTransmitLinkSpeedLabel, "cell 0 11,alignx leading");
        networkTransmitLinkSpeedTextField.setEditable(false);
        adapterPanel.add(networkTransmitLinkSpeedTextField, "cell 1 11,growx");

        adapterPanel.add(networkDuplexityLabel, "cell 0 12,alignx leading");
        networkDuplexityTextField.setEditable(false);
        adapterPanel.add(networkDuplexityTextField, "cell 1 12,growx");

        adapterPanel.add(networkVirtualityLabel, "cell 0 13,alignx leading");
        networkVirtualityTextField.setEditable(false);
        adapterPanel.add(networkVirtualityTextField, "cell 1 13,growx");

        adapterPanel.add(networkStatusLabel, "cell 0 14,alignx leading");
        networkStatusTextField.setEditable(false);
        adapterPanel.add(networkStatusTextField, "cell 1 14,growx");

        adapterPanel.add(networkPnPDeviceIdLabel, "cell 0 15,alignx leading");
        networkPnPDeviceIdTextField.setEditable(false);
        adapterPanel.add(networkPnPDeviceIdTextField, "cell 1 15,growx");

        return adapterPanel;
    }

    private @NonNull JTabbedPane createAdapterPropertyTabbedPane() {

        final JTabbedPane adapterPropertyTabbedPane = new JTabbedPane(SwingConstants.TOP);

        // ip panel
        adapterIpEditorPane.setEditable(false);
        adapterIpEditorPane.setContentType("text/html");

        // dns panel
        adapterDnsEditorPane.setEditable(false);
        adapterDnsEditorPane.setContentType("text/html");

        // connection profile
        adapterConnectionProfileEditorPane.setEditable(false);
        adapterConnectionProfileEditorPane.setContentType("text/html");


        // add panels to the tabbed pane
        adapterPropertyTabbedPane.addTab(
                "Adapter IP",
                new FlatSVGIcon(WMINetworkPanel.class.getResource("/icons/tab_icons_material_green/IP.svg")),
                new JScrollPane(adapterIpEditorPane),
                null
        );
        adapterPropertyTabbedPane.addTab(
                "Adapter DNS",
                new FlatSVGIcon(WMINetworkPanel.class.getResource("/icons/tab_icons_material_green/DNS.svg")),
                new JScrollPane(adapterDnsEditorPane),
                null
        );
        adapterPropertyTabbedPane.addTab(
                "Connection Profile",
                new FlatSVGIcon(WMINetworkPanel.class.getResource("/icons/tab_icons_material_green/ConnectionProfile.svg")),
                new JScrollPane(adapterConnectionProfileEditorPane),
                null
        );

        return adapterPropertyTabbedPane;
    }

    public @NonNull WMINetworkPanel initWorkers() {

        List<JTextField> adapterFields = List.of(networkDeviceIdTextField, networkNameTextField,
                networkDriverVersionTextField, networkDriverDateTextField, networkTypeTextField,
                networkLinkLayerAddressTextField, networkLinkSpeedTextField, networkMediaConnectStateTextField,
                networkMediaConnectTypeTextField, networkReceiveLinkSpeedTextField, networkTransmitLinkSpeedTextField,
                networkDuplexityTextField, networkVirtualityTextField, networkStatusTextField, networkPnPDeviceIdTextField);

        List<JEditorPane> editorPanes = List.of(adapterIpEditorPane, adapterDnsEditorPane, adapterConnectionProfileEditorPane);

        new WMINetworkPanelWorker(networkInterfaceIdComboBox, adapterFields, editorPanes).execute();

        return this;
    }

}
