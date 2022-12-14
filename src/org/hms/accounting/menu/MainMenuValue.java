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
@Table(name = TableName.MAINMENUVALUE)
@TableGenerator(name = "MAINMENUVALUE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MAINMENUVALUE_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MainMenuValue.findAll", query = "SELECT m FROM MainMenuValue m "),
		@NamedQuery(name = "MainMenuValue.findByMainMenuId", query = "SELECT m FROM MainMenuValue m WHERE m.mainMenu.id = :mainMenuId"),
		@NamedQuery(name = "MainMenuValue.findById", query = "SELECT m FROM MainMenuValue m WHERE m.id = :id") })
@EntityListeners(IDInterceptor.class)
@Data
public class MainMenuValue implements Serializable, Comparable<MainMenuValue> {

	private static final long serialVersionUID = 1983941086506677606L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MAINMENUVALUE_GEN")
	private String id;
	private boolean flag;

	@OneToOne
	@JoinColumn(name = "MAINMENUID", referencedColumnName = "ID")
	private MainMenu mainMenu;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "MAINMENUVALUEID", referencedColumnName = "ID")
	private List<SubMenuValue> subMenuValueList;

	@Embedded
	private BasicEntity basicEntity;
	@Version
	private int version;

	public MainMenuValue() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (flag ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mainMenu == null) ? 0 : mainMenu.hashCode());
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
		MainMenuValue other = (MainMenuValue) obj;
		if (flag != other.flag)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mainMenu == null) {
			if (other.mainMenu != null)
				return false;
		} else if (!mainMenu.equals(other.mainMenu))
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
	public int compareTo(MainMenuValue other) {
		return mainMenu.getPriority() - other.getMainMenu().getPriority();
	}
}
