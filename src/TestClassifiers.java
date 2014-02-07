import org.junit.Test;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;
import rripp.pocketmd.client.datahendler.Deserialization;
import junit.framework.TestCase;


public class TestClassifiers extends TestCase {
	@Test
	public void testSVNClassifier() {
		//for (ALLTHEMODELS){
			//for (All possivle values){
				Classifier svm = (Classifier) Deserialization.deserialize("libsvm.LibSVM");
				Instance instance = new SparseInstance();
            	instance.put(0, 193.0);
            	instance.put(0, 9.0);
            	instance.add(instance);
            	Object prediction = svm.classify(instance);
			//}
		//}
	}
	
	
}
