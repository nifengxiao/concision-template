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
        name = "concision"
        description = "创建concision相关的mvvm文件"
        minApi = 9
        minBuildApi = 15

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.ActivityGallery, WizardUiContext.MenuEntry, WizardUiContext.NewProject, WizardUiContext.NewModule)


        //包名
        val packageName = stringParameter {
            name = "Package name"
            visible = { !isNewModule }
            default = "com.mycompany.myapp"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        //功能文件名
        val functionDir = stringParameter {
            name = "function Name"
            default = "functionName"
            help = "请填写你的功能名"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
        }

        //页面名
        val pageName = stringParameter {
            name = "Page Name"
            default = "Main"
            help = "请填写页面名,如填写 Main,会自动生成 MainActivity、MainViewModel等文件,不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
        }

//
//        //activity
//        //是否需要生成Activity
//        val needActivity = booleanParameter {
//            name = "Generate Activity"
//            default = false
//            help = "是否需要生成 Activity ? 不勾选则不生成"
//        }
//
//        //布局名
//        val activityLayoutName = stringParameter {
//            name = "Activity Layout Name"
//            default = "activity_main"
//            visible = { needActivity.value }
//            help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
//            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
//            suggest = { "${activityToLayout(pageName.value.toLowerCase())}" }
//        }
//
//        //是否需要Activity的布局
//        val needActivityLayout = booleanParameter {
//            name = "Generate Activity Layout"
//            default = false
//            visible = { needActivity.value }
//            help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
//        }


        //Fragment
        //是否需要生成Fragment
        val needFragment = booleanParameter {
            name = "Generate Fragment"
            default = true
            help = "是否需要生成 Fragment ? 不勾选则不生成"
        }

        //是否需要Fragment的布局
        val needFragmentLayout = booleanParameter {
            name = "Generate Fragment Layout"
            default = true
            visible = { needFragment.value }
            help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
        }

        //布局名
        val fragmentLayoutName = stringParameter {
            name = "Fragment Layout Name"
            default = "fragment_main"
            visible = { needFragmentLayout.value }
            help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${fragmentToLayout(pageName.value.toLowerCase())}" }
        }


        //function的包名
        val functionPackageName = stringParameter {
            name = "function Package Name"
            default = "function Package Name"
            constraints = listOf(Constraint.PACKAGE)
            help = "Activity Fragment ViewModel 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
            suggest = { "${packageName.value}.ui.${functionDir.value}" }
        }

        widgets(
                PackageNameWidget(packageName),
                TextFieldWidget(functionDir),
                TextFieldWidget(pageName),
                TextFieldWidget(functionPackageName),
//                CheckBoxWidget(needActivity),
//                TextFieldWidget(activityLayoutName),
//                CheckBoxWidget(needActivityLayout),
                CheckBoxWidget(needFragment),
                CheckBoxWidget(needFragmentLayout),
                TextFieldWidget(fragmentLayoutName)
        )

        thumb { File("template_blank_activity.png") }


        recipe = { data: TemplateData ->
            concisionRecipe(
                    data as ModuleTemplateData,
                    packageName.value,
                    functionDir.value,
                    pageName.value,
//                    needActivity.value,
//                    activityLayoutName.value,
//                    needActivityLayout.value,
                    needFragment.value,
                    fragmentLayoutName.value,
                    needFragmentLayout.value,
                    functionPackageName.value
            )
        }
    }


