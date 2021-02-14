import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.text.*;

class Prescription implements Serializable{
	String date;
	String prescription;
}


class Demographies extends Prescription implements Serializable{
	String nationality=null,religion=null,occupation=null,email=null,gender=null,fathersname=null,mothersname=null;
	public String getNationality(){
		return this.nationality;
	}
	public String getReligion(){
		return this.religion;
	}
	public String getOccupation(){
		return this.occupation;
	}
	public String getEmail(){
		return this.email;
	}
	public String getGender(){
		return this.gender;
	}
	public String getFathersName(){
		return this.fathersname;
	}
	public String getMotherrsName(){
		return this.mothersname;
	}
}



class MedicalHistory extends Demographies implements Serializable{
	String smoking,alcohol,exercise;
	String vaccination,vaccinedate;
	String disease;
}

class PatientInfo{
	String username=null,firstname=null,lastname=null;
	long userid=0;
	String password=null;
	String phonenum=null;
	String address=null;
	String Dob=null;
	int age=0;
}


class Patient implements Serializable{
	private String username=null,firstname=null,lastname=null;
	private long userid=0;
	private String password=null;
	private String phonenum=null;
	private String address=null;
	private String Dob=null;
	private int age=0;
	Random rand = new Random();
	Patient pt =null;
	//private int medcount=0;
	ArrayList<MedicalHistory> medrec = new ArrayList<>();
	Doctor doc = null;
	MedicalHistory med = new MedicalHistory();
	Patient(PatientInfo pat,Doctor doc){
		this.userid=rand.nextInt(10000000)+1000000;
		this.username = pat.username;
		this.password = pat.password;
		this.address = pat.address;
		this.phonenum = pat.phonenum;
		this.Dob = pat.Dob;
		this.age = pat.age;
		this.firstname = pat.firstname;
		this.lastname = pat.lastname;
		this.doc = doc;
	}
	public String getName(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public long getId(){
		return userid;
	}
	public String getPhone(){
		return phonenum;
	}
	public String getAddress(){
		return address;
	}
	public String getFirstname(){
		return this.firstname;
	}
	public String getLastname(){
		return this.lastname;
	}
	public String getDob(){
		return this.Dob;
	}
	public int getAge(){
		return this.age;
	}
	
	public void updateMedicalRecord(){
		System.out.println("Medical record size:"+medrec.size());
		medrec.add(med);
		System.out.println("Medical record size:"+medrec.size());
		System.out.println("Medical record added");
	}
	public void updateInfo(PatientInfo pat){
		if(pat.username != null)
			this.username = pat.username;
		if(pat.password != null)
			this.password = pat.password;
		if(pat.address != null)
			this.address = pat.address;
		if(pat.phonenum != null)
			this.phonenum = pat.phonenum;
		if(pat.Dob != null)
			this.Dob = pat.Dob;
		if(pat.age != 0)
			this.age = pat.age;
		if(pat.firstname != null)
			this.firstname = pat.firstname;
		if(pat.lastname != null)
			this.lastname = pat.lastname;
	}

}

class DoctorInfo{
	String username=null,password=null;
	String designation=null,firstname=null,lastname=null;
	String address=null,phonenum=null,email=null; 
	String dob=null,gender = null;
	int age=0,slot1=0,slot2=0;
}

class AppointmentSchedule implements Serializable{
	Patient patient;
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	Date date;
	int hrs[] = new int[2];
	boolean booked = false;
}


class Doctor implements Serializable{
	private String username,password;
	private String designation,firstname,lastname;
	private String address,phonenum,email; 
	private String dob;
	private int age;
	private String gender;
	int slot1,slot2;
	ArrayList<AppointmentSchedule> appo = new ArrayList<>();
	ArrayList<Patient> patient = new ArrayList<>();
	Doctor(DoctorInfo doc){
		this.username = doc.username;
		this.password = doc.password;
		this.designation = doc.designation;
		this.firstname = doc.firstname;
		this.lastname = doc.lastname;
		this.address = doc.address;
		this.phonenum = doc.phonenum;
		this.email = doc.email;
		this.dob = doc.dob;
		this.age = doc.age;
		this.gender = doc.gender;
		this.slot1 = doc.slot1;
		this.slot2 = doc.slot2;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public String getDesignation(){
		return this.designation;
	}
	public String getFirstname(){
		return this.firstname;
	}
	public String getLastname(){
		return this.lastname;
	}
	public String getAddress(){
		return this.address;
	}
	public String getPhonenum(){
		return this.phonenum;
	}
	public String getEmail(){
		return this.email;
	}
	public String getDob(){
		return this.dob;
	}
	public int getAge(){
		return this.age;
	}
	public String getGender(){
		return this.gender;
	}
	public void updateInfo(DoctorInfo doc){
		if(doc.username != null)
			this.username = doc.username;
		if(doc.password != null)
			this.password = doc.password;
		if(doc.designation != null)
			this.designation = doc.designation;
		if(doc.firstname != null)
			this.firstname = doc.firstname;
		if(doc.lastname != null)
			this.lastname = doc.lastname;
		if(doc.address != null)
			this.address = doc.address;
		if(doc.phonenum !=null)
			this.phonenum = doc.phonenum;
		if(doc.email != null)
			this.email = doc.email;
		if(doc.dob != null)
			this.dob = doc.dob;
		if(doc.age != 0)
			this.age = doc.age;
		if(doc.gender != null)
			this.gender = doc.gender;
	}
}

class Database{
	static FileOutputStream dout=null,pout=null;
	static FileInputStream din=null,pin=null;
	static ArrayList<Patient> parray;
	static ArrayList<Doctor> darray;
	static ObjectOutputStream pwri=null,dwri=null;
	static ObjectInputStream red=null;
	static boolean running = true;
	static{
		try{
			pin = new FileInputStream("patient");
			red = new ObjectInputStream(pin);
			parray = (ArrayList<Patient>)red.readObject();
			red.close();
			pin.close();
			din = new FileInputStream("doctor");
			red = new ObjectInputStream(din);
			darray = (ArrayList<Doctor>)red.readObject();
			red.close();
			din.close();
		}
		catch(FileNotFoundException exc){
			//System.out.println("Savefile not found:"+exc.toString());
			//exc.printStackTrace();
		}
		catch(IOException io){
			//System.out.println("Unable to read savefile:"+io.toString());
			//io.printStackTrace();
		}
		catch(Throwable ex){
			//System.out.println("Error while reading savefile:"+ex.toString());
			//ex.printStackTrace();
		}
		try{
			//System.out.println("Reading savefile content...");
			pout = new FileOutputStream("patient",false);
			pwri = new ObjectOutputStream(pout);
			dout = new FileOutputStream("doctor",false);
			dwri = new ObjectOutputStream(dout);
			if(parray == null)
				parray = new ArrayList<>();
			if(darray == null)
				darray = new ArrayList<>();
			//System.out.println("Content read....");
		}
		catch(FileNotFoundException exc){
			//System.out.println("Savefile not found:"+exc.toString());
			//exc.printStackTrace();
		}
		catch(IOException i){
			//System.out.println("Unable to create savefile:"+i.toString());
			//i.printStackTrace();
		}
		
	}
	static boolean checkusername(String name){
		Doctor doc = null;
		Patient pat = null;
		for(int i=0;i<Database.darray.size();i++){
			doc = Database.darray.get(i);
			if(doc.getUsername().equals(name)){
				return true;
			}
		}
		for(int i=0;i<Database.parray.size();i++){
			pat = Database.parray.get(i);
			if(pat.getName().equals(name))
				return true;
		}
		return false;
	}
	public void createRecord(Patient p){
		if(Database.parray == null)
			Database.parray = new ArrayList<>();
		Database.parray.add(p);
	}
	public void createRecord(Doctor d){
		if(Database.darray == null)
			Database.darray = new ArrayList<>();
		Database.darray.add(d);
	}
	public Patient getPatient(String name){
		Patient p=null;
		for(int i=0;i<Database.parray.size();i++){
			p = Database.parray.get(i);
			if(p.getName().equals(name))
				return p;
		}
		return null;
	}
	public Doctor getDoctor(String name){
		Doctor d = null;
		for(int i=0;i<Database.darray.size();i++){
			d = Database.darray.get(i);
			if(d.getUsername().equals(name))
				return d;
		}
		return null;
	}
	public void commit(){
		try{
			if(Database.pwri!=null)
				Database.pwri.writeObject(Database.parray);
			else
				System.out.println("PWrite object not created");
			if(Database.dwri != null)
				Database.dwri.writeObject(Database.darray);
			else
				System.out.println("DWrite object not created");
			System.out.println("Data stored");
		}catch(IOException io){
			System.out.println("Unable to save changes"+io.toString());
		}
	}
}

class Credentials{
	String username,password;
	long userid;
}


class Login extends Database{
	Credentials cre = new Credentials();
	BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
	public void GetCredentials(){
		try{
			System.out.print("Please enter your username:");
			cre.username = in.readLine();
			System.out.print("Please enter your password:");
			cre.password = in.readLine();
		}
		catch(IOException io){
			System.out.println("Some error has occured");
			return;
		}
	}

	public Patient PatientAuthenticate(){
		Patient p= null;
		if(Database.parray != null){
			for(int i=0;i<Database.parray.size();i++){
				p = Database.parray.get(i);
				if(p.getName().equals(cre.username) && p.getPassword().equals(cre.password))
					return p;
				
			}
		}
		return null;
	}
	public Doctor DoctorAuthenticate(){
		Doctor d = null;
		if(Database.darray != null){
			for(int i=0;i<Database.darray.size();i++){
				d = Database.darray.get(i);
				if(d.getUsername().equals(cre.username) && d.getPassword().equals(cre.password))
					return d;
			}
		}
		return null;
	}
}

abstract class Signup extends Database{
	abstract public void signup();
}

class PatientSignUp extends Signup{
	PatientInfo pat = new PatientInfo();
	Patient p = null;
	Database d = new Database();
	Doctor doc = null;
	String docname=null;
	int age;
	public void signup(){
		try{
			BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Personal Info:");
			do{
				System.out.print("Please Enter your Username:");
				pat.username = bin.readLine();
				if(!Database.checkusername(pat.username)){
					break;
				}
				else{
					System.out.println("Username exists! Try Another or enter 'exit'");
				}
			}while(!pat.username.equals("exit"));
			System.out.print("Please Enter your Firstname:");
			pat.firstname = bin.readLine();
			System.out.print("Please Enter your Lastname:");
			pat.lastname = bin.readLine();
			System.out.print("Please Enter your Password:");
			pat.password = bin.readLine();
			do{
				System.out.print("Please Enter your Phonenum:");
				pat.phonenum = bin.readLine();
				if(pat.phonenum.length() != 10){
					System.out.println("Please enter valid phonenumber:");
				}
			}while(pat.phonenum.length() != 10);
			System.out.print("Please Enter your Address:");
			pat.address = bin.readLine();
			System.out.print("Please Enter your Dob:");
			pat.Dob = bin.readLine();
			System.out.print("Please Enter your Age:");
			pat.age = Integer.parseInt(bin.readLine());
			System.out.print("Please Enter the attending doctor:");
			docname = bin.readLine();
			doc = d.getDoctor(docname);
			if(doc != null){
				p = new Patient(pat,doc);
				doc.patient.add(p);
			}
			else{
				System.out.println("Doctor not found!");
				return;
			}
			System.out.print("Please Enter your Nationality:");
			p.med.nationality = bin.readLine();
			System.out.print("Please Enter your Religion:");
			p.med.religion = bin.readLine();
			System.out.print("Plese Enter your Occupation:");
			p.med.occupation = bin.readLine();
			do{
				System.out.print("Please Enter your Email:");
				p.med.email = bin.readLine();
				if(p.med.email.indexOf("@") == -1)
					System.out.println("Please enter valid email");
			}while(p.med.email.indexOf("@") == -1);
			System.out.print("Please Enter your Gender;");
			p.med.gender = bin.readLine();
			System.out.print("Please Enter your FathersName:");
			p.med.fathersname = bin.readLine();
			System.out.println("Please Enter your MothersName:");
			p.med.mothersname = bin.readLine();
			createRecord(p);
		}
		catch(IOException e){
			System.out.println("Something error happened");
		}
	}
}

class DoctorSignUp extends Signup{
	public void signup(){
		DoctorInfo doc = new DoctorInfo();
		Scanner scan = new Scanner(System.in);
		System.out.println("Personal Info:");
		do{
			System.out.print("Please Enter your username:");
			doc.username = scan.next();
			if(!Database.checkusername(doc.username))
				break;
			else
				System.out.println("Username exists! Try another or enter 'exit'");
		}while(!doc.username.equals("exit"));
		System.out.print("Please Enter your password:");
		doc.password = scan.next();
		System.out.print("Please Enter your Designation:");
		doc.designation = scan.next();
		System.out.print("Please Enter your Firstname:");
		doc.firstname = scan.next();
		System.out.print("Please Enter your Lastname:");
		doc.lastname = scan.next();
		System.out.print("Please Enter your Address:");
		doc.address = scan.next();
		do{
				System.out.print("Please Enter your Phonenum:");
				doc.phonenum= scan.next();
				if(doc.phonenum.length() != 10){
					System.out.println("Please enter valid phonenumber:");
				}
		}while(doc.phonenum.length() != 10);
		do{
			System.out.print("Please Enter your Email:");
			doc.email = scan.next();
			if(doc.email.indexOf("@") == -1)
				System.out.println("Please enter valid email");
		}while(doc.email.indexOf("@") == -1);
		System.out.print("Please Enter your DOB:");
		doc.dob = scan.next();
		System.out.print("Please Enter your Age:");
		doc.age = scan.nextInt();
		System.out.print("Please Enter your Gender:");
		doc.gender = scan.next();
		System.out.print("Please Enter your FreehrSlot1:");
		doc.slot1 = scan.nextInt();
		System.out.print("Please Enter your FreehrSlot2:");
		doc.slot2 = scan.nextInt();
		Doctor doctor = new Doctor(doc);
		createRecord(doctor);
	}
}

class PatientOps extends Database{
	Patient pat = null;
	Doctor doc = null;
	String name=null;
	String date,choise;
	int hr=0;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
	PatientOps(Patient pat){
		this.pat = pat;
		this.doc = pat.doc;
	}
	public void ScheduleAppointment(){
		Doctor app = null;
		try{
			do{
			boolean booked = false;
			System.out.print("Please Enter the Doctor's name:");
			name = bin.readLine();
			app = getDoctor(name);
			if(app != null){
				AppointmentSchedule sche= null;
				System.out.printf("Please Enter the date of Appointment(yyyy-MM-dd):");
				date = bin.readLine();
				for(int i=0;i<app.appo.size();i++){
					sche = app.appo.get(i);
					if(sche != null){
						if(sdf.parse(date).equals(sche.date)){
							if(!sche.booked){
								System.out.printf("Slots available:%d[%d] and %d[%d]",app.slot1,sche.hrs[0],app.slot2,sche.hrs[1]);
								hr = Integer.parseInt(bin.readLine());
								if(hr == app.slot1 && sche.hrs[0] == 0)
									sche.hrs[0] = 1;
								else if(hr == app.slot2 && sche.hrs[1] == 0)
									sche.hrs[1] = 1;
								if(sche.hrs[0] == 1 && sche.hrs[1] == 1)
									sche.booked = true;
								sche.patient = this.pat;
								booked = true;
								break;
							}
							else{
								System.out.println("Date booked!");
								break;
							}
						}
					}
				}
				if(booked == false){
					System.out.println("New booking");
					sche = new AppointmentSchedule();
					sche.patient = this.pat;
					sche.date = sdf.parse(date);
					System.out.printf("Slots available:%d[%d] and %d[%d]",app.slot1,sche.hrs[0],app.slot2,sche.hrs[1]);
					hr = Integer.parseInt(bin.readLine());
					if(hr == app.slot1)
						sche.hrs[0] = 1;
					else if(hr == app.slot2)
						sche.hrs[1] = 1;
					sche.patient = this.pat;
					app.appo.add(sche);
					System.out.println("Appointment Booked");
				}
			}
			else{
				System.out.println("Doctor not found!");
			}
			System.out.print("Do you wish to continue?(yes/no)");
			choise = bin.readLine();
			}while(choise.equals("yes"));
		}
		catch(Throwable exc){
			System.out.println("Some error occured"+exc.toString());
			exc.printStackTrace();
		}
	}	
	public void ViewDetails(){
		System.out.println("Userid:"+pat.getId());
		System.out.println("Username:"+pat.getName());
		System.out.println("Firstname:"+pat.getFirstname());
		System.out.println("Lastname:"+pat.getLastname());
		System.out.println("Address:"+pat.getAddress());
		System.out.println("Phonenum:"+pat.getPhone());
		System.out.println("DOB:"+pat.getDob());
		System.out.println("Age:"+pat.getAge());
		System.out.println("Nationality:"+pat.med.nationality);
		System.out.println("Religion:"+pat.med.religion);
		System.out.println("Occupation:"+pat.med.occupation);
		System.out.println("Email:"+pat.med.email);
		System.out.println("Gender:"+pat.med.gender);
		System.out.println("FathersName:"+pat.med.fathersname);
		System.out.println("MothersName:"+pat.med.mothersname);
	}
	public void UpdateInfo()throws IOException{
		PatientInfo pat = new PatientInfo();
		System.out.print("Please Enter your Firstname:");
		pat.firstname = bin.readLine();
		System.out.print("Please Enter your Lastname:");
		pat.lastname = bin.readLine();
		System.out.print("Please Enter your Password:");
		pat.password = bin.readLine();
		System.out.print("Please Enter your Phonenum:");
		pat.phonenum = bin.readLine();
		System.out.print("Please Enter your Address:");
		pat.address = bin.readLine();
		System.out.print("Please Enter your Dob:");
		pat.Dob = bin.readLine();
		System.out.print("Please Enter your Age:");
		pat.age = Integer.parseInt(bin.readLine());
		this.pat.updateInfo(pat);
	}
	public void ViewAppointments()throws ParseException{
		Doctor doc = null;
		for(int i=0;i<Database.darray.size();i++){
			doc = Database.darray.get(i);
			System.out.println("Doctor name:"+doc.getUsername());
			for(int j=0;j<doc.appo.size();j++){
				AppointmentSchedule a=doc.appo.get(j);
				System.out.printf("\t%s\t%d[%s]\t%d[%s]\n",a.fmt.format(a.date),doc.slot1,a.hrs[0]!=0?"blocked":"unblocked" ,doc.slot2,a.hrs[1]!=0?"blocked":"unblocked") ;
			}
		}
	}

}

class DoctorOps extends Database{
	Doctor doc = null;
	String patientname;
	BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
	DoctorOps(Doctor doc){
		this.doc = doc;
	}
	public void UpdateMedicalRecord()throws IOException{
		Patient p = null,pat = null;
		System.out.println("Please Enter the patients username:");
		patientname = bin.readLine();
		for(int i=0;i<doc.patient.size();i++){
			p = doc.patient.get(i);
			if(p.getName().equals(patientname)){
				pat = p;
				break;
			}
			else{
				System.out.println("Patient not found!");
				return;
			}
		}
		if(pat == null)
			return;
		System.out.print("Smoking?(yes/no)");
		pat.med.smoking = bin.readLine();
		System.out.print("Alcohol?(yes/no)");
		pat.med.alcohol = bin.readLine();
		System.out.print("Exercise?(yes/no)");
		pat.med.exercise = bin.readLine();
		System.out.print("Vaccination:");
		pat.med.vaccination = bin.readLine();
		System.out.print("Vaccinedate:");
		pat.med.vaccinedate = bin.readLine();
		System.out.print("Disease:");
		pat.med.disease = bin.readLine();
		pat.med.date = pat.med.vaccinedate;
		System.out.print("Prescription:");
		pat.med.prescription = bin.readLine();
		pat.updateMedicalRecord();
		System.out.println("Medical record updated");
	}
	public void ViewMedicalrecord()throws IOException{
		Patient pat = null;
		System.out.println("Please Enter the Patients username:");
		String username = bin.readLine();
		boolean found = false;
		for(int i=0;i<doc.patient.size();i++){
			pat = doc.patient.get(i);
			if(pat.getName().equals(username)){
				found = true;
				System.out.println("Patient found:"+pat.getName());
				System.out.println("Medical history size:"+pat.medrec.size());
				break;
			}
		}
		if(found  == false){
			System.out.println("Patient not found!");
			return;
		}
		System.out.println("Medical History:");
		System.out.println("Record size:"+pat.medrec.size());
		for(int i=0;i<pat.medrec.size();i++){
			MedicalHistory m = pat.medrec.get(i);
			System.out.println("Userid:"+pat.getId());
			System.out.println("Username:"+pat.getName());
			System.out.println("Firstname:"+pat.getFirstname());
			System.out.println("Lastname:"+pat.getLastname());
			System.out.println("Address:"+pat.getAddress());
			System.out.println("Phonenum:"+pat.getPhone());
			System.out.println("DOB:"+pat.getDob());
			System.out.println("Age:"+pat.getAge());
			System.out.println("Nationality:"+m.nationality);
			System.out.println("Religion:"+m.religion);
			System.out.println("Occupation:"+m.occupation);
			System.out.println("Email:"+m.email);
			System.out.println("Gender:"+m.gender);
			System.out.println("FathersName:"+m.fathersname);
			System.out.println("MothersName:"+m.mothersname);
			System.out.println("Alocohol:"+m.alcohol);
			System.out.println("Smoking:"+m.smoking);
			System.out.println("Exercise:"+m.exercise);
			System.out.println("Vaccication:"+m.vaccination);
			System.out.println("Vaccine Date:"+m.vaccinedate);
			System.out.println("Disease:"+m.disease);
			System.out.println("Consultation date:"+m.date);
			System.out.println("Prescription:"+m.prescription);
			System.out.println("=====================");
		}
	}
	public void UpdateInfo()throws IOException{
		DoctorInfo doc = new DoctorInfo();
		System.out.print("Please Enter your Firstname:");
		doc.firstname = bin.readLine();
		System.out.print("Please Enter your Lastname:");
		doc.lastname = bin.readLine();
		System.out.print("Please Enter your Designation:");
		doc.designation = bin.readLine();
		System.out.print("Please Enter your Password:");
		doc.password = bin.readLine();
		System.out.print("Please Enter your Phonenum:");
		doc.phonenum = bin.readLine();
		System.out.print("Please Enter your Address:");
		doc.address = bin.readLine();
		System.out.print("Please Enter your Dob:");
		doc.dob = bin.readLine();
		System.out.print("Please Enter your Age:");
		doc.age = Integer.parseInt(bin.readLine());
		this.doc.updateInfo(doc);
	}
	public void CheckAppointment()throws ParseException{
		for(int j=0;j<doc.appo.size();j++){
			AppointmentSchedule a=doc.appo.get(j);
			System.out.printf("\t%s\t%s\t%d[%s]\t%d[%s]\n",a.patient.getName(),a.fmt.format(a.date),doc.slot1,a.hrs[0]!=0?"blocked":"unblocked" ,doc.slot2,a.hrs[1]!=0?"blocked":"unblocked") ;
		}
	}

	public void ViewDetails(){
		System.out.println("Username:"+doc.getUsername());
		System.out.println("Firstname:"+doc.getFirstname());
		System.out.println("Lastname:"+doc.getLastname());
		System.out.println("Address:"+doc.getAddress());
		System.out.println("Designation:"+doc.getDesignation());
		System.out.println("Phonenumber:"+doc.getPhonenum());
		System.out.println("Email:"+doc.getEmail());
		System.out.println("Dob:"+doc.getDob());
		System.out.println("Age:"+doc.getAge());
		System.out.println("Gender:"+doc.getGender());
		System.out.printf("Free slots:%d and %d\n",doc.slot1,doc.slot2);
	}

}

class SMSSender implements Runnable{
	ArrayList<AppointmentSchedule> schedule = null;
	AppointmentSchedule sched = null;
	Doctor doc = null;
	Patient pat = null;
	public void checker(){
		for(int i=0;i<Database.darray.size();i++){
			doc = Database.darray.get(i);
			schedule = doc.appo;
			for(int j=0;j<schedule.size();j++){
				sched = schedule.get(j);
				LocalDate d = LocalDate.now();
				Instant instant = d.atStartOfDay(ZoneId.systemDefault()).toInstant();	
				if(sched.date.getTime() <= instant.toEpochMilli() + 172800000)
					sendsms(sched.patient);
			}
		}
	}
	public void run(){
		try{
			Thread.sleep(1000);
			while(true){
				if(Database.running == false)
					break;
				checker();
				Thread.sleep(10000);
			}
		}catch(InterruptedException i){
		}
	}

	public void sendsms(Patient p){
	}
}
				
class PatientManagementSystem{
	public static void main(String args[])throws IOException,ParseException{
		String choise=null,ch=null;
		SMSSender sms = new SMSSender();
		Thread td = new Thread(sms);
		td.start();
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Patient Management System:");
		do{
			try{
			System.out.println("1.Login");
			System.out.println("2.Signup");
			System.out.println("3.Exit");
			System.out.print("Enter your choise:");
			System.out.flush();
			choise = bin.readLine();
			//System.out.print(choise);
			if(choise.charAt(0) == '1'){
				String che;
				System.out.print("Patient(1) or Doctor(2)?");
				che = bin.readLine();
				if(che.charAt(0) == '1'){
					Login login = new Login();
					login.GetCredentials();
					Patient pat = null;
					if((pat=login.PatientAuthenticate())!=null){
						PatientOps ops=new PatientOps(pat);
						do{
						System.out.println("Authentication Successful");
						System.out.println("1.ScheduleAppointment");
						System.out.println("2.ViewDetails");
						System.out.println("3.Update Info");
						System.out.println("4.ViewAll appointments");
						System.out.println("5.Exit");
						System.out.print("Enter your choise:");
						che = bin.readLine();
						if(che.charAt(0) == '1'){
							ops.ScheduleAppointment();
						}
						else if(che.charAt(0) == '2'){
							ops.ViewDetails();
						}
						else if(che.charAt(0) == '3'){
							ops.UpdateInfo();
						}
						else if(che.charAt(0) == '4'){
							ops.ViewAppointments();
						}
						}while(che.charAt(0) != '5');
					}
					else{
						System.out.println("Authentication denied");
					}

				}
				else if(che.charAt(0) == '2'){
					Login login = new Login();
					login.GetCredentials();
					Doctor doc = null;
					String chee=null;
					if((doc = login.DoctorAuthenticate())!=null){
						System.out.println("Authentication Successful");
						DoctorOps ops = new DoctorOps(doc);
						do{
							System.out.println("1.ViewDetails");
							System.out.println("2.UpdateMedicalRecords");
							System.out.println("3.CheckAppointments");
							System.out.println("4.ViewPatientDetails");
							System.out.println("5.Exit");
							System.out.print("Enter your choise:");
							chee = bin.readLine();
							if(chee.charAt(0) == '1'){
								ops.ViewDetails();
							}
							else if(chee.charAt(0) == '2'){
								ops.UpdateMedicalRecord();
							}
							else if(chee.charAt(0) == '3'){
								ops.CheckAppointment();
							}
							else if(chee.charAt(0) == '4'){
								ops.ViewMedicalrecord();
							}
						}while(chee.charAt(0)!= '5');
					}
					else{
						System.out.println("Authentication denied");
					}
				}

				
			}
			else if(choise.charAt(0) == '2'){
					System.out.println("Patient(1) or Doctor(2)?");
					System.out.print("Enter your choise:");
					System.out.flush();
					ch = bin.readLine();
					//System.out.println("CH="+ch);
					if(ch.charAt(0) == '1'){
						PatientSignUp signup = new PatientSignUp();
						signup.signup();
					}
					else{
						DoctorSignUp signup = new DoctorSignUp();
						signup.signup();
					}
			} 
			else if(choise.charAt(0) == '3'){
				Database d = new Database();
				Database.running = false;
				try{
					td.join();
				}catch(InterruptedException e){
				}
				d.commit();
			}
			}
			catch(StringIndexOutOfBoundsException e){
			}
		}while(choise.charAt(0) != '3');
	}
}
