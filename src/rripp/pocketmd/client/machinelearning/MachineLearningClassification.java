package rripp.pocketmd.client.machinelearning;

public interface MachineLearningClassification {
	
	public void Predict();
	/*
	 * getting the classification for the instance
	 */
	public Integer getClassification();
	/*
	 * Getting the class membership distribution
	 */
	public Double getClassProbability();
}
