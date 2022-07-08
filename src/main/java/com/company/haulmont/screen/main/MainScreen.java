package com.company.haulmont.screen.main;

import com.company.haulmont.app.service.CreateTemplateServiceBean;
import com.company.haulmont.app.service.CreateTriggerServiceBean;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.component.AppWorkArea;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Window;
import io.jmix.ui.component.mainwindow.Drawer;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@UiController("MainScreen")
@UiDescriptor("main-screen.xml")
@Route(path = "main", root = true)
public class MainScreen extends Screen implements Window.HasWorkArea{

    @Autowired
    private CreateTemplateServiceBean createTemplateServiceBean;

    @Autowired
    private CreateTriggerServiceBean createTriggerServiceBean;

    @Subscribe
    public void onAfterShow1(AfterShowEvent event) throws IOException {
        String TEMPLATE1_CODE = "create-contract1";
        String TEMPLATE_SUBJECT = "Important information from the insurance company!";
        createTemplateServiceBean.createTemplate("Temp1", TEMPLATE_SUBJECT, TEMPLATE1_CODE,"/com/company/haulmont/template/template1.html");
        String TEMPLATE2_CODE = "edit-contract2";
        createTemplateServiceBean.createTemplate("Temp2", TEMPLATE_SUBJECT, TEMPLATE2_CODE,"/com/company/haulmont/template/template2.html");
        String TEMPLATE3_CODE = "ending-contract3";
        createTemplateServiceBean.createTemplate("Temp3", TEMPLATE_SUBJECT, TEMPLATE3_CODE,"/com/company/haulmont/template/template3.html");

        createTriggerServiceBean.createTrigger();
    }

    @Autowired
    private ScreenTools screenTools;

    @Autowired
    private AppWorkArea workArea;
    @Autowired
    private Drawer drawer;
    @Autowired
    private Button collapseDrawerButton;

    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

    @Subscribe("collapseDrawerButton")
    private void onCollapseDrawerButtonClick(Button.ClickEvent event) {
        drawer.toggle();
        if (drawer.isCollapsed()) {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_RIGHT);
        } else {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_LEFT);
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        screenTools.openDefaultScreen(
                UiControllerUtils.getScreenContext(this).getScreens());

        screenTools.handleRedirect();
    }
}
