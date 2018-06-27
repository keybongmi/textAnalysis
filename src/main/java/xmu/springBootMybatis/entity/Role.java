package xmu.springBootMybatis.entity;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public Role() {
	}
	
	public Role(Integer id,String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
