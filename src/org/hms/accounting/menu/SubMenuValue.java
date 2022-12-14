package org.hms.accounting.menu;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.hms.accounting.common.BasicEntity;
import org.hms.accounting.common.TableName;
import org.hms.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.SUBMENUVALUE)
@TableGenerator(name = "SUBMENUVALUE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "SUBMENUVALUE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "SubMenuValue.findAll", query = "SELECT m FROM SubMenuValue m "),
		@NamedQuery(name = "SubMenuValue.findBySubMenuId", query = "SELECT m FROM SubMenuValue m WHERE m.subMenu.id = :subMenuId"),
		@NamedQuery(name = "SubMenuValue.findById", query = "SELECT m FROM SubMenuValue m WHERE m.id = :id") })
@EntityListeners(IDInterceptor.class)
@Data
public class SubMenuValue implements Serializable, Comparable<SubMenuValue> {

	private static final long serialVersionUID = -5175053478980727255L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUBMENUVALUE_GEN")
	private String id;
	private boolean flag;

	@OneToOne
	@JoinColumn(name = "SUBMENUID", referencedColumnName = "ID")
	private SubMenu subMenu;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "SUBMENUVALUEID", referencedColumnName = "ID")
	private List<MenuItemValue> menuItemValueList;

	@Embedded
	private BasicEntity basicEntity;

	@Version
	private int version;

	public SubMenuValue() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((subMenu == null) ? 0 : subMenu.hashCode());
		result = prime * result + ((basicEntity == null) ? 0 : basicEntity.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubMenuValue other = (SubMenuValue) obj;
		if (flag != other.flag)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subMenu == null) {
			if (other.subMenu != null)
				return false;
		} else if (!subMenu.equals(other.subMenu))
			return false;
		if (basicEntity == null) {
			if (other.basicEntity != null)
				return false;
		} else if (!basicEntity.equals(other.basicEntity))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public int compareTo(SubMenuValue other) {
		return subMenu.getPriority() - other.getSubMenu().getPriority();
	}

}