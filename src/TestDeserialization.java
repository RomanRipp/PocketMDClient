import static org.junit.Assert.*;

import net.sf.javaml.classification.Classifier;

import org.junit.Test;

import rripp.pocketmd.client.datahendler.Deserialization;


public class TestDeserialization {

	@Test
	public void test() {
		//for (allfiles) {
			assertNotNull((Classifier) Deserialization.deserialize("libsvm.LibSVM"));
		//}
	}

}
