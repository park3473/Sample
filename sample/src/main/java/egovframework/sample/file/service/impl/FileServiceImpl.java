package egovframework.sample.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.sample.file.model.FileVo;
import egovframework.sample.file.service.FileService;

@Service("FileService")
@Transactional
public class FileServiceImpl implements FileService {

	@Resource(name="FileMapper")
	private FileMapper fileMapper;

	@Override
	public void setFileData(FileVo filevo) {
		
		fileMapper.setFileData(filevo);
		
	}

	@Override
	public List<?> getFileList(FileVo filevo) {
		
		List<?> filelist = fileMapper.getFileList(filevo);
		
		return filelist;
	}

	@Override
	public void setFileUpdate(FileVo fileVo) {
		
		fileMapper.setFileUpdate(fileVo);
		
	}

	
	
	
}
