package org.hms.accounting.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hms.accounting.common.BasicEntity;
import org.hms.accounting.common.ContactInfo;
import org.hms.accounting.common.TableName;
import org.hms.accounting.role.Role;
import org.hms.accounting.system.branch.Branch;
import org.hms.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.USER)
@TableGenerator(name = "USERS_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "USERS_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.name ASC"),
		@NamedQuery(name = "User.findByUsercode", query = "SELECT u FROM User u WHERE u.userCode = :userCode"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findUserId", query = "SELECT u.id FROM User u ORDER BY u.name ASC") })
@EntityListeners(IDInterceptor.class)
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USERS_GEN")
	private String id;

	private String userCode;

	private String password;

	private String name;

	private boolean isAdmin;

	private boolean isEditAllow;

	private boolean isDeleteAllow;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLEID", referencedColumnName = "ID")
	private Role role;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Embedded
	private ContactInfo contactInfo;

	@Version
	private int version;

	@Embedded
	private BasicEntity basicEntity;

	public User() {
		this.contactInfo = new ContactInfo();
	}

	public User(String id, String usercode, String password, String name) {
		this.id = id;
		this.userCode = usercode;
		this.password = password;
		this.name = name;
	}

}
