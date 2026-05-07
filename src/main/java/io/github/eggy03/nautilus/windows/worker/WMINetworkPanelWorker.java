/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.compounded.MsftNetAdapterToIpAndDnsAndProfile;
import io.github.eggy03.cimari.entity.network.MsftDnsClientServerAddress;
import io.github.eggy03.cimari.entity.network.MsftNetAdapter;
import io.github.eggy03.cimari.entity.network.MsftNetConnectionProfile;
import io.github.eggy03.cimari.entity.network.MsftNetIpAddress;
import io.github.eggy03.cimari.service.compounded.MsftNetAdapterToIpAndDnsAndProfileService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIValueResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIBooleanResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMINetworkResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class WMINetworkPanelWorker extends SwingWorker<Map<Long, MsftNetAdapterToIpAndDnsAndProfile>, Void> {

    private final JComboBox<Long> networkIndexComboBox;
    private final List<JTextField> networkFields;
    private final List<JEditorPane> networkEditorPanes;

    @Override
    protected Map<Long, MsftNetAdapterToIpAndDnsAndProfile> doInBackground() {

        List<MsftNetAdapterToIpAndDnsAndProfile> netList = new MsftNetAdapterToIpAndDnsAndProfileService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} MSFT_NetAdapter entry(s)", netList.size());

        return netList.stream()
                .filter(Objects::nonNull)
                .filter(net -> Objects.nonNull(net.interfaceIndex()))
                .collect(Collectors.toUnmodifiableMap(
                        net -> Objects.requireNonNull(net.interfaceIndex()),
                        net -> net
                )); // IntelliJ won't shut up even tho I literally filtered out nullable interface indexes

    }

    @Override
    protected void done() {
        try {
            Map<Long, MsftNetAdapterToIpAndDnsAndProfile> netMap = get();

            // populate the combo box with network interface indexes
            netMap.keySet().stream().sorted().forEach(networkIndexComboBox::addItem);
            // populate fields and editor panes for the first entry in the combo box
            populate(netMap);
            // add a listener to the combo box to re-populate fields on new selection
            networkIndexComboBox.addActionListener(selectAction -> populate(netMap));

        } catch (InterruptedException e) {
            log.error("Network Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("Network Fetch Failed", e);
        }
    }

    private void populate(Map<Long, MsftNetAdapterToIpAndDnsAndProfile> netMap) {

        Long interfaceIndex = (Long) networkIndexComboBox.getSelectedItem();

        MsftNetAdapterToIpAndDnsAndProfile currentNet = netMap.get(interfaceIndex);
        if (currentNet == null) return;

        MsftNetAdapter currentAdapter = currentNet.adapter();
        List<MsftNetIpAddress> currentNetIpAddressList = currentNet.ipAddressList();
        List<MsftDnsClientServerAddress> currentDnsAddressList = currentNet.dnsClientServerAddressList();
        List<MsftNetConnectionProfile> currentConnectionProfileList = currentNet.netConnectionProfileList();

        if (currentAdapter != null) {
            networkFields.get(0).setText(currentAdapter.deviceId());
            networkFields.get(1).setText(currentAdapter.interfaceDescription());
            networkFields.get(2).setText(currentAdapter.driverVersion());
            networkFields.get(3).setText(currentAdapter.driverDate());
            networkFields.get(4).setText(String.valueOf(currentAdapter.interfaceType())); //not parsed cause long list
            networkFields.get(5).setText(currentAdapter.linkLayerAddress());
            networkFields.get(6).setText(currentAdapter.linkSpeed());
            networkFields.get(7).setText(WMIValueResolver.resolveMsftNetAdapterMediaConnectState(currentAdapter.mediaConnectState()));
            networkFields.get(8).setText(currentAdapter.mediaType());
            networkFields.get(9).setText(WMINetworkResolver.resolveNetworkSpeedInMbps(currentAdapter.receiveLinkSpeedRaw()));
            networkFields.get(10).setText(WMINetworkResolver.resolveNetworkSpeedInMbps(currentAdapter.transmitLinkSpeedRaw()));
            networkFields.get(11).setText(WMIBooleanResolver.resolveBoolean(currentAdapter.fullDuplex()));
            networkFields.get(12).setText(WMIBooleanResolver.resolveBoolean(currentAdapter.virtual()));
            networkFields.get(13).setText(currentAdapter.status());
            networkFields.get(14).setText(currentAdapter.pnpDeviceId());
        }

        JEditorPane ipAddressPane = networkEditorPanes.get(0);
        JEditorPane dnsAddressPane = networkEditorPanes.get(1);
        JEditorPane connectionProfilePane = networkEditorPanes.get(2);
        // reset their contents on every invoke of this function
        ipAddressPane.setText(null);
        dnsAddressPane.setText(null);
        connectionProfilePane.setText(null);


        if (currentNetIpAddressList != null && !currentNetIpAddressList.isEmpty()) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body>");
            currentNetIpAddressList.forEach(ip -> stringBuilder
                    .append("<b>IP Interface Index:</b> ").append(ip.interfaceIndex()).append("<br>")
                    .append("<b>IP Interface Alias:</b> ").append(ip.interfaceAlias()).append("<br>")
                    .append("<b>Address Family:</b> ").append(WMIValueResolver.resolveMsftIPvAddressFamily(ip.addressFamily())).append("<br>")
                    .append("<b>IPv4 Address:</b> ").append(ip.ipv4Address()).append("<br>")
                    .append("<b>IPv6 Address:</b> ").append(ip.ipv6Address()).append("<br>")
                    .append("<b>Type:</b> ").append(WMIValueResolver.resolveMsftNetIpAddressType(ip.type())).append("<br>")
                    .append("<b>Prefix Origin:</b> ").append(WMIValueResolver.resolveMsftNetIpAddressPrefixOrigin(ip.prefixOrigin())).append("<br>")
                    .append("<b>Suffix Origin:</b> ").append(WMIValueResolver.resolveMsftNetIpAddressSuffixOrigin(ip.suffixOrigin())).append("<br><br>")
            );
            stringBuilder.append("</body></html>");

            ipAddressPane.setText(stringBuilder.toString());
        }

        if (currentDnsAddressList != null && !currentDnsAddressList.isEmpty()) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body>");
            currentDnsAddressList.forEach(dns -> stringBuilder
                    .append("<b>DNS Interface Index:</b> ").append(dns.interfaceIndex()).append("<br>")
                    .append("<b>DNS Interface Alias:</b> ").append(dns.interfaceAlias()).append("<br>")
                    .append("<b>Address Family:</b> ").append(WMIValueResolver.resolveMsftIPvAddressFamily(dns.addressFamily())).append("<br>")
                    .append("<b>DNS Server Addresses:</b> ").append(dns.dnsServerAddresses()).append("<br><br>")
            );
            stringBuilder.append("</body></html>");

            dnsAddressPane.setText(stringBuilder.toString());
        }

        if (currentConnectionProfileList != null && !currentConnectionProfileList.isEmpty()) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body>");
            currentConnectionProfileList.forEach(profile -> stringBuilder
                    .append("<b>Profile Interface Index:</b> ").append(profile.interfaceIndex()).append("<br>")
                    .append("<b>Connection Interface Alias:</b> ").append(profile.interfaceAlias()).append("<br>")
                    .append("<b>Category:</b> ").append(WMIValueResolver.resolveMsftNetConnectionProfileNetworkCategory(profile.networkCategory())).append("<br>")
                    .append("<b>Domain Auth Kind:</b> ").append(WMIValueResolver.resolveMsftNetConnectionProfileDomainAuthenticationKind(profile.domainAuthenticationKind())).append("<br>")
                    .append("<b>IPv4 Connectivity:</b> ").append(WMIValueResolver.resolveMsftNetConnectionProfileConnectivity(profile.ipv4Connectivity())).append("<br>")
                    .append("<b>IPv6 Connectivity:</b> ").append(WMIValueResolver.resolveMsftNetConnectionProfileConnectivity(profile.ipv6Connectivity())).append("<br><br>")
            );
            stringBuilder.append("</body></html>");

            connectionProfilePane.setText(stringBuilder.toString());
        }

    }
}
