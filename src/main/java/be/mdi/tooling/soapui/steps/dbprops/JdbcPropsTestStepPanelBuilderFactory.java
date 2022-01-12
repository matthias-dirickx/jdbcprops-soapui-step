package be.mdi.tooling.soapui.steps.dbprops;

import com.eviware.soapui.impl.EmptyPanelBuilder;
import com.eviware.soapui.model.PanelBuilder;
import com.eviware.soapui.model.util.PanelBuilderFactory;
import com.eviware.soapui.ui.desktop.DesktopPanel;

/**
 * Creates the DesktopPanel - could be extended to also support the bottom left Properties tab
 */

public class JdbcPropsTestStepPanelBuilderFactory implements PanelBuilderFactory<JdbcPropsTestStep>
{
	public PanelBuilder<JdbcPropsTestStep> createPanelBuilder()
	{
		return new EMailTestStepPanelBuilder();
	}

	public Class<JdbcPropsTestStep> getTargetModelItem()
	{
		return JdbcPropsTestStep.class;
	}

	public static class EMailTestStepPanelBuilder extends EmptyPanelBuilder<JdbcPropsTestStep>
	{
		@Override
		public DesktopPanel buildDesktopPanel( JdbcPropsTestStep modelItem )
		{
			return new JdbcPropsTestStepDesktopPanel( modelItem );
		}

		@Override
		public boolean hasDesktopPanel()
		{
			return true;
		}
	}
}
