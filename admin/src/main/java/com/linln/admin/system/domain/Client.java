package com.linln.admin.system.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "sys_client")
@Data
public class Client {
	/*
	 * 客户:姓名,电话,资源来源,业务类型,等级,跟踪计划,跟踪计划时间,备注
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	// 姓名
	private String name;
	// 电话
	private String tel;
	// 公司名称
	private String companyname;
	// 资源来源
	private String source;
	// 业务类型
	private String businesstype;
	// 意向等级
	private String grade;
	// 跟踪计划等级
	private String trackinggrade;
	// 跟踪时间
	private String trackingtime;
	// 备注
	private String memorandum;
	// 属于准客户还是公共资源
	private String genre;
	// 属于哪个员工
	@ManyToOne
	@JoinColumn(name = "client_user_id")
	private User user;

	public Client(int id, String name, String tel, String companyname, String source, String businesstype, String grade,
			String trackinggrade, String trackingtime, String memorandum, String genre, User user) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.companyname = companyname;
		this.source = source;
		this.businesstype = businesstype;
		this.grade = grade;
		this.trackinggrade = trackinggrade;
		this.trackingtime = trackingtime;
		this.memorandum = memorandum;
		this.genre = genre;
		this.user = user;
	}

	public Client() {
	}

}