package be.mdi.tooling.soapui.steps.dbprops;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Test;

import com.eviware.soapui.config.TestStepConfig;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.teststeps.JdbcRequestTestStep;
import com.eviware.soapui.support.SoapUIException;


public class JdbcPropDesktopPanelTest {
	
	@Test
	public void theDesktopPanelLafunchesTest() throws XmlException, IOException, SoapUIException, InterruptedException {
		// Create project & load dummy project
		// Add the other items
		WsdlProject p = new WsdlProject();
		p.loadProject(getClass().getClassLoader().getResourceAsStream("test-project-empty.xml"));
		WsdlTestSuite ts = p.addNewTestSuite("testsuite");
		WsdlTestCase tc = ts.addNewTestCase("newtestcase");
		JdbcRequestTestStep jdbcTestStep = new JdbcRequestTestStep(tc, TestStepConfig.Factory.newInstance(), false);
		
		// Create the step with the created items
		JdbcPropsTestStep step = new JdbcPropsTestStep(tc, TestStepConfig.Factory.newInstance(), false);
		System.out.println(String.format("The amount of JDBC test steps is: %s", Integer.toString(step.getTestCase().getTestStepList().size())));
		JdbcPropsTestStepDesktopPanel dp = new JdbcPropsTestStepDesktopPanel(step);
		
		//Pack the panel in a frame
		JFrame f = new JFrame();
		f.add(dp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 300);
		f.setVisible(true);
		
		// Save the image as a component in PNG format for verification.
		BufferedImage img = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
		f.paint(img.getGraphics());
		File outputfile = new File("target/screensnaps/jdbcpropsscreen.png");
		File outputDir = new File("target/screensnaps");
		if (!outputDir.exists()) outputDir.mkdirs();
		ImageIO.write(img, "png", outputfile);
	}

}
