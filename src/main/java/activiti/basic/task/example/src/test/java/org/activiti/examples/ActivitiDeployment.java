package activiti.basic.task.example.src.test.java.org.activiti.examples;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;

/**
 * @description 创建流程/部署流程
 * @author GuKang 2022/08/04
 * */
public class ActivitiDeployment {
    public static void main(String []args){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 2.创建ProcesEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 部署流程
        processDeployment(processEngine);
    }

    /**
     * @description 流程部署
     * @author GuKang 2022/08/04
     */
    public static void processDeployment(ProcessEngine processEngine){
        // 部署流程定义就是要将上边绘制的图形即流程定义（.bpmn）部署在工作流程引擎 activiti 中
        // 2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/test.bpmn")
                .name("编制校对审批流程")
                .deploy();

        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }
}
