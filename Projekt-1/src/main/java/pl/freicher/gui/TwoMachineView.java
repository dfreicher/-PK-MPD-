package pl.freicher.gui;

import pl.freicher.asset.Job;
import pl.freicher.chart.Item;
import pl.freicher.chart.TwoMachineChartComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Freicher
 * @version 1.0
 */
public class TwoMachineView extends JFrame {


    public TwoMachineView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        addTask = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        removeTask = new javax.swing.JButton();
        randomValuesButton = new javax.swing.JButton();
        backToMenuButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        diagramPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        calculateButton = new javax.swing.JButton();
        resultPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        cmaxLabel = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System dwumaszynowy");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setResizable(false);

        table.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numer zadania", "Maszyna 1 (czas wykonania)", "Maszyna 2 (czas wykonania)"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, Integer.class, Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(table);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        addTask.setText("Dodaj zadanie");
        addTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTask();
            }
        });

        clearButton.setText("Wyczyść");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!isTableJobsEmpty()) {
                    clearTasks();
                    cmaxLabel.setText("");
                    resultLabel.setText("");
                    diagramPanel.setVisible(false);
                }
            }
        });

        removeTask.setText("Usuń zadanie");
        removeTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!isTableJobsEmpty()) {
                    removeTask();
                }
            }
        });

        randomValuesButton.setText("Losuj wartości");
        randomValuesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!isTableJobsEmpty()) {
                    randomValues();
                }
            }
        });

        backToMenuButton.setText("Powrót do Menu");
        backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMenu();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addTask)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeTask)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(randomValuesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backToMenuButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTask)
                    .addComponent(clearButton)
                    .addComponent(removeTask)
                    .addComponent(randomValuesButton)
                    .addComponent(backToMenuButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        diagramPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        diagramPanel.setAutoscrolls(true);
        diagramPanel.setEnabled(false);
        diagramPanel.setFocusCycleRoot(true);
        diagramPanel.setLayout(new java.awt.BorderLayout());
        jScrollPane2.setViewportView(diagramPanel);

        calculateButton.setText("Wyznacz kolejność zadań");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!isTableJobsEmpty()) {
                    int firstValue = (int) table.getModel().getValueAt(0, 1);
                    if (firstValue == 0) {
                        JOptionPane.showMessageDialog(null, "Proszę wylosować wartości!");
                    } else {
                        diagramPanel.setVisible(true);
                        calculate();
                    }
                }
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calculateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calculateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        resultPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Cmax = ");

        jLabel3.setText("Kolejność zadań:");

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmaxLabel))
                    .addGroup(resultPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmaxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(resultLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void addNewTask() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{table.getRowCount() + 1, 0, 0});
    }

    private void clearTasks() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void removeTask() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (table.getSelectedRow() > -1) {
            model.removeRow(table.getSelectedRow());
        }
    }

    private void randomValues() {
        Random generator = new Random();
        for (int i = table.getRowCount() - 1; i >= 0; i--) {
            for (int y = 1; y <= 2; y++) {
                table.getModel().setValueAt(generator.nextInt(5) + 1, i, y);
            }
        }
    }

    private void backToMenu() {
        this.setVisible(false);
        this.dispose();

        JFrame frame = new MenuView();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void calculate() {
        List<Job> firstList = new ArrayList<Job>();
        List<Job> secondList = new ArrayList<Job>();

        
        for (int i = 0; i < table.getRowCount(); i++) {

            int firstMachineValue = (int) table.getModel().getValueAt(i, 1);
            int secondMachineValue = (int) table.getModel().getValueAt(i, 2);

            if (firstMachineValue <= secondMachineValue) {
                firstList.add(new Job("Z" + (i + 1), firstMachineValue));
            } else if (firstMachineValue > secondMachineValue) {
                secondList.add(new Job("Z" + (i + 1), secondMachineValue));
            }
        }

        Collections.sort(firstList);
        Collections.sort(secondList, Collections.reverseOrder());
        firstList.addAll(secondList);

        StringBuilder sb = new StringBuilder();
        for (Job t : firstList) {
            sb.append(t.getName() + " ");
        }
        resultLabel.setText(sb.toString());

        List<Item> firstMachineItems = new ArrayList<Item>();
        List<Item> secondMachineItems = new ArrayList<Item>();

        int current = 0;
        for (Job t : firstList) {

            String name = t.getName();
            int value = (int) table.getModel().getValueAt(Integer.parseInt(name.substring(1, name.length())) - 1, 1);
            int value2 = (int) table.getModel().getValueAt(Integer.parseInt(name.substring(1, name.length())) - 1, 2);

            Item item = new Item(name, current, current + value);
            firstMachineItems.add(item);
            current += value;

            Item item2 = new Item(name, current, current + value2);
            secondMachineItems.add(item2);
        }

        TwoMachineChartComponent chart = new TwoMachineChartComponent(firstMachineItems, secondMachineItems);
        diagramPanel.removeAll();
        diagramPanel.add(chart);

        cmaxLabel.setText(chart.getMaxX() + "");
    }

    public boolean isTableJobsEmpty() {
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Proszę wprowadzić zadania!");
            return true;
        }
        return false;
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TwoMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TwoMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TwoMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TwoMachineView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new TwoMachineView();
                frame.setVisible(true);

            }
        });

    }

    private javax.swing.JButton addTask;
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel cmaxLabel;
    private javax.swing.JPanel diagramPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton randomValuesButton;
    private javax.swing.JButton backToMenuButton;
    private javax.swing.JButton removeTask;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JTable table;
}
