package kr.bit.model;

//회원(object) -> MemberVO
public class MemberVO {

	private int num;
	private String id;
	private String pass ;
	private String name ;
	private int age;	
	private String email; 
	private String phone ;
	private String filename;
	
	
	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public MemberVO() {
		
	}

	
    //회원가입할 때는 Num을 사용하지 않기 때문에 num을 제외
	public MemberVO(String id, String pass, String name, int age, String email, String phone) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}
	
	//데이터 베이스에서 가져오거나 넣을 때는 Num 필드가 필요하다.
	public MemberVO(int num, String id, String pass, String name, int age, String email, String phone) {
		super();
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", id=" + id + ", pass=" + pass + ", name=" + name + ", age=" + age + ", email="
				+ email + ", phone=" + phone + ", filename=" + filename + "]";
	}
	
}
