package twels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankingSystemGUI extends JFrame {
    private final Bank bank;

    private AccountsTableModel accountsTableModel;
    private JTable accountsTable;

    private JComboBox<String> depositAccountCombo;
    private JTextField depositAmountField;

    private JComboBox<String> withdrawAccountCombo;
    private JTextField withdrawAmountField;

    private JComboBox<String> transferSourceCombo;
    private JComboBox<String> transferDestCombo;
    private JTextField transferAmountField;
    private JTextField transferDescField;

    private JComboBox<String> stolenOldAccountCombo;
    private JTextField stolenNameField;

    private JTextArea historyArea;

    public BankingSystemGUI() {
        super("Banking System");
        this.bank = new Bank();
        initLookAndFeel();
        initUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
    }

    private void initLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
    }

    private void initUI() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Accounts", buildAccountsPanel());
        tabs.addTab("Deposit", buildDepositPanel());
        tabs.addTab("Withdraw", buildWithdrawPanel());
        tabs.addTab("Transfer", buildTransferPanel());
        tabs.addTab("Stolen Card", buildStolenCardPanel());
        tabs.addTab("History", buildHistoryPanel());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabs, BorderLayout.CENTER);

        refreshAll();
    }

    private JPanel buildAccountsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        accountsTableModel = new AccountsTableModel(new ArrayList<>(bank.getAllAccounts()));
        accountsTable = new JTable(accountsTableModel);
        accountsTable.setFillsViewportHeight(true);
        panel.add(new JScrollPane(accountsTable), BorderLayout.CENTER);

        JButton createBtn = new JButton("Create Account");
        createBtn.addActionListener(this::onCreateAccount);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshAll());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(createBtn);
        top.add(refreshBtn);
        panel.add(top, BorderLayout.NORTH);

        return panel;
    }

    private JPanel buildDepositPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gc = baseGbc();

        depositAccountCombo = new JComboBox<>();
        depositAmountField = new JTextField(12);
        JButton depositBtn = new JButton("Deposit");
        depositBtn.addActionListener(this::onDeposit);

        addLabeled(panel, gc, 0, "Account", depositAccountCombo);
        addLabeled(panel, gc, 1, "Amount", depositAmountField);
        gc.gridx = 1; gc.gridy = 2; gc.gridwidth = 2; gc.anchor = GridBagConstraints.WEST;
        panel.add(depositBtn, gc);
        return panel;
    }

    private JPanel buildWithdrawPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gc = baseGbc();

        withdrawAccountCombo = new JComboBox<>();
        withdrawAmountField = new JTextField(12);
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.addActionListener(this::onWithdraw);

        addLabeled(panel, gc, 0, "Account", withdrawAccountCombo);
        addLabeled(panel, gc, 1, "Amount", withdrawAmountField);
        gc.gridx = 1; gc.gridy = 2; gc.gridwidth = 2; gc.anchor = GridBagConstraints.WEST;
        panel.add(withdrawBtn, gc);
        return panel;
    }

    private JPanel buildTransferPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gc = baseGbc();

        transferSourceCombo = new JComboBox<>();
        transferDestCombo = new JComboBox<>();
        transferAmountField = new JTextField(12);
        transferDescField = new JTextField(18);
        JButton transferBtn = new JButton("Transfer");
        transferBtn.addActionListener(this::onTransfer);

        addLabeled(panel, gc, 0, "From", transferSourceCombo);
        addLabeled(panel, gc, 1, "To", transferDestCombo);
        addLabeled(panel, gc, 2, "Amount", transferAmountField);
        addLabeled(panel, gc, 3, "Description", transferDescField);
        gc.gridx = 1; gc.gridy = 4; gc.gridwidth = 2; gc.anchor = GridBagConstraints.WEST;
        panel.add(transferBtn, gc);
        return panel;
    }

    private JPanel buildStolenCardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gc = baseGbc();

        stolenOldAccountCombo = new JComboBox<>();
        stolenNameField = new JTextField(18);
        JButton stolenBtn = new JButton("Report Stolen Card");
        stolenBtn.addActionListener(this::onStolenCard);

        addLabeled(panel, gc, 0, "Old Account", stolenOldAccountCombo);
        addLabeled(panel, gc, 1, "Account Holder Name", stolenNameField);
        gc.gridx = 1; gc.gridy = 2; gc.gridwidth = 2; gc.anchor = GridBagConstraints.WEST;
        panel.add(stolenBtn, gc);
        return panel;
    }

    private JPanel buildHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        panel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> refreshHistory());
        panel.add(refresh, BorderLayout.NORTH);
        return panel;
    }

    private void addLabeled(JPanel panel, GridBagConstraints gc, int row, String label, JComponent comp) {
        GridBagConstraints left = (GridBagConstraints) gc.clone();
        left.gridx = 0; left.gridy = row; left.gridwidth = 1; left.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(label + ":"), left);

        GridBagConstraints right = (GridBagConstraints) gc.clone();
        right.gridx = 1; right.gridy = row; right.gridwidth = 2; right.anchor = GridBagConstraints.WEST;
        panel.add(comp, right);
    }

    private GridBagConstraints baseGbc() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 8, 6, 8);
        gc.fill = GridBagConstraints.HORIZONTAL;
        return gc;
    }

    private void onCreateAccount(ActionEvent e) {
        JPanel form = new JPanel(new GridLayout(0, 1, 8, 8));
        JTextField name = new JTextField();
        JComboBox<String> type = new JComboBox<>(new String[]{"Savings", "Current"});
        form.add(new JLabel("Account Holder Name:"));
        form.add(name);
        form.add(new JLabel("Account Type:"));
        form.add(type);
        int res = JOptionPane.showConfirmDialog(this, form, "Create Account", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            String holder = name.getText().trim();
            if (!holder.matches("^[a-zA-Z\\s]+$") || holder.isEmpty()) {
                message("Invalid name. Use letters and spaces only.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int t = Objects.equals(type.getSelectedItem(), "Savings") ? 1 : 2;
            String accNo = bank.createAccount(holder, t);
            message("Account created. Number: " + accNo, JOptionPane.INFORMATION_MESSAGE);
            refreshAll();
        }
    }

    private void onDeposit(ActionEvent e) {
        String acc = (String) depositAccountCombo.getSelectedItem();
        double amount = parseAmount(depositAmountField.getText());
        if (acc == null) { message("Select an account.", JOptionPane.WARNING_MESSAGE); return; }
        if (amount <= 0) { message("Amount must be positive.", JOptionPane.WARNING_MESSAGE); return; }
        if (bank.depositToAccount(acc, amount)) {
            // Record deposit description for visibility
            Account a = bank.getAccount(acc);
            if (a != null) {
                a.transactionDescriptions.add(String.format("Deposit: +$%.2f", amount));
            }
            message("Deposit successful.", JOptionPane.INFORMATION_MESSAGE);
            refreshAll();
        } else {
            message("Deposit failed.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onWithdraw(ActionEvent e) {
        String acc = (String) withdrawAccountCombo.getSelectedItem();
        double amount = parseAmount(withdrawAmountField.getText());
        if (acc == null) { message("Select an account.", JOptionPane.WARNING_MESSAGE); return; }
        if (amount <= 0) { message("Amount must be positive.", JOptionPane.WARNING_MESSAGE); return; }
        boolean ok = bank.withdrawFromAccount(acc, amount);
        if (ok) {
            message("Withdrawal successful.", JOptionPane.INFORMATION_MESSAGE);
            refreshAll();
        } else {
            Account account = bank.getAccount(acc);
            if (account instanceof SavingsAccount) {
                message("Withdrawal failed. Insufficient funds or below minimum balance.", JOptionPane.ERROR_MESSAGE);
            } else {
                message("Withdrawal failed. Insufficient funds.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onTransfer(ActionEvent e) {
        String from = (String) transferSourceCombo.getSelectedItem();
        String to = (String) transferDestCombo.getSelectedItem();
        double amount = parseAmount(transferAmountField.getText());
        String desc = transferDescField.getText().trim();
        if (from == null || to == null) { message("Select both accounts.", JOptionPane.WARNING_MESSAGE); return; }
        if (from.equals(to)) { message("Cannot transfer to the same account.", JOptionPane.WARNING_MESSAGE); return; }
        if (amount <= 0) { message("Amount must be positive.", JOptionPane.WARNING_MESSAGE); return; }
        if (desc.isEmpty()) { desc = "General Transfer"; }

        boolean ok = bank.transferFunds(from, to, amount, desc);
        if (ok) {
            // add symmetric logs for better UX
            Account src = bank.getAccount(from);
            Account dst = bank.getAccount(to);
            if (src != null) src.transactionDescriptions.add(String.format("Transfer to %s: -$%.2f (%s)", to, amount, desc));
            if (dst != null) dst.transactionDescriptions.add(String.format("Transfer from %s: +$%.2f (%s)", from, amount, desc));
            message("Transfer successful.", JOptionPane.INFORMATION_MESSAGE);
            refreshAll();
        } else {
            message("Transfer failed. Check balances.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onStolenCard(ActionEvent e) {
        String oldAcc = (String) stolenOldAccountCombo.getSelectedItem();
        String name = stolenNameField.getText().trim();
        if (oldAcc == null) { message("Select an account.", JOptionPane.WARNING_MESSAGE); return; }
        if (!name.matches("^[a-zA-Z\\s]+$") || name.isEmpty()) { message("Enter a valid name.", JOptionPane.WARNING_MESSAGE); return; }
        String newAcc = bank.handleStolenCard(oldAcc, name);
        if (newAcc != null) {
            message("Account secured. New account number: " + newAcc, JOptionPane.INFORMATION_MESSAGE);
            refreshAll();
        } else {
            message("Failed to process. Verify details.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshAll() {
        // refresh tables and combos
        accountsTableModel.setAccounts(new ArrayList<>(bank.getAllAccounts()));
        accountsTableModel.fireTableDataChanged();

        List<String> ids = new ArrayList<>();
        for (Account a : bank.getAllAccounts()) {
            ids.add(a.getAccountNumber());
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(ids.toArray(new String[0]));
        depositAccountCombo.setModel(new DefaultComboBoxModel<>(ids.toArray(new String[0])));
        withdrawAccountCombo.setModel(new DefaultComboBoxModel<>(ids.toArray(new String[0])));
        transferSourceCombo.setModel(new DefaultComboBoxModel<>(ids.toArray(new String[0])));
        transferDestCombo.setModel(new DefaultComboBoxModel<>(ids.toArray(new String[0])));
        stolenOldAccountCombo.setModel(new DefaultComboBoxModel<>(ids.toArray(new String[0])));

        refreshHistory();
    }

    private void refreshHistory() {
        StringBuilder sb = new StringBuilder();
        for (String rec : bank.getTransferHistoryList()) {
            sb.append(rec).append('\n');
        }
        historyArea.setText(sb.toString());
    }

    private double parseAmount(String text) {
        try {
            return Double.parseDouble(text.trim());
        } catch (Exception ex) {
            return -1;
        }
    }

    private void message(String msg, int type) {
        JOptionPane.showMessageDialog(this, msg, "Banking System", type);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingSystemGUI().setVisible(true));
    }

    private static class AccountsTableModel extends AbstractTableModel {
        private final String[] cols = {"Account #", "Holder", "Type", "Balance", "Recent Transactions"};
        private List<Account> accounts;

        public AccountsTableModel(List<Account> accounts) {
            this.accounts = accounts;
        }

        public void setAccounts(List<Account> accounts) {
            this.accounts = accounts;
        }

        @Override
        public int getRowCount() {
            return accounts == null ? 0 : accounts.size();
        }

        @Override
        public int getColumnCount() {
            return cols.length;
        }

        @Override
        public String getColumnName(int column) {
            return cols[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Account a = accounts.get(rowIndex);
            switch (columnIndex) {
                case 0: return a.getAccountNumber();
                case 1: return a.getAccountHolderName();
                case 2: return a.getAccountType().getTypeName();
                case 3: return String.format("$%.2f", a.getBalance());
                case 4:
                    List<String> tx = a.getTransactionDescriptions();
                    if (tx.isEmpty()) return "-";
                    int n = Math.min(5, tx.size());
                    StringBuilder sb = new StringBuilder();
                    for (int i = tx.size() - n; i < tx.size(); i++) {
                        sb.append("• ").append(tx.get(i));
                        if (i < tx.size() - 1) sb.append(" | ");
                    }
                    return sb.toString();
                default: return "";
            }
        }
    }
}
