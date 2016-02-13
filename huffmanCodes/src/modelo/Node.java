package modelo;
public class Node extends Root{
	private char character;
	private String representation;
	public Node(char bit,int amount,Root left,Root right,char character) {
		super(bit,amount,left,right);
		this.character = character;
	}
	public char getBit(){
		return super.getBit();
	}
	public int getAmount(){
		return super.getAmount();
	}
	public Root getLeft(){
		return super.getLeft();
	}
	public Root getRight(){
		return super.getRight();
	}
	public char getCharacter(){
		return character;
	}
	public String getRepresentation(){
		return representation;
	}
	public void setBit(char bit){
		super.setBit(bit);
	}
	public void setAmount(int amount) {
		super.setAmount(amount);
	}
	public void setLeft(Root left){
		super.setLeft(left);
	}
	public void setRigth(Root right){
		super.setRight(right);
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	public void setRepresentation(String representation){
		this.representation=representation;
	}
}
