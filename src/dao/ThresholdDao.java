package dao;

import model.Threshold;

public interface ThresholdDao {
	void update(int id,double min,double max) throws Exception;
}
