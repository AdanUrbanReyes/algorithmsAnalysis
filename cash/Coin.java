import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
public class Coin{
	private int value;
	private int amount;
	private JLabel amountLabel;
	private JTextField amountField;
	public Coin(int value,int amount){
		this.value=value;
		this.amount=amount;
		amountLabel=new JLabel(""+amount);
		amountField=new JTextField(11);
		amountField.setText(""+amount);
		amountLabel.setFont(new Font("Serif",Font.BOLD,31));
		amountLabel.setForeground(java.awt.Color.blue);
		amountField.setFont(new Font("Serif",Font.BOLD,31));
		amountField.setForeground(java.awt.Color.blue);
	}
	public int getValue(){
		return value;
	}
	public int getAmount(){
		return amount;
	}
	public JLabel getAmountLabel(){
		return amountLabel;
	}
	public int getAmountFieldInteger(){
		return Integer.parseInt(amountField.getText());
	}
	public JTextField getAmountField(){
		return amountField;
	}
	public void setValue(int value){
		this.value=value;
	}
	public void setAmount(int amount){
		this.amount=amount;
		amountLabel.setText(""+amount);
		amountField.setText(""+amount);
	}
}
