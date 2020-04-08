import java.util.*;
import java.sql.*;
public class carBooking  {
	static
	{
		System.out.println("***********************************");
		System.out.println("Welcome to Car Booking System");
		System.out.println("***********************************");
	}
	static String username=new String("admin");
	static String password=new String("admin");
	static Scanner sc=new Scanner(System.in);
	private static void  createDatabase() throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt= con.createStatement();
		String db1="create table Driver_Table"    			
				+ " (driverId int NOT NULL , "
				+ "driverName varchar(50),"
				+ " driverLoc varchar(10),"
				+ " driverNumber varchar(10),"
				+ " PRIMARY KEY (driverId))"; 
		String db2="create table Vehicle_Table "
				+ "(vehicleId int NOT NULL, "
				+ "vehicleNumber varchar(10),"
				+ "vehicleName varchar(20),"
				+ "vehicleType varchar(20),"
				+ "vehicleStatus varchar(20),"
				+ "vehiclePrice int,"
				+ "FOREIGN KEY (vehicleId) references Driver_Table(driverId))";

		stmt.executeUpdate(db1);
		stmt.executeUpdate(db2);

		System.out.println("database Created..");
		con.close();
	}

	public static void printDriver() throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt= con.createStatement();
		String print1="select * from Driver_Table";
		ResultSet p1=stmt.executeQuery(print1);

		System.out.println("________________________________________________________________________");
		System.out.println("DriverId"+"     "+"DriverName"+"     "+"DriverLoc"+"     "+"DriverNumber");
		System.out.println("________________________________________________________________________");

		if(p1.next()!=false)
		{
			while(p1.next())
			{
				System.out.println(p1.getInt(1)+"\t"+p1.getString(2)+"\t  "+p1.getString(3)+"\t  "+p1.getString(4));
			}
		}
		else
			System.out.println("Table Empty...");

		System.out.println();
		con.close();
		toprintAdmin();
	}

	public static void printVehicle()throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt= con.createStatement();
		String print2="select * from Vehicle_Table";
		ResultSet p2=stmt.executeQuery(print2);

		System.out.println("_____________________________________________________________________________________________________________");
		System.out.println("vehicleId"+"    "+"vehicleNumber"+"    "+"vehicleName"+"    "+"vehicleType"+"    "+"vehicleStatus"+"    "+"vehiclePrice");
		System.out.println("_____________________________________________________________________________________________________________");

		while(p2.next())
		{

			System.out.println(p2.getInt(1)+"\t"+p2.getString(2)+"\t"+p2.getString(3)+"\t"+p2.getString(4)+"\t"+p2.getString(5)+"\t"+p2.getInt(6));
		}
		System.out.println();
		con.close();
		toprintAdmin();

	}

	private static int setid()
	{
		System.out.println("Enter id");
		int id=sc.nextInt();
		return id;
	}
	private static String setName()
	{
		System.out.println("Enter name of driver");
		String name=sc.next();
		return name;
	}
	private static String setLoc()
	{
		System.out.println("Enter the location");
		String loc=sc.next();
		return loc;
	}
	private static String setNumber()
	{
		System.out.println("Enter the phone number");
		String number=sc.next();
		return number;
	}
	private static void insertDriver() throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		String sql="insert into Driver_Table values(?,?,?,?)";
		PreparedStatement st1 = con.prepareStatement(sql);
		st1.setInt(1, setid());
		st1.setString(2, setName());
		st1.setString(3, setLoc());
		st1.setString(4, setNumber());
		st1.executeUpdate();
		System.out.println("Inserted.....");
		System.out.println();con.close();
		toprintAdmin();

	}

	private static String setNo()
	{
		System.out.println("Eneter Vehcile number");
		String no=sc.next();
		return no;
	}

	private static String setType()
	{
		System.out.println("Enter  type of vehicle (petrol/diesel)");
		String type=sc.next();
		return type;
	}

	private static String setVehicleName()
	{
		System.out.println("Enter name of vehicle");
		String name=sc.next();
		return name;
	}

	private static String setStatus()
	{
		System.out.println("Enter available(yes/no) ");
		String status=sc.next();
		return status;
	}
	private static int setPrice()
	{
		System.out.println("Enter price of car");
		int price=sc.nextInt();
		return price;
	}
	public static void insertVehicle() throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		String sql="insert into Vehicle_Table values(?,?,?,?,?,?)";
		PreparedStatement st1 = con.prepareStatement(sql);
		st1.setInt(1, setid());
		st1.setString(2, setNo());
		st1.setString(3, setVehicleName());
		st1.setString(4, setType());
		st1.setString(5, setStatus());
		st1.setInt(6, setPrice());
		st1.executeUpdate();
		System.out.println("Inserted");
		System.out.println();
		con.close();    	
		toprintAdmin();
	}

	public static void detailsForBooking()throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");

		Statement stmt= con.createStatement();

		String sql="select driverid, driverName, driverLoc, vehicleStatus, vehiclePrice  from Driver_Table,Vehicle_table where Driver_table.driverId=Vehicle_Table.vehicleId";

		ResultSet rs= stmt.executeQuery(sql);

		System.out.println("_______________________________________________________________");
		System.out.println("Id"+"\t"+"Name"+"\t"+"Location"+"\t"+"Availability"+"\t"+"Price");
		System.out.println("_______________________________________________________________");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t\t"
					+rs.getString(4)+"\t\t"+rs.getInt(5));
		}
		con.close();

	}

	private static String getName(int id) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();
		String sql="select driverName from Driver_Table where driverId="+id;
		ResultSet rs=stmt.executeQuery(sql);
		String toReturn="";
		while(rs.next())
		{
			toReturn=rs.getString(1);
		}
		con.close();
		return toReturn;
	}

	private static String getDriverNumber(int id) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();
		String sql="select driverNumber from Driver_Table where driverId="+id;
		ResultSet rs=stmt.executeQuery(sql);
		String toReturn="";
		while(rs.next())
		{
			toReturn=rs.getString(1);
		}
		con.close();
		return toReturn;
	}

	private static String getVehicleNumber(int id) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();
		String sql="select vehicleNumber from Vehicle_Table where vehicleId="+id;
		ResultSet rs=stmt.executeQuery(sql);
		String toReturn="";
		while(rs.next())
		{
			toReturn=rs.getString(1);
		}
		con.close();
		return toReturn;
	}

	private static String getVehicleName(int id) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();
		String sql="select vehicleName from Vehicle_Table where vehicleId="+id;
		ResultSet rs=stmt.executeQuery(sql);
		String toReturn="";
		while(rs.next())
		{
			toReturn=rs.getString(1);
		}
		con.close();
		return toReturn;
	}

	private static int getPrice(int id) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();
		String sql="select vehiclePrice from Vehicle_Table where vehicleId="+id;
		ResultSet rs=stmt.executeQuery(sql);
		int toReturn=0;
		while(rs.next())
		{
			toReturn=rs.getInt(1);
		}
		con.close();
		return toReturn;
	}

	private static boolean checkId(int id) throws Exception
	{

		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();


		String sql="select count(*) from Driver_Table where DriverId="+id;
		ResultSet rs = stmt.executeQuery(sql);
		int count=0;
		while(rs.next()) {
			count = rs.getInt(1);
		}
		con.close();
		if(count>0)
		{
			return true;
		}
		else
		{
			return false;
		}


	}
	public static void printBooking(int id,int days) throws Exception
	{
		System.out.println("****************************************");
		System.out.println();
		System.out.println("   YOUR BOOKING DETAILS ARE             ");
		System.out.println();
		System.out.println("****************************************");
		String name=getName(id);
		String Dnumber=getDriverNumber(id);
		String Vnumber=getVehicleNumber(id);
		String Vname=getVehicleName(id);
		int price=getPrice(id);

		System.out.println("-> Driver's Name    : "+name);
		System.out.println("-> Driver's Contact : "+Dnumber);
		System.out.println("-> Vehicle Name     : "+Vname);
		System.out.println("-> Vehicle Number   : "+Vnumber);
		System.out.println("-> Your price for "+days+" days are RS "+price*days+"/- only");
		System.out.println("THANK YOU !!");
		System.out.println();

	}

	private static boolean checkAdmin()
	{
		System.out.println("LOGGED IN AS AN ADMIN");
		System.out.println("Enter username : ");
		String user=sc.next();
		System.out.println("Enter password : ");
		String pass=sc.next();
		if(user.equals(username) && pass.equals(password))
		{
			return true;
		}
		else
			return false;
	}
	public static void toprintCustomer()throws Exception
	{

		detailsForBooking();
		System.out.println("Enter Driver Id to select");
		int id=sc.nextInt();
		if(checkId(id)==true)
		{
			System.out.println("Enter no Days to Book");
			int days=sc.nextInt();
			printBooking(id,days); 
			System.out.println("1 : for new Booking");
			System.out.println("2 : login as an Admin");
			int choice=sc.nextInt();
			if(choice==1)
				toprintCustomer();
			else if(choice ==2)
			{
				if(checkAdmin()==true)
					toprintAdmin();
				else
					checkAdmin();
			}
			else
				System.out.println("You made Invalid choice");

			System.out.println();
		}
		else
		{

			System.out.println("**   Enter Valid Id   **");
			toprintCustomer();			
		}
		System.out.println();

	}

	private static void delete()throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		Statement stmt=con.createStatement();
		ResultSet rs1=stmt.executeQuery("select * from Driver_Table");
		ResultSet rs2=stmt.executeQuery("select * from Vehicle_Table");
		System.out.println("Delete from");
		System.out.println("1 : Driver");
		System.out.println("2 : Vehicle");
		int choice=sc.nextInt();
		if(choice==1)
		{
			if (!rs1.isBeforeFirst() ) {    
				System.out.println("No data....."); 
			} 
			else {
				System.out.println("Enter Driver Id to delete");
				int toDelete=sc.nextInt();
				String sql="delete from Driver_Table where driverId="+toDelete;
				stmt.executeUpdate(sql);
				System.out.println("Deleted....");
			}
		}
		else if(choice==2 )
		{
			if (!rs2.isBeforeFirst() ) {    
				System.out.println("No data....."); 
			} 
			else {
				System.out.println("Enter Vehicle Id to delete");
				int toDelete=sc.nextInt();
				String sql="delete from Vehicle_Table where vehicleId="+toDelete;
				stmt.executeUpdate(sql);
				System.out.println("Deleted....");
			}
		}
		else
		{
			System.out.println("Invalid choice...");
			delete();
		}
		System.out.println();
		con.close();
		toprintAdmin();
	}

	private static void update()throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","nikit");
		System.out.println("Update from");
		System.out.println("1 : Driver");
		System.out.println("2 : Vehicle");
		int choice=sc.nextInt();

		if(choice==1)
		{
			System.out.println("Enter the column you want to update from Driver");
			System.out.println("1 : Name");
			System.out.println("2 : Location");
			System.out.println("3 : Contact");
			int option =sc.nextInt();
			switch(option)
			{
			case 1:
				System.out.println("Enter the DriverId to be updated");
				int id=sc.nextInt();
				System.out.println("Enter new Name");
				String name=sc.next();

				String update="update Driver_Table SET driverName=?"
						+ " where driverId=?";
				PreparedStatement st1 = con.prepareStatement(update);
				st1.setString(1,name);
				st1.setInt(2,id);
				st1.executeUpdate();

				System.out.println("Updated.......");
				break;
			case 2:
				System.out.println("Enter the DriverId to be updated");
				int id1=sc.nextInt();
				System.out.println("Enter new Location");
				String location=sc.next();

				String update1="update Driver_Table SET driverLoc=?"
						+ " where driverId=?";
				PreparedStatement st2 = con.prepareStatement(update1);
				st2.setString(1,location);
				st2.setInt(2,id1);
				st2.executeUpdate();

				System.out.println("Updated.......");
				break;
			case 3:
				System.out.println("Enter the DriverId to be updated");
				int id3=sc.nextInt();
				System.out.println("Enter new Contact");
				String number=sc.next();

				String update3="update Driver_Table SET driverNumber=?"
						+ " where driverNo=?";
				PreparedStatement st3 = con.prepareStatement(update3);
				st3.setString(1,number);
				st3.setInt(2,id3);
				st3.executeUpdate();

				System.out.println("Updated.......");
				break;
			}

		}
		else if(choice ==2)
		{
			System.out.println("Enter the column you want to update from Vehicle");
			System.out.println("1 : Status");
			System.out.println("2 : Price");
			int option=sc.nextInt();
			switch(option)
			{
			case 1:
				System.out.println("Enter the vehicleId to be updated");
				int id=sc.nextInt();
				System.out.println("Enter new status");
				String name=sc.next();

				String update="update Vehicle_Table SET vehicleStatus=?"
						+ " where vehicleId=?";
				PreparedStatement st1 = con.prepareStatement(update);
				st1.setString(1,name);
				st1.setInt(2,id);
				st1.executeUpdate();

				System.out.println("Updated.......");
				break;
			case 2:
				System.out.println("Enter the DriverId to be updated");
				int id1=sc.nextInt();
				System.out.println("Enter new Price");
				int price=sc.nextInt();

				String update1="update Vehicle_Table SET vehiclePrice=?"
						+ " where vehicleId=?";
				PreparedStatement st2 = con.prepareStatement(update1);
				st2.setInt(1,price);
				st2.setInt(2,id1);
				st2.executeUpdate();

				System.out.println("Updated.......");
				break;
			}
		}
		else
		{
			System.out.println("Invalid Choice... Try Again ");
			update();
		}
		System.out.println();con.close();
		toprintAdmin();
	}
	public static void toprintAdmin() throws Exception
	{


		System.out.println("SELECT ANY OF THESE INSTRUCTION");
		System.out.println("0 : Login as Customer ");
		System.out.println("1 : Enter details of Driver");
		System.out.println("2 : Enter details of vehicle");
		System.out.println("3 : Print Driver details ");
		System.out.println("4 : Print vehicle details ");
		System.out.println("5 : Delete an Entry ");
		System.out.println("6 : Update an Entry");
		System.out.println("7 : Exit");
		int choice=sc.nextInt();
		int flag=1;
		if(flag==1)
		{
			switch(choice)
			{
			case 0:
				System.out.println();
				System.out.println("Enter you name");
				String name=sc.next();
				System.out.println("**  Welcome "+name+"  **");
				toprintCustomer();
				flag=0;
				break;
			case 1:
				System.out.println();
				insertDriver();
				break;
			case 2:
				System.out.println();
				insertVehicle();
				break;
			case 3:
				System.out.println();
				printDriver();

				break;
			case 4:
				System.out.println();
				printVehicle();
				break;
			case 5:
				System.out.println();
				delete();
				break;
			case 6:
				System.out.println();
				update();
				break;

			case 7:
				System.out.println("EXITED.....");
				System.exit(0);
				break;
			default:
				System.out.println("Enter vaild option");
			}
		}
		else if(flag==0)
		{
			System.out.println();
			toprintCustomer();
		}

		System.out.println();


	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Creating db 
		//createDatabase();  



		System.out.println("Enter 1 : Acess As An Admin");
		System.out.println("Enter 2 : Acess As A  Customer"); 

		int acessKind=sc.nextInt();

		boolean flag=false;

		if(acessKind==1)
		{
			if(checkAdmin()==true)
			{
				System.out.println("Acess Granted.....");
				System.out.println();
				flag=true;
				toprintAdmin();
			}
			else
			{
				System.out.println("Acess Denide....");
				main(new String[] {"main"});
			}
			if(flag==true)
			{
				toprintAdmin();   
			}
		}
		else
		{
			System.out.println();
			System.out.println("LOGGED IN AS A CUSTOMER");
			System.out.println("Enter your name");
			String name=sc.next();
			System.out.println("**  Welcome "+name+"  **");
			toprintCustomer();   
		}

	}

}
