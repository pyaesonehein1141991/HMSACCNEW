package org.hms.accounting.system.systempost.service.interfaces;

import java.util.Date;
import java.util.List;

import org.hms.accounting.system.systempost.SystemPost;

public interface ISystemPostService {
	public List<SystemPost> findAll();
	public SystemPost findbyPostingName(String postingName);
	public void updatePostingDateByName(Date postingDate, String postingName);
}
