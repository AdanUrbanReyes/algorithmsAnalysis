package modelo;
public class Root{
	private char bit;
	private int amount;
	private Root left,right;
	public Root(char bit,int amount,Root left,Root right){
		this.bit=bit;
		this.amount=amount;
		this.left=left;
		this.right=right;
	}
	public char getBit(){
		return bit;
	}
	public int getAmount(){
		return amount;
	}
	public Root getLeft(){
		return left;
	}
	public Root getRight(){
		return right;
	}
	public void setBit(char bit){
		this.bit=bit;
	}
	public void setAmount(int amount){
		this.amount=amount;
	}
	public void setLeft(Root left){
		this.left=left;
	}
	public void setRight(Root right){
		this.right=right;
	}
}
