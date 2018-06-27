package xmu.springBootMybatis.entity;

import java.io.Serializable;
import org.springframework.boot.autoconfigure.domain.EntityScan;    

@EntityScan
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Long uid;    
    private String name; 
    private String password;
    private String phoneNumber;
    private String mail;
    
    public Long getId() {    
        return uid;    
    }    
    
    public void setId(Long id) {    
        this.uid = id;    
    }    
    
    public String getName() {    
        return name;    
    }    
    
    public void setName(String name) {    
        this.name = name;    
    }    
    
    @Override    
    public boolean equals(Object o) {    
        if (this == o) return true;    
        if (o == null || getClass() != o.getClass()) return false;    
    
        User user = (User) o;    
    
        if (uid != null ? !uid.equals(user.uid) : user.uid != null) return false;    
    
        return true;    
    }    
    
    @Override    
    public int hashCode() {    
        return uid != null ? uid.hashCode() : 0;    
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}  
	
} 