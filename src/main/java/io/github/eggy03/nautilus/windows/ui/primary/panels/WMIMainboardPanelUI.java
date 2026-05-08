/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import io.github.eggy03.nautilus.windows.worker.WMIBaseboardWorker;
import io.github.eggy03.nautilus.windows.worker.WMIBiosWorker;
import io.github.eggy03.nautilus.windows.worker.WMIPortConnectorWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.util.List;

public class WMIMainboardPanelUI extends JPanel {

    private JComboBox<Integer> baseboardNumberComboBox;
    private JTextField baseboardManufacturerTextField;
    private JTextField baseboardModelTextField;
    private JTextField baseboardProductTextField;
    private JTextField baseboardSerialNumberTextField;
    private JTextField baseboardVersionTextField;

    private JComboBox<String> baseboardPortTagComboBox;
    private JTextField baseboardPortTypeTextField;
    private JTextField baseboardPortIRDTextField;
    private JTextField baseboardPortERDTextField;

    private JComboBox<Integer> biosNumberComboBox;
    private JTextField biosCaptionTextField;
    private JTextField biosCurrentLanguageTextField;
    private JTextField biosManufacturerTextField;
    private JTextField biosNameTextField;
    private JTextField biosPrimaryTextField;
    private JTextField biosReleaseDateTextField;
    private JTextField biosSmbiosPresenceTextField;
    private JTextField biosSmbiosVersionTextField;
    private JTextField biosStatusTextField;
    private JTextField biosVersionTextField;


    /**
     * Create the panel.
     */
    public WMIMainboardPanelUI() {
        setLayout(new MigLayout("insets 0", "[grow][grow]", "[grow][grow]"));

        add(createBaseboardPanel(), "cell 0 0,grow");
        add(createBaseboardPortPanel(), "cell 1 0,grow");
        add(createBiosPanel(), "cell 0 1 2 1,grow");

        setWorkers();
    }

    public JPanel getPanel() {
        return this;
    }

    private JScrollPane createBaseboardPanel() {

        //add panel
        JPanel baseboardPanel = new JPanel();
        baseboardPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Baseboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        baseboardPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        JLabel baseboardNumberLabel = new JLabel("Baseboard#");
        baseboardPanel.add(baseboardNumberLabel, "cell 0 0,alignx leading");

        baseboardNumberComboBox = new JComboBox<>();
        baseboardPanel.add(baseboardNumberComboBox, "cell 1 0,growx");

        JLabel baseboardManufacturerLabel = new JLabel("Manufacturer");
        baseboardPanel.add(baseboardManufacturerLabel, "cell 0 1,alignx leading");

        baseboardManufacturerTextField = new JTextField();
        baseboardManufacturerTextField.setEditable(false);
        baseboardPanel.add(baseboardManufacturerTextField, "cell 1 1,growx");

        JLabel baseboardModelLabel = new JLabel("Model");
        baseboardPanel.add(baseboardModelLabel, "cell 0 2,alignx leading");

        baseboardModelTextField = new JTextField();
        baseboardModelTextField.setEditable(false);
        baseboardPanel.add(baseboardModelTextField, "cell 1 2,growx");

        JLabel baseboardProductLabel = new JLabel("Product");
        baseboardPanel.add(baseboardProductLabel, "cell 0 3,alignx leading");

        baseboardProductTextField = new JTextField();
        baseboardProductTextField.setEditable(false);
        baseboardPanel.add(baseboardProductTextField, "cell 1 3,growx");

        JLabel baseboardSerialNumberLabel = new JLabel("Serial Number");
        baseboardPanel.add(baseboardSerialNumberLabel, "cell 0 4,alignx leading");

        baseboardSerialNumberTextField = new JTextField();
        baseboardSerialNumberTextField.setEditable(false);
        baseboardPanel.add(baseboardSerialNumberTextField, "cell 1 4,growx");

        JLabel baseboardVersionLabel = new JLabel("Version");
        baseboardPanel.add(baseboardVersionLabel, "cell 0 5,alignx leading");

        baseboardVersionTextField = new JTextField();
        baseboardVersionTextField.setEditable(false);
        baseboardPanel.add(baseboardVersionTextField, "cell 1 5,growx");

        // add scroll-pane to the panel
        return new JScrollPane(baseboardPanel);

    }

    private JScrollPane createBaseboardPortPanel() {

        //add panel
        JPanel baseboardPortPanel = new JPanel();
        baseboardPortPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Baseboard Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        baseboardPortPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][]"));

        JLabel baseboardPortTagLabel = new JLabel("Tag");
        baseboardPortPanel.add(baseboardPortTagLabel, "cell 0 0,alignx leading");

        baseboardPortTagComboBox = new JComboBox<>();
        baseboardPortPanel.add(baseboardPortTagComboBox, "cell 1 0,growx");

        JLabel baseboardPortTypeLabel = new JLabel("Type");
        baseboardPortPanel.add(baseboardPortTypeLabel, "cell 0 1,alignx leading");

        baseboardPortTypeTextField = new JTextField();
        baseboardPortTypeTextField.setEditable(false);
        baseboardPortPanel.add(baseboardPortTypeTextField, "cell 1 1,growx");

        JLabel baseboardPortIRDLabel = new JLabel("Internal RD");
        baseboardPortPanel.add(baseboardPortIRDLabel, "cell 0 2,alignx leading");

        baseboardPortIRDTextField = new JTextField();
        baseboardPortIRDTextField.setEditable(false);
        baseboardPortPanel.add(baseboardPortIRDTextField, "cell 1 2,growx");

        JLabel baseboardPortERDLabel = new JLabel("External RD");
        baseboardPortPanel.add(baseboardPortERDLabel, "cell 0 3,alignx leading");

        baseboardPortERDTextField = new JTextField();
        baseboardPortERDTextField.setEditable(false);
        baseboardPortPanel.add(baseboardPortERDTextField, "cell 1 3,growx");

        // add scroll-pane to the panel
        return new JScrollPane(baseboardPortPanel);
    }

    private JScrollPane createBiosPanel() {

        // add panel
        JPanel biosPanel = new JPanel();
        biosPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "BIOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        biosPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][]"));

        JLabel biosNumberLabel = new JLabel("BIOS#");
        biosPanel.add(biosNumberLabel, "cell 0 0,alignx leading");

        biosNumberComboBox = new JComboBox<>();
        biosPanel.add(biosNumberComboBox, "cell 1 0,growx");

        JLabel biosCaptionLabel = new JLabel("Caption");
        biosPanel.add(biosCaptionLabel, "cell 0 1,alignx leading");

        biosCaptionTextField = new JTextField();
        biosCaptionTextField.setEditable(false);
        biosPanel.add(biosCaptionTextField, "cell 1 1,growx");
        biosCaptionTextField.setColumns(10);

        JLabel biosCurrentLanguageLevel = new JLabel("Current Language");
        biosPanel.add(biosCurrentLanguageLevel, "cell 0 2,alignx leading");

        biosCurrentLanguageTextField = new JTextField();
        biosCurrentLanguageTextField.setEditable(false);
        biosPanel.add(biosCurrentLanguageTextField, "cell 1 2,growx");
        biosCurrentLanguageTextField.setColumns(10);

        JLabel biosManufacturerLabel = new JLabel("Manufacturer");
        biosPanel.add(biosManufacturerLabel, "cell 0 3,alignx leading");

        biosManufacturerTextField = new JTextField();
        biosManufacturerTextField.setEditable(false);
        biosPanel.add(biosManufacturerTextField, "cell 1 3,growx");
        biosManufacturerTextField.setColumns(10);

        JLabel biosNameLabel = new JLabel("Name");
        biosPanel.add(biosNameLabel, "cell 0 4,alignx leading");

        biosNameTextField = new JTextField();
        biosNameTextField.setEditable(false);
        biosPanel.add(biosNameTextField, "cell 1 4,growx");
        biosNameTextField.setColumns(10);

        JLabel biosPrimaryLabel = new JLabel("Primary BIOS");
        biosPanel.add(biosPrimaryLabel, "cell 0 5,alignx leading");

        biosPrimaryTextField = new JTextField();
        biosPrimaryTextField.setEditable(false);
        biosPanel.add(biosPrimaryTextField, "cell 1 5,growx");
        biosPrimaryTextField.setColumns(10);

        JLabel biosReleaseDateLabel = new JLabel("Release Date");
        biosPanel.add(biosReleaseDateLabel, "cell 0 6,alignx leading");

        biosReleaseDateTextField = new JTextField();
        biosReleaseDateTextField.setEditable(false);
        biosPanel.add(biosReleaseDateTextField, "cell 1 6,growx");
        biosReleaseDateTextField.setColumns(10);

        JLabel biosSmbiosPresenceLabel = new JLabel("SMBIOS Present");
        biosPanel.add(biosSmbiosPresenceLabel, "cell 0 7,alignx leading");

        biosSmbiosPresenceTextField = new JTextField();
        biosSmbiosPresenceTextField.setEditable(false);
        biosSmbiosPresenceTextField.setColumns(10);
        biosPanel.add(biosSmbiosPresenceTextField, "cell 1 7,growx");

        JLabel biosSmbiosVersionLabel = new JLabel("SMBIOS BIOS Ver.");
        biosPanel.add(biosSmbiosVersionLabel, "cell 0 8,alignx leading");

        biosSmbiosVersionTextField = new JTextField();
        biosSmbiosVersionTextField.setEditable(false);
        biosPanel.add(biosSmbiosVersionTextField, "cell 1 8,growx");
        biosSmbiosVersionTextField.setColumns(10);

        JLabel biosStatusLabel = new JLabel("Status");
        biosPanel.add(biosStatusLabel, "cell 0 9,alignx leading");

        biosStatusTextField = new JTextField();
        biosStatusTextField.setEditable(false);
        biosPanel.add(biosStatusTextField, "cell 1 9,growx");
        biosStatusTextField.setColumns(10);

        JLabel biosVersionLabel = new JLabel("Version");
        biosPanel.add(biosVersionLabel, "cell 0 10,alignx leading");

        biosVersionTextField = new JTextField();
        biosVersionTextField.setEditable(false);
        biosPanel.add(biosVersionTextField, "cell 1 10,growx");
        biosVersionTextField.setColumns(10);

        // add scroll-pane to the panel
        return new JScrollPane(biosPanel);
    }

    private void setWorkers() {

        // baseboard worker
        List<JTextField> baseBoardFields = List.of(
                baseboardManufacturerTextField, baseboardModelTextField,
                baseboardProductTextField, baseboardSerialNumberTextField,
                baseboardVersionTextField
        );
        new WMIBaseboardWorker(baseboardNumberComboBox, baseBoardFields).execute();

        // baseboard port worker
        List<JTextField> baseBoardPortFields = List.of(
                baseboardPortTypeTextField, baseboardPortIRDTextField, baseboardPortERDTextField
        );
        new WMIPortConnectorWorker(baseboardPortTagComboBox, baseBoardPortFields).execute();

        // BIOS worker
        List<JTextField> biosFields = List.of(
                biosCaptionTextField, biosCurrentLanguageTextField, biosManufacturerTextField,
                biosNameTextField, biosPrimaryTextField, biosReleaseDateTextField,
                biosSmbiosPresenceTextField, biosSmbiosVersionTextField, biosStatusTextField,
                biosVersionTextField
        );
        new WMIBiosWorker(biosNumberComboBox, biosFields).execute();

    }

}
