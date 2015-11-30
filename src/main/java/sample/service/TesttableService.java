package sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.TesttableMapper;
import sample.model.Testtable;

@Service
public class TesttableService {
	@Autowired
	private TesttableMapper testtableMapper;

	public List<Testtable> getTesttableAll() {
		List<Testtable> testtableList = testtableMapper.getTesttableAll();

		return testtableList;
	}

	public int insertOne(Testtable testtable) {
		return testtableMapper.insert(testtable);
	}
}