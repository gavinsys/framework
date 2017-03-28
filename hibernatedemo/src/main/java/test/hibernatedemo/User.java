package test.hibernatedemo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="t_user") 
public class User implements java.io.Serializable {

	@Id  
	@Column  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(  
	name = "generator",//定义名为generator的生成策略  
	allocationSize = 1//每次sequence加1  
	//name="seq_a"//引用名为seq_a的sequence  
	)  
    private int userId;
    private String username;
    private String createdBy;
    private Date createdDate;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String username, String createdBy,
            Date createdDate) {
        this.userId = userId;
        this.username = username;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
