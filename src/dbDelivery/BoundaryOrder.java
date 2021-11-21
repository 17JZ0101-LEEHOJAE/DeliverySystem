/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 注文受付画面
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class BoundaryOrder extends javax.swing.JFrame {

    DefaultTableModel orderTableModel;
    DefaultTableCellRenderer rendar;
    Timer timer;
    ControlOrder control;
    int usingPoint = 0;

    /**
     * Creates new form BoundaryOrder
     */
    public BoundaryOrder() {
        initComponents();
        setTitle("注文受付画面");
        setLocationRelativeTo(this);
        initTableModel();
        initItemComboBox();
        initAmountComboBox();
        initHourComboBox();
        initMinuteComboBox();
        initTimer();
    }

    /**
     * テーブルモデルの初期化と表との連携
     */
    private void initTableModel() {
        String[] hedding = {"商品名", "注文数", "注文金額"};
        orderTableModel = new DefaultTableModel(hedding, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        jTableOrderDetail.setModel(orderTableModel);
        rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 0; i < jTableOrderDetail.getColumnCount(); i++) {
            jTableOrderDetail.getColumnModel().getColumn(i).setCellRenderer(rendar);
        }
        jTableOrderDetail.setColumnSelectionAllowed(false);
        jTableOrderDetail.setRowSelectionAllowed(true);
//        jTableOrderDetail.getModel().addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                System.out.println(e);
//                int row = jTableOrderDetail.getSelectedRow();
//                int column = e.getColumn();
//                if(column == 1) {
//                    String itemName = jTableOrderDetail.getValueAt(row, 0).toString();
//                    int amount = Integer.parseInt(jTableOrderDetail.getValueAt(row, column).toString());
//                    int price = getItemPrice(itemName, amount);
//                    jTableOrderDetail.setValueAt(price, row, 2);
//                    showSubtotalPrice();
//                    showTotalPrice();                        
//                }
//            }            
//        });
    }

    private void initItemComboBox() {
        jComboBoxItem.removeAllItems();
        String[] item = {"トリピミックス",
            "ベーコンピザ", "マルゲリータピザ", "オリーブピザ", "アンチョビピザ", "クアトロ・スタジオー二ピザ",
            "フライドチキン", "しょう油チキン", "チリチキン", "カルビーチキン", "チーズチキン",
            "半々チキン", "半々ピザ", "コーラ", "スプライト",
            "(S)コーラ", "(S)スプライト", "(S)トリピミックス", "(S)ミニチキン", "(S)ミニピザ"};
        for (String data : item) {
            jComboBoxItem.addItem(data);
        }
    }

    private void initHourComboBox() {
        jComboBoxHour.removeAllItems();
        for (int i = 11; i <= 21; i++) {
            jComboBoxHour.addItem(Integer.toString(i));
        }
    }

    private void initMinuteComboBox() {
        jComboBoxMinute.removeAllItems();
        for (int i = 0; i < 60; i += 10) {
            jComboBoxMinute.addItem(Integer.toString(i));
        }
    }

    private void initAmountComboBox() {
        jComboBoxAmount.removeAllItems();
        for (int i = 1; i <= 10; i++) {
            jComboBoxAmount.addItem(Integer.toString(i));
        }
    }

    private void initTimer() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime today = LocalDateTime.now();
                int hour = today.getHour();
                int minute = today.getMinute();
                int second = today.getSecond();
                jLabelDate.setText(today.toLocalDate().toString() + " ("
                        + today.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.JAPANESE) + ") "
                        + hour + " : " + minute + " : " + second);
            }
        }, 0, 100);
    }

    private void setUsingPoint(int usingPoint) {
        this.usingPoint += usingPoint;
    }

    private void setUsingPoint(int usingPoint, int amount) {
        this.usingPoint += usingPoint * amount;
    }

    private int getUsingPoint() {
        return usingPoint;
    }

    private void showOptionDialog() {
        String[] selectvalues = {"(S)コーラ", "(S)スプライト"};
        String itemName = jComboBoxItem.getSelectedItem().toString();
        String inputData = createAddData(itemName);

        int value = JOptionPane.showOptionDialog(this, "ドリンクを選択してください", "サービス商品選択",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, selectvalues, selectvalues);
        
        if(value != -1 && itemName.equals("(S)トリピミックス")) {
            control.addOrderItemData(inputData);
            inputData = createAddData(selectvalues[value]);
            control.addOrderItemData(inputData);         
        } else if(value != -1 && itemName.equals("トリピミックス")) {
            control.addOrderItemData(inputData);
            inputData = createAddData(selectvalues[value]);
            control.addOrderItemData(inputData);            
        }
    }

    /**
     * コントロール設定
     *
     * @param control
     */
    public void setControl(ControlOrder control) {
        this.control = control;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonCheckCustomer = new javax.swing.JButton();
        jTextFieldOrderName = new javax.swing.JTextField();
        jTextFieldOrderTel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabelOrderPoint = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxItem = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButtonOrderInput = new javax.swing.JButton();
        jComboBoxAmount = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrderDetail = new javax.swing.JTable();
        jButtonOrderCancel = new javax.swing.JButton();
        jButtonOrderDetailFinal = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelSubtotalPrice = new javax.swing.JLabel();
        jTextFieldDeliveryDate = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabelTotalPrice = new javax.swing.JLabel();
        jButtonPointDiscount = new javax.swing.JButton();
        jButtonOrderslipPrint = new javax.swing.JButton();
        jButtonOrderEnd = new javax.swing.JButton();
        jComboBoxHour = new javax.swing.JComboBox<>();
        jComboBoxMinute = new javax.swing.JComboBox<>();
        jLabelDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(192, 222, 242));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(704, 1024));

        jLabel1.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Trypy Mix");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("注文受付");

        jLabel3.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("名前(カナ)");

        jLabel4.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("電話番号");

        jButtonCheckCustomer.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonCheckCustomer.setText("顧客確認");
        jButtonCheckCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckCustomerActionPerformed(evt);
            }
        });

        jTextFieldOrderName.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jTextFieldOrderName.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jTextFieldOrderTel.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jTextFieldOrderTel.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel5.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("ポイント数");

        jLabelOrderPoint.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jLabelOrderPoint.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelOrderPoint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(133, 176, 247));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("商品・個数");

        jComboBoxItem.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jComboBoxItem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item1", "item2", "item3", "item4" }));
        jComboBoxItem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxItem.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("個");

        jButtonOrderInput.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonOrderInput.setText("注文入力");
        jButtonOrderInput.setEnabled(false);
        jButtonOrderInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderInputActionPerformed(evt);
            }
        });

        jComboBoxAmount.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jComboBoxAmount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item1", "item2", "item3", "item4" }));
        jComboBoxAmount.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxItem, 0, 187, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addComponent(jButtonOrderInput)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonOrderInput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBoxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxItem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        jPanel3.setBackground(new java.awt.Color(133, 176, 247));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel10.setText("注文明細");

        jTableOrderDetail.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jTableOrderDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableOrderDetail);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jButtonOrderCancel.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonOrderCancel.setText("注文取消");
        jButtonOrderCancel.setEnabled(false);
        jButtonOrderCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderCancelActionPerformed(evt);
            }
        });

        jButtonOrderDetailFinal.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonOrderDetailFinal.setText("明細確定");
        jButtonOrderDetailFinal.setEnabled(false);
        jButtonOrderDetailFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderDetailFinalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonOrderCancel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonOrderDetailFinal, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(35, 35, 35))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonOrderCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonOrderDetailFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel11.setText("配達希望日時");

        jLabel12.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel12.setText("注文小計金額");

        jLabelSubtotalPrice.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jLabelSubtotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelSubtotalPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextFieldDeliveryDate.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jTextFieldDeliveryDate.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel14.setText("注文合計金額");

        jLabelTotalPrice.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jLabelTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonPointDiscount.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonPointDiscount.setText("ポイント割引");
        jButtonPointDiscount.setEnabled(false);
        jButtonPointDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPointDiscountActionPerformed(evt);
            }
        });

        jButtonOrderslipPrint.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonOrderslipPrint.setText("注文伝票印刷");
        jButtonOrderslipPrint.setEnabled(false);
        jButtonOrderslipPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderslipPrintActionPerformed(evt);
            }
        });

        jButtonOrderEnd.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jButtonOrderEnd.setText("注文終了");
        jButtonOrderEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderEndActionPerformed(evt);
            }
        });

        jComboBoxHour.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jComboBoxHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item1", "item2", "item3", "item4" }));
        jComboBoxHour.setEnabled(false);

        jComboBoxMinute.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jComboBoxMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item1", "item2", "item3", "item4" }));
        jComboBoxMinute.setEnabled(false);

        jLabelDate.setFont(new java.awt.Font("MS UI Gothic", 0, 15)); // NOI18N
        jLabelDate.setText("yyyy-MM-dd(M) hh : mm : ss");

        jLabel6.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel6.setText("時");

        jLabel8.setFont(new java.awt.Font("MS UI Gothic", 0, 14)); // NOI18N
        jLabel8.setText("分");

        jLabel13.setFont(new java.awt.Font("MS UI Gothic", 0, 13)); // NOI18N
        jLabel13.setText("注文確定");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(jTextFieldOrderName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldOrderTel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCheckCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelOrderPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelSubtotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextFieldDeliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(jButtonPointDiscount))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jComboBoxHour, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addGap(29, 29, 29)
                                                .addComponent(jComboBoxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8)
                                                .addGap(75, 75, 75))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonOrderslipPrint)
                                        .addGap(34, 34, 34)
                                        .addComponent(jButtonOrderEnd))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel13))))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonOrderEnd, jButtonOrderslipPrint});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldOrderName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldOrderTel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel5))
                        .addComponent(jButtonCheckCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelOrderPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldDeliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxHour, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonPointDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel12))
                            .addComponent(jLabelSubtotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOrderslipPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOrderEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(19, 19, 19))
        );

        jLabelDate.getAccessibleContext().setAccessibleName("yyyy-mm hh : mm : ss");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCheckCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckCustomerActionPerformed
        // TODO add your handling code here:
        if (jTextFieldOrderName.getText().matches("^[\\u30A0-\\u30FF]+$")
                && jTextFieldOrderTel.getText().matches("(^(\\d{3,4})(\\d{3,4})(\\d{3,4})$)")) {
            control.showSearchCustomer(jTextFieldOrderName.getText(), jTextFieldOrderTel.getText());
            control.showEntryCustomer(jTextFieldOrderName.getText(), jTextFieldOrderTel.getText());
        } else if (jTextFieldOrderTel.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "[電話番号]を入力してください", "入力エラー",
                    JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldOrderName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "[名前]を入力してください", "入力エラー",
                    JOptionPane.ERROR_MESSAGE);
        } else if (!jTextFieldOrderName.getText().matches("^[\\u30A0-\\u30FF]+$")) {
            JOptionPane.showMessageDialog(this, "名前はカナ文字で入力してください", "入力エラー", JOptionPane.ERROR_MESSAGE);
        } else if (!jTextFieldOrderTel.getText().matches("(^(\\d{3,4})(\\d{3,4})(\\d{3,4})$)")) {
            JOptionPane.showMessageDialog(this, "電話番号の書式が違います", "入力エラー", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonCheckCustomerActionPerformed

    private void jButtonOrderEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderEndActionPerformed
        // TODO add your handling code here:
        int value = JOptionPane.showConfirmDialog(this, "注文を続けますか？" + "\n" + "「いいえ」の場合、システムメニューへ移動します",
                 "確認メッセージ", JOptionPane.YES_NO_CANCEL_OPTION);

        if (value == JOptionPane.YES_OPTION) {
            setClearCcomponent();
            control.allNew();
        } else if (value == JOptionPane.NO_OPTION) {
            setClearCcomponent();
            control.allNew();
            control.showSystem();
        }
    }//GEN-LAST:event_jButtonOrderEndActionPerformed

    private void jButtonOrderInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderInputActionPerformed
        // TODO add your handling code here:
        try {
            if (jComboBoxItem.getSelectedIndex() < 17) {
                String inputData = createAddData(jComboBoxItem.getSelectedItem().toString());
//                System.out.println("InputData = " + inputData);
                String item = jComboBoxItem.getSelectedItem().toString();
                int addAmount = Integer.parseInt(jComboBoxAmount.getSelectedItem().toString());
                int i;

                if (jTableOrderDetail.getRowCount() <= 0) {                   
                    if (item.equals("トリピミックス")) {                       
                        showOptionDialog();
                    } else {
                        control.addOrderItemData(inputData);
                    }
                    showSubtotalPrice();
                    showTotalPrice();
                } else if (jTableOrderDetail.getRowCount() >= 1) {
                    for (i = 0; i < jTableOrderDetail.getRowCount(); i++) {
                        String poItem = jTableOrderDetail.getValueAt(i, 0).toString();
//                        System.out.println(poItem);
                        if (item.equals(poItem)) {
                            int amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, 1).toString());
                            if (amount > 0) {
                                if (item.equals("トリピミックス")) {                                    
                                    showOptionDialog();
                                } else {
                                    jTableOrderDetail.setValueAt(amount + addAmount, i, 1);
                                }
                                amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, 1).toString());
                                int price = getItemPrice(poItem, amount);
                                jTableOrderDetail.setValueAt(price, i, 2);
                                showSubtotalPrice();
                                showTotalPrice();
                                break;
                            }
                        }
                    }
                    if (i == jTableOrderDetail.getRowCount()) {                   
                        if (item.equals("トリピミックス")) {                         
                            showOptionDialog();
                        } else {
                            control.addOrderItemData(inputData);
                        }
                        showSubtotalPrice();
                        showTotalPrice();
                    }
                }
            } else if (jComboBoxItem.getSelectedItem().toString().equals("(S)トリピミックス")) {
                if (Integer.parseInt(jLabelOrderPoint.getText().toString()) >= 40) {
                    String inputData = createAddData(jComboBoxItem.getSelectedItem().toString());
//                    System.out.println("InputData = " + inputData);
                    String item = jComboBoxItem.getSelectedItem().toString();
                    int addAmount = Integer.parseInt(jComboBoxAmount.getSelectedItem().toString());
                    int i;

                    if (jTableOrderDetail.getRowCount() <= 0) {                      
                        showOptionDialog();
                        showSubtotalPrice();
                        showTotalPrice();
                    } else if (jTableOrderDetail.getRowCount() >= 1) {
                        for (i = 0; i < jTableOrderDetail.getRowCount(); i++) {
                            String poItem = jTableOrderDetail.getValueAt(i, 0).toString();
//                            System.out.println(poItem);
                            if (item.equals(poItem)) {
                                int amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, 1).toString());
                                if (amount > 0) {
                                    if (item.equals("(S)トリピミックス")) {                                    
                                        showOptionDialog();
                                    } else {
                                        jTableOrderDetail.setValueAt(amount + addAmount, i, 1);
                                    }
                                    amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, 1).toString());
                                    int price = getItemPrice(poItem, amount);
                                    jTableOrderDetail.setValueAt(price, i, 2);
                                    showSubtotalPrice();
                                    showTotalPrice();
                                    break;
                                }
                            }
                        }
                        if (i == jTableOrderDetail.getRowCount()) {                        
                            showOptionDialog();
                            showSubtotalPrice();
                            showTotalPrice();
                        }
                    }
                }
            } else if (jComboBoxItem.getSelectedItem().toString().equals("(S)ミニチキン")
                    || jComboBoxItem.getSelectedItem().toString().equals("(S)ミニピザ")) {
                if (Integer.parseInt(jLabelOrderPoint.getText().toString()) >= 25) {
                    String inputData = createAddData(jComboBoxItem.getSelectedItem().toString());
//                    System.out.println("InputData = " + inputData);
                    String item = jComboBoxItem.getSelectedItem().toString();
                    int addAmount = Integer.parseInt(jComboBoxAmount.getSelectedItem().toString());
                    int i;

                    if (jTableOrderDetail.getRowCount() <= 0) {
                        control.addOrderItemData(inputData);
                        showSubtotalPrice();
                        showTotalPrice();
                    } else if (jTableOrderDetail.getRowCount() >= 1) {
                        for (i = 0; i < jTableOrderDetail.getRowCount(); i++) {
                            String poItem = jTableOrderDetail.getValueAt(i, 0).toString();
//                            System.out.println(poItem);
                            if (item.equals(poItem)) {
                                int amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, 1).toString());
                                if (amount > 0) {
                                    jTableOrderDetail.setValueAt(amount + addAmount, i, 1);
                                    amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, 1).toString());
                                    int price = getItemPrice(poItem, amount);
                                    jTableOrderDetail.setValueAt(price, i, 2);
                                    showSubtotalPrice();
                                    showTotalPrice();
                                    break;
                                }
                            }
                        }
                        if (i == jTableOrderDetail.getRowCount()) {
                            control.addOrderItemData(inputData);
                            showSubtotalPrice();
                            showTotalPrice();
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonOrderInputActionPerformed

    private void jButtonOrderCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderCancelActionPerformed
        // TODO add your handling code here:
        try {
            int row = jTableOrderDetail.getSelectedRow();

            orderTableModel.removeRow(row);
            showSubtotalPrice();
            showTotalPrice();
            if (jTableOrderDetail.getRowCount() == 0) {
                jLabelSubtotalPrice.setText("");
                jLabelTotalPrice.setText("");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "注文が選択されていません", "注文取消エラー", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonOrderCancelActionPerformed

    private void jButtonPointDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPointDiscountActionPerformed
        // TODO add your handling code here:
        try {
            int totalPrice = Integer.parseInt(jLabelTotalPrice.getText());
            String itemName = "10%割引";

            if (Integer.parseInt(jLabelOrderPoint.getText().toString()) >= 10) {
                totalPrice *= 0.9;
                jLabelTotalPrice.setText(Integer.toString(totalPrice));

                String inputData = createAddData(itemName);

//                System.out.println("InputData = " + inputData);
                control.addOrderItemData(inputData);
                showTotalPrice();
                jButtonPointDiscount.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "ポイントが不足しています", "ポイント不足", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "注文がありません", "注文エラー", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonPointDiscountActionPerformed

    private void jButtonOrderslipPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderslipPrintActionPerformed
        //TODO add your handling code here:
        if (!jTextFieldDeliveryDate.getText().equals("")
                && !jLabelTotalPrice.getText().equals("")
                && jButtonOrderDetailFinal.isEnabled() == false) {
            String name = jTextFieldOrderName.getText();
            String tel = jTextFieldOrderTel.getText();
            int point = Integer.parseInt(jLabelOrderPoint.getText());
            int grantPoint = 0;
            int subTotalPrice = Integer.parseInt(jLabelSubtotalPrice.getText());
            int totalPrice = Integer.parseInt(jLabelTotalPrice.getText());
            String[] str = jTextFieldDeliveryDate.getText().split("-");
            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            int hour = Integer.parseInt(jComboBoxHour.getSelectedItem().toString());
            int minutes = Integer.parseInt(jComboBoxMinute.getSelectedItem().toString());
            Timestamp deliveryDate = Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minutes, 0));
//            System.out.println(deliveryDate);
            int row = 0;
            int column = 1;
            for (int i = row; i < jTableOrderDetail.getRowCount(); i++) {
                String itemName = jTableOrderDetail.getValueAt(i, 0).toString();
                int amount = Integer.parseInt(jTableOrderDetail.getValueAt(i, column).toString());
                if (itemName.equals("(S)トリピミックス")) {
                    setUsingPoint(40, amount);
                } else if (itemName.equals("(S)ミニチキン")
                        || itemName.equals("(S)ミニピザ")) {
                    setUsingPoint(25, amount);
                } else if (itemName.equals("10%割引")) {
                    setUsingPoint(10);
                }
            }
            System.out.println(getUsingPoint());
            
            int value = JOptionPane.showConfirmDialog(this, "注文を確定し、注文伝票印刷を印刷しますか？" + "\n"
                    + "注文を確かめた上で「はい」を押してください", "確認メッセージ", JOptionPane.YES_NO_OPTION);
            
            if (value == JOptionPane.YES_OPTION && usedPoint(getUsingPoint()) <= point) {              
                if (jButtonCheckCustomer.isEnabled() == true) {
                    control.insertNewOrder(name, tel, totalPrice, deliveryDate, usedPoint(getUsingPoint()));
                } else {
                    control.insertNewCustomerOrder(name, tel, totalPrice, deliveryDate, usedPoint(getUsingPoint()));
                }
                for (int i = 0; i < orderTableModel.getRowCount(); i++) {
                    int orderCount = Integer.parseInt(orderTableModel.getValueAt(i, 1).toString());
                    int orderPrice = Integer.parseInt(orderTableModel.getValueAt(i, 2).toString());
                    String itemId = control.getItemId(orderTableModel.getValueAt(i, 0).toString());
                    control.insertOrderDetail(itemId, orderCount, orderPrice);
                }
                control.insertOrderSlip();
                control.updatePointAfterUsing(name, tel);
                if (Integer.parseInt(jLabelTotalPrice.getText()) >= 2000) {
                    if (deliveryDate.compareTo(Timestamp.valueOf(LocalDateTime.of(year, month, day, 11, 20, 0))) > 0
                            || deliveryDate.compareTo(Timestamp.valueOf(LocalDateTime.of(year, month, day, 12, 40, 0))) < 0
                            || deliveryDate.compareTo(Timestamp.valueOf(LocalDateTime.of(year, month, day, 17, 20, 0))) > 0
                            || deliveryDate.compareTo(Timestamp.valueOf(LocalDateTime.of(year, month, day, 19, 40, 0))) < 0) {
                        grantPoint = 5;
                        control.updateCustomerPointGrant(name, tel, grantPoint);
                        control.setOrderPriceInformationSlip(getUsingPoint(), subTotalPrice, totalPrice, grantPoint);
                    } else {
                        grantPoint = 10;
                        control.updateCustomerPointGrant(name, tel, grantPoint);
                        control.setOrderPriceInformationSlip(getUsingPoint(), subTotalPrice, totalPrice, grantPoint);
                    }
                }
                control.setOrderPriceInformationSlip(getUsingPoint(), subTotalPrice, totalPrice, grantPoint);
                if (jButtonCheckCustomer.isEnabled() == true) {
                    control.searchOrderCustomerInformation(name, tel);
                } else {
                    control.searchEntryCustomerInformation(name, tel);
                }
                control.showOrderCSlip();
                jButtonOrderslipPrint.setEnabled(false);
            } else if (value == JOptionPane.YES_OPTION  && usedPoint(getUsingPoint()) > point) {
                JOptionPane.showMessageDialog(this, "注文に必要なポイントが足りません", "ポイント不足", JOptionPane.ERROR_MESSAGE);
                jButtonOrderInput.setEnabled(true);
                jButtonOrderCancel.setEnabled(true);
                jScrollPane1.setEnabled(true);
                jTableOrderDetail.setEnabled(true);
                jButtonPointDiscount.setEnabled(false);
                jButtonOrderDetailFinal.setEnabled(true);
                for (int i = 0; i < jTableOrderDetail.getRowCount(); i++) {
                    String rowItemName = jTableOrderDetail.getValueAt(i, 0).toString();
                    if (rowItemName.equals("10%割引")) {
                        orderTableModel.removeRow(i);
                    }
                }
                setUsingPoint(-getUsingPoint());
            }
        } else if (jLabelTotalPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "注文内容がありません", "入力エラー", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldDeliveryDate.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "配達希望日時を入力してください", "入力エラー", JOptionPane.ERROR_MESSAGE);
        } else if (jButtonOrderDetailFinal.isEnabled() == true) {
            JOptionPane.showMessageDialog(this, "注文明細を確定してください", "注文エラー", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonOrderslipPrintActionPerformed

    private void jButtonOrderDetailFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrderDetailFinalActionPerformed
        // TODO add your handling code here:
        int value = JOptionPane.showConfirmDialog(this, "注文明細を確定しますか？", "明細確定確認", JOptionPane.YES_NO_OPTION);

        if (value == JOptionPane.YES_OPTION) {
            jButtonOrderInput.setEnabled(false);
            jButtonOrderCancel.setEnabled(false);
            jButtonOrderDetailFinal.setEnabled(false);
            jScrollPane1.setEnabled(false);
            jTableOrderDetail.setEnabled(false);
            jButtonPointDiscount.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonOrderDetailFinalActionPerformed

    public void setEnabled() {
        jComboBoxItem.setEnabled(true);
        jComboBoxAmount.setEnabled(true);
        jButtonOrderInput.setEnabled(true);
        jTableOrderDetail.setEnabled(true);
        jButtonOrderCancel.setEnabled(true);
        jTextFieldDeliveryDate.setEnabled(true);
        jComboBoxHour.setEnabled(true);
        jComboBoxMinute.setEnabled(true);
        jButtonOrderDetailFinal.setEnabled(true);
        jButtonOrderslipPrint.setEnabled(true);
        jTextFieldOrderName.setEnabled(false);
        jTextFieldOrderTel.setEnabled(false);
    }

    public void setClearCcomponent() {
        jTextFieldOrderName.setText("");
        jTextFieldOrderName.setEnabled(true);
        jTextFieldOrderTel.setText("");
        jTextFieldOrderTel.setEnabled(true);
        jTextFieldDeliveryDate.setText("");
        jTextFieldDeliveryDate.setEnabled(false);
        orderTableModel.setRowCount(0);
        jLabelOrderPoint.setText("");
        jLabelSubtotalPrice.setText("");
        jLabelTotalPrice.setText("");
        jComboBoxItem.setSelectedIndex(0);
        jComboBoxItem.setEnabled(false);
        jComboBoxAmount.setSelectedIndex(0);
        jComboBoxAmount.setEnabled(false);
        jComboBoxHour.setSelectedIndex(0);
        jComboBoxHour.setEnabled(false);
        jComboBoxMinute.setSelectedIndex(0);
        jComboBoxMinute.setEnabled(false);
        jButtonCheckCustomer.setEnabled(true);
        jButtonOrderInput.setEnabled(false);
        jButtonOrderCancel.setEnabled(false);
        jButtonOrderslipPrint.setEnabled(false);
        jButtonPointDiscount.setEnabled(false);
        setUsingPoint(-getUsingPoint());
    }

    public void showPoint(int point) {
        jLabelOrderPoint.setText(Integer.toString(point));
    }

    public int usedPoint(int usingPoint) {
        this.usingPoint = usingPoint;
        System.out.println(usingPoint);
        return usingPoint;
    }

    public int getPointAfterUsing() {
        int afterPoint = 0;
        int currentPoint = Integer.parseInt(jLabelOrderPoint.getText());
        afterPoint = currentPoint - usedPoint(getUsingPoint());
        return afterPoint;
    }

    public String createAddData(String name) {
        String itemName = name;
        int itemAmount = Integer.parseInt(jComboBoxAmount.getSelectedItem().toString());
        int itemPrice = getItemPrice(itemName, itemAmount);
        String itemId = control.getItemId(itemName);
        String inputData = itemName + ", " + itemAmount + ", " + itemPrice + ", " + itemId;
        return inputData;
    }

    public int getItemPrice(String itemName, int itemAmount) {
        int itemPrice = 0;
        itemPrice = control.setItemPrice(itemName);
        itemPrice *= itemAmount;
        return itemPrice;
    }

    public int getTotalPrice() {
        int totalPrice = Integer.parseInt(jLabelTotalPrice.getText());
        return totalPrice;
    }

    public void addOrderTableModel(String[] rowData) {
        orderTableModel.addRow(rowData);
    }

    public void showSubtotalPrice() {
        int subTotalPrice = 0;
        for (int row = 0; row <= jTableOrderDetail.getRowCount() - 1; row++) {
            for (int column = 0; column <= jTableOrderDetail.getColumnCount() - 1; column++) {
                if (column == 2) {
                    subTotalPrice += Integer.parseInt(jTableOrderDetail.getValueAt(row, column).toString());
                }
            }
        }
        jLabelSubtotalPrice.setText(Integer.toString(subTotalPrice));
    }

    public void showTotalPrice() {
        int totalPrice = 0;
        for (int row = 0; row <= jTableOrderDetail.getRowCount() - 1; row++) {
            for (int column = 0; column <= jTableOrderDetail.getColumnCount() - 1; column++) {
                if (column == 2) {
                    totalPrice += Integer.parseInt(jTableOrderDetail.getValueAt(row, column).toString());
                }
            }
        }
        jLabelTotalPrice.setText(Integer.toString(totalPrice));
    }

    public void showDeliveryDate() {
        LocalDateTime today = LocalDateTime.now();
        jTextFieldDeliveryDate.setText(today.toLocalDate().toString());
    }

    public Timestamp getOrderDates() {
        Timestamp orderDates = Timestamp.valueOf(LocalDateTime.now());
        return orderDates;
    }

    public void CheckCustomer() {
        jButtonCheckCustomer.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BoundaryOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoundaryOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoundaryOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoundaryOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoundaryOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCheckCustomer;
    private javax.swing.JButton jButtonOrderCancel;
    private javax.swing.JButton jButtonOrderDetailFinal;
    private javax.swing.JButton jButtonOrderEnd;
    private javax.swing.JButton jButtonOrderInput;
    private javax.swing.JButton jButtonOrderslipPrint;
    private javax.swing.JButton jButtonPointDiscount;
    private javax.swing.JComboBox<String> jComboBoxAmount;
    private javax.swing.JComboBox<String> jComboBoxHour;
    private javax.swing.JComboBox<String> jComboBoxItem;
    private javax.swing.JComboBox<String> jComboBoxMinute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelOrderPoint;
    private javax.swing.JLabel jLabelSubtotalPrice;
    private javax.swing.JLabel jLabelTotalPrice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOrderDetail;
    private javax.swing.JTextField jTextFieldDeliveryDate;
    private javax.swing.JTextField jTextFieldOrderName;
    private javax.swing.JTextField jTextFieldOrderTel;
    // End of variables declaration//GEN-END:variables
}
