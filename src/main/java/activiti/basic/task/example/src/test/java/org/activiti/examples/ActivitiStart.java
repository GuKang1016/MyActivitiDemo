package activiti.basic.task.example.src.test.java.org.activiti.examples;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @description 启动流程实例
 * @author GuKang 2022/08/04
 * */
public class ActivitiStart {

    public static void main(String []args){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 2.创建ProcesEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
        // 启动流程
        startProcess(processEngine,"Process_1");
    }


    /**
     * @description 启动流程实例
     * @author GuKang 2022/08/04
     * */
    public static void startProcess(ProcessEngine processEngine,String key){
        // 流程定义部署在 activiti 后就可以通过工作流管理业务流程了
        RuntimeService runtimeService=processEngine.getRuntimeService();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(key);
        System.out.println(processInstance.getDeploymentId());
        System.out.println(processInstance.getDescription());
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessInstanceId());
        System.out.println(processInstance.getId());


    }
}
