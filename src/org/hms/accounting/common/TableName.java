package org.hms.accounting.common;

public class TableName {

	/**
	 * Admin Management Table Name
	 */
	public static final String USER = "USERS";

	/**
	 * Permission management Table Name
	 */
	public static final String WEBPAGE = "WEBPAGE";
	public static final String ROLE = "USER_ROLE";
	public static final String USER_ROLE = "USER_ROLE";
	public static final String ROLE_WEBPAGE = "ROLEWEBPAGE";

	/**
	 * Branch Table Name
	 */
	public static final String BRANCH = "BRANCH";

	/**
	 * Chart Of Account Table Name
	 */
	public static final String COA = "COA";

	/**
	 * Currency Chart of Account Table Name
	 */
	public static final String CCOA = "CCOA";

	/**
	 * Previous Currency Chart Of Account History Table Name
	 */
	public static final String CCOA_HISTORY = "PREV_CCOA";

	/**
	 * Previous Currency Chart Of Account History Table Name
	 */
	public static final String CCOA_CLONE = "CLONE_CCOA";

	/**
	 * Currency Table Name
	 */
	public static final String CUR = "CUR";

	/**
	 * Department Table Name
	 */
	public static final String DEPARTMENT = "DEPTCODE";

	/**
	 * Allocation table Name for viewing department profit and loss
	 */
	public static final String ALLOCATECODE = "ALLOCATE_CODE";

	/**
	 * COA Setup Table Name
	 */
	public static final String COASETUP = "COASETUP";

	/**
	 * Format file table Name for Report Format Entry.
	 */
	public static final String FORMATFILE = "FORMATFILE";

	/**
	 * Rate Info table Name
	 */
	public static final String RATEINFO = "RATEINFO";

	/**
	 * Voucher Transaction Type Table Name
	 */
	public static final String TRANTYPE = "TRANTYPE";

	/**
	 * TLF Table Name
	 */
	public static final String TLF = "TLF";

	/**
	 * TLF History Table Name
	 */
	public static final String TLFHIST = "TLF_HIST";

	/**
	 * TLF History Table Name
	 */
	public static final String TLFCLONE = "TLF_CLONE";

	/**
	 * System Post Table Name
	 */
	public static final String SYSTEMPOST = "SYSPOST";

	/**
	 * Setup Table Name
	 */
	public static final String SETUP = "SETUP";

	/**
	 * Setup History Table Name
	 */
	public static final String SETUP_HISTORY = "PREV_SETUP";

	/**
	 * Clean Cash View Table Name
	 */
	public static final String CLEANCASHVIEW = "VWT_CLEANCASH";

	/**
	 * View table entity for bank cash scroll
	 */
	public static final String VW_BANKCASH = "VW_BANKCASH";

	/**
	 * View table entity for ccoa and prev_ccoa union all
	 */
	public static final String VW_CCOA = "VW_CCOA";

	/**
	 * View table entity for tlf and tlfhist union all
	 */
	public static final String VW_TLF = "VW_TLF";

	/**
	 * View table entity for tlf and tlfhist union all
	 */
	public static final String VW_TLFCLONE = "VW_TLFCLONE";

	/* Gain and Loss */
	public static final String EXCHANGECONFIG = "EXCHANGECONFIG";

	public static final String CUSTOM_ID_GEN = "CUSTOM_ID_GEN";

	/* Menu */
	public static final String MAINMENU = "MAINMENU";
	public static final String SUBMENU = "SUBMENU";
	public static final String MENUITEM = "MENUITEM";
	public static final String MAINMENUVALUE = "MAINMENUVALUE";
	public static final String SUBMENUVALUE = "SUBMENUVALUE";
	public static final String MENUITEMVALUE = "MENUITEMVALUE";
}
