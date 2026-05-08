/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import io.github.eggy03.nautilus.windows.worker.WMIVideoControllerPanelWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.util.List;

public class WMIVideoControllerPanel extends JPanel {

    // identifier panel
    private final JLabel gpuDeviceIdLabel = new JLabel("Device ID");
    private final JComboBox<String> gpuDeviceIdComboBox = new JComboBox<>();

    private final JLabel gpuNameLabel = new JLabel("Name");
    private final JTextField gpuNameTextField = new JTextField();

    private final JLabel gpuPnPDeviceIdLabel = new JLabel("PnP Dev. ID");
    private final JTextField gpuPnPDeviceIdTextField = new JTextField();

    // display panel
    private final JLabel gpuHorizontalResLabel = new JLabel("Horizontal Res.");
    private final JTextField gpuHorizontalResTextField = new JTextField();

    private final JLabel gpuVerticalResLabel = new JLabel("Vertical Res.");
    private final JTextField gpuVerticalResTextField = new JTextField();

    private final JLabel gpuBitsPerPixelLabel = new JLabel("Bits Per Pixel");
    private final JTextField gpuBitsPerPixelTextField = new JTextField();

    private final JLabel gpuMinRefreshRateLabel = new JLabel("Min Refresh Rate");
    private final JTextField gpuMinRefreshRateTextField = new JTextField();

    private final JLabel gpuMaxRefreshRateLabel = new JLabel("Max Refresh Rate");
    private final JTextField gpuMaxRefreshRateTextField = new JTextField();

    private final JLabel gpuCurrentRefreshRateLabel = new JLabel("Current Refresh Rate");
    private final JTextField gpuCurrentRefreshRateTextField = new JTextField();

    // driver panel
    private final JLabel gpuAdapterDACTypeLabel = new JLabel("Adapter DAC Type");
    private final JTextField gpuAdapterDACTypeTextField = new JTextField();

    private final JLabel gpuVRAMLabel = new JLabel("VRAM");
    private final JTextField gpuVRAMTextField = new JTextField();

    private final JLabel gpuDriverVersionLabel = new JLabel("Driver Version");
    private final JTextField gpuDriverVersionTextField = new JTextField();

    private final JLabel gpuDriverDateLabel = new JLabel("Driver Date");
    private final JTextField gpuDriverDateTextField = new JTextField();

    private final JLabel gpuVideoProcessorLabel = new JLabel("Video Processor");
    private final JTextField gpuVideoProcessorTextField = new JTextField();

    public WMIVideoControllerPanel initUI() {
        setLayout(new MigLayout("insets 0", "[grow][grow]", "[grow][grow]"));
        return this;
    }

    public WMIVideoControllerPanel initComponents() {
        add(new JScrollPane(createIdentifierPanel()), "cell 0 0 2 1, grow");
        add(new JScrollPane(createDisplayPanel()), "cell 0 1, grow");
        add(new JScrollPane(createDriverPanel()), "cell 1 1, grow");

        return this;
    }

    private JPanel createIdentifierPanel() {

        final JPanel identifierPanel = new JPanel();
        identifierPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Identifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        identifierPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][]"));

        identifierPanel.add(gpuDeviceIdLabel, "cell 0 0,alignx leading");
        identifierPanel.add(gpuDeviceIdComboBox, "cell 1 0,growx");

        identifierPanel.add(gpuNameLabel, "cell 0 1,alignx leading");
        identifierPanel.add(gpuNameTextField, "cell 1 1,growx");

        identifierPanel.add(gpuPnPDeviceIdLabel, "cell 0 2,alignx leading");
        identifierPanel.add(gpuPnPDeviceIdTextField, "cell 1 2,growx");

        gpuNameTextField.setEditable(false);
        gpuPnPDeviceIdTextField.setEditable(false);

        return identifierPanel;
    }

    private JPanel createDisplayPanel() {

        final JPanel displayPanel = new JPanel();
        displayPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Display", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        displayPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        displayPanel.add(gpuHorizontalResLabel, "cell 0 0,alignx leading");
        displayPanel.add(gpuHorizontalResTextField, "cell 1 0,growx");

        displayPanel.add(gpuVerticalResLabel, "cell 0 1,alignx leading");
        displayPanel.add(gpuVerticalResTextField, "cell 1 1,growx");

        displayPanel.add(gpuBitsPerPixelLabel, "cell 0 2,alignx leading");
        displayPanel.add(gpuBitsPerPixelTextField, "cell 1 2,growx");

        displayPanel.add(gpuMinRefreshRateLabel, "cell 0 3,alignx leading");
        displayPanel.add(gpuMinRefreshRateTextField, "cell 1 3,growx");

        displayPanel.add(gpuMaxRefreshRateLabel, "cell 0 4,alignx leading");
        displayPanel.add(gpuMaxRefreshRateTextField, "cell 1 4,growx");

        displayPanel.add(gpuCurrentRefreshRateLabel, "cell 0 5,alignx leading");
        displayPanel.add(gpuCurrentRefreshRateTextField, "cell 1 5,growx");

        gpuHorizontalResTextField.setEditable(false);
        gpuVerticalResTextField.setEditable(false);
        gpuBitsPerPixelTextField.setEditable(false);
        gpuMinRefreshRateTextField.setEditable(false);
        gpuMaxRefreshRateTextField.setEditable(false);
        gpuCurrentRefreshRateTextField.setEditable(false);

        return displayPanel;
    }

    private JPanel createDriverPanel() {

        final JPanel driverPanel = new JPanel();
        driverPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Driver", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        driverPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][]"));

        driverPanel.add(gpuAdapterDACTypeLabel, "cell 0 0,alignx leading");
        driverPanel.add(gpuAdapterDACTypeTextField, "cell 1 0,growx");

        driverPanel.add(gpuVRAMLabel, "cell 0 1,alignx leading");
        driverPanel.add(gpuVRAMTextField, "cell 1 1,growx");

        driverPanel.add(gpuDriverVersionLabel, "cell 0 2,alignx leading");
        driverPanel.add(gpuDriverVersionTextField, "cell 1 2,growx");

        driverPanel.add(gpuDriverDateLabel, "cell 0 3,alignx leading");
        driverPanel.add(gpuDriverDateTextField, "cell 1 3,growx");

        driverPanel.add(gpuVideoProcessorLabel, "cell 0 4,alignx leading");
        driverPanel.add(gpuVideoProcessorTextField, "cell 1 4,growx");

        gpuAdapterDACTypeTextField.setEditable(false);
        gpuVRAMTextField.setEditable(false);
        gpuDriverVersionTextField.setEditable(false);
        gpuDriverDateTextField.setEditable(false);
        gpuVideoProcessorTextField.setEditable(false);

        return driverPanel;
    }

    public WMIVideoControllerPanel initWorkers() {

        List<JTextField> gpuFields = List.of(
                gpuNameTextField, gpuPnPDeviceIdTextField, gpuHorizontalResTextField, gpuVerticalResTextField,
                gpuBitsPerPixelTextField, gpuMinRefreshRateTextField, gpuMaxRefreshRateTextField, gpuCurrentRefreshRateTextField,
                gpuAdapterDACTypeTextField, gpuVRAMTextField, gpuDriverVersionTextField, gpuDriverDateTextField,
                gpuVideoProcessorTextField
        );

        new WMIVideoControllerPanelWorker(gpuDeviceIdComboBox, gpuFields).execute();

        return this;
    }
}
