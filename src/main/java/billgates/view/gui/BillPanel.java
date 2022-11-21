package billgates.view.gui;

import billgates.interface_adapters.BillPanelUpdatable;
import billgates.usecases.bill_update.BillUpdateViewModel;
import billgates.view.BillTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott
 */
public class BillPanel extends JPanel implements BillPanelUpdatable {

    public static final int DEFAULT_WIDTH = MainFrame.DEFAULT_WIDTH - ActionPanel.DEFAULT_WIDTH - 14;
    // public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT - 37;

    private final MainFrame mainFrame;

    private final BillTable billTable = new BillTable();

    public BillPanel(MainFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame;

        JScrollPane scrollPane = new JScrollPane(this.billTable);
        this.add(scrollPane, BorderLayout.CENTER);
        this.initBorder();

        // The bill table is disabled at the beginning
        this.billTable.setEnabled(false);
        this.billTable.setVisible(false);

        // Add mouse event
        this.billTable.addMouseListener(new BillTableMouseAdapter());
        this.billTable.getModel().addTableModelListener(this::billTableModelAltered);
    }

    private void initBorder() {
        TitledBorder billsBorder = new CustomTitleBorder("Bills");
        this.setBorder(billsBorder);
    }

    /**
     * this method will be invoked when there is any change in the table.
     *
     * @param event a TableModelEvent representing any change in the table
     */
    private void billTableModelAltered(TableModelEvent event) {
        if (event.getType() == TableModelEvent.UPDATE) {
            // TODO: call the alter entry use case

            // we want to update current table
            SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-1));
        }
    }

    public BillTable getBillTable() {
        return this.billTable;
    }

    @Override
    public void update(BillUpdateViewModel viewModel) {
        String[] columns = viewModel.getColumns();
        List<List<Object>> entries = viewModel.getEntries();
        BillTableModel model = this.getBillTable().getModel();
        model.setColumnNames(columns);
        model.setData(entries);
        this.getBillTable().updateUI();
    }

    /**
     * This class serves as the mouse events listener for the bill table.
     */
    private class BillTableMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                ActionButton deleteEntryButton = (ActionButton) BillPanel.this.mainFrame.
                        getActionPanel().getDeleteEntryButton();
                deleteEntryButton.setEnabled(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2) {
                System.out.println(2);
                // trigger to splitter bill
                Point point = new Point(e.getX(), e.getY());
                int row = BillPanel.this.billTable.rowAtPoint(point);
                int column = BillPanel.this.billTable.columnAtPoint(point);
                if (row == -1 || column == -1)
                    return;
                String name = BillPanel.this.billTable.getColumnName(column);
                if ("Splitter".equals(name))
                    return;
                String splitter = (String) BillPanel.this.billTable.getModel().getValueAt(row, column);
                if ("No".equals(splitter)) {
                    // TODO: call create splitter bill use case
                }
                // get the entry id
                int entryId = (int) BillPanel.this.getBillTable().getModel().getValueAt(row, 0);
                // for debugging TODO: delete it
                System.out.println(entryId);
                // call the bill update use case on the entryId
                // TODO: uncomment the line below. I commented because I don't have create splitter use case now.
                // SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(entryId));
            }
        }
    }

}