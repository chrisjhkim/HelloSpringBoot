package com.chrisjhkim.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQuery(name = "findWithName", query = "from MyData where name like :fname")
@Table(name="mydata")
@XmlRootElement
public class MyData {

//	@OneToMany(cascade = CascadeType.ALL)
//	@Column(nullable = true)
//	private List<MsgData> msgdatas;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@NotNull
	private long id;
	
	@Column(length = 50, nullable = false)
	@NotBlank
	private String name;

	@Column(length = 200, nullable = true)
	@Email
	private String mail;
	
	@Column(nullable = true)
	@Min(0)
	@Max(200)
	@NotNull
	private Integer age;
	
	@Column(nullable = true)
	@Phone(onlyNumber = true)
	private String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
//	public List<MsgData> getMsgdatas() {
//		return msgdatas;
//	}
//
//	public void setMsgdatas(List<MsgData> msgdatas) {
//		this.msgdatas = msgdatas;
//	}

	@Override
	public String toString() {
		return "MyData [id=" + id + ", name=" + name + ", mail=" + mail + ", age=" + age + ", memo=" + memo + "]";
	}
}
