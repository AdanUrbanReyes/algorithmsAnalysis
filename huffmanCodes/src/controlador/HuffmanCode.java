/*
	javac -d bin -cp src controlador/*.java modelo/*.java
	java -cp bin controlador.HuffmanCode
*/
package controlador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import modelo.Node;
import modelo.Root;
public class HuffmanCode{
	public static File selectFile(){
		File fileSelected=null;
		JFileChooser selector= new JFileChooser(".");
		selector.setMultiSelectionEnabled(false);
		if(selector.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
			fileSelected=selector.getSelectedFile();
		return fileSelected;
	}
	public static void printWithFormat(String string,int tope){
		int i=tope-string.length();
		if(string.equals("\n")){
			System.out.print("\\n");
			i--;
		}else{
			if(string.equals(" ")){
				System.out.print("blank space");
				i-=11;
			}else{
				System.out.print(string);
			}
		}
		while((i--)>0){
			System.out.print(" ");
		}
	}
	public static void printOut(LinkedList <Node> list){
		int i,size=list.size();
		Node node;
		printWithFormat("Character",37);
		printWithFormat("Frequency",13);
		printWithFormat("Code",73);
		System.out.print("\n");
		for(i=0;i<size;i++){
			node=list.get(i);
			printWithFormat(""+node.getCharacter(),37);
			printWithFormat(""+node.getAmount(),13);
			printWithFormat(""+node.getRepresentation(),73);
			System.out.print("\n");
		}
	}
	public static void printNodesList(LinkedList <Node> list){
		int i,size=list.size();
		Node node;
		for(i=0;i<size;i++){
			node=list.get(i);
			System.out.print("["+node.getLeft()+","+node.getCharacter()+","+node.getBit()+","+node.getAmount()+","+node.getRepresentation()+","+node.getRight()+"]\n");
		}
	}
	public static String getRepresentationFromCharacter(LinkedList <Node> nodes,char character){
		int i,size=nodes.size();
		for(i=0;i<size;i++){
				if(nodes.get(i).getCharacter()==character){
					return nodes.get(i).getRepresentation();
				}
		}
		return null; 
	}
	public static char getCharacterFromRepresentation(LinkedList <Node> nodes,String representation){
		int i,size=nodes.size();
		for(i=0;i<size;i++){
				if(nodes.get(i).getRepresentation().equals(representation)){
					return nodes.get(i).getCharacter();
				}
		}
		return '\r'; 
	}
	public static boolean createOutFileHuffmanCode(File huffmanCode,String nameOutFile,LinkedList<Node> nodes){
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(huffmanCode.getAbsolutePath()),"utf-8"));
			BufferedOutputStream writer=new BufferedOutputStream(new FileOutputStream("./"+nameOutFile));
			int character;
			String representation="";
			while((character=reader.read())!=-1){
				if((char)character!='\r'){
					representation=getRepresentationFromCharacter(nodes,(char)character);
					writer.write(representation.getBytes(),0,representation.length());
					writer.flush();
				}
			}
			return true;
		}catch(UnsupportedEncodingException uee){
			System.out.print(""+uee.toString()+"\n");
		}catch(FileNotFoundException fnfe){
			System.out.print(""+fnfe.toString()+"\n");
		}catch(IOException ioe){
			System.out.print(""+ioe.toString()+"\n");
		}
		return false;
	}
	public static boolean readHuffmanCodeAndCreateOutFileHuffmanCodeDecifrado(LinkedList <Node> nodes,File huffmanCode,String nameOutFile){
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(huffmanCode.getAbsolutePath()),"utf-8"));
			BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./"+nameOutFile),"utf-8"));
			int character;
			String codeCarrier="",characterFromRepresentation;
			while((character=reader.read())!=-1){
				codeCarrier+=""+(char)character;
				characterFromRepresentation=""+getCharacterFromRepresentation(nodes,codeCarrier);
				if(!characterFromRepresentation.equals("\r")){
					writer.write(characterFromRepresentation,0,characterFromRepresentation.length());
					writer.flush();
					codeCarrier="";
				}
			}
			return true;
		}catch(UnsupportedEncodingException uee){
			System.out.print(""+uee.toString()+"\n");
		}catch(FileNotFoundException fnfe){
			System.out.print(""+fnfe.toString()+"\n");
		}catch(IOException ioe){
			System.out.print(""+ioe.toString()+"\n");
		}
		return false;
	}
	public static void orderNodeListByAmount(LinkedList <Node> list){
		int i,j,size=list.size();
		Node nodeI,nodeJ;
		for(i=0;i<size;i++){
			nodeI=list.get(i);
			for(j=i;j<size;j++,nodeI=list.get(i)){
				nodeJ=list.get(j);
				if(nodeJ.getAmount()<nodeI.getAmount()){
					list.set(j,list.set(i,nodeJ));
				}
			}
		}
	}
	public static void addCharacterToNodeList(LinkedList <Node>list,char character){
		int i,size=list.size();
		Node node;
		for(i=0;i<size;i++){
			node=list.get(i);
			if(node.getCharacter()==character){
				node.setAmount(node.getAmount()+1);
				return;
			}
		}
		list.add(new Node((char)48,1,null,null,character));
	}
	public static LinkedList<Node> readHuffmanCode(File huffmanCode) {
		LinkedList <Node> nodes=new LinkedList<Node>();
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(huffmanCode.getAbsolutePath()),"utf-8"));
			int character;
			while((character=reader.read())!=-1){
				if((char)character!='\r'){
					addCharacterToNodeList(nodes,(char)character);
				}
				//System.out.print(""+(char)character);
			}
		}catch(UnsupportedEncodingException uee){
			System.out.print(""+uee.toString()+"\n");
		}catch(FileNotFoundException fnfe){
			System.out.print(""+fnfe.toString()+"\n");
		}catch(IOException ioe){
			System.out.print(""+ioe.toString()+"\n");
		}
		return nodes;
	}
	public static LinkedList<Object> get2Lest(LinkedList <Node> nodes,LinkedList<Root> roots){//this function take in cuenta that two list was order lower to upper
		LinkedList <Object> lest=new LinkedList <Object>();
		while(lest.size()<2){
			if(nodes.size()>0){
				if(roots.size()>0){
					if(roots.get(0).getAmount()<nodes.get(0).getAmount()){
						lest.add(roots.remove(0));
					}else{
						lest.add(nodes.remove(0));
					}
				}else{
					lest.add(nodes.remove(0));
				}
			}else{
				if(roots.size()>0){
					lest.add(roots.remove(0));
				}else{
					return null;
				}
			}
		}
		return lest;
	}

/*all down was in class Tree*/

	public static Root addNodesToTree(Root nodeLeft,Root nodeRight){//return root created 
		Root root=new Root((char)48,nodeLeft.getAmount()+nodeRight.getAmount(),nodeLeft,nodeRight);
		if(nodeLeft.getAmount()<nodeRight.getAmount()){
			nodeLeft.setBit((char)48);
			nodeRight.setBit((char)49);
		}else{
			nodeLeft.setBit((char)49);
			nodeRight.setBit((char)48);
		}
		return root;
	}
	public static void printPre (Root root){
		if (root != null){
			if(root instanceof Node){
				Node node=(Node)root;
				System.out.print("["+node.getCharacter()+","+node.getBit()+","+node.getAmount()+"]\n");
			}
			else
				System.out.print("["+root.getBit()+","+root.getAmount()+"]\n");
			printPre (root.getLeft());
			printPre (root.getRight());
		}
	}
	public static String search (Root root,String carrier,char character){
		if(root==null){
			return "";
		}
		carrier+=""+root.getBit();
		if(root instanceof Node){
			Node node=(Node)root;
			if(node.getCharacter()==character){
				return carrier;
			}
		}
		return ""+search(root.getLeft(),carrier,character)+search(root.getRight(),carrier,character);
	}
/*all up was in class Tree*/
	public static LinkedList<Node> cloneListNodes(LinkedList <Node> nodes){//this method can ahorrar with method of LinkedList clone but i dont know use this INVESTIGARLO
		LinkedList <Node> clonListNodes=new LinkedList <Node>();
		int i,size=nodes.size();
		for(i=0;i<size;i++){
			clonListNodes.add(nodes.get(i));
		}
		return clonListNodes;
	}
	public static Root buildTree(LinkedList <Node> nodes){
		LinkedList <Root> roots=new LinkedList<Root>();
		LinkedList <Object> lest;
		while(nodes.size()>0 || roots.size()!=1 ){
			lest=get2Lest(nodes,roots);
			roots.add(addNodesToTree((Root)lest.get(0),(Root)lest.get(1)));
		}
//	printPre(roots.get(0));
		return roots.get(0);
	}
	public static void setRepresentation(Root rootTrees,LinkedList <Node> nodes){
		int i,size=nodes.size();
		for(i=0;i<size;i++){
			nodes.get(i).setRepresentation(search(rootTrees,"",nodes.get(i).getCharacter()).substring(1));
		}
	}
	public static void main(String []args){
		File inputText=selectFile();
		LinkedList <Node> nodes=readHuffmanCode(inputText);
		Root rootTrees;
		orderNodeListByAmount(nodes);
		//System.out.print("list of nodes before of buildtree\n");
		//printNodesList(nodes);
		rootTrees=buildTree(cloneListNodes(nodes));
		//System.out.print("\tlist of nodes after of buildtree\n");
		//printNodesList(nodes);
		setRepresentation(rootTrees,nodes);
		printOut(nodes);
		createOutFileHuffmanCode(inputText,"huffmanCode.txt",nodes);	
		readHuffmanCodeAndCreateOutFileHuffmanCodeDecifrado(nodes,new File("huffmanCode.txt"),"outputText.txt");
	}
}
