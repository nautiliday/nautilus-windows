/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import io.github.eggy03.nautilus.windows.worker.WMIBaseboardWorker;
import io.github.eggy03.nautilus.windows.worker.WMIBiosWorker;
import io.github.eggy03.nautilus.windows.worker.WMIPortConnectorWorker;

import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.util.List;

public class WMIMainboardPanel extends JPanel {

    // Init Components in the main panel
    // the main panel consists of 3 sub panels (baseboard, port and bios)

    // Baseboard panel components
    private final @NonNull JLabel baseboardNumberLabel = new JLabel("Baseboard#");
    private final @NonNull JComboBox<Integer> baseboardNumberComboBox = new JComboBox<>();

    private final @NonNull JLabel baseboardManufacturerLabel = new JLabel("Manufacturer");
    private final @NonNull JTextField baseboardManufacturerTextField = new JTextField();

    private final @NonNull JLabel baseboardModelLabel = new JLabel("Model");
    private final @NonNull JTextField baseboardModelTextField = new JTextField();

    private final @NonNull JLabel baseboardProductLabel = new JLabel("Product");
    private final @NonNull JTextField baseboardProductTextField = new JTextField();

    private final @NonNull JLabel baseboardSerialNumberLabel = new JLabel("Serial Number");
    private final @NonNull JTextField baseboardSerialNumberTextField = new JTextField();

    private final @NonNull JLabel baseboardVersionLabel = new JLabel("Version");
    private final @NonNull JTextField baseboardVersionTextField = new JTextField();

    // Port components
    private final @NonNull JLabel baseboardPortTagLabel = new JLabel("Tag");
    private final @NonNull JComboBox<String> baseboardPortTagComboBox = new JComboBox<>();

    private final @NonNull JLabel baseboardPortTypeLabel = new JLabel("Type");
    private final @NonNull JTextField baseboardPortTypeTextField = new JTextField();

    private final @NonNull JLabel baseboardPortIRDLabel = new JLabel("Internal RD");
    private final @NonNull JTextField baseboardPortIRDTextField = new JTextField();

    private final @NonNull JLabel baseboardPortERDLabel = new JLabel("External RD");
    private final @NonNull JTextField baseboardPortERDTextField = new JTextField();

    // BIOS components
    private final @NonNull JLabel biosNumberLabel = new JLabel("BIOS#");
    private final @NonNull JComboBox<Integer> biosNumberComboBox = new JComboBox<>();

    private final @NonNull JLabel biosCaptionLabel = new JLabel("Caption");
    private final @NonNull JTextField biosCaptionTextField = new JTextField();

    private final @NonNull JLabel biosCurrentLanguageLevel = new JLabel("Current Language");
    private final @NonNull JTextField biosCurrentLanguageTextField = new JTextField();

    private final @NonNull JLabel biosManufacturerLabel = new JLabel("Manufacturer");
    private final @NonNull JTextField biosManufacturerTextField = new JTextField();

    private final @NonNull JLabel biosNameLabel = new JLabel("Name");
    private final @NonNull JTextField biosNameTextField = new JTextField();

    private final @NonNull JLabel biosPrimaryLabel = new JLabel("Primary BIOS");
    private final @NonNull JTextField biosPrimaryTextField = new JTextField();

    private final @NonNull JLabel biosReleaseDateLabel = new JLabel("Release Date");
    private final @NonNull JTextField biosReleaseDateTextField = new JTextField();

    private final @NonNull JLabel biosSmbiosPresenceLabel = new JLabel("SMBIOS Present");
    private final @NonNull JTextField biosSmbiosPresenceTextField = new JTextField();

    private final @NonNull JLabel biosSmbiosVersionLabel = new JLabel("SMBIOS Version");
    private final @NonNull JTextField biosSmbiosVersionTextField = new JTextField();

    private final @NonNull JLabel biosStatusLabel = new JLabel("Status");
    private final @NonNull JTextField biosStatusTextField = new JTextField();

    private final @NonNull JLabel biosVersionLabel = new JLabel("Version");
    private final @NonNull JTextField biosVersionTextField = new JTextField();

    // set the layout for the main panel
    public @NonNull WMIMainboardPanel initUI() {
        setLayout(new MigLayout("insets 0", "[grow][grow]", "[grow][grow]"));
        return this;
    }

    // add the sub panels to the main panels
    // each sub panel is initialized in its dedicated function and is wrapped in a scroll pane
    public @NonNull WMIMainboardPanel initComponents() {
        add(new JScrollPane(createBaseboardPanel()), "cell 0 0,grow");
        add(new JScrollPane(createBaseboardPortPanel()), "cell 1 0,grow");
        add(new JScrollPane(createBiosPanel()), "cell 0 1 2 1,grow");

        return this;
    }

    private @NonNull JPanel createBaseboardPanel() {

        final JPanel baseboardPanel = new JPanel();
        baseboardPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Baseboard", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        baseboardPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));

        baseboardPanel.add(baseboardNumberLabel, "cell 0 0,alignx leading");
        baseboardPanel.add(baseboardNumberComboBox, "cell 1 0,growx");

        baseboardPanel.add(baseboardManufacturerLabel, "cell 0 1,alignx leading");
        baseboardPanel.add(baseboardManufacturerTextField, "cell 1 1,growx");

        baseboardPanel.add(baseboardModelLabel, "cell 0 2,alignx leading");
        baseboardPanel.add(baseboardModelTextField, "cell 1 2,growx");

        baseboardPanel.add(baseboardProductLabel, "cell 0 3,alignx leading");
        baseboardPanel.add(baseboardProductTextField, "cell 1 3,growx");

        baseboardPanel.add(baseboardSerialNumberLabel, "cell 0 4,alignx leading");
        baseboardPanel.add(baseboardSerialNumberTextField, "cell 1 4,growx");

        baseboardPanel.add(baseboardVersionLabel, "cell 0 5,alignx leading");
        baseboardPanel.add(baseboardVersionTextField, "cell 1 5,growx");

        baseboardManufacturerTextField.setEditable(false);
        baseboardModelTextField.setEditable(false);
        baseboardProductTextField.setEditable(false);
        baseboardSerialNumberTextField.setEditable(false);
        baseboardVersionTextField.setEditable(false);

        return baseboardPanel;
    }

    private @NonNull JPanel createBaseboardPortPanel() {

        final JPanel baseboardPortPanel = new JPanel();
        baseboardPortPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Baseboard Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        baseboardPortPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][]"));

        baseboardPortPanel.add(baseboardPortTagLabel, "cell 0 0,alignx leading");
        baseboardPortPanel.add(baseboardPortTagComboBox, "cell 1 0,growx");

        baseboardPortPanel.add(baseboardPortTypeLabel, "cell 0 1,alignx leading");
        baseboardPortPanel.add(baseboardPortTypeTextField, "cell 1 1,growx");

        baseboardPortPanel.add(baseboardPortIRDLabel, "cell 0 2,alignx leading");
        baseboardPortPanel.add(baseboardPortIRDTextField, "cell 1 2,growx");

        baseboardPortPanel.add(baseboardPortERDLabel, "cell 0 3,alignx leading");
        baseboardPortPanel.add(baseboardPortERDTextField, "cell 1 3,growx");

        baseboardPortTypeTextField.setEditable(false);
        baseboardPortIRDTextField.setEditable(false);
        baseboardPortERDTextField.setEditable(false);

        return baseboardPortPanel;
    }

    private @NonNull JPanel createBiosPanel() {

        final JPanel biosPanel = new JPanel();
        biosPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "BIOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        biosPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][]"));

        biosPanel.add(biosNumberLabel, "cell 0 0,alignx leading");
        biosPanel.add(biosNumberComboBox, "cell 1 0,growx");

        biosPanel.add(biosCaptionLabel, "cell 0 1,alignx leading");
        biosPanel.add(biosCaptionTextField, "cell 1 1,growx");

        biosPanel.add(biosCurrentLanguageLevel, "cell 0 2,alignx leading");
        biosPanel.add(biosCurrentLanguageTextField, "cell 1 2,growx");

        biosPanel.add(biosManufacturerLabel, "cell 0 3,alignx leading");
        biosPanel.add(biosManufacturerTextField, "cell 1 3,growx");

        biosPanel.add(biosNameLabel, "cell 0 4,alignx leading");
        biosPanel.add(biosNameTextField, "cell 1 4,growx");

        biosPanel.add(biosPrimaryLabel, "cell 0 5,alignx leading");
        biosPanel.add(biosPrimaryTextField, "cell 1 5,growx");

        biosPanel.add(biosReleaseDateLabel, "cell 0 6,alignx leading");
        biosPanel.add(biosReleaseDateTextField, "cell 1 6,growx");

        biosPanel.add(biosSmbiosPresenceLabel, "cell 0 7,alignx leading");
        biosPanel.add(biosSmbiosPresenceTextField, "cell 1 7,growx");

        biosPanel.add(biosSmbiosVersionLabel, "cell 0 8,alignx leading");
        biosPanel.add(biosSmbiosVersionTextField, "cell 1 8,growx");

        biosPanel.add(biosStatusLabel, "cell 0 9,alignx leading");
        biosPanel.add(biosStatusTextField, "cell 1 9,growx");

        biosPanel.add(biosVersionLabel, "cell 0 10,alignx leading");
        biosPanel.add(biosVersionTextField, "cell 1 10,growx");

        biosCaptionTextField.setEditable(false);
        biosCurrentLanguageTextField.setEditable(false);
        biosManufacturerTextField.setEditable(false);
        biosNameTextField.setEditable(false);
        biosPrimaryTextField.setEditable(false);
        biosReleaseDateTextField.setEditable(false);
        biosSmbiosPresenceTextField.setEditable(false);
        biosSmbiosVersionTextField.setEditable(false);
        biosStatusTextField.setEditable(false);
        biosVersionTextField.setEditable(false);

        return biosPanel;
    }


    public @NonNull WMIMainboardPanel initWorkers() {

        //todo consider record/DTO based ordering
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

        return this;
    }
}
