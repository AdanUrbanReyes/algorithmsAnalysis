import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Font;
public class MoneyBox extends JPanel{
	private static final String path="./imagens/";
	public JScrollPane scroll;
	public MoneyBox(){//numberCoins is number of villetes or monedas that will to mostrar in panel
		super(new GridLayout(0,2));
		scroll=new JScrollPane(this);
	}
	public void addCoin(Coin coin){
		addImage(path+""+coin.getValue()+".jpg",150,80);		
		addAmountOfCoin(coin);
	}
	public void addFormPay(Coin coin){
		addImage(path+""+coin.getValue()+".jpg",150,80);		
		this.add(coin.getAmountField());
		this.revalidate();
	}
	public void addImage(String route,int width,int height){
		Icon icon=new ImageIcon(new ImageIcon(route).getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		this.add(new JLabel(icon));
		this.revalidate();
	}
	public void addAmountOfCoin(Coin coin){
		/*JLabel label=new JLabel(" * ");
		label.setFont(new Font("Serif",Font.BOLD,31));
		this.add(label);	//add signo of by e.g 50 * 2 (2 villetes of 50 pons)
		this.revalidate();*/
		//label.setBackground(java.awt.Color.yellow);
		this.add(coin.getAmountLabel());
		this.revalidate();
	}
}
