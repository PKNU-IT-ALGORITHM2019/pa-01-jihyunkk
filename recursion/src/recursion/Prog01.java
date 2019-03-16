package recursion;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
//import java.io.InputStreamReader;
import java.util.*;

public class Prog01 {
	
	
	
	
	
	
	
	public static int i=0;
	public static int count=0;
	public static int Begin;
	public static int End;
	public static int []MiddleIndex=new int[100000];
	
	public static String[]Words=new String[9999999];
	public static String[]WordClass =new String[9999999];
	public static String[]Meaning=new String[9999999];


	public static void size() {
		System.out.println(Prog01.i);
	}
	

	
	public static int find(String[] items,String target,int begin,int end) {
		
		int fla=0;
		if(begin>end) {
		  if(fla==1) {
			  Begin=begin+1;End=end;
			  }
			  else {
				  Begin=begin;End=end-1;
			  }
				  
			if(end>0) {
		 
				return end;
				}
			return -1;
			}
		else {
			int middle=(begin+end)/2;
		//	System.out.println(begin+" "+middle+" "+end);
		
			int compResult=target.compareToIgnoreCase(items[middle]);//string두개 비교할때 문법
			if(compResult==0) {
				if(fla==1) {
					Begin=begin;End=middle-1;
				}
				else {
					Begin=middle+1;End=end;
				}
				MiddleIndex[count++]=middle;
				return middle;
				}
			else if(compResult<0) {
				fla=1;
				return find(items,target,begin,middle-1);
			}
			else {
				fla=0;
				return find(items,target,middle+1,end);
			}
		}
	}
	
	
	public static void Read(String fileName) {
		
		
		
		
		try {
			//파일객체 생성
			String filePath="C:\\Users\\user\\Desktop\\spring2019\\algorithm2019\\pa-01-jihyunkk\\";
			filePath=filePath+fileName;
			File file =new File(filePath);
			//입력스트림생성
			FileReader filereader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(filereader);
			String line="";
			while((line=bufReader.readLine())!=null) {
				if(line.length()==0)
					continue;
			//	line=line+"\0";
				Words[Prog01.i]="";
				WordClass[Prog01.i]="";
				Meaning[Prog01.i]="";
				int flag=0;
				
				StringTokenizer str=new StringTokenizer(line);
				while(str.hasMoreTokens()) {
					if(flag==0) {
						Words[Prog01.i]=str.nextToken("(");
						Words[Prog01.i]=Words[Prog01.i].substring(0,Words[Prog01.i].length()-1);
				
					
						char[] ch=Words[Prog01.i].toCharArray();//첫글자 추출
						
						if(!Character.isLetter(ch[0]))///첫번째가 문자가 아니면
							Words[Prog01.i]=Words[Prog01.i].substring(1,Words[Prog01.i].length());//첫글자 제거
						flag=1;
					}
					else if(flag==1) {
						WordClass[Prog01.i]=str.nextToken(")");
						flag=2;
					}
					else if(flag==2)
					{
						Meaning[Prog01.i]=str.nextToken("\0");
						flag=0;
					}
				}
				
			//	System.out.println(prog_assign01.i+" : "+Words[prog_assign01.i]+WordClass[prog_assign01.i]+Meaning[prog_assign01.i]);
			//	System.out.println(Words[prog_assign01.i].length());
				Prog01.i++;

				
			}filereader.close();
		
			
		}catch(FileNotFoundException e) {
		
	}catch(IOException e) {
		System.out.println(e);
	}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
public static void main(String[] args) {
		
		
		while(true) {
		System.out.print("$");
		Scanner scn=new Scanner(System.in);
		String funtion=scn.next();
		if(funtion.equals("exit"))
			break;
		
		if(funtion.equals("read")) {
			String argument=scn.next();
			scn.nextLine();
			Read(argument);
			}
		else if(funtion.equals("size")) {
			scn.nextLine();
			size();
		}
		else if(funtion.equals("find")) {
			String argument=scn.nextLine();
			
			//scn.nextLine();
			count=0;
			argument=argument.substring(1, argument.length());
			int number=find(Words,argument,0,i-1);
		
			
			if(number==-1&&count==0)
				System.out.println("찾는단어의 앞단어가 존재하지않음");
			else if(number!=-1&&count==0)
			{
				//System.out.println(number);
				System.out.println("Not Found.");
				System.out.println(Words[number]+WordClass[number]+")");
				System.out.println("-   -   -");
				System.out.println(Words[number+1]+WordClass[number+1]+")");
			}
			else if(count!=0) {
				while(End>=Begin) {
					find(Words,argument,Begin,End);
					
					
				}
				System.out.println("Found "+count+" items.");
				for(int k=0;k<count;k++) {
				System.out.println(Words[MiddleIndex[k]]+" "+WordClass[MiddleIndex[k]]+Meaning[MiddleIndex[k]]);
				}
			}

		}
			
		}
		
		
	}



}
