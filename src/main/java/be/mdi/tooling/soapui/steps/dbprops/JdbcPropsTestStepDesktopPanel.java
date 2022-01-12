package be.mdi.tooling.soapui.steps.dbprops;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import be.mdi.tooling.soapui.steps.dbprops.enums.AxisType;
import be.mdi.tooling.soapui.steps.dbprops.enums.ScopeType;
import com.eviware.soapui.analytics.Analytics;
import com.eviware.soapui.analytics.SoapUIActions;
import com.eviware.soapui.impl.support.actions.ShowOnlineHelpAction;
import com.eviware.soapui.impl.wsdl.panels.support.MockTestRunContext;
import com.eviware.soapui.impl.wsdl.panels.support.MockTestRunner;
import com.eviware.soapui.impl.wsdl.teststeps.JdbcRequestTestStep;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.components.JXToolBar;
import com.eviware.soapui.support.components.SimpleBindingForm;
import com.eviware.soapui.ui.support.ModelItemDesktopPanel;
import com.jgoodies.binding.PresentationModel;

/**
 * Simple DesktopPanel that provides a basic UI for configuring the EMailTestStep. Should perhaps be improved with
 * a "Test" button and a log panel.
 */

public class JdbcPropsTestStepDesktopPanel extends ModelItemDesktopPanel<JdbcPropsTestStep>
{
	/**
	 * Default serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	protected JdbcPropsTestStep jdbcPropsTestStep;
	
	private PresentationModel<JdbcPropsTestStep> jdbcPropsForm;
    
    private JButton submitButton;
    private JButton cancelButton;
    
    
	public JdbcPropsTestStepDesktopPanel( JdbcPropsTestStep modelItem )
	{
		super( modelItem );
		this.jdbcPropsTestStep = modelItem;
		
		this.setSize(500, 500);
		buildUI();
	}

	private void buildUI()
	{
		add( buildToolbar(), BorderLayout.NORTH );
		add( createJdbcPropsBaseInput(), BorderLayout.CENTER );
		
	}
	
    private Component buildToolbar() {
    	
        JXToolBar toolbar = UISupport.createToolbar();
        
        toolbar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        
        submitButton = UISupport.createToolbarButton(new SubmitAction(), true);
        toolbar.add(submitButton);
        
        cancelButton = UISupport.createToolbarButton(new CancelAction(), false);
        toolbar.add(cancelButton);
        
        toolbar.addGlue();
        
        toolbar.add(UISupport.createToolbarButton(new ShowOnlineHelpAction("")));

        return toolbar;
    }
	
	private List<String> getJdbcTestSteps() {
		List<TestStep> stepList = getModelItem().getTestCase().getTestStepList();
		List<String> jdbcList = new ArrayList<String>();
		
		for( TestStep step : stepList ) {
			if( step instanceof JdbcRequestTestStep ) {
				jdbcList.add(step.getName());
			}
		}
		return jdbcList;
	}
	
	private JComponent createJdbcPropsBaseInput()
	{
		jdbcPropsForm = new PresentationModel<JdbcPropsTestStep>( getModelItem() );
		SimpleBindingForm form = new SimpleBindingForm( jdbcPropsForm );
		form.addSpace(10);
		form.appendComboBox( 
				"sourceStep",
				"Source Test Step",
				getJdbcTestSteps().toArray(),
				"Select the JDBC Test Step from within this Test Case."
				);
		form.addSpace(10);
		form.appendTextField(
				"suffix",
				"Suffix",
				"The suffix to assign to distinquish multiple items with the same root column name."
				);
		form.addSpace(10);
		form.appendComboBox("axisType", "Axis", AxisType.values(), "The axis of the property labels used for the creation. \n"
				+ "'Column' uses the column names as property names. \n"
				+ "'Row' use the values in column 1 as labels, and the subsequent columns as series of values.");

		form.addSpace(10);
		
		form.appendSeparator();

		form.addSpace(10);
		
		form.appendComboBox(
				"validityScope",
				"Scope of Properties",
				ScopeType.values(),
				"The scope of the properties."
				);
		form.addSpace(10);
		form.appendCheckBox(
				"limitOne",
				"Limit 1",
				"Limit the properties to the first returned result."
				);
		form.addSpace(10);
		form.appendCheckBox(
				"cleanPropertiesAfterRun",
				"Delete Properties after Run",
				"Clean all created properties after run."
				);
		
		form.getPanel().setBorder( BorderFactory.createEmptyBorder( 3, 3, 3, 3 ) );

		return new JScrollPane( form.getPanel() );
	}

    public class SubmitAction extends AbstractAction {
        /**
		 * Added default serial ID to shut up the warnings.
		 */
		private static final long serialVersionUID = 1L;

		public SubmitAction() {
            putValue(Action.SMALL_ICON, UISupport.createImageIcon("/run.png"));
            putValue(Action.SHORT_DESCRIPTION, "Runs the JDBC Properties step.");
            putValue(Action.ACCELERATOR_KEY, UISupport.getKeyStroke("alt ENTER"));
        }

        public void actionPerformed(ActionEvent e) {
        	submitButton.setEnabled(false);
            cancelButton.setEnabled(true);

            Analytics.trackAction(SoapUIActions.RUN_TEST_STEP_FROM_PANEL, "RequestType", "JDBC Properties");
            
            MockTestRunner mockRunner = new MockTestRunner(jdbcPropsTestStep.getTestCase());
            MockTestRunContext context = new MockTestRunContext(mockRunner, jdbcPropsTestStep);
            jdbcPropsTestStep.run(mockRunner, context);
            
            submitButton.setEnabled(true);
            cancelButton.setEnabled(false);
        }
    }

    private class CancelAction extends AbstractAction {
        /**
		 * Added default serial ID to shut up the warnings.
		 */
		private static final long serialVersionUID = 1L;

		public CancelAction() {
            super();
            putValue(Action.SMALL_ICON, UISupport.createImageIcon("/cancel_request.png"));
            putValue(Action.SHORT_DESCRIPTION, "Aborts ongoing request");
            putValue(Action.ACCELERATOR_KEY, UISupport.getKeyStroke("alt X"));
        }

        public void actionPerformed(ActionEvent e) {
            cancelButton.setEnabled(false);
            submitButton.setEnabled(true);
            setEnabled(true);
        }
    }
}
