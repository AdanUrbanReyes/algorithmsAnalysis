import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.LinkedList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
public class Index extends JFrame implements ActionListener{
	private LinkedList <Coin> coinsOfPay;
	private LinkedList <Coin> coinsInBox;
	private MoneyBox moneyBox;
	private MoneyBox formPayClient;
	private JTextField amountToPay;
	private JButton pay;
	private int cash;
	public Index(){
		super("cash");
		setSize(1080,720);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addCoins();
		addCobroBox();
		addFormPayClient();
		addButtonCobrar();
		setVisible(true);
	}
	public void addCoins(){
		coinsInBox=new LinkedList<Coin>();
		coinsInBox.add(new Coin(1000,1));
		coinsInBox.add(new Coin(500,2));
		coinsInBox.add(new Coin(200,3));
		coinsInBox.add(new Coin(100,4));
		coinsInBox.add(new Coin(50,7));
		coinsInBox.add(new Coin(20,5));
		coinsInBox.add(new Coin(10,10));
		coinsInBox.add(new Coin(5,20));
		coinsInBox.add(new Coin(2,20));
		coinsInBox.add(new Coin(1,30));
		
		moneyBox=new MoneyBox();
		int i,size=coinsInBox.size();
		for(i=0;i<size;i++){
			moneyBox.addCoin(coinsInBox.get(i));
		}
		moneyBox.scroll.setBounds(0,0,250,600);
		add(moneyBox.scroll);
	}	
	public void addCobroBox(){
		JLabel label=new JLabel("ingrese la cantidad a cobrar:");
		label.setFont(new Font("Serif",Font.BOLD,18));
		label.setBounds(250,0,300,30);
		add(label);
		amountToPay=new JTextField(11);
		amountToPay.setText("0");
		amountToPay.setBounds(250,30,100,30);	
		add(amountToPay);	
	}
	public void addFormPayClient(){
		coinsOfPay=new LinkedList<Coin>();
		coinsOfPay.add(new Coin(1000,0));
		coinsOfPay.add(new Coin(500,0));
		coinsOfPay.add(new Coin(200,0));
		coinsOfPay.add(new Coin(100,0));
		coinsOfPay.add(new Coin(50,0));
		coinsOfPay.add(new Coin(20,0));
		coinsOfPay.add(new Coin(10,0));
		coinsOfPay.add(new Coin(5,0));
		coinsOfPay.add(new Coin(2,0));
		coinsOfPay.add(new Coin(1,0));
		formPayClient=new MoneyBox();
		int i,size=coinsOfPay.size();
		for(i=0;i<size;i++){
			formPayClient.addFormPay(coinsOfPay.get(i));
		}
		formPayClient.scroll.setBounds(550,0,250,600);
		add(formPayClient.scroll);	
	}
	public void addButtonCobrar(){
		pay=new JButton("cobrar");
		pay.addActionListener(this);
		pay.setBounds(300,500,100,30);
		add(pay);
	}
	public int getTotal(LinkedList <Coin> list){
		int total=0,i,size=list.size();
		for(i=0;i<size;i++){
			total+=list.get(i).getValue()*list.get(i).getAmountFieldInteger();
		}
		return total;
	}
	public void resetCoinsClient(){
		amountToPay.setText("0");
		int i,size=coinsOfPay.size();
		for(i=0;i<size;i++){
			coinsOfPay.get(i).setAmount(0);
		}
	}
	public void passCoins(LinkedList <Coin> coinsOfPay,LinkedList <Coin> coinsInBox){
		int i,j,si=coinsOfPay.size(),sj=coinsInBox.size();
		for(i=0;i<si;i++){
			if(coinsOfPay.get(i).getAmountFieldInteger()==0)
				continue;
			for(j=0;j<sj;j++){
				if(coinsInBox.get(j).getValue()==coinsOfPay.get(i).getValue()){
					coinsInBox.get(j).setAmount(coinsInBox.get(j).getAmount()+coinsOfPay.get(i).getAmountFieldInteger());
				}
			}
		}
	}
	public Coin getMoreCercano(LinkedList<Coin> coinsInBox){
		int diferencia=17311;
		Coin best=new Coin(0,1);
		int i,size=coinsInBox.size();
		for(i=0;i<size;i++){
			if(coinsInBox.get(i).getValue()>cash)
				continue;
      	if(cash-coinsInBox.get(i).getValue() < diferencia){
				diferencia=cash-coinsInBox.get(i).getValue();
				best.setValue(coinsInBox.get(i).getValue());
			}
		}
		//System.out.print("best.value="+best.getValue()+"\n");
		cash-=best.getValue();	
		return best;
	}
	public void printList(LinkedList <Coin> list){
		int i,size=list.size();
		for(i=0;i<size;i++){
			System.out.print(""+list.get(i).getValue()+"\t"+list.get(i).getAmount()+"\n");
		}
	}
	public void retiraCoinsOfBox(LinkedList <Coin> coinsRetiradas){
		int i,j,si=coinsRetiradas.size(),sj=coinsInBox.size();
		for(i=0;i<si;i++){
			for(j=0;j<sj;j++){
				if(coinsInBox.get(j).getValue()==coinsRetiradas.get(i).getValue())
					coinsInBox.get(j).setAmount(coinsInBox.get(j).getAmount()-coinsRetiradas.get(i).getAmount());
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e){
		int moneyTotalClient=getTotal(coinsOfPay);
		passCoins(coinsOfPay,coinsInBox);	
		cash=moneyTotalClient-Integer.parseInt(amountToPay.getText());
		Coin c;
		LinkedList <Coin>coinsRetiradas=new LinkedList<Coin>();
		while(cash>0){
			c=getMoreCercano(coinsInBox);
			coinsRetiradas.add(c);
		}
		retiraCoinsOfBox(coinsRetiradas);
		resetCoinsClient();
	}
	public static void main(String []args){
		Index index=new Index();
	}
}
