package com.idev.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.idev.util.DateUtil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "ideas")
@EntityListeners(value = AuditingEntityListener.class)
public class Idea implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column
	private String target;
	
	@Column
	private String merit;
	
	@Column
	private String diff;
	
	@Column
	private String conflict;
	
	@Column
	private String goal;
	
	@Column
	private String category;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createDate;
	
	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateDate;
	
	public String getUpdateDateStr() {
		if(updateDate == null) {
			return "";
		}
		return DateUtil.format(updateDate,"yyyy-MM-dd HH:mm");
	}
	
}
