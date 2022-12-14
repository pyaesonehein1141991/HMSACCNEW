package org.hms.accounting.menu;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.hms.accounting.common.BasicEntity;
import org.hms.accounting.common.TableName;
import org.hms.java.component.idgen.service.IDInterceptor;

import lombok.Data;

@Entity
@Table(name = TableName.MENUITEMVALUE)
@TableGenerator(name = "MENUITEMVALUE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MENUITEMVALUE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MenuItemValue.findAll", query = "SELECT m FROM MenuItemValue m "),
		@NamedQuery(name = "MenuItemValue.findByMenuItemId", query = "SELECT m FROM MenuItemValue m WHERE m.menuItem.id = :menuItemId"),
		@NamedQuery(name = "MenuItemValue.findById", query = "SELECT m FROM MenuItemValue m WHERE m.id = :id") })
@EntityListeners(IDInterceptor.class)
@Data
public class MenuItemValue implements Serializable, Comparable<MenuItemValue> {
	private static final long serialVersionUID = -6321603825355259602L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MENUITEMVALUE_GEN")
	private String id;
	private boolean flag;

	@OneToOne
	@JoinColumn(name = "MENUITEMID", referencedColumnName = "ID")
	private MenuItem menuItem;

	@Embedded
	private BasicEntity basicEntity;
	@Version
	private int version;

	public MenuItemValue() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((menuItem == null) ? 0 : menuItem.hashCode());
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
		MenuItemValue other = (MenuItemValue) obj;
		if (flag != other.flag)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menuItem == null) {
			if (other.menuItem != null)
				return false;
		} else if (!menuItem.equals(other.menuItem))
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
	public int compareTo(MenuItemValue other) {
		return menuItem.getPriority() - other.getMenuItem().getPriority();
	}

}
