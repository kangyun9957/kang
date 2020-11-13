import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;


public class Db
{	public String var;
	
	public Db(String s) throws SQLException,IOException{
		  	this.var=s;
		  	String [] tables = {"department","dependent","dept_locations","employee","project","works_on"}; 
	        Connection conn=null;
	        ArrayList <String> listA = new ArrayList();        
	        ArrayList <String> listB = new ArrayList();
	        String dna,check,a1,a2,a3,a4,a5,a6,a7,a8;
	    	Scanner scanner=new Scanner(System.in);
	        String url,id,pwd;
	        System.out.println("Enter database:");
	        url=scanner.nextLine();
	        System.out.println("Enter id:");
	        id=scanner.nextLine();
	        System.out.println("Enter pwd:");
	        pwd=scanner.nextLine();
	        String[] login= {url,id,pwd};
	        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+url+"?serverTimezone=UTC", id,pwd);
	        System.out.println(s);
	      
	        if(s!="employee") {
	        	String stmt2 = "select column_name from information_schema.columns where table_name = ? order by ordinal_position" ;
	 	        String stmt1 = "select * from "+s+"";
	 	       		
	 	       
	 	        PreparedStatement p2=conn.prepareStatement(stmt2);
	 	        PreparedStatement p=conn.prepareStatement(stmt1);
	 	      
	 	        p2.clearParameters();
	 	       
	 	        p2.setString(1,s);
	 	       
	 	        p.clearParameters();
	 	       
	 	     
	 	        ResultSet r=p2.executeQuery();
	 	        ResultSet r2=p.executeQuery();
	 	      
	        	 
	        	while(r.next()){
	        		dna=r.getString(1);
	        		System.out.println(dna);
	        		listA.add(dna);
	            
	        	}
	        	System.out.println(listA.size());
	        	while(r2.next()) {
	        		String ss="";
	        		for(int i=1;i<listA.size()+1;i++) {
	        		
	        			check=r2.getString(i);
	        			ss=ss+check+"&";
	        			System.out.println(ss);
	        		
	        		
	        		}
	        		listB.add(ss);
	        	
	        	}
	        
	        }
	        else {
	        	 String stmt3 =  "select concat(e.fname,' ',e.minit,' ',e.lname) name,e.ssn,e.bdate,e.address,e.sex,e.salary,concat (s.fname,' ',s.minit,' ',s.lname) Super_name, dname\r\n from  ("+s+" as e left outer join "+s+" as s on e.super_ssn=s.ssn),department\r\n where e.dno=dnumber";
	        	 PreparedStatement p3=conn.prepareStatement(stmt3);
	  	         ResultSet r3=p3.executeQuery();
	        	
	        	listA.add("name");
	        	listA.add("ssn");
	        	listA.add("bdate");
	        	listA.add("address");
	        	listA.add("sex");
	        	listA.add("salary");
	        	listA.add("Super_ssn");
	        	listA.add("dname");
	        	while(r3.next()) {
	        		a1=r3.getString(1);
		        	a2=r3.getString(2);
		        	a3=r3.getString(3);
		        	a4=r3.getString(4);
		        	a5=r3.getString(5);
		        	a6=r3.getString(6);
		        	a7=r3.getString(7);
		        	a8=r3.getString(8);
	        		listB.add(a1+"&"+a2+"&"+a3+"&"+a4+"&"+a5+"&"+a6+"&"+a7+"&"+a8);
	        		
	        	}
	        
	        }
	        for(int i=0;i<6;i++) {
	        	if(tables[i]==s) {
	        		tables[i]=tables[0];
	        		tables[0]=s;
	        		
	        	}
	        }
	        Gui gui = new Gui(listA,listB,s,tables,login);
	       

	        try {
	            if (conn != null)
	                conn.close();
	        } catch (SQLException e) {
	        }
	}
	
	
	
    public static void main (String args []) throws SQLException, IOException{
    	
    	Db db = new Db("employee");
    	
    	
      
    }
}
