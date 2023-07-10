package egovframework.sample.admin.select.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.sample.admin.select.model.AdminSelectVo;

@Mapper("adminSelectMapper")
public interface AdminSelectMapper {

	public List<?> getSelectAllList(AdminSelectVo adminSelectVo);

	public void setSelectDelete(AdminSelectVo adminSelectVo);

	public void setSelectUpdate(AdminSelectVo adminSelectVo);

	public void setSelectInsert(AdminSelectVo adminSelectVo);

	public AdminSelectVo getSelectView(AdminSelectVo adminSelectVo);

	public List<?> getSelectList(String value);

	public void setSelectAllDelete(AdminSelectVo adminSelectVo);

}
