/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.user.Win32UserAccount;
import io.github.eggy03.cimari.service.user.Win32UserAccountService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIBooleanResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIValueResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class WMIUserAccountWorker extends SwingWorker<Map<String, Win32UserAccount>, Void> {

    private final JComboBox<String> userAccountSIDComboBox;
    private final List<JTextField> userAccountFields;

    @Override
    protected Map<String, Win32UserAccount> doInBackground() {
        List<Win32UserAccount> accountList = new Win32UserAccountService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} Win32_UserAccount entry(s)", accountList.size());

        return accountList.stream()
                .filter(Objects::nonNull)
                .filter(account -> Objects.nonNull(account.sid()))
                .collect(Collectors.toUnmodifiableMap(
                        account -> Objects.requireNonNull(account.sid()),
                        account -> account
                ));
    }

    @Override
    protected void done() {
        try {
            Map<String, Win32UserAccount> accountMap = get();
            // fill the combo box with user account SIDs
            accountMap.keySet().stream().sorted().forEach(userAccountSIDComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFields(accountMap);
            // add a listener to the combo box to re-populate fields on new selection
            userAccountSIDComboBox.addActionListener(selectEvent -> populateFields(accountMap));

        } catch (InterruptedException e) {
            log.error("User Account Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("User Account Fetch Failed", e);
        }
    }

    private void populateFields(Map<String, Win32UserAccount> accountMap) {
        String userAccountSid = String.valueOf(userAccountSIDComboBox.getSelectedItem());

        Win32UserAccount account = accountMap.get(userAccountSid);
        if (account == null) return;

        userAccountFields.get(0).setText(account.name());
        userAccountFields.get(1).setText(account.caption());
        userAccountFields.get(2).setText(account.domain());
        userAccountFields.get(3).setText(account.description());
        userAccountFields.get(4).setText(WMIBooleanResolver.resolveBoolean(account.passwordRequired()));
        userAccountFields.get(5).setText(WMIBooleanResolver.resolveBoolean(account.passwordChangeable()));
        userAccountFields.get(6).setText(WMIBooleanResolver.resolveBoolean(account.passwordExpires()));
        userAccountFields.get(7).setText(WMIBooleanResolver.resolveBoolean(account.localAccount()));
        userAccountFields.get(8).setText(WMIBooleanResolver.resolveBoolean(account.disabled()));
        userAccountFields.get(9).setText(WMIBooleanResolver.resolveBoolean(account.lockout()));
        userAccountFields.get(10).setText(WMIValueResolver.resolveWMIUserAccountType(account.accountType()));
        userAccountFields.get(11).setText(WMIValueResolver.resolveWMIUserAccountSidType(account.sidType()));
        userAccountFields.get(12).setText(account.status());
    }
}
