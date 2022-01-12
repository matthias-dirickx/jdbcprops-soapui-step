package be.mdi.tooling.soapui.steps.dbprops;

import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.eviware.soapui.impl.wsdl.teststeps.TestRequest;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlMessageAssertion;
import com.eviware.soapui.impl.wsdl.teststeps.assertions.TestAssertionRegistry.AssertableType;
import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.model.iface.Attachment;
import com.eviware.soapui.model.iface.Interface;
import com.eviware.soapui.model.iface.MessagePart;
import com.eviware.soapui.model.iface.Operation;
import com.eviware.soapui.model.iface.Response;
import com.eviware.soapui.model.iface.Submit;
import com.eviware.soapui.model.iface.SubmitContext;
import com.eviware.soapui.model.iface.SubmitListener;
import com.eviware.soapui.model.settings.Settings;
import com.eviware.soapui.model.support.AbstractModelItem;
import com.eviware.soapui.model.support.AnimatableItem;
import com.eviware.soapui.model.testsuite.AssertionsListener;
import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestStep;

public class JdbcProps extends AbstractModelItem implements TestRequest, AnimatableItem {

	@Override
	public void addSubmitListener(SubmitListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dependsOn(ModelItem arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Attachment[] getAttachments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOperation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagePart[] getRequestParts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessagePart[] getResponseParts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTimeout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSubmitListener(SubmitListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEndpoint(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Submit submit(SubmitContext arg0, boolean arg1) throws SubmitException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelItem getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Settings getSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestAssertion addAssertion(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAssertionsListener(AssertionsListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TestAssertion cloneAssertion(TestAssertion arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAssertableContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAssertableContentAsXml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssertableType getAssertableType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestAssertion getAssertionAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestAssertion getAssertionByName(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAssertionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TestAssertion> getAssertionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssertionStatus getAssertionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, TestAssertion> getAssertions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultAssertableContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interface getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelItem getModelItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestAssertion moveAssertion(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAssertion(TestAssertion arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAssertionsListener(AssertionsListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIcon(ImageIcon arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Response getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestStep getTestStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WsdlMessageAssertion importAssertion(WsdlMessageAssertion arg0, boolean arg1, boolean arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDiscardResponse() {
		// TODO Auto-generated method stub
		return false;
	}

}
