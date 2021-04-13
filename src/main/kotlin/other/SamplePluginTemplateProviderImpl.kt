package other
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.concision.activity.concisionTemplate

/**
 * @CreateDate:     2021/4/1
 * @Author:         Creator
 * @Description:
 */
class SamplePluginTemplateProviderImpl : WizardTemplateProvider(){
    override fun getTemplates(): List<Template> = listOf(
            concisionTemplate
    )
}
