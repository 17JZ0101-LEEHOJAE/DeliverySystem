package system;

import dbDelivery.ControlOrder;
import dbDelivery.ControlOrderView;

/**
 * システムコントロール
 * @author 17jz0101 Lee Ho Jae
 */
public class ControlSystem {
    private BoundarySystemMenu  boundary;         // メインメニュー
    private ControlOrder        controlOrder;
    private ControlOrderView    controlOrderView;
    
    public ControlSystem() {
        boundary          = new BoundarySystemMenu();               // メインメニュー生成
        controlOrder      = new ControlOrder();
        controlOrderView  = new ControlOrderView();
    }
    
    public void setVisible() {
        boundary.setVisible(true);
        boundary.setControl(this);
    }
    
    public void start() {
        boundary.setControl(this);
        boundary.setVisible(true);
        controlOrder.setControlSystem(this);
        controlOrderView.setControlSystem(this);
    }
    
    public void showOrder() {
        boundary.setVisible(false);
        controlOrder.start();
    }
    
    public void showOrderView() {
        boundary.setVisible(false);
        controlOrderView.start();
    }
    
    public void systemExit() {
        System.exit(0);
    }
    
    /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new ControlSystem().start();  // コントロールをnewし、start()メソッドを呼び出す
    }       
}
