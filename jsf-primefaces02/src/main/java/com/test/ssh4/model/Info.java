package com.test.ssh4.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_INFO", schema = "ssh4")
public class Info implements Serializable
{
	private static final long serialVersionUID = 3007003187520480357L;
	private Integer id;
    private String title;
    private Date time;
    @Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "TITLE", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
