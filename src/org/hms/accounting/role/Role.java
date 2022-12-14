package org.hms.accounting.role;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.hms.accounting.common.BasicEntity;
import org.hms.accounting.common.TableName;
import org.hms.accounting.menu.MainMenuValue;
import org.hms.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.ROLE)
@TableGenerator(name = "ROLE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "ROLE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "Role.findAll", query = "SELECT m FROM Role m ORDER BY m.name ASC"),
		@NamedQuery(name = "Role.findById", query = "SELECT m FROM Role m WHERE m.id = :id") })
@EntityListeners(IDInterceptor.class)
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ROLE_GEN")
	private String id;
	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ROLEID", referencedColumnName = "ID")
	private List<MainMenuValue> mainMenuValueList;

	@Embedded
	private BasicEntity basicEntity;

	@Version
	private int version;

	public Role() {
	}

}
