package bean.task;

import bean.executor.Expert;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:41
 */
public class ExpertTask extends SuperviseTask{
    //专家抽检任务
    private Expert expert;

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public ExpertTask(Expert expert) {
        this.expert = expert;
    }
}
