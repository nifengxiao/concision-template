package other.concision.activity

import com.android.tools.idea.wizard.template.*
import java.io.File

/**
 * @CreateDate:     2021/4/1
 * @Author:         Creator
 * @Description:    菜单
 */
val concisionTemplate
    get() = template {
        revision = 1
        name = "concisionTemplate"
        description = "创建concision相关的mvvm文件"
        minApi = 9
        minBuildApi = 15

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)

        //包名
        val packageName = stringParameter {
            name = "Package name"
            default = "com.mycompany.myapp"
            constraints = listOf(Constraint.PACKAGE)
        }

        //功能文件名
        val functionDir = stringParameter {
            name = "function Name"
            default = "functionName"
            help = "功能名,填写后相应功能会自动生成文件夹,必填"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        //function的包名
        val functionPackageName = stringParameter {
            name = "function Package Name"
            default = "function Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.UNIQUE)
            help = "Activity Fragment ViewModel 将被输出到此文件夹下,请认真核实此包名是否是你需要输出的目标文件夹名"
            suggest = { "${packageName.value}.ui.${functionDir.value.toLowerCase()}" }
        }

        //页面名
        val pageName = stringParameter {
            name = "Page Name"
            default = "Main"
            help = "请填写页面名,如填写 Main,会自动生成 MainActivity、MainViewModel等文件,不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        //activity
        //是否需要生成Activity
        val needActivity = booleanParameter {
            name = "need Activity"
            default = false
            help = "是否需要生成 Activity ? 不勾选则不生成"
        }

        //布局名
        val activityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            visible = { needActivity.value }
            help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(pageName.value.toLowerCase())}" }
        }

        //是否需要Activity的布局
        val needActivityLayout = booleanParameter {
            name = "Need Activity Layout"
            default = true
            visible = { needActivity.value }
            help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
        }


        //Fragment
        //是否需要生成Fragment
        val needFragment = booleanParameter {
            name = "Need Fragment"
            default = true
            help = "是否需要生成 Fragment ? 不勾选则不生成"
        }

        //是否需要Fragment的布局
        val needFragmentLayout = booleanParameter {
            name = "Need Fragment Layout"
            default = true
            visible = { needFragment.value }
            help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
        }


        //是否需要加载到Navigation中
        val needToNavigation = booleanParameter {
            name = "Need To Navigation"
            default = true
            visible = { needFragment.value }
            help = "是否需要加载到navigation中? 若勾选,Fragment会自动加载到navigation文件中"
        }

        //navigation name
        val navigationNameId = stringParameter {
            name = "Navigation Name"
            default = "navigation"
            visible = { needToNavigation.value && needFragment.value }
            constraints = listOf(Constraint.NONEMPTY)
            help = "这是fragment创建完成后自动配置到navigation文件的Id，默认navigation"
            suggest = { "${pageName.value.toLowerCase()}_fragment" }
        }

        //布局名
        val fragmentLayoutName = stringParameter {
            name = "Fragment Layout Name"
            default = "fragment_main"
            visible = { needFragment.value }
            help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(pageName.value.toLowerCase())}" }
        }

        widgets(
                PackageNameWidget(packageName),
                TextFieldWidget(functionDir),
                TextFieldWidget(functionPackageName),
                TextFieldWidget(pageName),
                CheckBoxWidget(needActivity),
                CheckBoxWidget(needActivityLayout),
                TextFieldWidget(activityLayoutName),
                CheckBoxWidget(needFragment),
                CheckBoxWidget(needFragmentLayout),
                TextFieldWidget(fragmentLayoutName),
                CheckBoxWidget(needToNavigation),
                TextFieldWidget(navigationNameId)
        )

        thumb { File("template_blank_activity.png") }


        recipe = { data: TemplateData ->
            concisionRecipe(
                    data as ModuleTemplateData,
                    packageName.value,
                    functionDir.value.toLowerCase(),
                    pageName.value,
                    needActivity.value,
                    activityLayoutName.value,
                    needActivityLayout.value,
                    needFragment.value,
                    fragmentLayoutName.value,
                    needFragmentLayout.value,
                    functionPackageName.value,
                    needToNavigation.value,
                    navigationNameId.value
            )
        }
    }


